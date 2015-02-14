/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer;

import Diagram.Diagram;
import Signal.Signal;
import javafx.geometry.Point2D;
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
    private final Diagram m_diagram;
    private final DiagramAdapter m_diagramAdapter;
    private Point2D m_origine;

    public Renderer(Canvas canvas, Signal signal, Diagram diagram) {
        m_signal = signal;
        m_canvas = canvas;
        m_diagram = diagram;
        m_diagramAdapter = new DiagramAdapter(m_diagram);
        m_origine = m_diagramAdapter.getOrigin();
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
        drawCharts();
        drawSignals();
    }

    public void drawSignals() {
        double invertedYFactor = -1;
        double zoomFactorX = 0;
        double zoomFactorY = 0;
        double xOffset = m_diagramAdapter.getOrigin().getX();
        double yOffset = m_diagramAdapter.getOrigin().getY();
        gc.setLineWidth(1);

        gc.setStroke(Color.BLACK);

        gc.setFill(Color.BLACK);

        double[] xPoints = new double[m_signal.getPoints().size()];
        double[] yPoints = new double[m_signal.getPoints().size()];

        for (int i = 0; i < m_signal.getPoints().size(); i++) {

            double testval = (m_signal.getPoints().get(i).getX() * 10) + xOffset;

            xPoints[i] = (m_signal.getPoints().get(i).getX() * 10) + xOffset;
            yPoints[i] = (m_signal.getPoints().get(i).getY() * invertedYFactor * 25) + yOffset;
        }

        gc.strokePolyline(xPoints, yPoints, m_signal.getPoints().size());
    }

    public void drawCharts() {

        // Draw Borders
        gc.setLineWidth(m_diagramAdapter.getDiagram().getDiagramStyle().getBorderLineWidth());
        gc.setStroke(m_diagramAdapter.getDiagram().getDiagramStyle().getBorderColor());
        gc.strokePolyline(m_diagramAdapter.getXborders(), m_diagramAdapter.getYborders(), m_diagramAdapter.getXborders().length);

        gc.setLineWidth(.5);

        // Draw X Axis
        gc.strokePolyline(m_diagramAdapter.getX_HorzontalAxis(), m_diagramAdapter.getY_HorzontalAxis(), m_diagramAdapter.getX_HorzontalAxis().length);

        // Draw X Axis
        gc.strokePolyline(m_diagramAdapter.getX_VerticalAxis(), m_diagramAdapter.getY_VerticalAxis(), m_diagramAdapter.getY_VerticalAxis().length);

        // Draw Axis Title
        // Draw Title
    }
}
