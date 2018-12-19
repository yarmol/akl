package com.gtm.lukspay.devices;

//import com.gtm.lukspay.security.annotation.EnableLukspayTokenSecurity;
import com.gtm.lukspay.swagger.EnableLuksPaySwagger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {"com.gtm.lukspay",
        "com.gtm.lukspay.devices.repository",
        "com.gtm.lukspay.devices.controller"})
@EnableLuksPaySwagger
//@EnableEurekaClient
@Slf4j
//@EnableLukspayTokenSecurity
public class DevicesApplication {



    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DevicesApplication.class);
        Environment env = run.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "Swagger: \thttp://127.0.0.1:{}/swagger-ui.html\n\t" +
                        "External: \thttp://{}:{}\n\t" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port")+env.getProperty("server.servlet.contextPath"),
                env.getProperty("server.port")+env.getProperty("server.servlet.contextPath"),
                //InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port")+env.getProperty("server.servlet.contextPath"));
        log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
    }
}
