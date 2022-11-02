package com.shf.jwt.entity.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @Author shengs
 * @Date 2022/11/01
 * @Version V1.0
 **/
@ConfigurationProperties(prefix = "jwt")
@Data
@Component
public class JwtProperties {
    /**
     * request header key
     */
    private String Header;
    /**
     * 秘钥
     */
    private String Secret;
    /**
     * 过期时间
     */
    private long Expiration;
    /**
     * token 的header
     */
    private String TokenHead;
}
