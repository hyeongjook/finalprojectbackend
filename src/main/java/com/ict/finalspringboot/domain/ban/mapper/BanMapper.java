package com.ict.finalspringboot.domain.ban.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.finalspringboot.domain.ban.vo.BanVO;

@Mapper
public interface BanMapper {

    List<BanVO> getAllBannedUsers();  // 차단된 모든 사용자 목록 조회

    BanVO getBanById(int userStop_idx); // 차단 정보 조회

    int addBan(BanVO banVO);           // 사용자 차단

    int updateBan(BanVO banVO);        // 차단 상태 업데이트

    int deleteBan(int userStop_idx);    // 차단 해제
    
}
