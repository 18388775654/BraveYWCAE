package com.yang.cae.modules.mapper.jpa;

import com.yang.cae.modules.entity.UserSearchRecord;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSearchRecordJpa extends JpaRepository<UserSearchRecord, String> {
    @Select({})
    List <UserSearchRecord> getByUserIdAndIsDeletedIsNull(String userId);

}
