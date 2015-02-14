/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diagram;

import javafx.geometry.Dimension2D;
import javafx.geometry.Orientation;

/**
 *
 * @author Dom
 */
public class Diagram {

    private String m_title;

    private Dimension2D m_dimension;

    private Dimension2D m_insets;
    private DiagramAxis m_horizontalAxis;
    private DiagramAxis m_verticalAxis;
    private DiagramStyle m_diagramStyle;

    public Diagram() {
    }

    public Diagram(String title, double width, double height, double[] horizontalAxis, double[] verticalAxis) {
        m_title = title;
        m_dimension = new Dimension2D(width, height);
        m_diagramStyle = new DiagramStyle();
        double[] axisLimit = new double[3];
        m_horizontalAxis = new DiagramAxis("", Orientation.HORIZONTAL, horizontalAxis);
        m_verticalAxis = new DiagramAxis("", Orientation.VERTICAL, verticalAxis);
        m_insets = new Dimension2D(10, 10); 

    }

    public Diagram(String title, double width, double height) {
        m_title = title;
        m_dimension = new Dimension2D(width, height);
        m_diagramStyle = new DiagramStyle();
        double[] axisLimit = new double[3];
        m_horizontalAxis = new DiagramAxis("", Orientation.HORIZONTAL, new double[]{-.1 * m_dimension.getWidth(), 0, .9 * m_dimension.getWidth()});
        m_verticalAxis = new DiagramAxis("", Orientation.VERTICAL, new double[]{-.5 * m_dimension.getHeight(), 0, .5 * m_dimension.getHeight()});

        m_insets = new Dimension2D(10, 10); 

    }

    public void setSize(double width, double length) {
        m_dimension = new Dimension2D(width, length);
    }

    public void setTitle(String title) {
        m_title = title;
    }

    public String getTitle() {
        return m_title;
    }

    public Dimension2D getDimensions() {
        return m_dimension;
    }

    public Dimension2D getInsets() {
        return m_insets;
    }

    public DiagramAxis getAxis(Orientation orientation) {
        DiagramAxis diagramAxis;

        diagramAxis = orientation == Orientation.HORIZONTAL ? m_horizontalAxis : m_verticalAxis;

        return diagramAxis;
    }

    public DiagramStyle getDiagramStyle() {
        return m_diagramStyle;
    }

}
