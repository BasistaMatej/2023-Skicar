package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.Logic;

import javax.swing.*;
import java.awt.*;

public class SideMenu extends JPanel {
    public static final String CIRCLE_TEXT = "Kruh";
    public static final String SQUARE_TEXT = "Stvorec";
    public static final String WATCH_TEXT = "Hodiny";

    public SideMenu(Logic logic) {
        this.setLayout(new BorderLayout());
        this.setFocusable(false);

        this.add(createTopMenu(), BorderLayout.PAGE_START);
        this.add(createMiddleMenu(logic), BorderLayout.CENTER);
        this.add(createBottomMenu(logic), BorderLayout.PAGE_END);
    }

    private JPanel createBottomMenu(Logic logic) {
        JPanel bottomPartMenu = new JPanel();
        bottomPartMenu.setLayout(new GridLayout(1, 1));
        bottomPartMenu.add(createComboBox(logic));
        return bottomPartMenu;
    }

    private JPanel createMiddleMenu(Logic logic) {
        JPanel middlePartMenu = new JPanel();
        middlePartMenu.setLayout(new GridLayout(1, 3));
        middlePartMenu.add(logic.getLenghtSlider());
        middlePartMenu.add(logic.getRadiusSlider());
        middlePartMenu.add(logic.getSpacingSlider());
        return middlePartMenu;
    }

    private JPanel createTopMenu() {
        JPanel topPartMenu = new JPanel();
        topPartMenu.setLayout(new GridLayout(1, 3));
        topPartMenu.add(new JLabel("Lenght"));
        topPartMenu.add(new JLabel("Radius"));
        topPartMenu.add(new JLabel("Spacing"));
        return topPartMenu;
    }

    private JComboBox createComboBox(Logic logic) {
        JComboBox comboBox = new JComboBox(new String[]{CIRCLE_TEXT, SQUARE_TEXT, WATCH_TEXT});
        comboBox.addActionListener(logic);
        comboBox.setFocusable(false);
        return comboBox;
    }
}
