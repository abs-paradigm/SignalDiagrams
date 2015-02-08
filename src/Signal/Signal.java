/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Signal;

import static Encoder.SignalEncoding.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;

/**
 *
 * @author Dom
 */
public class Signal extends SignalAbstract {

    private modulationType m_signalType;

    public ObservableList<Point2D> signalList;

    public Signal() {
    }

    public Signal(String message, modulationType type) {
        m_signalType = type;
        setMessage(message);
    }

    public ObservableList getLists() {
        return signalList;
    }

    private Signal encodeSignal() {
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
        }
        return this;
    }

    public Signal setType(modulationType type) {
        m_signalType = type;
        encodeSignal();
        return this;
    }

    @Override
    protected SignalAbstract updateSignal() {
        return this;
    }

    @Override
    public Signal setMessage(String message) {
        m_message = message;
        encodeSignal();
        signalList = FXCollections.observableList(m_encodedSignal);

        return this;
    }

    public enum modulationType {

        NRZ, RZ, UNIPOLAR, BIPOLAR, MANCHESTER, MILLER
    }
}
