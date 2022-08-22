package com.yang.cae.modules.service.Impl;

import com.yang.cae.modules.dto.DataDTO;
import com.yang.cae.modules.entity.MessageCertificate;
import com.yang.cae.modules.entity.MessageExam;
import com.yang.cae.modules.entity.UserBasicInformation;
import com.yang.cae.modules.entity.UserLoginInformation;
import com.yang.cae.modules.mapper.MessageCertificateMapper;
import com.yang.cae.modules.mapper.MessageExamMapper;
import com.yang.cae.modules.mapper.jpa.UserBasicInformationJpa;
import com.yang.cae.modules.mapper.jpa.UserLoginInformationJpa;
import com.yang.cae.modules.service.RecommendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class RecommendServiceImpl implements RecommendService {
    @Resource
    private UserLoginInformationJpa userLoginInformationJpa;
    @Resource
    private UserBasicInformationJpa userBasicInformationJpa;
    @Resource
    private MessageCertificateMapper messageCertificateMapper;
    @Resource
    private MessageExamMapper messageExamMapper;


    @Override
    public List<DataDTO> getRecommend(String userId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

        List<DataDTO> dataDTOList = new ArrayList<>();

        //查询用户登录信息，拿到邮箱
        Optional<UserLoginInformation> userLoginInformation = userLoginInformationJpa.findById(userId);
        UserLoginInformation loginInformation = userLoginInformation.get();
        String email = loginInformation.getEmail();
        //通过邮箱查询用户基本信息
        UserBasicInformation userBasicInformation = userBasicInformationJpa.getByEmail(email);
        String major = userBasicInformation.getMajor();
        String profession = userBasicInformation.getProfession();
        //查询推荐
        //证书推荐
        List<MessageCertificate> messageCertificateList = new ArrayList<>();
        //考试推荐
        List<MessageExam> messageExamList = new ArrayList<>();

        if((major == null && profession == null) || ("".equals(major) && "".equals(profession)) ){
            List<MessageCertificate> recommendByNull = messageCertificateMapper.getRecommendByNull();
            List<MessageExam> recommendByNull1 = messageExamMapper.getRecommendByNull();
            if (recommendByNull != null && !recommendByNull.isEmpty()){
                messageCertificateList.addAll(recommendByNull);
            }
            if (recommendByNull1 != null && !recommendByNull1.isEmpty()){
                messageExamList.addAll(recommendByNull1);
            }
        }else {
            if(major != null){
                List<MessageCertificate> recommendByMajor = messageCertificateMapper.getRecommendByMajor(major);
                List<MessageExam> recommendByMajor1 = messageExamMapper.getRecommendByMajor(major);

                if(recommendByMajor != null && !recommendByMajor.isEmpty()){
                    messageCertificateList.addAll(recommendByMajor);
                }
                if(recommendByMajor1 != null && !recommendByMajor1.isEmpty()){
                    messageExamList.addAll(recommendByMajor1);
                }
            }
            if(profession != null){
                List<MessageCertificate> recommendByProfession = messageCertificateMapper.getRecommendByProfession(profession);
                List<MessageExam> recommendByProfession1 = messageExamMapper.getRecommendByProfession(profession);

                if(recommendByProfession != null && !recommendByProfession.isEmpty()){
                    messageCertificateList.addAll(recommendByProfession);
                }
                if(recommendByProfession1 != null && !recommendByProfession1.isEmpty()){
                    messageExamList.addAll(recommendByProfession1);
                }
            }
            if(major!=null && profession != null){
                List<MessageCertificate> recommendByMajorAndProfession
                        = messageCertificateMapper.getRecommendByMajorAndProfession(major,profession);
                List<MessageExam> recommendByMajorAndProfession1
                        = messageExamMapper.getRecommendByMajorAndProfession(major,profession);

                if(recommendByMajorAndProfession != null && !recommendByMajorAndProfession.isEmpty()){
                    messageCertificateList.addAll(recommendByMajorAndProfession);
                }
                if(recommendByMajorAndProfession1 != null && !recommendByMajorAndProfession1.isEmpty()){
                    messageExamList.addAll(recommendByMajorAndProfession1);
                }
            }
        }
        if (messageCertificateList != null && !messageCertificateList.isEmpty()){
            for (MessageCertificate certificate : messageCertificateList) {
                DataDTO dataDTO = new DataDTO();
                dataDTO.setFlag("certificate");
                dataDTO.setMessageId(certificate.getId());
                dataDTO.setMessageName(certificate.getCertificateName());
                dataDTO.setDate(dateFormat.format(new Date()));
                dataDTOList.add(dataDTO);
            }
        }
        if (messageExamList != null && !messageExamList.isEmpty()){
            for (MessageExam exam : messageExamList) {
                DataDTO dataDTO = new DataDTO();
                dataDTO.setFlag("exam");
                dataDTO.setMessageId(exam.getId());
                dataDTO.setMessageName(exam.getExamName());
                dataDTO.setDate(dateFormat.format(new Date()));
                dataDTOList.add(dataDTO);
            }
        }
        return dataDTOList;
    }
}
