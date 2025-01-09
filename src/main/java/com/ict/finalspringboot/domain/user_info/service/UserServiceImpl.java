package com.ict.finalspringboot.domain.user_info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.finalspringboot.domain.user_info.mapper.UserMapper;
import com.ict.finalspringboot.domain.user_info.vo.userVO;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 일반회원 목록 조회
    @Override
    public List<userVO> userinfoListByLevel1() {
        return userMapper.getUserListByLevel1();
    }

    // 일반회원이 아닌 목록 조회
    @Override
    public List<userVO> userinfoListByLevelNot1() {
        return userMapper.getUserListByLevelNot1();
    }

    // 회원 상세 조회
    @Override
    public userVO getUsersDetails(int user_idx) {
        return userMapper.getUsersDetails(user_idx);
    }

    // 회원 수정
    @Override
    public int userinfoUpdate(userVO uvo) {
        return userMapper.updateUser(uvo);
    }

    // 회원 삭제
    @Override
    @Transactional
    public int userDelete(int user_idx) {
        return userMapper.deleteUser(user_idx);
    }

    // 프로필 이미지 URL 업데이트
    @Override
    public int updateProfileImage(int user_idx, String imageUrl) {
        return userMapper.updateProfileImage(user_idx, imageUrl);
    }
}
