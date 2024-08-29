package com.web.bookservice.repository;

import com.web.bookservice.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
@Component
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;
    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public String getFileDir(){
        return fileDir;
    }

    //멀티파트 파일을 받아서 업로드 파일로 바꾼다라는 것이다.
    public UploadFile storeFile(MultipartFile multipartFile, String loginId) throws IOException
    {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String userDir = getFileDir() + File.separator + loginId;
        Path userDirPath = Paths.get(userDir);

        //Member 파일이 존재하지 않다면 생성한다.
        if (!Files.exists(userDirPath)) {
            try {
                Files.createDirectories(userDirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String originalFilename = multipartFile.getOriginalFilename();

        //서버에 저장하는 파일명을 createStroeFileName 함수를 통해 만들어낸다.
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(userDirPath.toString()+File.separator +storeFileName));
        System.out.println("userDirPath.toString()+storeFileName = " + userDirPath.toString()+File.separator +storeFileName);
        return new UploadFile(originalFilename, storeFileName);
    }

    //UUID를 통해서 파일명을 구분하기 위함이다.
    //UUID.확장자가 완성이 된다.
    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    //확장자를 뽑기 위한 함수이다. ex) png와 같은.
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
