package com.jhw.sistema.server.testing_spring;

import com.jhw.module.gestion.contabilidad.rest.A_ModuleGestionContabilidadEmpresarial;
import com.jhw.module.gestion.gastos.rest.A_ModuleGestionGastos;
import com.jhw.module.util.mysql.services.MySQLHandler;
import javax.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication/*(scanBasePackages = {"com.jhw.example.spring_a.rest"})*/
@ComponentScan(basePackages = {
    A_ModuleGestionContabilidadEmpresarial.BASE_PACKAGE,
    A_ModuleGestionGastos.BASE_PACKAGE
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
/*
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.deserializerByType(LocalDate.class, new LocalDateJsonDeserializer());
        builder.serializerByType(LocalDate.class, new LocalDateJsonSerializer());
        return builder;
    }*/
}
