package com.ict.finalspringboot.domain.user_info.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ict.finalspringboot.common.util.FileUploadController;
import com.ict.finalspringboot.domain.auth.vo.DataVO;
import com.ict.finalspringboot.domain.user_info.service.UserService;
import com.ict.finalspringboot.domain.user_info.vo.userVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user_info")
@CrossOrigin(origins = "http://localhost:3000")  // CORS 설정 (Next.js에서 API를 호출하려면 이 설정이 필요)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileUploadController fileUploadController;  // 파일 업로드 기능을 추가

    // 일반회원 목록 조회
    @GetMapping("/list/level/1")
    public DataVO getuserListByLevel1() {
        DataVO dataVO = new DataVO();
        try {
            List<userVO> list = userService.userinfoListByLevel1();
            if (list == null || list.isEmpty()) {
                list = new ArrayList<>(); // 빈 리스트로 초기화
            }
            dataVO.setSuccess(true);
            dataVO.setMessage("일반회원 조회 성공");
            dataVO.setData(list); // 데이터를 JSON 형식으로 반환
        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("일반회원 조회 실패");
            log.error("Exception occurred while fetching level 1 user list", e); // 에러 로그
        }
        return dataVO;
    }

    // 전문회원 목록 조회 (user_level_idx가 1이 아닌 값들)
    @GetMapping("/list/level/not1")
    public DataVO getuserListByLevelNot1() {
        DataVO dataVO = new DataVO();
        try {
            List<userVO> list = userService.userinfoListByLevelNot1();
            if (list == null || list.isEmpty()) {
                list = new ArrayList<>(); // 빈 리스트로 초기화
            }
            dataVO.setSuccess(true);
            dataVO.setMessage("user_level_idx가 1이 아닌 회원 조회 성공");
            dataVO.setData(list); // 데이터를 JSON 형식으로 반환
        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("user_level_idx가 1이 아닌 회원 조회 실패");
            log.error("Exception occurred while fetching level not 1 user list", e); // 에러 로그
        }
        return dataVO;
    }

    // 회원 상세보기 (ResponseEntity를 사용하여 상태 코드와 응답을 제어)
    @GetMapping("/detail/{user_idx}")
    public ResponseEntity<DataVO> getUsersDetails(@PathVariable("user_idx") int user_idx) {
        DataVO dataVO = new DataVO();
        if (user_idx <= 0) { // user_idx가 유효한지 체크
            dataVO.setSuccess(false);
            dataVO.setMessage("유효하지 않은 user_idx");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dataVO); // 400 Bad Request 반환
        }

        try {
            log.info("Received request for user details, user_idx: {}", user_idx);

            // 사용자 정보 조회
            userVO userDetails = userService.getUsersDetails(user_idx);

            // 사용자가 존재하면
            if (userDetails != null) {
                dataVO.setSuccess(true);
                dataVO.setMessage("회원 정보 조회 성공");
                dataVO.setData(userDetails);
                return ResponseEntity.ok(dataVO); 
            } else {
                dataVO.setSuccess(false);
                dataVO.setMessage("회원 정보가 존재하지 않습니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataVO); // 404 Not Found 반환
            }
        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("회원 정보 조회 실패");
            log.error("Exception occurred while fetching user details", e); // 로그에 에러 내용 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dataVO); // 500 Internal Server Error 반환
        }
    }

    // 회원 삭제
    @DeleteMapping("/delete/{user_idx}")  
    public ResponseEntity<DataVO> getuserDelete(@PathVariable("user_idx") int user_idx) {
        DataVO dataVO = new DataVO();
        try {
            log.info("Attempting to delete user with ID: {}", user_idx);

            int result = userService.userDelete(user_idx);
            if (result == 0) {
                dataVO.setSuccess(false);
                dataVO.setMessage("회원 삭제 실패");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataVO); 
            }
            dataVO.setSuccess(true);
            dataVO.setMessage("회원 삭제 성공");
            return ResponseEntity.ok(dataVO);  // 성공적인 삭제

        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("회원 삭제 오류 발생");
            log.error("Exception occurred while deleting user", e); // 로그에 에러 내용 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dataVO); // 500 Internal Server Error 반환
        }
    }

    // 회원 수정
    @PutMapping("/update/{user_idx}")
    public DataVO getuserUpdate(@PathVariable int user_idx, @RequestBody userVO uvo) {
        DataVO dataVO = new DataVO();
        try {
            int result = userService.userinfoUpdate(uvo);

            if (result == 0) {
                log.info("Update failed for user with ID: {}", user_idx);
                dataVO.setSuccess(false);
                dataVO.setMessage("회원 수정 실패");
                return dataVO;
            }
            dataVO.setSuccess(true);
            dataVO.setMessage("회원 수정 성공");

        } catch (Exception e) {
            log.error("Exception occurred while updating user", e);
            dataVO.setSuccess(false);
            dataVO.setMessage("회원 수정 오류 발생");
        }
        return dataVO;
    }

    // 프로필 이미지 업로드 및 수정
    @PostMapping("/uploadProfileImage/{user_idx}")
    public ResponseEntity<DataVO> uploadProfileImage(@PathVariable int user_idx, @RequestParam("file") MultipartFile file) {
        DataVO dataVO = new DataVO();
        try {
            // 파일 업로드
            String imageUrl = fileUploadController.FileUpload(file);

            if (imageUrl == null || imageUrl.isEmpty()) {
                dataVO.setSuccess(false);
                dataVO.setMessage("파일 업로드 실패");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dataVO);
            }

            // 사용자 프로필 이미지 URL 업데이트
            userVO updatedUser = userService.getUsersDetails(user_idx);
            updatedUser.setUser_profile(imageUrl); // 프로필 이미지 URL 설정

            // 사용자 정보 업데이트
            int result = userService.userinfoUpdate(updatedUser);

            if (result > 0) {
                dataVO.setSuccess(true);
                dataVO.setMessage("프로필 이미지 업데이트 성공");
                dataVO.setData(updatedUser);  // 업데이트된 사용자 정보 반환
                return ResponseEntity.ok(dataVO);
            } else {
                dataVO.setSuccess(false);
                dataVO.setMessage("프로필 이미지 업데이트 실패");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dataVO);
            }
        } catch (Exception e) {
            log.error("Exception occurred while uploading profile image", e);
            dataVO.setSuccess(false);
            dataVO.setMessage("프로필 이미지 업로드 중 오류 발생");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dataVO);
        }
    }
}
