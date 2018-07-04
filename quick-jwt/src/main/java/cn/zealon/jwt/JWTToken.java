package cn.zealon.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @auther: Zealon
 * @Date: 2018-07-04 16:40
 */
public class JWTToken implements AuthenticationToken {
    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
