package com.yang.cae.modules.mapper;

import com.yang.cae.modules.entity.MessageCertificate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageCertificateMapper {
    public List<MessageCertificate> queryData(String queryData);


    //推荐查询
    List<MessageCertificate> getRecommendByNull();
    List<MessageCertificate> getRecommendByMajor(String major);
    List<MessageCertificate> getRecommendByProfession(String profession);
    List<MessageCertificate> getRecommendByMajorAndProfession(String major, String profession);
}
