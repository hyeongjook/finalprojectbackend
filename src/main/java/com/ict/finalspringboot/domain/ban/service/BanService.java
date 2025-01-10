package com.ict.finalspringboot.domain.ban.service;

import com.ict.finalspringboot.domain.ban.vo.BanVO;

import java.util.List;

public interface BanService {

    // 모든 차단된 사용자 목록 조회
    List<BanVO> getAllBannedUsers() throws Exception;

    // 특정 차단된 사용자 조회
    BanVO getBanById(int user_stop_idx) throws Exception;

    // 사용자 차단
    void addBan(BanVO banVO) throws Exception;

    // 차단 정보 수정
    void updateBan(BanVO banVO) throws Exception;

    // 차단 해제
    void deleteBan(int user_stop_idx) throws Exception;
}
