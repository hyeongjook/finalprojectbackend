package com.ict.finalspringboot.domain.admin_info.service;

import java.util.List;

import com.ict.finalspringboot.domain.admin_info.vo.adminVO;

public interface AdminService {

    // 조회 불러오기

    List<adminVO> admininfoList();

    // 수정하기
    int admininfoUpdate(adminVO auvo);

    // 삭제하기
    int admininfoDelete(int admin_idx);

    // 생성하기
    int admininfoWrite(adminVO auvo);

    // 상세보기
    adminVO getadminsById(int admin_idx);

}