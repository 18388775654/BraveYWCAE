package com.yang.cae.modules.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.yang.cae.modules.dto.DataDTO;
import com.yang.cae.modules.entity.OrderCollect;
import com.yang.cae.modules.entity.OrderRecommend;
import com.yang.cae.modules.mapper.jpa.OrderCollectJpa;
import com.yang.cae.modules.mapper.jpa.OrderRecommendJpa;
import com.yang.cae.modules.service.CollectService;
import com.yang.cae.util.result.Result;
import com.yang.cae.util.tokenUtil.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/collect")
@Api(tags = {"收藏"})
@Slf4j
public class CollectController {
    @Resource
    private OrderCollectJpa orderCollectJpa;
    @Resource
    private CollectService collectService;

    @ApiOperation(value = "获取收藏")
    @GetMapping(value = "/getCollect")
    public Result<?> getCollect(@RequestHeader("token")String token){
        System.out.println(token);
        DecodedJWT verify = TokenUtil.verify(token);
        if (verify == null){
            return Result.ok("token is timeout");
        }else {
            String id = verify.getClaim("id").asString();
            List<DataDTO> dataDTOList = collectService.getCollect(id);
            return Result.ok(dataDTOList);
        }
    }
    @ApiOperation(value = "添加收藏")
    @PostMapping(value = "/addCollect")
    public Result<?> addCollect(@RequestHeader("token")String token, @RequestBody DataDTO dataDTO){
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        DecodedJWT verify = TokenUtil.verify(token);
        if (verify == null){
            return Result.error("token is timeout");
        }else {
            String id = verify.getClaim("id").asString();
            boolean b = orderCollectJpa.existsByCollectMessageIdAndUserId(dataDTO.getMessageId(), id);
            if (b){
                return Result.error("收藏已存在！");
            }else {
                OrderCollect orderCollect = new OrderCollect();
                orderCollect.setCollectTime(dateFormat.format(new Date()));
                orderCollect.setFlag(dataDTO.getFlag());
                orderCollect.setUserId(id);
                orderCollect.setCollectMessageName(dataDTO.getMessageName());
                orderCollect.setCollectMessageId(dataDTO.getMessageId());
                OrderCollect collect = orderCollectJpa.save(orderCollect);
                return Result.ok("收藏成功");
            }

        }
    }
    @ApiOperation(value = "取消收藏")
    @DeleteMapping(value = "/deleteCollect")
    public Result<?> deleteCollect(@RequestHeader("token")String token, String messageId){
        DecodedJWT verify = TokenUtil.verify(token);
        if (verify == null){
            return Result.ok("token is timeout");
        }else {
            String id = verify.getClaim("id").asString();
            boolean b = orderCollectJpa.existsByCollectMessageIdAndUserId(messageId, id);
            if (b){
                OrderCollect orderCollect = orderCollectJpa.getByCollectMessageIdAndUserId(messageId, id);

                orderCollectJpa.delete(orderCollect);
                List<DataDTO> dataDTOList = collectService.getCollect(id);
                return Result.ok(dataDTOList);
            }else {
                return Result.error("Id is Null!");
            }

        }

    }
}
