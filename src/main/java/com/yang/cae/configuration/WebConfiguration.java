package com.yang.cae.configuration;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.yang.cae.configuration.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;


/**
 * 跨域请求支持/token拦截
 * tip:只能写在一个配置类里
 */
@Configuration
@EnableKnife4j
public class WebConfiguration implements WebMvcConfigurer {

//    private TokenInterceptor tokenInterceptor;
//
//    //构造方法
//    public WebConfiguration(TokenInterceptor tokenInterceptor){
//        this.tokenInterceptor = tokenInterceptor;
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowCredentials(true)
//                .allowedHeaders("*")
//                .allowedMethods("*")
//                .allowedOrigins("*");
//    }
//
//    @Override
//    public void configureAsyncSupport(AsyncSupportConfigurer configurer){
//        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
//        configurer.setDefaultTimeout(30000);
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        List<String> excludePath = new ArrayList<>();
//        //对以下资源请求不进行拦截和验证
//        excludePath.add("/user/register");  //注册
//        excludePath.add("/user/login");     //登录
//        excludePath.add("/static/**");  //静态资源
//        excludePath.add("/assets/**");  //静态资源
//
//        registry.addInterceptor(tokenInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns(excludePath);
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }
////Knife4j3Config
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("doc.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
}