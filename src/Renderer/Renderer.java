/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer;

import Signal.Signal;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Dom
 */
public class Renderer {

    //private final List<Signal> m_signals;
    private final Canvas m_canvas;
    private final GraphicsContext gc;
    private final Signal m_signal;

    public Renderer(Canvas canvas, Signal signal) {
        m_signal = signal;
        m_canvas = canvas;
        gc = m_canvas.getGraphicsContext2D();
        draw();
    }

    public void setVisible(Boolean isVisible) {
        gc.getCanvas().setVisible(isVisible);
        System.out.println("isVisible: " + isVisible);
    }

    public void resetCanvas() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    public final void draw() {
        resetCanvas();
        drawSignals();
    }

    public void drawSignals() {
        double invertedYFactor = -1;
        double zoomFactorX = 20;
        double zoomFactorY = 50;
        double xOffset = 10;
        double yOffset = gc.getCanvas().getHeight() / 2;
        gc.setFill(Color.BLACK);

        double[] xPoints = new double[m_signal.getPoints().size()];
        double[] yPoints = new double[m_signal.getPoints().size()];

        for (int i = 0; i < m_signal.getPoints().size(); i++) {
            // System.out.println("X: " + m_signal.getPoints().get(i).getX() * zoomFactorX + " Y: " + m_signal.getPoints().get(i).getY() * zoomFactorY);
            xPoints[i] = (m_signal.getPoints().get(i).getX() * zoomFactorX) + xOffset;
            yPoints[i] = (m_signal.getPoints().get(i).getY() * invertedYFactor * zoomFactorY) + yOffset;
        }

        gc.strokePolyline(xPoints, yPoints, m_signal.getPoints().size());
    }

    public void drawCharts() {
        System.out.println("drawCharts");
    }
}
