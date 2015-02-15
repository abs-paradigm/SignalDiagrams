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
public class Renderer {

    //private final List<Signal> m_signals;
    private final Canvas m_canvas;
    private final GraphicsContext gc;
    private final DigitalSignal m_digitalSignal;
    private final Diagram m_diagram;
    private final DiagramAdapter m_diagramAdapter;
    private Point2D m_origine;
    private double zoomFactorX = 20;
    private double zoomFactorY = 60;
    private AnalogSignal m_analogSignal;

    public Renderer(Canvas canvas, DigitalSignal signal, Diagram diagram) {
        m_digitalSignal = signal;
        m_canvas = canvas;
        m_diagram = diagram;
        m_diagramAdapter = new DiagramAdapter(m_diagram);
        m_origine = m_diagramAdapter.getOrigin();
        gc = m_canvas.getGraphicsContext2D();
        m_analogSignal = new AnalogSignal("10111", AnalogSignal.analogType.NORMAL);
        draw();
//        m_canvas.widthProperty().bind(
//                m_scrollPane.widthProperty());
//        m_canvas.heightProperty().bind(
//                m_scrollPane.heightProperty());
//        
//        m_canvas.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//            System.out.println("Changed width: " + m_canvas.widthProperty());
//            m_diagram.setSize(newValue.doubleValue(), 500);
//        });
//        m_canvas.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//            System.out.println("Changed height: " + m_canvas.heightProperty());
//            m_diagram.setSize(500, newValue.doubleValue());
//        });
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
        drawAnalogSignals();
    }

    private void drawDigitalSignals() {
        double invertedYFactor = -1;
        double xOffset = m_diagramAdapter.getOrigin().getX();
        double yOffset = m_diagramAdapter.getOrigin().getY();
        gc.setLineWidth(1);

        gc.setStroke(Color.BLACK);

        gc.setFill(Color.BLACK);

        double[] xPoints = new double[m_digitalSignal.getPoints().size()];
        double[] yPoints = new double[m_digitalSignal.getPoints().size()];

        for (int i = 0; i < m_digitalSignal.getPoints().size(); i++) {

            xPoints[i] = (m_digitalSignal.getPoints().get(i).getX() * zoomFactorX) + xOffset;
            yPoints[i] = (m_digitalSignal.getPoints().get(i).getY() * invertedYFactor * zoomFactorY) + yOffset;
        }
        gc.strokePolyline(xPoints, yPoints, m_digitalSignal.getPoints().size());
    }

    private void drawAnalogSignals() {
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
//
//        gc.beginPath();
//        gc.moveTo(x, y);
//        gc.quadraticCurveTo(x1, y1, x2, y2);
//
//        x = (m_analogSignal.getPoints().get(i + 2).getX() * xFactor) + 100;//
//
//        y = (m_analogSignal.getPoints().get(i + 2).getY() * yFactor) + 200;
//
//        x1 = (m_analogSignal.getPoints().get(i + 3).getX() * xFactor) + 100;//
//
//        y1 = (m_analogSignal.getPoints().get(i + 3).getY() * yFactor) + 200;
//
//        x2 = (m_analogSignal.getPoints().get(i + 4).getX() * xFactor) + 100;//
//
//        y2 = (m_analogSignal.getPoints().get(i + 4).getY() * yFactor) + 200;
//        System.out.println("x: " + m_analogSignal.getPoints().get(i + 2).getX() + " y: " + m_analogSignal.getPoints().get(i + 2).getY());
//        System.out.println("x1: " + m_analogSignal.getPoints().get(i + 3).getX() + " y1: " + m_analogSignal.getPoints().get(i + 3).getY());
//        System.out.println("x2: " + m_analogSignal.getPoints().get(i + 4).getX() + " y2: " + m_analogSignal.getPoints().get(i + 4).getY());
//
//        gc.quadraticCurveTo(x1, y1, x2, y2);
//        gc.stroke();
//        gc.closePath();

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

        double xOffset = m_diagramAdapter.getOrigin().getX();
        double yOffset = m_diagramAdapter.getOrigin().getY();
        double xPoints;
        double yPoints;

        for (int i = 0; i <= m_digitalSignal.getLength(); i++) {

            double testval = (m_digitalSignal.getPoints().get(i).getX() * zoomFactorX) + xOffset;

            xPoints = (i * zoomFactorX) + xOffset;
            //yPoints = (1/4) * yOffset;
            gc.strokeLine(xPoints, yOffset - 50, xPoints, yOffset + 50);

        }

    }
}