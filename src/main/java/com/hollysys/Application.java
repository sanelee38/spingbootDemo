package com.hollysys;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author lizhi186545
 */

//@MapperScan("com.hollysys.smartfactory.projectdemo.mapper")
@EnableCaching
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);

//        SpringApplication application = new SpringApplication(Application.class);
//        ConfigurableApplicationContext context = application.run(args);
//        //发布事件
//        context.publishEvent(new MyApplicationEvent(new Object()));
////        context.close();
    }
}
