package com.ict.finalspringboot.domain.admin_info.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ict.finalspringboot.domain.admin_info.vo.adminVO;

@Mapper
public interface AdminMapper {

    List<adminVO> admininfoList();

    // 수정하기
    int admininfoUpdate(adminVO auvo);

    // 삭제하기
    int admininfoDelete(int admin_idx);

    // 상세보기
    adminVO getadminsById(int admin_idx);

    // 생성하기
    int admininfoWrite(adminVO auvo);

}
