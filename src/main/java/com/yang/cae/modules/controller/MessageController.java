package com.yang.cae.modules.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.yang.cae.modules.dto.DataDTO;
import com.yang.cae.modules.entity.*;
import com.yang.cae.modules.mapper.jpa.*;
import com.yang.cae.util.result.Result;
import com.yang.cae.util.tokenUtil.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
@Api(tags = {"信息"})
@Slf4j
public class MessageController {
    @Resource
    private MessageCertificateJpa messageCertificateJpa;
    @Resource
    private MessageExamJpa messageExamJpa;
    @Resource
    private MessageWorkJpa messageWorkJpa;
    @Resource
    private UserBasicInformationJpa userBasicInformationJpa;
    @Resource
    private UserLoginInformationJpa userLoginInformationJpa;


    @ApiOperation(value = "证书信息")
    @GetMapping(value = "/getMessageCertificate")
    public Result<?> getMessageCertificate(String messageId){
        MessageCertificate messageCertificate = messageCertificateJpa.getById(messageId);
        return Result.ok(messageCertificate);
    }
    @ApiOperation(value = "考试信息")
    @GetMapping(value = "/getMessageExam")
    public Result<?> getMessageExam(String messageId){
        MessageExam messageExam = messageExamJpa.getById(messageId);
        return Result.ok(messageExam);
    }
    @ApiOperation(value = "办事信息")
    @GetMapping(value = "/getMessageWork")
    public Result<?> getMessageWork(String messageId){
        MessageWork messageWork = messageWorkJpa.getById(messageId);
        return Result.ok(messageWork);
    }

    @ApiOperation(value = "用户基本信息")
    @GetMapping(value = "/getUserMessage")
    public Result<?> getUserMessage(@RequestHeader("token") String token){
        DecodedJWT verify = TokenUtil.verify(token);
        if (verify == null){
            return Result.ok("token is error");
        }
        String userId = verify.getClaim("id").asString();
        UserLoginInformation userLoginInformation = userLoginInformationJpa.getById(userId);
        String email = userLoginInformation.getEmail();
        UserBasicInformation userBasicInformation = userBasicInformationJpa.getByEmail(email);
        return Result.ok(userBasicInformation);
    }
    @ApiOperation(value = "用户信息修改")
    @PostMapping(value = "/setUserMessage")
    public Result<?> setUserMessage(@RequestHeader("token") String token,
                                @RequestBody UserBasicInformation userBasicInformation){

        DecodedJWT verify = TokenUtil.verify(token);
        if (verify == null){
            return Result.ok("token is error");
        }else {
            if (userBasicInformationJpa.existsByEmail(userBasicInformation.getEmail())){
                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                UserBasicInformation information = userBasicInformationJpa.getByEmail(userBasicInformation.getEmail());
                userBasicInformation.setId(information.getId());
                userBasicInformation.setUpdateTime(dateFormat.format(new Date()));
                userBasicInformationJpa.save(userBasicInformation);
                return Result.ok("保存成功！");
            }else {
                return Result.error("userId is Null");
            }

        }
    }

    @ApiOperation(value = "用户昵称")
    @GetMapping(value = "/getNickName")
    public Result<?> getNickName(@RequestHeader("token") String token){
        DecodedJWT verify = TokenUtil.verify(token);
        if (verify == null){
            return Result.ok("token is error");
        }
        String userId = verify.getClaim("id").asString();
        UserLoginInformation userLoginInformation = userLoginInformationJpa.getById(userId);
        return Result.ok(userLoginInformation.getNickName());
    }
}
