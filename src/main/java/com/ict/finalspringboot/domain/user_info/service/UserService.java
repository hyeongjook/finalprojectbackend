package com.ict.finalspringboot.domain.user_info.service;

import java.util.List;

import com.ict.finalspringboot.domain.user_info.vo.userVO;

public interface UserService {

    // 일반회원 목록 조회
    List<userVO> userinfoListByLevel1();

    // 일반회원이 아닌 목록 조회
    List<userVO> userinfoListByLevelNot1();

    // 회원 상세 조회
    userVO getUsersDetails(int user_idx);

    // 회원 수정
    int userinfoUpdate(userVO uvo);

    // 회원 삭제
    int userDelete(int user_idx);

    // 프로필 이미지 URL 업데이트
    int updateProfileImage(int user_idx, String imageUrl);
}
