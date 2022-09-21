package com.netintech.abutment.domain;

import lombok.Data;

/**
 * 描述：
 *
 * @author AI
 * createTime 2022/9/5 17:33
 * lastModify
 * lastModifyTime
 * 小组
 */
@Data
public class KeySecret {
    private String apiKey;
    private String secret;

    public KeySecret(String apiKey, String secret) {
        this.apiKey = apiKey;
        this.secret = secret;
    }
}
