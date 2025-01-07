package com.ict.finalspringboot.domain.admin_info.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.finalspringboot.domain.admin_info.service.AdminService;
import com.ict.finalspringboot.domain.admin_info.vo.adminVO;
import com.ict.finalspringboot.domain.auth.vo.DataVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin_info")
@CrossOrigin(origins = "http://localhost:3000") // CORS 설정 추가 (Next.js에서 API를 호출하려면 이 설정이 필요)
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 관리자 추가
    @PostMapping("/write")
    public DataVO getadminWrite(@RequestBody adminVO auvo) {
        DataVO dataVO = new DataVO();
        try {
            log.info("Received data: " + auvo.toString());
            int result = adminService.admininfoWrite(auvo);

            if (result == 0) {
                dataVO.setSuccess(false);
                dataVO.setMessage("관리자 추가 실패");
                return dataVO;
            }
            dataVO.setSuccess(true);
            dataVO.setMessage("관리자 추가 성공");

        } catch (Exception e) {
            log.error("Exception occurred while writing admin", e); // 스택 트레이스 출력
            dataVO.setSuccess(false);
            dataVO.setMessage("관리자 추가 오류 발생");
        }
        return dataVO;
    }

    // 관리자 목록 조회
    @GetMapping("/list")
    public DataVO getAdminList() {
        DataVO dataVO = new DataVO();
        try {
            List<adminVO> list = adminService.admininfoList();
            // 리스트가 null일 경우 빈 리스트로 초기화
            if (list == null) {
                list = new ArrayList<>(); // 빈 리스트로 초기화
            }
            dataVO.setSuccess(true);
            dataVO.setMessage("관리자 조회 성공");
            dataVO.setData(list); // 데이터를 JSON 형식으로 반환

        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("관리자 조회 실패");
            log.error("Exception occurred while fetching admin list", e); // 로그에 에러 내용 출력
        }
        return dataVO;
    }

    // 관리자 상세보기
    @GetMapping("/detail/{admin_idx}")
    public DataVO getadminsById(@PathVariable int admin_idx) { // admin_idx를 int로 변경
        DataVO dataVO = new DataVO();

        try {
            log.info("Received request for admin details, admin_idx: {}", admin_idx);
            
            // 사용자 정보 조회
            adminVO auvo = adminService.getadminsById(admin_idx); 
            if (auvo == null) {
                dataVO.setSuccess(false);
                dataVO.setMessage("관리자 상세보기 실패: 해당 사용자가 존재하지 않습니다.");
                return dataVO;
            }

            dataVO.setSuccess(true);
            dataVO.setMessage("관리자 상세보기 성공");
            dataVO.setData(auvo);  // 사용자 정보 포함
        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("관리자 상세보기 실패");
            log.error("Exception occurred while fetching admin details", e); // 로그에 에러 내용 출력
        }
        return dataVO;
    }

    // 관리자 삭제
    @GetMapping("/delete/{admin_idx}")
    public DataVO getadminDelete(@PathVariable int admin_idx) {  // admin_idx를 int로 변경
        DataVO dataVO = new DataVO();
        try {
            log.info("Deleting admin with ID: " + admin_idx);

            int result = adminService.admininfoDelete(admin_idx);
            if (result == 0) {
                dataVO.setSuccess(false);
                dataVO.setMessage("관리자 삭제 실패");
                return dataVO;
            }
            dataVO.setSuccess(true);
            dataVO.setMessage("관리자 삭제 성공");

        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("관리자 삭제 오류 발생");
            log.error("Exception occurred while deleting admin", e); // 로그에 에러 내용 출력
        }
        return dataVO;
    }

    // 관리자 수정
    @PutMapping("/update/{admin_idx}")
    public DataVO getadminUpdate(@PathVariable int admin_idx, @RequestBody adminVO auvo) {  // admin_idx를 int로 변경
        DataVO dataVO = new DataVO();
        try {
            int result = adminService.admininfoUpdate(auvo);

            if (result == 0) {
                log.info("Update failed for admin with ID: " + admin_idx);
                dataVO.setSuccess(false);
                dataVO.setMessage("관리자 수정 실패");
                return dataVO;
            }
            dataVO.setSuccess(true);
            dataVO.setMessage("관리자 수정 성공");

        } catch (Exception e) {
            log.error("Exception occurred while updating admin", e); // 로그에 에러 내용 출력
            dataVO.setSuccess(false);
            dataVO.setMessage("관리자 수정 오류 발생");
        }
        return dataVO;
    }
}
