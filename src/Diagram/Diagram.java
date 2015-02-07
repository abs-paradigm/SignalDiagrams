/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diagram;

import javafx.geometry.Dimension2D;

/**
 *
 * @author Dom
 */
public class Diagram {

    private String m_title;

    private Dimension2D m_size;
    private double m_horizontalAxis;
    private double m_majorHorizontal;
    private double m_minorHorizontal;
    private double m_majorVertical;
    private double m_minorVertical;

    public Diagram() {
    }

    public Diagram(String title, double width, double length) {
        m_title = title;
        m_size = new Dimension2D(width, length);
        updateHorizontalAxis();
    }

    public void setGrid(double majorHorizontal, double minorHorizontal, double majorVertical, double minorVertical) {
        m_majorHorizontal = majorHorizontal;
        m_minorHorizontal = minorHorizontal;
        m_majorVertical = majorVertical;
        m_minorVertical = minorVertical;
    }

    public void setSize(double width, double length) {
        m_size = new Dimension2D(width, length);
        updateHorizontalAxis();
    }

    public void setTitle(String title) {
        m_title = title;
    }

    public String getTitle() {
        return m_title;
    }

    private void updateHorizontalAxis() {
        m_horizontalAxis = m_size.getHeight() / 2;
    }

}
