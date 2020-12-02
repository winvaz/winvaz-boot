package com.icore.winvaz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Spring Boot官方推荐启动类在root packages包下(所有包的顶级包)
 * @author wdq
 */
@EnableScheduling
@SpringBootApplication
public class WinvazBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(WinvazBootApplication.class, args);
    }

}
