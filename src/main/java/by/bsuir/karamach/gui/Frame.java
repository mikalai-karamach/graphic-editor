package by.bsuir.karamach.gui;

import javax.swing.*;

public class Frame extends JFrame {

    private DrawPanel drawPanel;


    public Frame(String title, int width, int height) {

        setTitle(title);
        setSize(width, height);

        initUI();
    }


    private void initUI() {

        var DrawPanel = new DrawPanel();
        add(DrawPanel);

        this.drawPanel = DrawPanel;

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public DrawPanel getDrawPanel() {
        return drawPanel;
    }
}
