package com.lee.wxlogin.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wx")
@Data
public class WxProperties {
    private String appId;
    private String appSecret;
    private String redictUrl;
    private String loginUrl;
    private String accessTokenUrl;
    private String userInfoUrl;
}
