package com.ict.finalspringboot.domain.ban.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.finalspringboot.domain.ban.service.BanService;
import com.ict.finalspringboot.domain.ban.vo.BanVO;

@RestController
@RequestMapping("/api/ban")
public class BanController {

    @Autowired
    private BanService banService;

    // 모든 차단된 사용자 목록 조회
    @GetMapping("/list")
    public ResponseEntity<List<BanVO>> getAllBannedUsers() {
        try {
            List<BanVO> bannedUsers = banService.getAllBannedUsers();
            return ResponseEntity.ok(bannedUsers);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // 특정 차단된 사용자 조회
    @GetMapping("/{user_stop_idx}")
    public ResponseEntity<BanVO> getBanById(@PathVariable int user_stop_idx) {
        try {
            BanVO banVO = banService.getBanById(user_stop_idx);
            return ResponseEntity.ok(banVO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // 사용자 차단
    @PostMapping("/add")
    public ResponseEntity<String> addBan(@RequestBody BanVO banVO) {
        try {
            banService.addBan(banVO);
            return ResponseEntity.ok("사용자 차단이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("차단 추가 실패");
        }
    }

    // 차단 정보 수정
    @PutMapping("/update")
    public ResponseEntity<String> updateBan(@RequestBody BanVO banVO) {
        try {
            banService.updateBan(banVO);
            return ResponseEntity.ok("차단 정보가 수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("차단 정보 수정 실패");
        }
    }

    // 차단 해제
    @DeleteMapping("/delete/{user_stop_idx}")
    public ResponseEntity<String> deleteBan(@PathVariable int user_stop_idx) {
        try {
            banService.deleteBan(user_stop_idx);
            return ResponseEntity.ok("차단이 해제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("차단 해제 실패");
        }
    }
}
