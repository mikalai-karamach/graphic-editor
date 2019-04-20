package by.bsuir.karamach.model.figure;

import by.bsuir.karamach.gui.Frame;

import javax.swing.*;

public abstract class Plugin {
    public abstract void renderOnFrame(Frame frameToPaint, JFrame frameToRender);
}
