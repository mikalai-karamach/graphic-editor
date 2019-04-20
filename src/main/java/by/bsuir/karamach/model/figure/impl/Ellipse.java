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

public class Ellipse extends Plugin implements Printable {
    private static final long serialVersionUID = 4676735044770176830L;

    private Point center;
    private int width;
    private int height;

    public Ellipse() {
    }

    public Ellipse(Point center, int width, int height) {
        this.center = center;
        this.width = width;
        this.height = height;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Ellipse ellipse = (Ellipse) o;
        return getWidth() == ellipse.getWidth() &&
                getHeight() == ellipse.getHeight() &&
                Objects.equals(getCenter(), ellipse.getCenter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCenter(), getWidth(), getHeight());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Ellipse.class.getSimpleName() + "[", "]")
                .add("center=" + center)
                .add("width=" + width)
                .add("height=" + height)
                .toString();
    }

    @Override
    public void print(Graphics2D graphics2D) {
        graphics2D.drawOval(center.getX() - width, center.getY() - height, width * 2, height * 2);
    }

    @Override
    public void renderOnFrame(Frame frameToPaint, JFrame frameToRender) {
        JButton ellipse = new JButton(getClass().getSimpleName());
        ellipse.setBounds(10, 120, 100, 50);
        ellipse.setLayout(null);

        ellipse.addActionListener(event -> {
            int amountOfCoordinatesExpected = 4;


            String message = JOptionPane.showInputDialog(
                    "Enter coordinates (" + amountOfCoordinatesExpected + ")");

            if ((message != null) && (!message.isEmpty())) {

                List<Integer> coordinates;
                try {
                    coordinates = UICoordinatesParser.parseToCoordinates(message, amountOfCoordinatesExpected);

                    Point point1 = new Point(coordinates.get(0), coordinates.get(1));

                    frameToPaint.getDrawPanel().getFigureList().save(new Ellipse(point1, coordinates.get(2), coordinates.get(3)));
                    frameToPaint.repaint();
                } catch (ParserException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

        });
        frameToRender.getContentPane().add(ellipse);
    }
}
