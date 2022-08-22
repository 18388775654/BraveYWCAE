package com.yang.cae.modules.mapper.jpa;

import com.yang.cae.modules.entity.UserLoginInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginInformationJpa extends JpaRepository<UserLoginInformation, String> {
     @Select({})
     UserLoginInformation getByEmailAndPassword(String email, String password);
     @Select({})
     UserLoginInformation getByEmail(String email);
     @Select({})
     Boolean existsByEmail(String email);
     @Select({})
     Boolean existsByPhoneNumber(String phoneNuber);


}
