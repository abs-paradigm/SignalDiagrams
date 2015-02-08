/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Signal;

import static Encoder.SignalEncoding.*;

/**
 *
 * @author Dom
 */
public class Signal extends SignalAbstract {

    private modulationType m_signalType;

    public Signal() {
    }

    public Signal(String message, modulationType type) {
        m_message = message;
        m_signalType = type;

        switch (type) {
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
    }

    public Signal setType(modulationType type) {
        switch (type) {
            case BIPOLAR:
                m_encodedSignal = bipolar(m_message);
            case NRZ:
                m_encodedSignal = nrz(m_message);
            case RZ:
                m_encodedSignal = rz(m_message);
            case MANCHESTER:
                m_encodedSignal = manchester(m_message);
            case MILLER:
                m_encodedSignal = miller(m_message);
        }
        return this;
    }

    @Override
    protected SignalAbstract updateSignal() {
        return this;
    }

    public enum modulationType {

        NRZ, RZ, UNIPOLAR, BIPOLAR, MANCHESTER, MILLER
    }
}
