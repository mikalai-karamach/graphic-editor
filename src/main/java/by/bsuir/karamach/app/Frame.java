package by.bsuir.karamach.app;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private static final String TITLE = "Graphic-editor";
    private static final int APP_WIDTH = 400;
    private static final int APP_HEIGHT = 400;


    public Frame() {
        initUI();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            var ex = new Frame();
            ex.setVisible(true);
        });

    }

    private void initUI() {

        var DrawPanel = new DrawPanel();
        add(DrawPanel);

        setTitle(TITLE);
        setSize(APP_WIDTH, APP_HEIGHT);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
