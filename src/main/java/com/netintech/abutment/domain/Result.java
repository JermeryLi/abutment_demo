package com.netintech.abutment.domain;

import lombok.Data;

/**
 * 描述：
 *
 * @author AI
 * createTime 2022/9/5 17:37
 * lastModify
 * lastModifyTime
 * 小组
 */
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;
}
