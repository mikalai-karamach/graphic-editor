package by.bsuir.karamach.app;

import javax.swing.*;
import java.awt.*;

public class ToolkitPanel extends JFrame {
    public ToolkitPanel(String title, int width, int height) throws HeadlessException {
        super();

        this.setSize(width, height);
        this.setTitle(title);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
