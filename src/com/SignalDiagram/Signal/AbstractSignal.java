/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram.Signal;

/**
 *
 * @author Dom
 */
public abstract class AbstractSignal {

    protected String m_message;

    protected abstract AbstractSignal encodeMessage();

    public int getLength() {
        return m_message.length();
    }

    public String getMessage() {
        return m_message;
    }

    protected void initMessage(String message) {
        m_message = message;
        encodeMessage();
    }

    public AbstractSignal setMessage(String message) {
        m_message = message;
        encodeMessage();
        return this;
    }

}
