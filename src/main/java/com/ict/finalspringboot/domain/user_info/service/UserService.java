package com.ict.finalspringboot.domain.user_info.service;

import java.util.List;

import com.ict.finalspringboot.domain.user_info.vo.userVO;

public interface UserService {

    // 조회 불러오기

    List<userVO> userinfoList();

    // 수정하기
    int userinfoUpdate(userVO uvo);

    // 삭제하기
    int userinfoDelete(int user_idx);

    // 생성하기
    int userinfoWrite(userVO uvo);

    // 상세보기
    userVO getusersById(int user_idx);

}