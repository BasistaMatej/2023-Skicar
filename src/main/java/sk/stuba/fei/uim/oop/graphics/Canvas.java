package sk.stuba.fei.uim.oop.graphics;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.controls.Logic;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    private int[] pointsX;
    private int[] pointsY;
    @Setter
    private int spacing;
    @Setter
    private int radius;
    @Setter
    private Shapes currentShape;
    @Getter @Setter
    private int currentPoint;
    private int numberOfPoints;

    public Canvas(int numberOfPoints, Logic logic) {
        this.pointsX = new int[numberOfPoints];
        this.pointsY = new int[numberOfPoints];
        this.numberOfPoints = numberOfPoints;
        initializePoints();
        this.currentPoint = 0;
        this.addMouseListener(logic);
        this.addMouseMotionListener(logic);
    }

    public void setAndResetPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
        int[] tmpY = pointsY;
        int[] tmpX = pointsX;
        this.pointsX = new int[numberOfPoints];
        this.pointsY = new int[numberOfPoints];

        initializePoints();
        for (int j = 0; j < numberOfPoints; j++) {
            try {
                if (tmpX[j] > 0 && tmpY[j] > 0) {
                    pointsX[j] = tmpX[j];
                    pointsY[j] = tmpY[j];
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return;
            }
        }

    }

    public void addPoint(int x, int y) {
        this.pointsX[currentPoint] = x;
        this.pointsY[currentPoint] = y;
        currentPoint++;
    }

    private void initializePoints() {
        for (int i = 0; i < pointsY.length; i++) {
            pointsY[i] = -20;
            pointsX[i] = -20;
        }
    }

    private void drawShapes(Graphics g, int i) {
        switch (currentShape) {
            case CIRCLE:
                g.fillOval(pointsX[i] - radius / 2, pointsY[i] - radius / 2, radius, radius);
                break;
            case SQUARE:
                g.fillRect(pointsX[i] - radius / 2, pointsY[i] - radius / 2, radius, radius);
                break;
            case WATCH:
                int[] x = {pointsX[i] - radius / 2, pointsX[i] + radius / 2, pointsX[i] + radius / 2, pointsX[i] - radius / 2};
                int[] y = {pointsY[i] - radius / 2, pointsY[i] + radius / 2, pointsY[i] - radius / 2, pointsY[i] + radius / 2};
                g.fillPolygon(x, y, 4);
                break;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(240,240,240));
        for (int j = 0; j < pointsX.length - 1; j++) {
            if(pointsX[j] > 0 && pointsY[j] > 0 && pointsX[j+1] > 0 && pointsY[j+1] > 0) {
                g.setColor(new Color(180, 180, 255 / numberOfPoints * j));
                g.drawLine(pointsX[j], pointsY[j], pointsX[1 + j], pointsY[j + 1]);
            }
        }
        int i;
        for (i = 0; i < pointsY.length; i = i + spacing) {
            g.setColor(new Color(180, 180, 255 / numberOfPoints * i));
            drawShapes(g, i);
        }
        if (i > pointsX.length - 1) {
            g.setColor(new Color(180, 180, 255));
            drawShapes(g, pointsX.length - 1);
        }
    }
}
