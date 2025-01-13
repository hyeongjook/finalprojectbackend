package com.ict.finalspringboot.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadController {

    // 파일을 업로드할 경로
    private static final String UPLOAD_DIR = "D:/upload";

    // 프로필 이미지 업로드 메서드
    public String uploadProfileImage(MultipartFile file) {
        // 업로드할 파일 이름
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.isEmpty()) {
            return null;  // 파일이 비어 있으면 null 반환
        }

        // 파일 저장 경로
        Path targetLocation = Paths.get(UPLOAD_DIR).resolve(fileName);
        
        try {
            // 경로가 존재하지 않으면 생성
            Files.createDirectories(targetLocation.getParent());

            // 파일을 해당 경로에 저장
            file.transferTo(targetLocation);

            // 업로드 후 경로 반환 (웹에서 접근할 수 있는 경로)
            return "/upload/" + fileName;

        } catch (IOException ex) {
            ex.printStackTrace();  // 에러 로그 출력
            return null;
        }
    }
}