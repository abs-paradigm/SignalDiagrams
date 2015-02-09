/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Signal;

import static Encoder.SignalEncoders.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;

/**
 *
 * @author Dom
 */
public class Signal extends SignalAbstract {

    private modulationType m_signalType;

    public ObservableList<Point2D> signalList = FXCollections.observableArrayList();

    ;

    public Signal() {
    }

    public Signal(String message, modulationType type) {
        m_signalType = type;
        setMessage(message);
    }

    private Signal encodeMessage() {
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

    public Signal setType(modulationType type) {
        m_signalType = type;
        encodeMessage();
        return this;
    }

    public Signal setType(String type) {

        if (modulationType.BIPOLAR.name().equals(type.toUpperCase())) {
            m_signalType = modulationType.BIPOLAR;
        } else if (modulationType.MANCHESTER.name().equals(type.toUpperCase())) {
            m_signalType = modulationType.MANCHESTER;
        } else if (modulationType.MILLER.name().equals(type.toUpperCase())) {
            m_signalType = modulationType.MILLER;
        } else if (modulationType.NRZ.name().equals(type.toUpperCase())) {
            m_signalType = modulationType.NRZ;
        } else if (modulationType.RZ.name().equals(type.toUpperCase())) {
            m_signalType = modulationType.RZ;
        } else if (modulationType.UNIPOLAR.name().equals(type.toUpperCase())) {
            m_signalType = modulationType.UNIPOLAR;
        }

        encodeMessage();
        return this;
    }

    @Override
    protected SignalAbstract updateSignal() {
        return this;
    }

    @Override
    public Signal setMessage(String message) {
        m_message = message;
        encodeMessage();

        return this;
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
