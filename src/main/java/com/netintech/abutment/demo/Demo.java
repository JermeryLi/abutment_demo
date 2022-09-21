package com.netintech.abutment.demo;

import com.netintech.abutment.domain.KeySecret;
import com.netintech.abutment.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import static com.netintech.abutment.demo.Constant.ACCESS_TOKEN_ADDRESS;
import static com.netintech.abutment.demo.Constant.ENV_PREFIX;

/**
 * 描述：
 *
 * @author AI
 * createTime 2022/9/5 16:19
 * lastModify
 * lastModifyTime
 * 公共智慧底座-人工智能技术团队
 */
public class Demo {

    @Autowired
    private RestTemplate restTemplate;

    private void getAccessToken(String key, String secret) {
        KeySecret keySecret = new KeySecret(key, secret);
        HttpEntity<KeySecret> httpEntity = new HttpEntity<>(keySecret);
        Result<Integer> result = restTemplate.postForObject(ENV_PREFIX + ACCESS_TOKEN_ADDRESS, httpEntity, Result.class);
    }

    public static void main(String[] args) {

    }

}
