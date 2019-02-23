package by.bsuir.karamach.app;

import by.bsuir.karamach.model.figure.Printable;
import by.bsuir.karamach.model.figure.basic.Point;
import by.bsuir.karamach.model.figure.impl.*;
import by.bsuir.karamach.model.figure.impl.Rectangle;
import by.bsuir.karamach.model.store.FigureArrayList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    private static FigureArrayList arrayList = new FigureArrayList();

    static {
        BrokenLine brokenLine = createBrokenLine();

        arrayList.save(brokenLine);

        Circle circle = new Circle(new Point(50, 50), 25);

        arrayList.save(circle);

        Ellipse ellipse = new Ellipse(new Point(100, 100), 25, 25);

        arrayList.save(ellipse);

        Line line = new Line(new Point(10, 10), new Point(150, 150));

        arrayList.save(line);

        Rectangle rectangle = new Rectangle(new Point(10, 10), new Point(380, 355));

        arrayList.save(rectangle);

        Square square = new Square(new Point(50, 50), new Point(150, 150));

        arrayList.save(square);
    }

    private static BrokenLine createBrokenLine() {
        BrokenLine brokenLine = new BrokenLine();
        List<Point> points = new ArrayList<>();

        points.add(new Point(10, 10));
        points.add(new Point(10, 50));
        points.add(new Point(50, 50));
        points.add(new Point(50, 100));
        points.add(new Point(100, 100));
        points.add(new Point(100, 150));
        points.add(new Point(150, 150));

        brokenLine.setPoints(points);
        return brokenLine;
    }


    private void doDrawing(Graphics g) {

        var g2d = (Graphics2D) g;


        g2d.setColor(Color.RED);

        for (Printable printable : arrayList.getAll()) {
            printable.print(g2d);
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }


}
