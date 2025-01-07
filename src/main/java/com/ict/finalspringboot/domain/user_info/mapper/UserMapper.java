package com.ict.finalspringboot.domain.user_info.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ict.finalspringboot.domain.user_info.vo.userVO;

@Mapper
public interface UserMapper {

    List<userVO> userinfoList();

    // 수정하기
    int userinfoUpdate(userVO uvo);

    // 삭제하기
    int userinfoDelete(int user_idx);

    // 상세보기
    userVO getusersById(int user_idx);

    // 생성하기
    int userinfoWrite(userVO uvo);

}
