package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.graphics.Canvas;
import sk.stuba.fei.uim.oop.gui.SideMenu;
import sk.stuba.fei.uim.oop.graphics.Shapes;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class Logic extends UniversalAdapter {
    @Getter
    private Canvas drawingCanvas;
    private int lenght;
    @Getter
    private final JSlider lenghtSlider;
    @Getter
    private final JSlider radiusSlider;
    @Getter
    private final JSlider spacingSlider;

    public Logic() {
        this.lenght = 50;
        this.drawingCanvas = new Canvas(lenght, this);
        this.drawingCanvas.setSpacing(5);
        this.drawingCanvas.setCurrentShape(Shapes.CIRCLE);
        this.drawingCanvas.setRadius(5);

        this.lenghtSlider = createSlider(200, 20, 10, lenght);
        this.radiusSlider = createSlider(20, 1, 1, 5);
        this.spacingSlider = createSlider(20, 1, 1, 5);
    }

    private JSlider createSlider(int max, int min, int step, int defaultValue) {
        JSlider slider = new JSlider();
        slider.setOrientation(JSlider.VERTICAL);
        slider.setMaximum(max);
        slider.setMinimum(min);
        slider.setMajorTickSpacing(step);
        slider.setMinorTickSpacing(step);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        slider.setFocusable(false);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setValue(defaultValue);
        slider.addChangeListener(this);
        return slider;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getSource().equals(this.drawingCanvas) && drawingCanvas.getCurrentPoint() < this.lenght) {
            drawingCanvas.addPoint(e.getX(), e.getY());
            drawingCanvas.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSlider) {
            JSlider slider = (JSlider) e.getSource();
            if (e.getSource().equals(lenghtSlider)) {
                if(slider.getValue() < drawingCanvas.getCurrentPoint()) {
                    drawingCanvas.setCurrentPoint(slider.getValue());
                }
                this.lenght = slider.getValue();
                this.drawingCanvas.setAndResetPoints(lenght);
            } else if (e.getSource().equals(spacingSlider)) {
                drawingCanvas.setSpacing(slider.getValue());
            } else if (e.getSource().equals(radiusSlider)) {
                drawingCanvas.setRadius(slider.getValue());
            }
            drawingCanvas.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox) {
            JComboBox comboBox = (JComboBox) e.getSource();

            if (comboBox.getSelectedItem().equals(SideMenu.CIRCLE_TEXT)) {
                drawingCanvas.setCurrentShape(Shapes.CIRCLE);
            } else if (comboBox.getSelectedItem().equals(SideMenu.SQUARE_TEXT)) {
                drawingCanvas.setCurrentShape(Shapes.SQUARE);
            } else if (comboBox.getSelectedItem().equals(SideMenu.WATCH_TEXT)) {
                drawingCanvas.setCurrentShape(Shapes.WATCH);
            }
            drawingCanvas.repaint();
        }
    }
}
