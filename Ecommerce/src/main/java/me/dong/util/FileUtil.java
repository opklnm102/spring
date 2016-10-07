package me.dong.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static File upload(MultipartFile multipartFile, String uploadDir){
        File dirPath = new File(uploadDir);
        if(!dirPath.exists()){
            boolean made = dirPath.mkdirs();  //해당 경로에 없는 dir 모두 생성
            if(!made){
                throw new RuntimeException(String.format("make directory(%s) fail", uploadDir));
            }
        }

        String targetFilePath = uploadDir + "/" + multipartFile.getOriginalFilename();
        File targetFile = new File(targetFilePath);

        try {
            multipartFile.transferTo(targetFile);
        } catch (IOException e) {
            throw new RuntimeException(String.format("file upload error:%s", targetFilePath), e);
        }
        return targetFile;
    }
}
