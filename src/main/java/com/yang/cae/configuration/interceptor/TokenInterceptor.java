package com.yang.cae.configuration.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yang.cae.util.tokenUtil.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.yang.cae.util.SureValue._TOKEN;

@Component
public class TokenInterceptor implements HandlerInterceptor {

//    @Autowired
//    StringRedisTemplate stringRedisTemplate;
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 获取到token
//        String token=request.getHeader("token");
//        System.out.println("用户token>>>>"+token);
//        DecodedJWT verify = TokenUtil.verify(token);
//        if (verify == null){
//            System.out.println("\n token过期! \n");
//            response.setStatus(404);
//            return false;
//        }
//        Claim id = verify.getClaim("id");
//        if(stringRedisTemplate.hasKey(id + _TOKEN)){
//            System.out.println("\n toke认证通过! \n");
//            return true;
//        }else {
//            System.out.println("\n toke认证不通过！ \n");
//            response.setStatus(404);
//            return false;
//        }
//
//    }

}