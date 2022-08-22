package com.yang.cae.modules.controller;

import com.yang.cae.modules.dto.DataDTO;
import com.yang.cae.modules.entity.Advertise;
import com.yang.cae.modules.entity.MessageCertificate;
import com.yang.cae.modules.entity.MessageExam;
import com.yang.cae.modules.entity.MessageWork;
import com.yang.cae.modules.mapper.jpa.AdvertiseJpa;
import com.yang.cae.modules.mapper.jpa.MessageCertificateJpa;
import com.yang.cae.modules.mapper.jpa.MessageExamJpa;
import com.yang.cae.modules.mapper.jpa.MessageWorkJpa;
import com.yang.cae.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/home")
@Api(tags = {"首页"})
@Slf4j
public class HomeController {

    @Resource
    private MessageCertificateJpa messageCertificateJpa;
    @Resource
    private MessageExamJpa messageExamJpa;
    @Resource
    private MessageWorkJpa messageWorkJpa;
    @Resource
    private AdvertiseJpa advertiseJpa;



    @ApiOperation(value = "首页数据")
    @GetMapping(value = "/getHomeData")
    public Result<?> getHomeData(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        List<DataDTO> homeDataDTOList = new ArrayList<>();
        List<MessageCertificate> certificateMapperAll = messageCertificateJpa.findAll();
        for (MessageCertificate certificate : certificateMapperAll) {
            DataDTO dataDTO = new DataDTO();
            dataDTO.setFlag("certificate");
            dataDTO.setMessageId(certificate.getId());
            dataDTO.setMessageName(certificate.getCertificateName());
            dataDTO.setDate(dateFormat.format(new Date()));
            homeDataDTOList.add(dataDTO);
        }
        List<MessageExam> examList = messageExamJpa.findAll();
        for (MessageExam exam : examList) {
            DataDTO dataDTO = new DataDTO();
            dataDTO.setFlag("exam");
            dataDTO.setMessageId(exam.getId());
            dataDTO.setMessageName(exam.getExamName());
            dataDTO.setDate(dateFormat.format(new Date()));
            homeDataDTOList.add(dataDTO);
        }
        List<MessageWork> workList = messageWorkJpa.findAll();
        for (MessageWork work : workList) {
            DataDTO dataDTO = new DataDTO();
            dataDTO.setFlag("work");
            dataDTO.setMessageId(work.getId());
            dataDTO.setMessageName(work.getWorkName());
            dataDTO.setDate(dateFormat.format(new Date()));
            homeDataDTOList.add(dataDTO);
        }
        return Result.ok(homeDataDTOList);
    }

    @ApiOperation(value = "首页广告")
    @GetMapping(value = "/getAdvertise")
    public Result<?> getAdvertise(){
        List<Advertise> advertiseList = advertiseJpa.findAll();
        return Result.ok(advertiseList);
    }
}
