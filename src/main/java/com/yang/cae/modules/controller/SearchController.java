package com.yang.cae.modules.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.yang.cae.modules.dto.DataDTO;
import com.yang.cae.modules.entity.UserSearchRecord;
import com.yang.cae.modules.mapper.jpa.UserSearchRecordJpa;
import com.yang.cae.modules.service.SearchService;
import com.yang.cae.util.result.Result;
import com.yang.cae.util.tokenUtil.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/search")
@Api(tags = {"搜索"})
@Slf4j
public class SearchController {
    @Resource
    private SearchService searchService;
    @Resource
    private UserSearchRecordJpa userSearchRecordJpa;
    @ApiOperation(value = "搜索")
    @GetMapping(value = "/getSearch")
    public Result<?> getSearch(@RequestHeader("token") String token,String queryData){
        System.out.println(token);
        System.out.println(new Date()+">>>>>>>>>>>>>"+queryData);
        List<DataDTO> data = searchService.getData(queryData);

        DecodedJWT verify = TokenUtil.verify(token);
        if (verify == null){
            return Result.ok(data);
        }
        String id = verify.getClaim("id").asString();
        searchService.saveSearchRecord(id,queryData);
        return Result.ok(data);
    }
    @ApiOperation(value = "搜索记录")
    @GetMapping(value = "/getSearchRecord")
    public Result<?> getSearchRecord(@RequestHeader("token") String token){
        DecodedJWT verify = TokenUtil.verify(token);
        if (verify == null){
            return Result.ok("token is error");
        }
        String id = verify.getClaim("id").asString();
        List<DataDTO> searchRecord = searchService.getSearchRecord(id);
        return Result.ok(searchRecord);
    }
    @ApiOperation(value = "删除记录")
    @DeleteMapping(value = "/deleteSearchRecord")
    public Result<?> deleteSearchRecord(@RequestHeader("token") String token, String recordId){
        DecodedJWT verify = TokenUtil.verify(token);
        if (verify == null){
            return Result.ok("token is error");
        }
        boolean b = userSearchRecordJpa.existsById(recordId);
        if (b){
            //userSearchRecordJpa.deleteById(id);
            userSearchRecordJpa.deleteById(recordId);
            String id = verify.getClaim("id").asString();
            List<DataDTO> searchRecord = searchService.getSearchRecord(id);
            return Result.ok(searchRecord);
        }else {
            return Result.error("Id is NULL!");
        }

    }
}
