package com.ict.finalspringboot.domain.ban.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.finalspringboot.domain.ban.mapper.BanMapper;
import com.ict.finalspringboot.domain.ban.vo.BanVO;

@Service
public class BanServiceImpl implements BanService {

    @Autowired
    private BanMapper banMapper;

    // 모든 차단된 사용자 목록 조회
    @Override
    public List<BanVO> getAllBannedUsers() throws Exception {
        return banMapper.getAllBannedUsers();  
    }

    // 특정 차단된 사용자 조회
    @Override
    public BanVO getBanById(int user_stop_idx) throws Exception {
        return banMapper.getBanById(user_stop_idx);  // 특정 차단된 사용자 정보 조회
    }

    // 사용자 차단
    @Override
    public void addBan(BanVO banVO) throws Exception {
        banMapper.addBan(banVO);  // 사용자 차단을 위한 쿼리 실행
    }

    // 차단 정보 수정
    @Override
    public void updateBan(BanVO banVO) throws Exception {
        banMapper.updateBan(banVO);  // 차단 정보 수정 쿼리 실행
    }

    // 차단 해제
    @Override
    public void deleteBan(int user_stop_idx) throws Exception {
        banMapper.deleteBan(user_stop_idx);  // 차단 해제 쿼리 실행
    }
}
