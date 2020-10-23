package com.jhw.sistema.server.testing_spring;

import com.jhw.example.spring_a.rest.A_SpringA;
import com.jhw.module.util.mysql.services.MySQLHandler;
import javax.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication/*(scanBasePackages = {"com.jhw.example.spring_a.rest"})*/
@ComponentScan(basePackages = {
    A_SpringA.BASE_PACKAGE
})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        MySQLHandler.init();
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    @PreDestroy
    public void onExit() {//test para cerrar el servicio
        System.out.println("Cerrando Servicio");
        MySQLHandler.close();
    }
}
