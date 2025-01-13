package com.ict.finalspringboot.domain.user_info.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ict.finalspringboot.domain.user_info.vo.userVO;

@Mapper
public interface UserMapper {

    // 특정 회원 상세 조회
    userVO getUsersDetails(@Param("user_idx") int user_idx);

    // 일반회원 리스트 조회 (user_level_idx == 1)
    List<userVO> getUserListByLevel1();

    // 전문회원 리스트 조회 (user_level_idx != 1)
    List<userVO> getUserListByLevelNot1();

    // 회원 수정
    int updateUser(userVO uvo);

    // 회원 삭제
    int deleteUser(@Param("user_idx") int user_idx);

    // 프로필 이미지 URL 업데이트
    int updateProfileImage(@Param("user_idx") int user_idx, @Param("image_url") String imageUrl);
}