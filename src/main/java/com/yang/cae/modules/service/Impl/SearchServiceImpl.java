package com.yang.cae.modules.service.Impl;

import com.yang.cae.modules.dto.DataDTO;
import com.yang.cae.modules.entity.MessageCertificate;
import com.yang.cae.modules.entity.MessageExam;
import com.yang.cae.modules.entity.MessageWork;
import com.yang.cae.modules.entity.UserSearchRecord;
import com.yang.cae.modules.mapper.MessageCertificateMapper;
import com.yang.cae.modules.mapper.MessageExamMapper;
import com.yang.cae.modules.mapper.MessageWorkMapper;
import com.yang.cae.modules.mapper.jpa.UserSearchRecordJpa;
import com.yang.cae.modules.service.SearchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class SearchServiceImpl implements SearchService {
    @Resource
    private MessageCertificateMapper certificateMapper;
    @Resource
    private MessageExamMapper examMapper;
    @Resource
    private MessageWorkMapper workMapper;
    @Resource
    private UserSearchRecordJpa userSearchRecordJpa;


    @Override
    public List<DataDTO> getData(String queryData) {
        List<MessageCertificate> certificateList = certificateMapper.queryData(queryData);
        List<MessageExam> examList = examMapper.queryData(queryData);
        List<MessageWork> workList = workMapper.queryData(queryData);
        List<DataDTO> searchDataDTOS = new ArrayList<>();
        for (MessageCertificate certificate : certificateList) {
            DataDTO searchDataDTO = new DataDTO();
            searchDataDTO.setFlag("certificate");
            searchDataDTO.setMessageId(certificate.getId());
            searchDataDTO.setMessageName(certificate.getCertificateName());
            searchDataDTOS.add(searchDataDTO);
        }
        for (MessageExam messageExam : examList) {
            DataDTO searchDataDTO = new DataDTO();
            searchDataDTO.setFlag("exam");
            searchDataDTO.setMessageId(messageExam.getId());
            searchDataDTO.setMessageName(messageExam.getExamName());
            searchDataDTOS.add(searchDataDTO);
        }
        for (MessageWork messageWork : workList) {
            DataDTO searchDataDTO = new DataDTO();
            searchDataDTO.setFlag("work");
            searchDataDTO.setMessageId(messageWork.getId());
            searchDataDTO.setMessageName(messageWork.getWorkName());
            searchDataDTOS.add(searchDataDTO);
        }

        return searchDataDTOS;
    }

    @Override
    public void saveSearchRecord(String userId, String queryData) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        UserSearchRecord userSearchRecord = new UserSearchRecord();
        userSearchRecord.setUserId(userId);
        userSearchRecord.setSearch(queryData);
        userSearchRecord.setSearchTime(dateFormat.format(new Date()));
        userSearchRecordJpa.save(userSearchRecord);
    }

    @Override
    public List<DataDTO> getSearchRecord(String userId) {
        List<DataDTO> dataDTOList = new ArrayList<>();
        List<UserSearchRecord> searchRecordList = userSearchRecordJpa.getByUserIdAndIsDeletedIsNull(userId);
        if (searchRecordList != null && !searchRecordList.isEmpty())
        for (UserSearchRecord searchRecord : searchRecordList) {
            DataDTO dataDTO = new DataDTO();
            dataDTO.setFlag("search");
            dataDTO.setMessageId(searchRecord.getId());
            dataDTO.setMessageName(searchRecord.getSearch());
            dataDTO.setDate(searchRecord.getSearchTime());
            dataDTOList.add(dataDTO);
        }
        return dataDTOList;
    }
}
