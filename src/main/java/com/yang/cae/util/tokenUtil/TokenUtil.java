package com.yang.cae.util.tokenUtil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yang.cae.modules.entity.UserLoginInformation;

import java.util.Date;

import static com.yang.cae.util.SureValue.TOKEN_EXPIRE_TIME;

public class TokenUtil {

    //使用静态的字符密文或者key来获取算法器，token秘钥，请勿泄露，请勿随便修改
    private static final String TOKEN_SECRET = "yangwei";  //密钥盐
    /**
     * 签名生成
     * @param userDTO
     * @return
     */
    public static String sign(UserLoginInformation userDTO) {
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("id", userDTO.getId())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
            System.out.println(userDTO.getEmail()+"=>token:\t"+token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }

    /**
     * 签名验证
     *
     * @param token
     * @return
     */
    public static DecodedJWT verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("id: " + jwt.getClaim("id").asString());
            //System.out.println("password: " + jwt.getClaim("password").asString());
            System.out.println("过期时间：" + jwt.getExpiresAt());
            return jwt;
        } catch (Exception e) {
            return null;
        }
    }
}