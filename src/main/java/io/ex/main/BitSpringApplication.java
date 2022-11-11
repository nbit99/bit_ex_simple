package io.ex.main;

import io.bit.encrypt.config.CommonConfig;
import io.ex.notice.config.ApiCallbackConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.Console;
import java.io.InputStream;
import java.util.Scanner;

public class BitSpringApplication extends SpringApplication {

    public BitSpringApplication(Class<?>... primarySources) {
        super(primarySources);
    }

    /**
     * 初始化配置文件
     */
    private static void initConfig() throws Exception {
        String filePath = "api.properties";
        InputStream inputStream = CommonConfig.getInputStream(filePath);
        String password = null;

        if(inputStream == null){
            //先读取环境变量的密码
            String CONFIG_DECRYPT_PWD = System.getenv("CONFIG_DECRYPT_PWD");
            if(!StringUtils.isEmpty(CONFIG_DECRYPT_PWD)){
                password = CONFIG_DECRYPT_PWD;
            }else{//控制台输入密码
                password = CommonConfig.getPassword();
            }
        }
        //System.out.println("输入的密码为：" + password);
        ApiCallbackConfig.init(filePath, password);
    }

    @Override
    public ConfigurableApplicationContext run(String... args) {
        try {
            initConfig();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return super.run(args);
    }

    public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
        return run(new Class[]{primarySource}, args);
    }

    public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
        return (new BitSpringApplication(primarySources)).run(args);
    }

}
