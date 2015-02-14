/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer;

import Diagram.Diagram;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Dimension2D;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

/**
 *
 * @author Dom
 */
public class DiagramAdapter {

    private Dimension2D m_offset;
    private Diagram m_diagram;
    private List<Shape> m_diagramShapes;

    private Polyline m_border;
    private Point2D m_origin;
    private double[] m_Xborder;
    private double[] m_Yborder;
    private double[] m_xHorizontalAxis;
    private double[] m_yHorizontalAxis;
    private double[] m_yVerticalAxis;
    private double[] m_xVerticalAxis;
    private Text m_TitleText;
    private Text m_HorizontalText;
    private Text m_VerticalText;

    public DiagramAdapter(Diagram diagram) {
        m_offset = new Dimension2D(20, 20);
        m_diagram = diagram;
        m_diagramShapes = new ArrayList<>();
        createBorders();
        create_Axis();
    }

    public Diagram getDiagram() {
        return m_diagram;
    }

    private void updateShapes() {
        m_diagramShapes.add(m_border);
        m_diagramShapes.add(m_HorizontalText);
        m_diagramShapes.add(m_VerticalText);
        m_diagramShapes.add(m_TitleText);
    }

    public List<Shape> getShapes() {
        return m_diagramShapes;
    }

    public double[] getXborders() {

        return m_Xborder;
    }

    public double[] getYborders() {

        return m_Yborder;
    }

    private void createBorders() {
        m_Xborder = new double[]{
            0.0 + m_offset.getWidth(),
            0.0 + m_offset.getWidth(),
            m_diagram.getDimensions().getWidth() + m_offset.getWidth(),
            m_diagram.getDimensions().getWidth() + m_offset.getWidth(),
            0.0 + m_offset.getWidth()};

        m_Yborder = new double[]{
            0.0 + m_offset.getWidth(),
            m_diagram.getDimensions().getHeight() + m_offset.getWidth(),
            m_diagram.getDimensions().getHeight() + m_offset.getWidth(),
            0.0 + m_offset.getWidth(),
            0.0 + m_offset.getWidth()};
    }

    private void create_Axis() {
        double xMidPointRatio = Math.abs(m_diagram.getAxis(Orientation.HORIZONTAL).getMid() - m_diagram.getAxis(Orientation.HORIZONTAL).getMin()) / m_diagram.getAxis(Orientation.HORIZONTAL).getWidth();
        double horizontalAxisMidPoint = xMidPointRatio * (m_diagram.getDimensions().getWidth() - 2 * m_diagram.getInsets().getWidth());

        double yMidPointRatio = Math.abs(m_diagram.getAxis(Orientation.VERTICAL).getMid() - m_diagram.getAxis(Orientation.VERTICAL).getMin()) / m_diagram.getAxis(Orientation.VERTICAL).getWidth();
        double verticalAxisMidPoint = yMidPointRatio * (m_diagram.getDimensions().getHeight() - 2 * m_diagram.getInsets().getHeight());

        m_xHorizontalAxis = new double[]{
            0.0 + m_offset.getWidth() + m_diagram.getInsets().getWidth(),
            horizontalAxisMidPoint + m_diagram.getInsets().getWidth() + m_offset.getWidth(),
            m_diagram.getDimensions().getWidth() - m_diagram.getInsets().getWidth() + m_offset.getWidth()};

        m_yHorizontalAxis = new double[]{
            verticalAxisMidPoint + m_diagram.getInsets().getHeight() + m_offset.getWidth(),
            verticalAxisMidPoint + m_diagram.getInsets().getHeight() + m_offset.getWidth(),
            verticalAxisMidPoint + m_diagram.getInsets().getHeight() + m_offset.getWidth()};

        m_xVerticalAxis = new double[]{
            horizontalAxisMidPoint + m_diagram.getInsets().getWidth() + m_offset.getWidth(),
            horizontalAxisMidPoint + m_diagram.getInsets().getWidth() + m_offset.getWidth(),
            horizontalAxisMidPoint + m_diagram.getInsets().getWidth() + m_offset.getWidth()};

        m_yVerticalAxis = new double[]{
            0.0 + m_offset.getHeight() + m_diagram.getInsets().getHeight(),
            verticalAxisMidPoint,
            m_diagram.getDimensions().getHeight() - m_diagram.getInsets().getHeight() + m_offset.getHeight()};

        m_origin = new Point2D(horizontalAxisMidPoint + m_diagram.getInsets().getWidth() + m_offset.getWidth(), verticalAxisMidPoint
                + m_diagram.getInsets().getHeight() + m_offset.getWidth());
        System.out.println("Create axis xxMid: " + horizontalAxisMidPoint);

    }

    public double[] getX_HorzontalAxis() {

        return m_xHorizontalAxis;
    }

    public double[] getY_HorzontalAxis() {

        return m_yHorizontalAxis;
    }

    public double[] getY_VerticalAxis() {

        return m_yVerticalAxis;
    }

    public double[] getX_VerticalAxis() {

        return m_xVerticalAxis;
    }

    public Point2D getOrigin() {
        return m_origin;
    }
}
