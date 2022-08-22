package com.yang.cae.modules.mapper;

import com.yang.cae.modules.entity.MessageCertificate;
import com.yang.cae.modules.entity.MessageExam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface MessageExamMapper{
    List<MessageExam> queryData(String queryData);
    //推荐查询
    List<MessageExam> getRecommendByNull();
    List<MessageExam> getRecommendByMajor(String major);
    List<MessageExam> getRecommendByProfession(String profession);
    List<MessageExam> getRecommendByMajorAndProfession(String major, String profession);
}
