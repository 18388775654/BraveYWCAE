package com.yang.cae.modules.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;//一定要用@Autowired
    //application.properties中已配置的值
    @Value("${spring.mail.username}")
    private String from;
    /**
     * 给前端输入的邮箱，发送验证码
     * @param userEmail
     * @return
     */
    public String sendVerifyMail(String userEmail) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setSubject("验证码邮件");//主题
            //生成随机数
            String authCode = randomCode();
            String text = "您的验证码为：" + authCode + ",请勿泄露给他人。有效时间5分钟";
            mailMessage.setText(text);//内容

            mailMessage.setTo(userEmail);//发给谁

            mailMessage.setFrom(from);//你自己的邮箱
            mailMessage.setSentDate(new Date());
            mailSender.send(mailMessage);//发送
            return  authCode;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 随机生成6位数的验证码
     * @return String code
     */
    public String randomCode(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

//    // 随机验证码
//    public String randomCode() {  //由于数字 1 、 0 和字母 O 、l 有时分不清楚，所以，没有数字 1 、 0
//        String[] beforeShuffle= new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "A", "B", "C", "D", "E", "F",
//                "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a",
//                "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
//                "w", "x", "y", "z" };
//        List list = Arrays.asList(beforeShuffle);//将数组转换为集合
//        Collections.shuffle(list);  //打乱集合顺序
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < list.size(); i++) {
//            sb.append(list.get(i)); //将集合转化为字符串
//        }
//        return sb.substring(4, 8);  //截取字符串第4到8
//    }

}
