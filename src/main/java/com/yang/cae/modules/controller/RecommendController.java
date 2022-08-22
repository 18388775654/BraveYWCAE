package com.yang.cae.modules.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.yang.cae.modules.dto.DataDTO;
import com.yang.cae.modules.service.RecommendService;
import com.yang.cae.util.result.Result;
import com.yang.cae.util.tokenUtil.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

import static com.yang.cae.util.SureValue._TOKEN;

@RestController
@RequestMapping("/recommend")
@Api(tags = {"推荐"})
@Slf4j
public class RecommendController {
    @Resource
    private RecommendService recommendService;
    @ApiOperation(value = "用户推荐")
    @PostMapping(value = "/getRecommend")
    public Result<?> getRecommend(@RequestHeader("token")String token){
        System.out.println(token);
        DecodedJWT verify = TokenUtil.verify(token);
        if (verify == null){
            return Result.ok("token is timeout");
        }else {
            String id = verify.getClaim("id").asString();
            List<DataDTO> recommendList = recommendService.getRecommend(id);
            return Result.ok(recommendList);
        }

    }

}
