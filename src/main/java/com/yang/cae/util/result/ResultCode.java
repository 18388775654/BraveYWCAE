package com.yang.cae.util.result;

public interface ResultCode {
    //响应成功，并且返回数据 返回数据data
    public static final Integer success_200 = 200;
    //响应成功，返回message
    public static final Integer success_204 = 204;
    //请求错误
    public static final Integer success_400 = 400;
    //服务器错误
    public static final Integer success_500 = 500;

}
