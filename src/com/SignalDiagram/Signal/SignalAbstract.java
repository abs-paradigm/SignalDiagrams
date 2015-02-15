/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram.Signal;

import java.util.List;
import javafx.geometry.Point2D;

/**
 *
 * @author Dom
 */
abstract class SignalAbstract {

    protected List<Point2D> m_encodedSignal;
    protected String m_message;

    public int getLength() {
        return m_message.length();
    }

    public List<Point2D> getPoints() {
        return m_encodedSignal;
    }

    public SignalAbstract setMessage(String message) {
        m_message = message;
        updateSignal();
        return this;
    }

    public String getMessage() {
        return m_message;
    }

    abstract protected SignalAbstract updateSignal();

}
