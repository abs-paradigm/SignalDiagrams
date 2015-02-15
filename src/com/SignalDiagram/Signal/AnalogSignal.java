package com.SignalDiagram.Signal;

import static com.SignalDiagram.Encoder.AnalogEncoders.*;

public class AnalogSignal extends SignalAbstract {

    private analogType m_analoglType;

    public AnalogSignal(String message, analogType signalTypes) {
        m_message = message;
        m_analoglType = signalTypes;
        initMessage(message);
        //setSignal(type);
    }

    public AnalogSignal() {

    }

    private void initMessage(String message) {
        m_message = message;
        encodeMessage();
    }

    @Override
    protected SignalAbstract updateSignal() {
        return this;
    }

    private AnalogSignal encodeMessage() {
        System.out.println("m_signalType: " + m_analoglType);

        switch (m_analoglType) {
            case NORMAL:
                m_encodedSignal = baseSignal(m_message);
                break;
            case FREQUENCE:
//                m_encodedSignal = frequence(m_message);
                break;
            case AMPLITUDE:
//                m_encodedSignal = amplitude(m_message);
                break;
            case PHASE:
//                m_encodedSignal = phase(m_message);
                break;
        }

        return this;
    }

    public enum analogType {

        NORMAL, FREQUENCE, AMPLITUDE, PHASE
    }

}
