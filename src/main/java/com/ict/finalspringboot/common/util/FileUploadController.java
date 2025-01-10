package com.ict.finalspringboot.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class FileUploadController {

    @Value("${file.upload-dir}")  // 설정 파일에서 업로드 디렉토리 경로를 가져옴
    private String uploadDir;

    // 프로필 이미지 업로드 처리
    public String uploadProfileImage(MultipartFile file) {
        try {
            // 파일이 비어 있지 않으면
            if (file.isEmpty()) {
                return null;
            }

            // 파일의 원본 이름
            String fileName = file.getOriginalFilename();

            // 저장할 파일 경로 설정 (프로필 이미지를 저장할 디렉토리 지정)
            File uploadDirPath = new File(uploadDir);
            if (!uploadDirPath.exists()) {
                uploadDirPath.mkdirs();  // 디렉토리가 없으면 생성
            }

            // 파일을 저장할 실제 경로
            File serverFile = new File(uploadDirPath, fileName);

            // 파일 저장
            file.transferTo(serverFile);

            // 업로드된 파일의 URL 반환 (웹에서 접근할 수 있는 URL)
            return "/upload/" + fileName;

        } catch (IOException e) {
            e.printStackTrace();
            return null;  // 파일 업로드 실패 시 null 반환
        }
    }
}
