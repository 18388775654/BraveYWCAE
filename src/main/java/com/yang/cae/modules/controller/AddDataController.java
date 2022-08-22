package com.yang.cae.modules.controller;

import com.yang.cae.modules.entity.*;
import com.yang.cae.modules.mapper.jpa.*;
import com.yang.cae.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/addData")
@Api(tags = {"添加数据"})
@Slf4j
public class AddDataController {

    @Resource
    private MessageExamJpa messageExamJpa;
    @Resource
    private MessageCertificateJpa messageCertificateJpa;
    @Resource
    private MessageWorkJpa messageWorkJpa;
    @Resource
    private NameMajorJpa nameMajorJpa;
    @Resource
    private NameProfessionJpa nameProfessionJpa;
    @Resource
    private AdvertiseJpa advertiseJpa;

    @ApiOperation(value = "考试信息")
    @GetMapping(value = "/addMessageExam")
    public Result<?> addMessageExam(@ModelAttribute MessageExam messageExam){
        MessageExam exam = messageExamJpa.save(messageExam);
        return Result.ok(exam);
    }

    @ApiOperation(value = "证书信息")
    @GetMapping(value = "/addMessageCertificate")
    public Result<?> addMessageCertificate(@ModelAttribute MessageCertificate messageCertificate){
        MessageCertificate certificate = messageCertificateJpa.save(messageCertificate);
        return Result.ok(certificate);
    }

    @ApiOperation(value = "办事信息")
    @GetMapping(value = "/addMessageWork")
    public Result<?> addMessageWork(@ModelAttribute MessageWork messageWork){
        MessageWork work = messageWorkJpa.save(messageWork);
        return Result.ok(work);
    }

    @ApiOperation(value = "专业名称")
    @GetMapping(value = "/addNameMajor")
    public Result<?> addNameMajor(@ModelAttribute NameMajor nameMajor){
        NameMajor major = nameMajorJpa.save(nameMajor);
        return Result.ok(major);
    }
    @ApiOperation(value = "职业名称")
    @GetMapping(value = "/addNameProfession")
    public Result<?> addNameProfession(@ModelAttribute NameProfession nameProfession){
        NameProfession profession = nameProfessionJpa.save(nameProfession);
        return Result.ok(profession);
    }

    @ApiOperation(value = "广告")
    @GetMapping(value = "/addAdvertise")
    public Result<?> addAdvertise(@RequestParam("file")String file, @RequestParam("url")String url){
        Advertise advertise = new Advertise();
        advertise.setImageUrl("http://47o654i322.qicp.vip/cae/image/"+file);
        advertise.setAdvertiseUrl(url);
        Advertise save = advertiseJpa.save(advertise);
        return Result.ok(save);
    }
}
