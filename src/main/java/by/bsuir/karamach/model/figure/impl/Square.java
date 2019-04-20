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

public class Square extends Plugin implements Printable {
    private static final long serialVersionUID = -1248098980465687912L;

    private Point leftTopPoint;
    private Point rightBotPoint;

    public Square() {
    }

    public Square(Point leftTopPoint, Point rightBotPoint) {
        this.leftTopPoint = leftTopPoint;
        this.rightBotPoint = rightBotPoint;
    }

    public Point getLeftTopPoint() {
        return leftTopPoint;
    }

    public void setLeftTopPoint(Point leftTopPoint) {
        this.leftTopPoint = leftTopPoint;
    }

    public Point getRightBotPoint() {
        return rightBotPoint;
    }

    public void setRightBotPoint(Point rightBotPoint) {
        this.rightBotPoint = rightBotPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Square square = (Square) o;
        return Objects.equals(getLeftTopPoint(), square.getLeftTopPoint()) &&
                Objects.equals(getRightBotPoint(), square.getRightBotPoint());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeftTopPoint(), getRightBotPoint());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Square.class.getSimpleName() + "[", "]")
                .add("leftTopPoint=" + leftTopPoint)
                .add("rightBotPoint=" + rightBotPoint)
                .toString();
    }

    @Override
    public void print(Graphics2D graphics2D) {
        int width = Math.abs(leftTopPoint.getX() - rightBotPoint.getX());
        int height = Math.abs(rightBotPoint.getY() - leftTopPoint.getY());

        graphics2D.drawRect(leftTopPoint.getX(), leftTopPoint.getY(), width, height);
    }

    @Override
    public void renderOnFrame(Frame frameToPaint, JFrame frameToRender) {
        JButton square = new JButton(getClass().getSimpleName());
        square.setBounds(10, 175, 100, 50);
        square.setLayout(null);

        square.addActionListener(event -> {
            int amountOfCoordinatesExpected = 4;


            String message = JOptionPane.showInputDialog(
                    "Enter coordinates(" + amountOfCoordinatesExpected + ")");

            if ((message != null) && (!message.isEmpty())) {

                List<Integer> coordinates;
                try {
                    coordinates = UICoordinatesParser.parseToCoordinates(message, amountOfCoordinatesExpected);

                    Point point1 = new Point(coordinates.get(0), coordinates.get(1));
                    Point point2 = new Point(coordinates.get(2), coordinates.get(3));

                    frameToPaint.getDrawPanel().getFigureList().save(new Square(point1, point2));
                    frameToPaint.repaint();
                } catch (ParserException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

        });
        frameToRender.getContentPane().add(square);
    }
}
