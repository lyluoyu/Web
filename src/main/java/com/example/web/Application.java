package com.example.web;

import com.example.web.provider.GsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.example.web.service.AccountService;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Logger;

/**
 *
 */
public class Application extends ResourceConfig{
    public Application(){
        // 注册逻辑处理的包名
        //packages("com.example.web.service");
        packages(AccountService.class.getPackage().getName());

        // 注册Json解析器
        //register(JacksonJsonProvider.class);
        //替换解析器为Gson
        register(GsonProvider.class);

        // 注册日志打印输出
        register(Logger.class);

    }
}
