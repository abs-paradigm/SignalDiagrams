/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram.Renderer;

import com.SignalDiagram.Diagram.Diagram;
import com.SignalDiagram.Signal.AnalogSignal;
import com.SignalDiagram.Signal.DigitalSignal;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Dom
 */
public class MainRenderer {

    //private final List<Signal> m_signals;
    private final Canvas m_canvas;
    private final GraphicsContext gc;
    private AnalogSignal m_analogSignal;
    private final DigitalSignal m_digitalSignal;
    private final Diagram m_diagram;
    private final DiagramAdapter m_diagramAdapter;
    private Point2D m_origine;
    private double zoomFactorX = 25;
    private double zoomFactorY = 50;
    private ChartRenderer m_chartRenderer;
    private DigitalRenderer m_digitalRenderer;
    private AnalogRenderer m_analogRenderer;

    public MainRenderer(Canvas canvas, DigitalSignal signal, Diagram diagram) {
        m_digitalSignal = signal;
        m_canvas = canvas;
        m_diagram = diagram;
        m_diagramAdapter = new DiagramAdapter(m_diagram);
        m_origine = m_diagramAdapter.getOrigin();
        gc = m_canvas.getGraphicsContext2D();
        m_analogSignal = new AnalogSignal("011100101011101", AnalogSignal.analogType.FREQUENCE);

        m_chartRenderer = new ChartRenderer(gc, m_diagram, new Point2D(zoomFactorX, zoomFactorY));
        m_digitalRenderer = new DigitalRenderer(gc, m_digitalSignal, m_origine, new Point2D(zoomFactorX, zoomFactorY));
        m_analogRenderer = new AnalogRenderer(gc, m_analogSignal, m_origine, new Point2D(zoomFactorX, zoomFactorY));

        draw();

    }

    public void setVisible(Boolean isVisible) {
        gc.getCanvas().setVisible(isVisible);
        System.out.println("isVisible: " + isVisible);
    }

    private void resetCanvas() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    public final void draw() {
        resetCanvas();
        m_chartRenderer.draw();
        m_digitalRenderer.draw();
        //m_analogRenderer.draw();

    }

    private void strokeCubicBezier(Point2D p0, Point2D p1, Point2D p2, Point2D p3) {
        gc.beginPath();
        gc.moveTo(p0.getX(), p0.getY());
        gc.bezierCurveTo(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
        gc.stroke();
        gc.closePath();
    }
}
