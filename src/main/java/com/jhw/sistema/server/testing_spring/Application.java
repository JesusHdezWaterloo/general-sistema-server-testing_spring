package com.jhw.sistema.server.testing_spring;

import com.jhw.module.admin.kanban.rest.A_ModuleAdminKanban;
import com.jhw.module.gestion.contabilidad.rest.A_ModuleGestionContabilidadEmpresarial;
import com.jhw.module.gestion.gastos.rest.A_ModuleGestionGastos;
import com.jhw.module.util.mysql.services.MySQLHandler;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication/*(scanBasePackages = {"com.jhw.example.spring_a.rest"})*/
@ComponentScan(basePackages = {
    A_ModuleGestionContabilidadEmpresarial.BASE_PACKAGE,
    A_ModuleGestionGastos.BASE_PACKAGE,
    A_ModuleAdminKanban.BASE_PACKAGE
})
@RestController
@RequestMapping(value = "/admin")
public class Application extends SpringBootServletInitializer {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    /**
     * Cierra el servicio, sin seguridad pero bueno
     */
    @GetMapping("/close")
    @CrossOrigin(origins = {"http://127.0.0.1:8080"})//NOT WORKING
    public void close() {
        System.out.println("Cerrando App");
        SpringApplication.exit(context);
        System.out.println("Cerrado contexto");

        System.out.println("Cerrando System(0)");
        System.exit(0);
    }

    @PostConstruct
    public void onCreate() {
        System.out.println("onCreate");
        MySQLHandler.init();
        System.out.println("onCreate finalizado");
    }

    @PreDestroy
    public void onExit() {
        System.out.println("onExit");
        MySQLHandler.close();
        System.out.println("onExit finalizado");
    }

}
