package by.bsuir.karamach.app;

import by.bsuir.karamach.gui.Frame;
import by.bsuir.karamach.gui.ToolkitPanel;
import by.bsuir.karamach.model.figure.basic.Point;
import by.bsuir.karamach.model.figure.impl.*;
import by.bsuir.karamach.util.BsonWorker;
import by.bsuir.karamach.util.ParserException;
import by.bsuir.karamach.util.UICoordinatesParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
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

    private final BsonWorker bsonWorker;

    @Value("${button.default.height}")
    private int BUTTON_HEIGHT;

    @Value("${button.default.width}")
    private int BUTTON_WIDTH;


    private Frame frame;
    private ToolkitPanel toolkitPanel;
    @Value("${enter.file.name.message}")
    private String ENTER_FILE_NAME;

    @Autowired
    public Initializer(BsonWorker bsonWorker) {
        this.bsonWorker = bsonWorker;
    }


    public void initGUI() {
        frame = new Frame(APP_NAME, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        toolkitPanel = new ToolkitPanel(TOOLKIT_TITTLE, TOOLKIT_WINDOW_WIDTH, TOOLKIT_WINDOW_HEIGHT);
        toolkitPanel.setLayout(null);

        addComponents();

        toolkitPanel.repaint();

        frame.setVisible(true);

        toolkitPanel.setVisible(true);
    }

    private void addComponents() {
        addRectangleButton();

        addCircleButton();

        addEllipseButton();

        addSquareButton();

        addLineButton();

        addBrokenLineButton();

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

    private void addBrokenLineButton() {
        JButton brokenLine = new JButton("Broken line");
        brokenLine.setBounds(10, 285, BUTTON_WIDTH, BUTTON_HEIGHT);
        brokenLine.setLayout(null);

        brokenLine.addActionListener(event -> {
            String message = JOptionPane.showInputDialog(
                    ENTER_COORDINATES_MESSAGE + "( even number )");

            if ((message != null) && (!message.isEmpty())) {

                List<Integer> coordinates;
                try {
                    coordinates = UICoordinatesParser.parseToCoordinates(message);

                    List<Point> points = new ArrayList<>();

                    for (int i = 0; i < coordinates.size(); i += 2) {
                        Point point = new Point(coordinates.get(i), coordinates.get(i + 1));
                        points.add(point);
                    }

                    frame.getDrawPanel().getFigureList().save(new BrokenLine(points));
                    frame.repaint();
                } catch (ParserException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

        });
        toolkitPanel.getContentPane().add(brokenLine);
    }

    private void addLineButton() {
        JButton line = new JButton("Line");
        line.setBounds(10, 230, BUTTON_WIDTH, BUTTON_HEIGHT);
        line.setLayout(null);

        line.addActionListener(event -> {
            int amountOfCoordinatesExpected = 4;


            String message = JOptionPane.showInputDialog(
                    ENTER_COORDINATES_MESSAGE + "(" + amountOfCoordinatesExpected + ")");

            if ((message != null) && (!message.isEmpty())) {

                List<Integer> coordinates;
                try {
                    coordinates = UICoordinatesParser.parseToCoordinates(message, amountOfCoordinatesExpected);

                    Point point1 = new Point(coordinates.get(0), coordinates.get(1));
                    Point point2 = new Point(coordinates.get(2), coordinates.get(3));

                    frame.getDrawPanel().getFigureList().save(new Line(point1, point2));
                    frame.repaint();
                } catch (ParserException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

        });
        toolkitPanel.getContentPane().add(line);
    }

    private void addSquareButton() {
        JButton square = new JButton("Square");
        square.setBounds(10, 175, BUTTON_WIDTH, BUTTON_HEIGHT);
        square.setLayout(null);

        square.addActionListener(event -> {
            int amountOfCoordinatesExpected = 4;


            String message = JOptionPane.showInputDialog(
                    ENTER_COORDINATES_MESSAGE + "(" + amountOfCoordinatesExpected + ")");

            if ((message != null) && (!message.isEmpty())) {

                List<Integer> coordinates;
                try {
                    coordinates = UICoordinatesParser.parseToCoordinates(message, amountOfCoordinatesExpected);

                    Point point1 = new Point(coordinates.get(0), coordinates.get(1));
                    Point point2 = new Point(coordinates.get(2), coordinates.get(3));

                    frame.getDrawPanel().getFigureList().save(new Square(point1, point2));
                    frame.repaint();
                } catch (ParserException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

        });
        toolkitPanel.getContentPane().add(square);
    }

    private void addEllipseButton() {
        JButton ellipse = new JButton("Ellipse");
        ellipse.setBounds(10, 120, BUTTON_WIDTH, BUTTON_HEIGHT);
        ellipse.setLayout(null);

        ellipse.addActionListener(event -> {
            int amountOfCoordinatesExpected = 4;


            String message = JOptionPane.showInputDialog(
                    ENTER_COORDINATES_MESSAGE + "(" + amountOfCoordinatesExpected + ")");

            if ((message != null) && (!message.isEmpty())) {

                List<Integer> coordinates;
                try {
                    coordinates = UICoordinatesParser.parseToCoordinates(message, amountOfCoordinatesExpected);

                    Point point1 = new Point(coordinates.get(0), coordinates.get(1));

                    frame.getDrawPanel().getFigureList().save(new Ellipse(point1, coordinates.get(2), coordinates.get(3)));
                    frame.repaint();
                } catch (ParserException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

        });
        toolkitPanel.getContentPane().add(ellipse);
    }

    private void addCircleButton() {
        JButton circle = new JButton("Circle");
        circle.setBounds(10, 65, BUTTON_WIDTH, BUTTON_HEIGHT);
        circle.setLayout(null);

        circle.addActionListener(event -> {
            int amountOfCoordinatesExpected = 3;


            String message = JOptionPane.showInputDialog(
                    ENTER_COORDINATES_MESSAGE + "(" + amountOfCoordinatesExpected + ")");

            if ((message != null) && (!message.isEmpty())) {

                List<Integer> coordinates;
                try {
                    coordinates = UICoordinatesParser.parseToCoordinates(message, amountOfCoordinatesExpected);

                    Point point1 = new Point(coordinates.get(0), coordinates.get(1));

                    frame.getDrawPanel().getFigureList().save(new Circle(point1, coordinates.get(2)));
                    frame.repaint();
                } catch (ParserException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

        });
        toolkitPanel.getContentPane().add(circle);

    }

    private void addRectangleButton() {
        JButton rectangle = new JButton("Rectangle");
        rectangle.setLayout(null);
        rectangle.setBounds(10, 10, BUTTON_WIDTH, BUTTON_HEIGHT);

        rectangle.addActionListener(event -> {
            int amountOfCoordinatesExpected = 4;

            String message = JOptionPane.showInputDialog(
                    ENTER_COORDINATES_MESSAGE + "(" + amountOfCoordinatesExpected + ")");


            if ((message != null) && (!message.isEmpty())) {

                List<Integer> coordinates;
                try {
                    coordinates = UICoordinatesParser.parseToCoordinates(message, amountOfCoordinatesExpected);

                    Point point1 = new Point(coordinates.get(0), coordinates.get(1));
                    Point point2 = new Point(coordinates.get(2), coordinates.get(3));

                    frame.getDrawPanel().getFigureList().save(new Rectangle(point1, point2));
                    frame.repaint();
                } catch (ParserException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

        });
        toolkitPanel.getContentPane().add(rectangle);
    }
}
