package by.bsuir.karamach.model.figure;

import java.awt.*;
import java.io.Serializable;

public interface Printable extends Serializable {
    void print(Graphics2D graphics2D);
}
