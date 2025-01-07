package com.ict.finalspringboot.domain.user_info.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.finalspringboot.domain.user_info.mapper.UserMapper;
import com.ict.finalspringboot.domain.user_info.vo.userVO;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public userVO getusersById(int user_idx) {

        return userMapper.getusersById(user_idx);
    }

    @Override
    public int userinfoDelete(int user_idx) {

        return userMapper.userinfoDelete(user_idx);
    }


    @Override
    public List<userVO> userinfoList() {
        List<userVO> list = userMapper.userinfoList();
        if (list == null) {
            list = new ArrayList<>(); // null일 경우 빈 리스트로 초기화
            System.out.println("데이터가 없습니다.");
        }
        return list;
    }

    @Override
    public int userinfoUpdate(userVO uvo) {

        return userMapper.userinfoUpdate(uvo);
    }

    @Override
    public int userinfoWrite(userVO uvo) {

        return userMapper.userinfoWrite(uvo); // Mapper 호출
    }

}
