package io.ex.notice.config;

import io.bit.encrypt.config.CommonConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ApiCallbackConfig {
    public static String callKey = "";
    public static String sha256Passphrase = "";
    public static String publicKey = "";
    //IP白名单
    public static final List<String> WHITE_IPS = new ArrayList<>();//Arrays.asList("127.0.0.1", "192.168.132.13");

    public static void init(String encryptPassword) throws Exception {
        Properties api_properties = CommonConfig.getProperties("api.properties", encryptPassword);

        callKey = api_properties.getProperty("callKey");
        sha256Passphrase = api_properties.getProperty("sha256Passphrase");
        publicKey = api_properties.getProperty("publicKey");
        //IP白名单
        String whiteIps = api_properties.getProperty("whiteIps");
        if(whiteIps != null && whiteIps.length() > 0){
            String[] ips = whiteIps.split(",");
            if(ips != null && ips.length > 0){
                for(String ip : ips){
                    WHITE_IPS.add(ip);
                }
            }
        }
    }
}
