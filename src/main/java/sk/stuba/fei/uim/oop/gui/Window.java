package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.Logic;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public static final int WINDOW_SIZE = 700;

    public Window() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_SIZE, WINDOW_SIZE);

        Logic logic = new Logic();

        this.setLayout(new BorderLayout());
        this.add(new SideMenu(logic), BorderLayout.LINE_START);
        this.add(logic.getDrawingCanvas(), BorderLayout.CENTER);

        this.setFocusable(true);
        this.setVisible(true);

    }
}
