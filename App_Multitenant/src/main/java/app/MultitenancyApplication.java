package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import app.config.MultitenancyProperties;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//Necesario para que se utilice la configuracion MultiTenant de JPA
@EnableConfigurationProperties(MultitenancyProperties.class)
public class MultitenancyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultitenancyApplication.class, args);
    }
}
