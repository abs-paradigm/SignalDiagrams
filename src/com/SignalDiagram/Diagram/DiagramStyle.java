/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram.Diagram;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Dom
 */
public class DiagramStyle {

    private double m_borderLineWidth;
    private Font m_titleFont;
    private int m_titleFontSize;
    private Color m_borderColor;
    private Color m_axisColor;
    private double m_axisLineWidth;
    private Color m_titleFontColor;

    public DiagramStyle() {
        m_borderLineWidth = 1;
        m_borderColor = Color.BLACK;
        m_axisLineWidth = .5;
        m_axisColor = Color.GREY;
        m_titleFontSize = 12;
        m_titleFontColor = Color.BLACK;
    }

    public DiagramStyle setBorderWidth(double borderWidth) {
        m_borderLineWidth = borderWidth;
        return this;
    }

    public DiagramStyle setBorderColor(Color borderColor) {
        m_borderColor = borderColor;
        return this;
    }

    public DiagramStyle setTitleFont(Font font) {
        m_titleFont = font;
        return this;
    }

    public DiagramStyle setFontSize(int fontSize) {
        m_titleFontSize = fontSize;
        return this;
    }

    public DiagramStyle setTitleFontColor(Color titleFontColor) {
        m_titleFontColor = titleFontColor;
        return this;
    }

    public double getBorderLineWidth() {
        return m_borderLineWidth;
    }

    public Font getTitleFont() {
        return m_titleFont;
    }

    public int getTitleFontSize() {
        return m_titleFontSize;
    }

    public Color getBorderColor() {
        return m_borderColor;
    }

    public Color getTitleColor() {
        return m_titleFontColor;
    }

    public Color getAxisColor() {
        return m_axisColor;
    }

    public double getAxisLineWidth() {
        return m_axisLineWidth;
    }

}
