package by.bsuir.karamach.model.figure.impl;

import by.bsuir.karamach.gui.Frame;
import by.bsuir.karamach.model.figure.Plugin;
import by.bsuir.karamach.model.figure.Printable;
import by.bsuir.karamach.model.figure.basic.Point;
import by.bsuir.karamach.util.ParserException;
import by.bsuir.karamach.util.UICoordinatesParser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class BrokenLine extends Plugin implements Printable {
    private static final long serialVersionUID = 2624108269281147639L;

    private List<Point> points;

    public BrokenLine() {
    }

    public BrokenLine(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BrokenLine that = (BrokenLine) o;
        return Objects.equals(getPoints(), that.getPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPoints());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BrokenLine.class.getSimpleName() + "[", "]")
                .add("points=" + points)
                .toString();
    }

    @Override
    public void print(Graphics2D graphics2D) {
        for (int i = 0; i < points.size() - 1; i++) {
            Point pointFrom = points.get(i);
            Point pointTo = points.get(i + 1);

            graphics2D.drawLine(pointFrom.getX(), pointFrom.getY(), pointTo.getX(), pointTo.getY());

        }

    }

    @Override
    public void renderOnFrame(Frame frameToPaint, JFrame frameToRender) {
        JButton brokenLine = new JButton(getClass().getSimpleName());
        brokenLine.setBounds(10, 285, 100, 50);
        brokenLine.setLayout(null);

        brokenLine.addActionListener(event -> {
            String message = JOptionPane.showInputDialog("Enter coordinates  ( even number )");

            if ((message != null) && (!message.isEmpty())) {

                List<Integer> coordinates;
                try {
                    coordinates = UICoordinatesParser.parseToCoordinates(message);

                    List<Point> points = new ArrayList<>();

                    for (int i = 0; i < coordinates.size(); i += 2) {
                        Point point = new Point(coordinates.get(i), coordinates.get(i + 1));
                        points.add(point);
                    }

                    frameToPaint.getDrawPanel().getFigureList().save(new BrokenLine(points));
                    frameToPaint.repaint();

                } catch (ParserException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

        });

        frameToRender.getContentPane().add(brokenLine);
    }
}
