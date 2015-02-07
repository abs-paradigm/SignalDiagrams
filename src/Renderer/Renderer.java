/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer;

import Signal.Signal;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Dom
 */
public class Renderer {

    //private final List<Point2D> m_signal;
    private final Canvas m_canvas;
    private final GraphicsContext gc;
    private final Signal m_signal;

//    public Renderer(Canvas canvas, List<Point2D> signal) {
//        m_signal = signal;
//        m_canvas = canvas;
//        gc = m_canvas.getGraphicsContext2D();
//        draw();
//    }
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

    public void reset() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        System.out.println("Clear canvas");
    }

    public final void draw() {

        System.out.println("Draw");
        drawSignals();
    }

    private void drawModulations() {
        System.out.println("drawDiagrams");

    }

    public void drawSignals() {
        System.out.println("drawSignals");
        double zoomFactorX = 20;
        double zoomFactorY = 50;
        double yOffset = gc.getCanvas().getHeight() / 2;
        gc.setFill(Color.BLUE);

        double[] xPoints = new double[m_signal.getPoints().size()];
        double[] yPoints = new double[m_signal.getPoints().size()];

        for (int i = 0; i < m_signal.getPoints().size(); i++) {
            System.out.println("X: " + m_signal.getPoints().get(i).getX() * zoomFactorX + " Y: " + m_signal.getPoints().get(i).getY() * zoomFactorY);
            xPoints[i] = m_signal.getPoints().get(i).getX() * zoomFactorX;
            yPoints[i] = (m_signal.getPoints().get(i).getY() * zoomFactorY) + yOffset;
        }

        gc.strokePolyline(xPoints, yPoints, m_signal.getPoints().size());
    }

    public void drawCharts() {
        System.out.println("drawCharts");

    }

}