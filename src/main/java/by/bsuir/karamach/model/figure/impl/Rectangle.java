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

public class Rectangle extends Plugin implements Printable {
    private static final long serialVersionUID = 963919353861018595L;

    private Point leftTopCorner;
    private Point rightBotCorner;

    public Rectangle() {
    }

    public Rectangle(Point leftTopCorner, Point rightBotCorner) {
        this.leftTopCorner = leftTopCorner;
        this.rightBotCorner = rightBotCorner;
    }

    public Point getLeftTopCorner() {
        return leftTopCorner;
    }

    public void setLeftTopCorner(Point leftTopCorner) {
        this.leftTopCorner = leftTopCorner;
    }

    public Point getRightBotCorner() {
        return rightBotCorner;
    }

    public void setRightBotCorner(Point rightBotCorner) {
        this.rightBotCorner = rightBotCorner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(getLeftTopCorner(), rectangle.getLeftTopCorner()) &&
                Objects.equals(getRightBotCorner(), rectangle.getRightBotCorner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeftTopCorner(), getRightBotCorner());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Rectangle.class.getSimpleName() + "[", "]")
                .add("leftTopCorner=" + leftTopCorner)
                .add("rightBotCorner=" + rightBotCorner)
                .toString();
    }

    @Override
    public void print(Graphics2D graphics2D) {
        int width = Math.abs(leftTopCorner.getX() - rightBotCorner.getX());
        int height = Math.abs(rightBotCorner.getY() - leftTopCorner.getY());

        graphics2D.drawRect(leftTopCorner.getX(), leftTopCorner.getY(), width, height);
    }

    @Override
    public void renderOnFrame(Frame frameToPaint, JFrame frameToRender) {
        JButton rectangle = new JButton(getClass().getSimpleName());
        rectangle.setLayout(null);
        rectangle.setBounds(10, 10, 100, 50);

        rectangle.addActionListener(event -> {
            int amountOfCoordinatesExpected = 4;

            String message = JOptionPane.showInputDialog(
                    "Enter coordinates(" + amountOfCoordinatesExpected + ")");


            if ((message != null) && (!message.isEmpty())) {

                List<Integer> coordinates;
                try {
                    coordinates = UICoordinatesParser.parseToCoordinates(message, amountOfCoordinatesExpected);

                    Point point1 = new Point(coordinates.get(0), coordinates.get(1));
                    Point point2 = new Point(coordinates.get(2), coordinates.get(3));

                    frameToPaint.getDrawPanel().getFigureList().save(new Rectangle(point1, point2));
                    frameToPaint.repaint();
                } catch (ParserException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

        });
        frameToRender.getContentPane().add(rectangle);
    }
}
