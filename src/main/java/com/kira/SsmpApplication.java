package com.kira;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
@Slf4j
@EnableCaching
public class SsmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsmpApplication.class, args);
        log.info("项目启动成功");
    }

}
