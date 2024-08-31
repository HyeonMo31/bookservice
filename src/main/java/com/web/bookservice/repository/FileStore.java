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

    @Value("${file.default-dir}")
    private String fileDefaultDir;

    public String getFullDefaultPath(String fileName) {

        return fileDefaultDir + fileName;
    }

    public String getMemberDir(String loginId){
        return fileDir + loginId + File.separator;
    }

    //멀티파트 파일을 받아서 업로드 파일로 바꾼다라는 것이다.
    public UploadFile storeMemberFile(MultipartFile multipartFile, String loginId) throws IOException
    {

        if (multipartFile.isEmpty()) {
            return null;
        }

        String memberDir = getMemberDir(loginId);
        Path memberDirPath = Paths.get(memberDir);

        //Member 파일이 존재하지 않다면 생성한다.
        if (!Files.exists(memberDirPath)) {
            try {
                Files.createDirectories(memberDirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String originalFilename = multipartFile.getOriginalFilename();

        //서버에 저장하는 파일명을 createStroeFileName 함수를 통해 만들어낸다.
        String storeFileName = createStoreFileName(originalFilename);

        //실제 저장소 경로에 파일을 저장하기.
        multipartFile.transferTo(new File(memberDir + storeFileName));

        return new UploadFile(originalFilename, storeFileName);
    }

    public UploadFile storeDefaultFile(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return new UploadFile("tuna.jpg", "tuna.jpg");
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullDefaultPath(storeFileName)));
        return new UploadFile(originalFilename, storeFileName);

    }

    public void deleteFile(String filename) {
        File file = new File(getFullDefaultPath(filename));
        if(file.exists()) {
            file.delete();
        }
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
        return originalFilename.substring(pos + 1).toLowerCase();
    }

    public String getExtract(String originalFilename) {
        return extractExt(originalFilename);
    }
}
