package com.netintech.abutment.demo;

/**
 * 描述： 接口调用相关配置常量
 * 可以写在配置文件中，方便修改
 * @author AI
 * createTime 2022/9/5 16:23
 * 公共智慧底座-人工智能技术团队
 */
public class Constant {

    /**
     * 系统分配的apiKey，每个应用不同
     */
    public static final String API_KEY = "ea538f9a8a3847d0a3750e2068e26fb2";
    /**
     * 系统分配的apiSecret，每个应用不同
     */
    public static final String API_SECRET = "pT85cg4EDw8WCrSJXIVKtz/jcikYx6F1j2W8iEWOC9Y=";
    /**
     * 系统分配的apiId，每个算法接口不同
     */
    public static final String API_ID = "1548854296194408449";
    /**
     * 接口前缀，不通过环境不同，根据实际配置
     */
    public static final String ENV_PREFIX = "https://172.16.0.101/test-api/";
    /**
     * 获取accessToken接口地址
     */
    public static final String ACCESS_TOKEN_ADDRESS = "/openapiService/auth/getAccessToken/";
    /**
     * dispatch接口地址
     */
    public static final String DISPATCH_ADDRESS = "/openapiService/openApi/dispatch/";


    /**
     * 上传文件接口地址
     */
    public static final String UPLOAD_FILE_ADDRESS = "/aiService/api/upload/out/";
}
