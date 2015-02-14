/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer;

import Diagram.Diagram;
import Signal.Signal;
import javafx.geometry.Orientation;
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
        drawDigitalSignals();
    }

    private void drawDigitalSignals() {
        double invertedYFactor = -1;
        double zoomFactorX = 10;
        double zoomFactorY = 25;
        double xOffset = m_diagramAdapter.getOrigin().getX();
        double yOffset = m_diagramAdapter.getOrigin().getY();
        gc.setLineWidth(1);

        gc.setStroke(Color.BLACK);

        gc.setFill(Color.BLACK);

        double[] xPoints = new double[m_signal.getPoints().size()];
        double[] yPoints = new double[m_signal.getPoints().size()];

        for (int i = 0; i < m_signal.getPoints().size(); i++) {

            double testval = (m_signal.getPoints().get(i).getX() * zoomFactorX) + xOffset;

            xPoints[i] = (m_signal.getPoints().get(i).getX() * zoomFactorX) + xOffset;
            yPoints[i] = (m_signal.getPoints().get(i).getY() * invertedYFactor * zoomFactorY) + yOffset;
        }

        gc.strokePolyline(xPoints, yPoints, m_signal.getPoints().size());
    }

    private void drawAnalogSignals() {

    }

    private void drawCharts() {
        drawChartBorder();
        drawChartTitle();
        drawChartAxis();
        drawIndent();
        // Draw Axis Title
        // Draw Title
    }

    private void drawChartAxis() {
        gc.setLineWidth(m_diagramAdapter.getDiagram().getDiagramStyle().getAxisLineWidth());
        gc.setStroke(m_diagramAdapter.getDiagram().getDiagramStyle().getAxisColor());
        // Draw X Axis
        gc.strokePolyline(m_diagramAdapter.getX_HorzontalAxis(), m_diagramAdapter.getY_HorzontalAxis(), m_diagramAdapter.getX_HorzontalAxis().length);

        // Draw Y Axis
        gc.strokePolyline(m_diagramAdapter.getX_VerticalAxis(), m_diagramAdapter.getY_VerticalAxis(), m_diagramAdapter.getY_VerticalAxis().length);

    }

    private void drawChartBorder() {
        // Draw Borders
        gc.setLineWidth(m_diagramAdapter.getDiagram().getDiagramStyle().getBorderLineWidth());
        gc.setStroke(m_diagramAdapter.getDiagram().getDiagramStyle().getBorderColor());
        gc.strokePolyline(m_diagramAdapter.getXborders(), m_diagramAdapter.getYborders(), m_diagramAdapter.getXborders().length);

    }

    private void drawChartTitle() {
        //gc.setStroke(m_diagramAdapter.getDiagram().getDiagramStyle().getTitleColor());
        //gc.strokeText(m_diagramAdapter.getDiagram().getTitle(), x, y);
    }

    private void drawAxisTitle() {
        //gc.strokeText(m_diagramAdapter.getDiagram().getAxis(Orientation.HORIZONTAL).getTitle(), x, y);
    }

    private void drawIndent() {
        gc.setLineWidth(.2);
        gc.setStroke(Color.GREY);

        double zoomFactorX = 10;
        double zoomFactorY = 25;
        double xOffset = m_diagramAdapter.getOrigin().getX();
        double yOffset = m_diagramAdapter.getOrigin().getY();
        double xPoints;
        double yPoints;

        for (int i = 0; i < m_signal.getLength(); i++) {

            double testval = (m_signal.getPoints().get(i).getX() * zoomFactorX) + xOffset;

            xPoints = (i * zoomFactorX) + xOffset;
            //yPoints = (1/4) * yOffset;
            gc.strokeLine(xPoints, yOffset - 40, xPoints, yOffset + 40);

        }

    }
}
