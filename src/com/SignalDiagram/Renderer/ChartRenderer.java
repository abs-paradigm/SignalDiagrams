/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram.Renderer;

import com.SignalDiagram.Diagram.Diagram;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Dom
 */
public class ChartRenderer {

    GraphicsContext m_gc;
    private final DiagramAdapter m_diagramAdapter;
    private final Diagram m_diagram;
    Point2D m_Zoom;

    public ChartRenderer(GraphicsContext gc, Diagram diagram, Point2D Zoom) {
        m_gc = gc;
        m_Zoom = Zoom;
        m_diagram = diagram;
        m_diagramAdapter = new DiagramAdapter(m_diagram);

    }

    public void draw() {

        drawChartBorder();
        drawChartTitle();
        drawChartAxis();
        drawIndent();
        // Draw Axis Title
        // Draw Title
    }

    private void drawChartAxis() {
        m_gc.setLineWidth(m_diagramAdapter.getDiagram().getDiagramStyle().getAxisLineWidth());
        m_gc.setStroke(m_diagramAdapter.getDiagram().getDiagramStyle().getAxisColor());
        // Draw X Axis
        m_gc.strokePolyline(m_diagramAdapter.getX_HorzontalAxis(), m_diagramAdapter.getY_HorzontalAxis(), m_diagramAdapter.getX_HorzontalAxis().length);

        // Draw Y Axis
        m_gc.strokePolyline(m_diagramAdapter.getX_VerticalAxis(), m_diagramAdapter.getY_VerticalAxis(), m_diagramAdapter.getY_VerticalAxis().length);

    }

    private void drawChartBorder() {
        // Draw Borders
        m_gc.setLineWidth(m_diagramAdapter.getDiagram().getDiagramStyle().getBorderLineWidth());
        m_gc.setStroke(m_diagramAdapter.getDiagram().getDiagramStyle().getBorderColor());
        m_gc.strokePolyline(m_diagramAdapter.getXborders(), m_diagramAdapter.getYborders(), m_diagramAdapter.getXborders().length);
    }

    private void drawChartTitle() {
        // TODO drawChartTitle
        //gc.setStroke(m_diagramAdapter.getDiagram().getDiagramStyle().getTitleColor());
        //gc.strokeText(m_diagramAdapter.getDiagram().getTitle(), x, y);
    }

    private void drawAxisTitle() {
        // TODO drawAxisTitle
        //gc.strokeText(m_diagramAdapter.getDiagram().getAxis(Orientation.HORIZONTAL).getTitle(), x, y);
    }

    private void drawIndent() {
        m_gc.setLineWidth(.2);
        m_gc.setStroke(Color.GREY);
//
//        double xOffset = m_diagramAdapter.getOrigin().getX();
//        double yOffset = m_diagramAdapter.getOrigin().getY();

//        for (int i = 0; i <= m_digitalSignal.getLength(); i++) {
//            double xPoints;
//            double yPoints;
//            double testval = (m_digitalSignal.getPoints().get(i).getX() * m_zoomFactorX) + xOffset;
//
//            xPoints = (i * m_zoomFactorX) + xOffset;
//            //yPoints = (1/4) * yOffset;
//            m_gc.strokeLine(xPoints, yOffset - 50, xPoints, yOffset + 50);
//
//        }
    }
}
