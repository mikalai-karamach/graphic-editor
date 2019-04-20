package by.bsuir.karamach.app.loader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class PluginLoader extends ClassLoader {

    private static final Logger logger = LogManager.getLogger(PluginLoader.class);
    private static final int BEGIN_INDEX = 0;

    @Value("${plugins.class.files.extension}")
    private final String CLASS_EXTENSION = ".class";
    @Value("${plugins.package.name}")
    private String packageName;
    @Value("${plugins.path.to.files}")
    private String pathToFiles;


    public PluginLoader() {
        super(ClassLoader.getSystemClassLoader());
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        byte[] filePayload;

        try {

            Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(pathToFiles)).getFile() + "/" + className + CLASS_EXTENSION);

            filePayload = Files.readAllBytes(path);

        } catch (IOException e) {

            logger.error(e);
            throw new ClassNotFoundException("Sorry, can't find class", e);

        }

        return defineClass(packageName + '.' + className, filePayload, BEGIN_INDEX, filePayload.length);
    }

    public List<Class> loadPlugins() throws LoaderException {

        File dir = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(pathToFiles)).getFile());
        String[] modules = dir.list();

        if (modules == null) {
            throw new LoaderException("Unable to find files in directory!");
        }

        List<Class> loadedClasses = new ArrayList<>();

        try {
            for (String module : modules) {

                if (module.contains(CLASS_EXTENSION)) {

                    String moduleName = module.split("\\" + CLASS_EXTENSION)[0];
                    Class loadedClass = this.loadClass(moduleName);

                    if (loadedClass != null) {
                        loadedClasses.add(loadedClass);
                    }
                }
            }

        } catch (ClassNotFoundException e) {

            logger.error(e);

            throw new LoaderException("Unable to load class, due to:", e);
        }

        return loadedClasses;
    }
}
