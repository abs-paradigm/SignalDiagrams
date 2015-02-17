/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram.Renderer;

import com.SignalDiagram.Signal.AnalogSignal;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Dom
 */
public class AnalogRenderer {

    GraphicsContext m_gc;
    private AnalogSignal m_analogSignal;
    Point2D m_origin;
    Point2D m_Zoom;

    public AnalogRenderer(GraphicsContext gc, AnalogSignal analogSignal, Point2D origin, Point2D zoom) {
        m_origin = origin;
        m_Zoom = zoom;
        m_analogSignal = analogSignal;
        m_gc = gc;
    }

    public void draw() {
        // for (int i = 0; i < 2; i++) {
//        double yFactor = 40;
//        double xFactor = 40;
//        int i = 0;
//        double x = (m_analogSignal.getPoints().get(i).getX() * xFactor) + 100;//
//        double y = (m_analogSignal.getPoints().get(i).getY() * yFactor) + 200;
//        double x1 = (m_analogSignal.getPoints().get(i + 1).getX() * xFactor) + 100;//
//        double y1 = (m_analogSignal.getPoints().get(i + 1).getY() * yFactor) + 200;
//        double x2 = (m_analogSignal.getPoints().get(i + 2).getX() * xFactor) + 100;//
//        double y2 = (m_analogSignal.getPoints().get(i + 2).getY() * yFactor) + 200;
//        System.out.println("x: " + m_analogSignal.getPoints().get(i).getX() + " y: " + m_analogSignal.getPoints().get(i).getY());
//        System.out.println("x1: " + m_analogSignal.getPoints().get(i + 1).getX() + " y1: " + m_analogSignal.getPoints().get(i + 1).getY());
//        System.out.println("x2: " + m_analogSignal.getPoints().get(i + 2).getX() + " y2: " + m_analogSignal.getPoints().get(i + 2).getY());

        double[] xPoints = new double[m_analogSignal.getPoints().size()];
        double[] yPoints = new double[m_analogSignal.getPoints().size()];
        // System.out.println("m_analogSignal.getLength()  " + m_analogSignal.getLength());

        for (int h = 0; h < m_analogSignal.getPoints().size(); h++) {
            // double xx = (m_analogSignal.getPoints().get(i).getX() * xFactor) + 100;//
            //double yy = (m_analogSignal.getPoints().get(i).getY() * yFactor) + 200;

            xPoints[h] = (m_analogSignal.getPoints().get(h).getX() * 30.0) + 10;
            //  System.out.println("xPoints  " + xPoints[h]);
            yPoints[h] = (m_analogSignal.getPoints().get(h).getY() * 50) + 180;
            // System.out.println("yPoints  " + yPoints[h]);

//            gc.strokeLine(xPoints[i-1],  yPoints[i-1], xPoints[i], yPoints[i]);
        }
        m_gc.strokePolyline(xPoints, yPoints, m_analogSignal.getPoints().size());
//        gc.strokeLine(0, 300, 600, 300);
//        gc.strokeLine(0, 20, 250, 20);
//        Point2D p0 = new Point2D(0, 0);
//        Point2D p1 = new Point2D(25, 26.66);
//        Point2D p2 = new Point2D(75, 26.66);
//        Point2D p3 = new Point2D(100, 0);
//        strokeCubicBezier(p0, p1, p2, p3);
//        x = (m_analogSignal.getPoints().get(i + 2).getX() * xFactor) + 100;//
//        y = (m_analogSignal.getPoints().get(i + 2).getY() * yFactor) + 200;
//        x1 = (m_analogSignal.getPoints().get(i + 3).getX() * xFactor) + 100;//
//        y1 = (m_analogSignal.getPoints().get(i + 3).getY() * yFactor) + 200;
//        x2 = (m_analogSignal.getPoints().get(i + 4).getX() * xFactor) + 100;//
//        y2 = (m_analogSignal.getPoints().get(i + 4).getY() * yFactor) + 200;
//        System.out.println("x: " + m_analogSignal.getPoints().get(i + 2).getX() + " y: " + m_analogSignal.getPoints().get(i + 2).getY());
//        System.out.println("x1: " + m_analogSignal.getPoints().get(i + 3).getX() + " y1: " + m_analogSignal.getPoints().get(i + 3).getY());
//        System.out.println("x2: " + m_analogSignal.getPoints().get(i + 4).getX() + " y2: " + m_analogSignal.getPoints().get(i + 4).getY());
//       
    }
}
