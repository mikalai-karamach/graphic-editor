package by.bsuir.karamach.app;

import by.bsuir.karamach.gui.Frame;
import by.bsuir.karamach.gui.ToolkitPanel;
import by.bsuir.karamach.model.figure.Plugin;
import by.bsuir.karamach.model.store.FigureList;
import by.bsuir.karamach.util.BsonWorker;
import by.bsuir.karamach.util.FigureClassesReader;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.NoSuchFileException;
import java.util.List;

@Component
public class Initializer {

    @Value("${app.title}")
    private String APP_NAME;

    @Value("${app.window.width}")
    private int MAIN_WINDOW_WIDTH;

    @Value("${app.window.height}")
    private int MAIN_WINDOW_HEIGHT;

    @Value("${toolkit.title}")
    private String TOOLKIT_TITTLE;

    @Value("${toolkit.window.width}")
    private int TOOLKIT_WINDOW_WIDTH;

    @Value("${toolkit.window.height}")
    private int TOOLKIT_WINDOW_HEIGHT;

    @Value("${enter.coordinates.message}")
    private String ENTER_COORDINATES_MESSAGE;

    @Value("${button.default.height}")
    private int BUTTON_HEIGHT;

    @Value("${button.default.width}")
    private int BUTTON_WIDTH;

    @Value("${enter.file.name.message}")
    private String ENTER_FILE_NAME;

    @Value("${enter.figure.name.message}")
    private String ENTER_FIGURE_NAME;

    private final BsonWorker bsonWorker;

    private Frame frame;
    private ToolkitPanel toolkitPanel;


    @Autowired
    public Initializer(BsonWorker bsonWorker) {
        this.bsonWorker = bsonWorker;
    }


    public void initGUI(List<Class> figureClasses) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        frame = new Frame(APP_NAME, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        toolkitPanel = new ToolkitPanel(TOOLKIT_TITTLE, TOOLKIT_WINDOW_WIDTH, TOOLKIT_WINDOW_HEIGHT);
        toolkitPanel.setLayout(null);

        addComponents(figureClasses);

        toolkitPanel.repaint();

        frame.setVisible(true);

        toolkitPanel.setVisible(true);
    }

    private void addComponents(List<Class> figureClasses) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        for (Class figureClass : figureClasses) {

            Constructor constructor = figureClass.getConstructor();
            Object instance = constructor.newInstance();

            if (instance instanceof Plugin) {
                figureClass.getMethod("renderOnFrame", Frame.class, JFrame.class).invoke(instance, frame, toolkitPanel);
            }
        }

        addSerializeButton();

        addDeserializeButton();

        addClearAllButton();

        addSaveFigureButton();

        JButton loadUserClass = new JButton("load figure");
        loadUserClass.setBounds(150, 285, BUTTON_WIDTH + 50, BUTTON_HEIGHT);
        loadUserClass.setLayout(null);

        loadUserClass.addActionListener(event -> {
            JOptionPane.showMessageDialog(null, FigureClassesReader.readAllFigures("data/classes/"));

            String message = JOptionPane.showInputDialog(ENTER_FIGURE_NAME);

            if ((message != null) && (!message.isEmpty())) {
                try {
                    FigureList arrayList = bsonWorker.deserializeFromFile("/classes/" + message);


                    frame.getDrawPanel().setArrayList(arrayList);
                    JOptionPane.showMessageDialog(null, "Success");

                } catch (InvalidTypeIdException e) {
                    JOptionPane.showMessageDialog(null, "Unable to find required plugins." +
                            "\nPlease check the connected plugins.");
                } catch (NoSuchFileException e) {
                    JOptionPane.showMessageDialog(null, "No such file: " + e.getMessage());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

                frame.repaint();

            } else {
                JOptionPane.showMessageDialog(null, "File name can't be empty or null");
            }


        });
        toolkitPanel.getContentPane().add(loadUserClass);
    }

    private void addSaveFigureButton() {
        JButton saveAsUserClass = new JButton("Save figure");
        saveAsUserClass.setBounds(150, 230, BUTTON_WIDTH + 50, BUTTON_HEIGHT);
        saveAsUserClass.setLayout(null);

        saveAsUserClass.addActionListener(event -> {
            String message = JOptionPane.showInputDialog(ENTER_FIGURE_NAME);

            if ((message != null) && (!message.isEmpty())) {
                try {

                    bsonWorker.serializeToFile(frame.getDrawPanel().getFigureList(), "classes/" + message);
                    JOptionPane.showMessageDialog(null, "Success");

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }


            } else {
                JOptionPane.showMessageDialog(null, "File name can't be empty or null");
            }

        });

        toolkitPanel.getContentPane().add(saveAsUserClass);
    }

    private void addClearAllButton() {
        JButton clearAll = new JButton("Clear All");
        clearAll.setBounds(150, 65, BUTTON_WIDTH + 50, BUTTON_HEIGHT);
        clearAll.setLayout(null);

        clearAll.addActionListener(event -> {
            frame.getDrawPanel().getFigureList().clear();
            frame.getDrawPanel().repaint();
        });
        toolkitPanel.getContentPane().add(clearAll);
    }

    private void addDeserializeButton() {
        JButton deserializeButton = new JButton("DeSerialize from file");
        deserializeButton.setBounds(150, 120, BUTTON_WIDTH + 50, BUTTON_HEIGHT);
        deserializeButton.setLayout(null);

        deserializeButton.addActionListener(event -> {
            String message = JOptionPane.showInputDialog(ENTER_FILE_NAME);

            if ((message != null) && (!message.isEmpty())) {
                try {
                    FigureList arrayList = bsonWorker.deserializeFromFile(message);


                    frame.getDrawPanel().setArrayList(arrayList);
                    JOptionPane.showMessageDialog(null, "Success");

                } catch (InvalidTypeIdException e) {
                    JOptionPane.showMessageDialog(null, "Unable to find required plugins." +
                            "\nPlease check the connected plugins.");
                } catch (NoSuchFileException e) {
                    JOptionPane.showMessageDialog(null, "No such file: " + e.getMessage());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

                frame.repaint();

            } else {
                JOptionPane.showMessageDialog(null, "File name can't be empty or null");
            }


        });
        toolkitPanel.getContentPane().add(deserializeButton);
    }

    private void addSerializeButton() {
        JButton serializeButton = new JButton("Serialize to file");
        serializeButton.setBounds(150, 175, BUTTON_WIDTH + 50, BUTTON_HEIGHT);
        serializeButton.setLayout(null);

        serializeButton.addActionListener(event -> {
            String message = JOptionPane.showInputDialog(ENTER_FILE_NAME);

            if ((message != null) && (!message.isEmpty())) {
                try {

                    bsonWorker.serializeToFile(frame.getDrawPanel().getFigureList(), message);
                    JOptionPane.showMessageDialog(null, "Success");

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }


            } else {
                JOptionPane.showMessageDialog(null, "File name can't be empty or null");
            }

        });
        toolkitPanel.getContentPane().add(serializeButton);
    }

}
