package com.ict.finalspringboot.domain.user_info.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin; // CORS 관련 추가

import com.ict.finalspringboot.domain.auth.vo.DataVO;
import com.ict.finalspringboot.domain.user_info.service.UserService;
import com.ict.finalspringboot.domain.user_info.vo.userVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user_info")
@CrossOrigin(origins = "http://localhost:3000") // CORS 설정 추가 (Next.js에서 API를 호출하려면 이 설정이 필요)
public class UserController {

    @Autowired
    private UserService userService;

    // 회원 추가
    @PostMapping("/write")
    public DataVO getuserWrite(@RequestBody userVO uvo) {
        DataVO dataVO = new DataVO();
        try {
            log.info("Received data: " + uvo.toString());
            int result = userService.userinfoWrite(uvo);

            if (result == 0) {
                dataVO.setSuccess(false);
                dataVO.setMessage("회원 추가 실패");
                return dataVO;
            }
            dataVO.setSuccess(true);
            dataVO.setMessage("회원 추가 성공");

        } catch (Exception e) {
            log.error("Exception occurred while writing user", e); // 스택 트레이스 출력
            dataVO.setSuccess(false);
            dataVO.setMessage("회원 추가 오류 발생");
        }
        return dataVO;
    }

    // 회원 목록 조회
    @GetMapping("/list")
    public DataVO getuserList() {
        DataVO dataVO = new DataVO();
        try {
            List<userVO> list = userService.userinfoList();
            // 리스트가 null일 경우 빈 리스트로 초기화
            if (list == null) {
                list = new ArrayList<>(); // 빈 리스트로 초기화
            }
            dataVO.setSuccess(true);
            dataVO.setMessage("회원 조회 성공");
            dataVO.setData(list); // 데이터를 JSON 형식으로 반환

        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("회원 조회 실패");
            log.error("Exception occurred while fetching user list", e); // 로그에 에러 내용 출력
        }
        return dataVO;
    }

    // 회원 상세보기
    @GetMapping("/detail/{user_idx}")
    public DataVO getusersById(@PathVariable int user_idx) { // user_idx를 int로 변경
        DataVO dataVO = new DataVO();

        try {
            log.info("Received request for user details, user_idx: {}", user_idx);
            
            // 사용자 정보 조회
            userVO uvo = userService.getusersById(user_idx); 
            if (uvo == null) {
                dataVO.setSuccess(false);
                dataVO.setMessage("회원 상세보기 실패: 해당 사용자가 존재하지 않습니다.");
                return dataVO;
            }

            dataVO.setSuccess(true);
            dataVO.setMessage("회원 상세보기 성공");
            dataVO.setData(uvo);  // 사용자 정보 포함
        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("회원 상세보기 실패");
            log.error("Exception occurred while fetching user details", e); // 로그에 에러 내용 출력
        }
        return dataVO;
    }

    // 회원 삭제
    @GetMapping("/delete/{user_idx}")
    public DataVO getuserDelete(@PathVariable int user_idx) {  // user_idx를 int로 변경
        DataVO dataVO = new DataVO();
        try {
            log.info("Deleting user with ID: " + user_idx);

            int result = userService.userinfoDelete(user_idx);
            if (result == 0) {
                dataVO.setSuccess(false);
                dataVO.setMessage("회원 삭제 실패");
                return dataVO;
            }
            dataVO.setSuccess(true);
            dataVO.setMessage("회원 삭제 성공");

        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("회원 삭제 오류 발생");
            log.error("Exception occurred while deleting user", e); // 로그에 에러 내용 출력
        }
        return dataVO;
    }

    // 회원 수정
    @PutMapping("/update/{user_idx}")
    public DataVO getuserUpdate(@PathVariable int user_idx, @RequestBody userVO uvo) {  // user_idx를 int로 변경
        DataVO dataVO = new DataVO();
        try {
            int result = userService.userinfoUpdate(uvo);

            if (result == 0) {
                log.info("Update failed for user with ID: " + user_idx);
                dataVO.setSuccess(false);
                dataVO.setMessage("회원 수정 실패");
                return dataVO;
            }
            dataVO.setSuccess(true);
            dataVO.setMessage("회원 수정 성공");

        } catch (Exception e) {
            log.error("Exception occurred while updating user", e); // 로그에 에러 내용 출력
            dataVO.setSuccess(false);
            dataVO.setMessage("회원 수정 오류 발생");
        }
        return dataVO;
    }
}
