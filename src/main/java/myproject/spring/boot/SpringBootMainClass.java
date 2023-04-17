package myproject.spring.boot;

/**
 * Created by psingh on 3/10/2017.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;




@SpringBootApplication
public class SpringBootMainClass {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringBootMainClass.class, args);
    }
}