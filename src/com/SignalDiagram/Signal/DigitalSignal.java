/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram.Signal;

import static com.SignalDiagram.Encoder.DigitalEncoders.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;

/**
 *
 * @author Dom
 */
public class DigitalSignal extends SignalAbstract {

    private modulationType m_signalType;

    public ObservableList<Point2D> signalList = FXCollections.observableArrayList();

    public DigitalSignal() {
    }

    public DigitalSignal(String message, modulationType type) {
        m_signalType = type;
        initMessage(message);
    }

    private DigitalSignal encodeMessage() {
        System.out.println("m_signalType: " + m_signalType);
        switch (m_signalType) {
            case BIPOLAR:
                m_encodedSignal = bipolar(m_message);
                break;
            case NRZ:
                m_encodedSignal = nrz(m_message);
                break;
            case RZ:
                m_encodedSignal = rz(m_message);
                break;
            case MANCHESTER:
                m_encodedSignal = manchester(m_message);
                break;
            case MILLER:
                m_encodedSignal = miller(m_message);
                break;
            case UNIPOLAR:
                m_encodedSignal = unipolar(m_message);
                break;
        }

        return this;
    }

    public DigitalSignal setType(modulationType type) {
        m_signalType = type;
        encodeMessage();
        return this;
    }

    public DigitalSignal setType(String type) {

        for (modulationType mt : modulationType.values()) {

            if (mt.name().equals(type.toUpperCase())) {
                m_signalType = mt;
                break;
            }
        }

        encodeMessage();
        return this;
    }

    @Override
    protected SignalAbstract updateSignal() {
        return this;
    }

    @Override
    public DigitalSignal setMessage(String message) {
        m_message = message;
        encodeMessage();

        return this;
    }

    private void initMessage(String message) {
        m_message = message;
        encodeMessage();
    }

    public List<String> getModulationTypes() {
        List<String> modulationTypes = new ArrayList<>();

        for (modulationType c : modulationType.values()) {
            modulationTypes.add(c.toString().toLowerCase());
        }
        return modulationTypes;
    }

    public enum modulationType {

        NRZ, RZ, UNIPOLAR, BIPOLAR, MANCHESTER, MILLER
    }
}