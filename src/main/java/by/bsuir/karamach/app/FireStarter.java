package by.bsuir.karamach.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FireStarter {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);

        Initializer initializer = context.getBean(Initializer.class);
        initializer.initGUI();
    }
}
