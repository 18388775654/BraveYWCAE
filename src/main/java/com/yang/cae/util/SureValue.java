package  com.yang.cae.util;

public interface SureValue {
    //token过期时间 单位:毫秒
    static final long TOKEN_EXPIRE_TIME = 24 * 60 * 60 * 1000;
    //邮箱验证码过期时间 单位:分钟
    static final long EMAIL_EXPIRE_TIME = 5;
    //图片验证码期时间 单位:分钟
    static final long PIC_EXPIRE_TIME = 3;
    //token后缀
    static final String _TOKEN = "_token";
    // 图片验证码 key
    static final String AUTH_CODE = "_auth_code";


}
