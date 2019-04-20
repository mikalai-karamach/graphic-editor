package by.bsuir.karamach.app;

import by.bsuir.karamach.gui.Frame;
import by.bsuir.karamach.gui.ToolkitPanel;
import by.bsuir.karamach.model.figure.Plugin;
import by.bsuir.karamach.util.BsonWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
                    frame.getDrawPanel().setArrayList(bsonWorker.deserializeFromFile(message));
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

                frame.repaint();
                JOptionPane.showMessageDialog(null, "Success");

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
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

                JOptionPane.showMessageDialog(null, "Success");
            } else {
                JOptionPane.showMessageDialog(null, "File name can't be empty or null");
            }

        });
        toolkitPanel.getContentPane().add(serializeButton);
    }

}
