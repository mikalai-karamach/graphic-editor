package by.bsuir.karamach.model.figure.impl;

import by.bsuir.karamach.gui.Frame;
import by.bsuir.karamach.model.figure.Plugin;
import by.bsuir.karamach.model.figure.Printable;
import by.bsuir.karamach.model.figure.basic.Point;
import by.bsuir.karamach.util.ParserException;
import by.bsuir.karamach.util.UICoordinatesParser;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Circle extends Plugin implements Printable {
    private static final long serialVersionUID = 5040939918749439071L;

    private Point center;
    private int radius;

    public Circle() {
    }

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Circle circle = (Circle) o;
        return Double.compare(circle.getRadius(), getRadius()) == 0 &&
                Objects.equals(getCenter(), circle.getCenter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCenter(), getRadius());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Circle.class.getSimpleName() + "[", "]")
                .add("center=" + center)
                .add("radius=" + radius)
                .toString();
    }

    @Override
    public void print(Graphics2D graphics2D) {
        graphics2D.drawOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
    }

    @Override
    public void renderOnFrame(Frame frameToPaint, JFrame frameToRender) {
        JButton circle = new JButton(getClass().getSimpleName());
        circle.setBounds(10, 65, 100, 50);
        circle.setLayout(null);

        circle.addActionListener(event -> {
            int amountOfCoordinatesExpected = 3;


            String message = JOptionPane.showInputDialog(
                    "Enter coordinates (" + amountOfCoordinatesExpected + ")");

            if ((message != null) && (!message.isEmpty())) {

                List<Integer> coordinates;
                try {
                    coordinates = UICoordinatesParser.parseToCoordinates(message, amountOfCoordinatesExpected);

                    Point point1 = new Point(coordinates.get(0), coordinates.get(1));

                    frameToPaint.getDrawPanel().getFigureList().save(new Circle(point1, coordinates.get(2)));
                    frameToPaint.repaint();
                } catch (ParserException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

        });

        frameToRender.getContentPane().add(circle);
    }
}
