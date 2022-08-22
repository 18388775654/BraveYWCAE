package com.yang.cae.modules.mapper;

import com.yang.cae.modules.entity.MessageWork;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface MessageWorkMapper{
    List<MessageWork> queryData(String queryData);
}
