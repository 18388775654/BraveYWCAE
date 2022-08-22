package com.yang.cae.modules.mapper.jpa;

import com.yang.cae.modules.entity.UserBasicInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBasicInformationJpa extends JpaRepository<UserBasicInformation, String> {
    @Select({})
    Boolean existsByEmail(String email);
    @Select({})
    Boolean existsByPhoneNumber(String phoneNuber);
    @Select({})
    UserBasicInformation getByEmail(String email);
}
