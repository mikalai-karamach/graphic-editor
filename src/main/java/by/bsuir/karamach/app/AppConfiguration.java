package by.bsuir.karamach.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:gui.properties")
@PropertySource("classpath:application.properties")
@ComponentScan("by.bsuir.karamach")
public class AppConfiguration {

}
