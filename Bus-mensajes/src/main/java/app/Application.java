package app;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class Application implements WebApplicationInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        //Primeras pruebas con POST
//        HttpConfig peticiones = new HttpConfig();
//        NameValuePair parametro = new BasicNameValuePair("nombre", "Enviado desde Bus2");
//        NameValuePair parametro2 = new BasicNameValuePair("apellido", "Enviado desde Bus");
//        System.out.println(peticiones.httpPostSimple("http://localhost:8070/consultorio_1/medico/add", parametro, parametro2));
//         //Primeras pruebas con GET
//        System.out.println(peticiones.httpGetSimple("http://localhost:8070/consultorio_1/medico/"));
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Create root application context
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebSocketConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("dispatcher", dispatcherServlet);

        servletRegistration.setAsyncSupported(true);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");

        //servletContext.addFilter("corsFilter", CorsFilter.class);
    }
}
