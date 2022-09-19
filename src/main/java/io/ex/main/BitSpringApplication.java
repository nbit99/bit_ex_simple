package io.ex.main;

import io.bit.encrypt.config.CommonConfig;
import io.ex.notice.config.ApiCallbackConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.Console;
import java.util.Scanner;

public class BitSpringApplication extends SpringApplication {

    public BitSpringApplication(Class<?>... primarySources) {
        super(primarySources);
    }

    /**
     * 初始化配置文件
     */
    private static void initConfig() throws Exception {
        System.out.print("请输入密码：");
        String password = CommonConfig.getPassword();
        //System.out.println("输入的密码为：" + password);
        ApiCallbackConfig.init(password);
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
