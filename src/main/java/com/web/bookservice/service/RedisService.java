package com.web.bookservice.service;

import com.web.bookservice.dto.ResponseCodeDto;
import com.web.bookservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;
    private final PostRepository postRepository;

    // 특정 id가 특정 cookieValue에서 조회되었는지 확인(존재하면 true, 존재하지 않으면 false)
    public boolean isViewed(Long id, String cookieValue) {
        String key = "view:" + id;
        return redisTemplate.opsForSet().isMember(key, cookieValue);
    }

    // 특정 id가 특정 cookie에서 조회되었는지 확인
    public void addView(Long id, String cookieValue) {
        String key = "view:" + id;
        redisTemplate.opsForSet().add(key, cookieValue);
        redisTemplate.expire(key, 1, TimeUnit.MINUTES);
    }

    // isViewed를 호출하여 이미 해당 페이지를 조회한 cookie인지 확인하고 조회하지 않은
    // ip이면 score(조회수)를 1증가 시키고 set에 id(key)와 ip주소(value)를 추가한다
    public void increaseViewCount(Long id, String cookieValue) {
        if (!isViewed(id, cookieValue)) {
            String key = "viewCount";
            redisTemplate.opsForZSet().incrementScore(key, String.valueOf(id), 1);
            addView(id, cookieValue);
        }
    }

    // 해당 id의 조회수(score)를 조회
    public Double getViewCount(String key, Long id) {

        return redisTemplate.opsForZSet().score(key, String.valueOf(id));
    }

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    @Scheduled(fixedRate = 60000)

    public void updateView() {
        Set<ZSetOperations.TypedTuple<String>> tuples =
                redisTemplate.opsForZSet().rangeWithScores("viewCount", 0, -1);

        //조회가 아예 없다면
        if(tuples.isEmpty())
            return;

        tuples.forEach(tuple -> {
            Long id = Long.valueOf(tuple.getValue());
            Long viewCount = tuple.getScore().longValue();

            if(viewCount > 0) {
                //업데이트 할 데이터가 있다면
                syncWithView(id, viewCount);
            }
        });
    }


    public void syncWithView(Long id, Long viewCount) {
        //DB에 반영.
        postRepository.incrementViewCount(id, viewCount);
        //데이터 정확성을 위해 키를 삭제하지 않는다.
        //수정하는 도중에 value가 증가할 수 있다. 예를 들면
        //10값을 가지고 업데이트하려는 와중에 1조회수가 증가하여 11이 되었을 수 있다.
        //이때 10값을 가지고 업데이트하고 삭제되면 1의 조회수가 무시된다. 따라서 키를 삭제하지 않고
        //조회수를 redis에서 차감하는 방식으로 사용하겠다.
        redisTemplate.opsForZSet().incrementScore("viewCount", String.valueOf(id), -viewCount);
    }

}
