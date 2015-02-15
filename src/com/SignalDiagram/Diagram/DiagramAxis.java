/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram.Diagram;

import javafx.geometry.Orientation;

/**
 *
 * @author Dom
 */
public class DiagramAxis {
    
    private Orientation m_orientation = Orientation.HORIZONTAL;
    private String m_text;
    private double[] m_axisLimits = new double[3];
    private double m_increment;
    
    public DiagramAxis(String text, Orientation orientation, double[] axisLimits) {
        m_text = text;
        m_orientation = orientation;
        System.arraycopy(axisLimits, 0, m_axisLimits, 0, m_axisLimits.length);
    }
    
    public void setOrientation(Orientation orientation) {
        m_orientation = orientation;
    }
    
    public void setText(String text) {
        m_text = text;
    }
    
    public void setAxisLimits(double[] axisLimits) {
        m_axisLimits = axisLimits;
    }
    
    public void getIncrement(double increment) {
        m_increment = increment;
    }
    
    public Orientation getOrientation() {
        return m_orientation;
    }
    
    public String getTitle() {
        return m_text;
    }
    
    public double[] getAxisLimits() {
        return m_axisLimits;
    }
    
    public double getMin() {
        return m_axisLimits[0];
    }
    
    public double getMid() {
        return m_axisLimits[1];
    }
    
    public double getMax() {
        return m_axisLimits[2];
    }
    
    public double getWidth() {
        return (Math.abs(m_axisLimits[1] - m_axisLimits[0]) + Math.abs(m_axisLimits[2] - m_axisLimits[1]));
    }
    
    public double getIncrement() {
        return m_increment;
    }
}
