package com.netintech.abutment.controller;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.netintech.abutment.domain.DispatchRequest;
import com.netintech.abutment.domain.KeySecret;
import com.netintech.abutment.domain.Params;
import com.netintech.abutment.domain.Result;
import com.netintech.abutment.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.netintech.abutment.demo.Constant.*;

/**
 * 描述：
 *
 * @author AI
 * createTime 2022/9/5 18:23
 * lastModify
 * lastModifyTime
 * 小组
 */
@RestController
public class DemoController {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/demo")
    public void demo() throws Exception{
        //1、获取token
        Result<String> tokenResult = getAccessToken(API_KEY, API_SECRET);
        //System.out.println(tokenResult);
        String token = tokenResult.getData();
        //String token = "8e94c970-1f07-45f9-95c2-a9788480c280";
        //2、获取文件fileId
        //获取fileId 通过文档中文件上传接口
        String fileId = "52c2874a2eac4bb2bca8f7831b26732b";
        //3、转发接口调用
        getDispatchResult(token,fileId);
    }

    /**
     * 获取token临时通行令牌
     * 调用该接口会在服务总线内加载最新的缓存数据时效为24小时
     * 每次调用均会重新加载缓存数据。
     * demo只是示例，实际调用应当遵循定时调用原则，避免频繁请求
     */
    private Result<String> getAccessToken(String key, String secret) {
        KeySecret keySecret = new KeySecret(key, secret);
        return restTemplate.postForObject(ENV_PREFIX + ACCESS_TOKEN_ADDRESS, keySecret, Result.class);
    }

    private void getDispatchResult(String authorization, String fileId) throws Exception {
        DispatchRequest dispatchRequest = new DispatchRequest();
        dispatchRequest.setApiId(API_ID);
        //添加接口参数
        Params params = new Params();
        params.setFileId(fileId);
        params.setDate(Util.getFormatDate());
        params.setAttestation(Util.getAttestation(params.getDate()));
        dispatchRequest.setParams(params);

        String json = JSON.toJSONString(dispatchRequest);
        System.out.println("请求体json字符串：" + json);
        String md5Json = SecureUtil.md5(json);
        System.out.println("请求体json字符串加密：" + md5Json);
        //添加服务总线参数
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);
        headers.add("apiKey", API_KEY);
        headers.add("Signature", md5Json);
        headers.add("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Result> result = restTemplate.postForEntity(ENV_PREFIX + DISPATCH_ADDRESS, request, Result.class);
        System.out.println(result);
        System.out.println(JSON.toJSONString(result.getBody()));

    }

}
