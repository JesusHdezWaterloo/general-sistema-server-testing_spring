package com.jhw.sistema.server.testing_spring;

import com.jhw.module.gestion.contabilidad.rest.A_ModuleGestionContabilidadEmpresarial;
import com.jhw.module.util.mysql.services.MySQLHandler;
import com.jhw.utils.jackson.serializer_deserializer.local_date.LocalDateJsonDeserializer;
import com.jhw.utils.jackson.serializer_deserializer.local_date.LocalDateJsonSerializer;
import java.time.LocalDate;
import javax.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication/*(scanBasePackages = {"com.jhw.example.spring_a.rest"})*/
@ComponentScan(basePackages = {
    A_ModuleGestionContabilidadEmpresarial.BASE_PACKAGE
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
