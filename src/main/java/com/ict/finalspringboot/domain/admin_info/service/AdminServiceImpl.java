package com.ict.finalspringboot.domain.admin_info.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.finalspringboot.domain.admin_info.mapper.AdminMapper;
import com.ict.finalspringboot.domain.admin_info.vo.adminVO;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public adminVO getadminsById(int admin_idx) {

        return adminMapper.getadminsById(admin_idx);
    }

    @Override
    public int admininfoDelete(int admin_idx) {

        return adminMapper.admininfoDelete(admin_idx);
    }


    @Override
    public List<adminVO> admininfoList() {
        List<adminVO> list = adminMapper.admininfoList();
        if (list == null) {
            list = new ArrayList<>(); // null일 경우 빈 리스트로 초기화
            System.out.println("데이터가 없습니다.");
        }
        return list;
    }

    @Override
    public int admininfoUpdate(adminVO auvo) {

        return adminMapper.admininfoUpdate(auvo);
    }

    @Override
    public int admininfoWrite(adminVO auvo) {

        return adminMapper.admininfoWrite(auvo); // Mapper 호출
    }

}
