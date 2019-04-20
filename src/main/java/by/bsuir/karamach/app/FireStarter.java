package by.bsuir.karamach.app;

import by.bsuir.karamach.app.loader.LoaderException;
import by.bsuir.karamach.app.loader.PluginLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class FireStarter {
    public static void main(String[] args) throws LoaderException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);

        PluginLoader pluginLoader = context.getBean(PluginLoader.class);

        List<Class> plugins = pluginLoader.loadPlugins();

        plugins.remove(plugins.get(0));

        Initializer initializer = context.getBean(Initializer.class);
        initializer.initGUI(plugins);
    }
}
