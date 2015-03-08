/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram.Signal;

import static com.SignalDiagram.Encoder.DigitalEncoders.bipolar;
import static com.SignalDiagram.Encoder.DigitalEncoders.cmi;
import static com.SignalDiagram.Encoder.DigitalEncoders.manchester;
import static com.SignalDiagram.Encoder.DigitalEncoders.manchesterDifferential;
import static com.SignalDiagram.Encoder.DigitalEncoders.miller;
import static com.SignalDiagram.Encoder.DigitalEncoders.mlt3;
import static com.SignalDiagram.Encoder.DigitalEncoders.nrz_l;
import static com.SignalDiagram.Encoder.DigitalEncoders.nrz_m;
import static com.SignalDiagram.Encoder.DigitalEncoders.nrz_s;
import static com.SignalDiagram.Encoder.DigitalEncoders.pseudoternary;
import static com.SignalDiagram.Encoder.DigitalEncoders.rz;
import static com.SignalDiagram.Encoder.DigitalEncoders.unipolar;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;

/**
 *
 * @author Dom
 */
public class DigitalSignal extends AbstractSignal {

    protected List<Point2D> m_encodedSignal;

    private Boolean m_invertedSignal = false;

    private modulationType m_signalType;

    public ObservableList<Point2D> signalList = FXCollections.observableArrayList();

    public DigitalSignal() {
    }

    public DigitalSignal(String message, modulationType type, Boolean isInverted) {
        m_signalType = type;
        m_invertedSignal = isInverted;
        initMessage(message);
    }

    @Override
    protected DigitalSignal encodeMessage() {

        switch (m_signalType) {
            case BIPOLAR:
                m_encodedSignal = bipolar(m_message, m_invertedSignal);
                break;
//            case BIPHASE_M:
//                m_encodedSignal = biphase_m(m_message, m_invertedSignal);
//                break;
//            case BIPHASE_S:
//                m_encodedSignal = biphase_s(m_message, m_invertedSignal);
//                break;
            case NRZ_L:
                m_encodedSignal = nrz_l(m_message, m_invertedSignal);
                break;
            case NRZ_M:
                m_encodedSignal = nrz_m(m_message, m_invertedSignal);
                break;
            case NRZ_S:
                m_encodedSignal = nrz_s(m_message, m_invertedSignal);
                break;
            case RZ:
                m_encodedSignal = rz(m_message, m_invertedSignal);
                break;
            case CMI:
                m_encodedSignal = cmi(m_message, m_invertedSignal);
                break;
            case MANCHESTER:
                m_encodedSignal = manchester(m_message, m_invertedSignal);
                break;
            case MANCHESTERDIFFERENTIAL:
                m_encodedSignal = manchesterDifferential(m_message, m_invertedSignal);
                break;
            case MLT3:
                m_encodedSignal = mlt3(m_message, m_invertedSignal);
                break;
            case MILLER:
                m_encodedSignal = miller(m_message, m_invertedSignal);
                break;
            case PSEUDOTERNARY:
                m_encodedSignal = pseudoternary(m_message, m_invertedSignal);
                break;
            case UNIPOLAR:
                m_encodedSignal = unipolar(m_message, m_invertedSignal);
                break;
        }

        return this;
    }

    public List<String> getModulationTypes() {
        List<String> modulationTypes = new ArrayList<>();

        for (modulationType c : modulationType.values()) {
            modulationTypes.add(c.toString().toLowerCase());
        }
        return modulationTypes;
    }

    public List<Point2D> getPoints() {
        return m_encodedSignal;
    }

    public Boolean isInverted() {
        return m_invertedSignal;
    }

    public DigitalSignal setInverted(Boolean inverted) {
        m_invertedSignal = inverted;
        encodeMessage();
        return this;
    }

    @Override
    public DigitalSignal setMessage(String message) {
        m_message = message;
        encodeMessage();

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

    public enum modulationType {

        BIPOLAR, CMI, MANCHESTER, NRZ_L, NRZ_M, NRZ_S,
        MANCHESTERDIFFERENTIAL, MILLER, MLT3, PSEUDOTERNARY, RZ, UNIPOLAR
        //, BIPHASE_M, BIPHASE_S
    }
}
