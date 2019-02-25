package com.guli.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Create with IntelliJ IDEA。
 * User : Lyhang
 * Data : 2019-02-23
 * Time : 16:47
 **/

@SpringBootApplication
@ComponentScan(basePackages={"com.guli.edu","com.guli.common"})
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }

}
