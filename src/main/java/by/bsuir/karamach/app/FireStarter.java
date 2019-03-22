package by.bsuir.karamach.app;

import by.bsuir.karamach.model.figure.basic.Point;
import by.bsuir.karamach.model.figure.impl.*;
import by.bsuir.karamach.util.ParserException;
import by.bsuir.karamach.util.UICoordinatesParser;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FireStarter {

    private static final String APP_NAME = "Graphic editor";
    private static final int MAIN_WINDOW_WIDTH = 400;
    private static final int MAIN_WINDOW_HEIGHT = 400;
    private static final String TOOLKIT_TITTLE = "Toolkits";
    private static final int TOOLKIT_WINDOW_WIDTH = 120;
    private static final int TOOLKIT_WINDOW_HEIGHT = 365;
    private static final String ENTER_COORDINATES_MESSAGE = "Enter coordinates ";
    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_WIDTH = 100;

    private static Frame frame;
    private static ToolkitPanel toolkitPanel;

    public static void main(String[] args) {

        InitGUI();
    }

    private static void InitGUI() {
        frame = new Frame(APP_NAME, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        toolkitPanel = new ToolkitPanel(TOOLKIT_TITTLE, TOOLKIT_WINDOW_WIDTH, TOOLKIT_WINDOW_HEIGHT);
        toolkitPanel.setLayout(null);

        addRectangleButton();

        addCircleButton();

        addEllipseButton();

        addSquareButton();

        addLineButton();

        addBrokenLineButton();


        toolkitPanel.repaint();

        frame.setVisible(true);

        toolkitPanel.setVisible(true);
    }

    private static void addBrokenLineButton() {
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

    private static void addLineButton() {
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

    private static void addSquareButton() {
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

    private static void addEllipseButton() {
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

    private static void addCircleButton() {
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

    private static void addRectangleButton() {
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
