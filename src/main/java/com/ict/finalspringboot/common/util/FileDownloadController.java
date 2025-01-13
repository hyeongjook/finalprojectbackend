package com.ict.finalspringboot.common.util;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download/api")
public class FileDownloadController {
    
    // upload 기본 경로
    @Value("${file.upload-dir}")
    private String uploadDir;
    
    // 이미지 다운로드 시 MIME 타입을 설정
    private static final String IMAGE_TYPE = "image/";

    @GetMapping("/{uploadpath}/{filename}")
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable String uploadpath, @PathVariable String filename) {
        // 경로 설정
        File file = new File(uploadDir + "/" + uploadpath, filename);
        
        // 파일이 존재하는지 확인
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        
        FileSystemResource resource = new FileSystemResource(file);
        
        // HTTP 헤더에 들어가기 때문에 utf-8 인코딩이 필요
        String encodedFilename;
        try {
            encodedFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            encodedFilename = filename; // 인코딩 실패 시 기본 파일 이름 사용
        }

        // 파일 확장자에 따라 MIME 타입을 설정
        String contentType = getContentType(filename);

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"")
            .body(resource);
    }

    // 파일 확장자에 맞는 MIME 타입을 반환
    private String getContentType(String filename) {
        if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
            return IMAGE_TYPE + "jpeg";
        } else if (filename.endsWith(".png")) {
            return IMAGE_TYPE + "png";
        } else if (filename.endsWith(".gif")) {
            return IMAGE_TYPE + "gif";
        }
        return "application/octet-stream"; // 기본 MIME 타입
    }
}