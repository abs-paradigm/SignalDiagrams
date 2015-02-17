/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram.Renderer;

import com.SignalDiagram.Signal.DigitalSignal;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Dom
 */
public class DigitalRenderer {

    GraphicsContext m_gc;
    private final DigitalSignal m_digitalSignal;
    Point2D m_origin;
    Point2D m_Zoom;

    public DigitalRenderer(GraphicsContext gc, DigitalSignal digitalSignal, Point2D origin, Point2D Zoom) {
        m_gc = gc;
        m_digitalSignal = digitalSignal;
        m_origin = origin;
        m_Zoom = Zoom;

        draw();
    }

    public void draw() {
        if (m_digitalSignal.getPoints().isEmpty()) {
            return;
        }
        double invertedYFactor = -1;

        m_gc.setLineWidth(1);
        m_gc.setStroke(Color.BLACK);
        m_gc.setFill(Color.BLACK);

        double[] xPoints = new double[m_digitalSignal.getPoints().size()];
        double[] yPoints = new double[m_digitalSignal.getPoints().size()];

        for (int i = 0; i < m_digitalSignal.getPoints().size(); i++) {

            xPoints[i] = (m_digitalSignal.getPoints().get(i).getX() * m_Zoom.getX()) + m_origin.getX();
            yPoints[i] = (m_digitalSignal.getPoints().get(i).getY() * invertedYFactor * m_Zoom.getY()) + m_origin.getY();
        }
        m_gc.strokePolyline(xPoints, yPoints, m_digitalSignal.getPoints().size());
    }
}
