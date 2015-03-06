package com.SignalDiagram.Signal;

import static com.SignalDiagram.Encoder.AnalogEncoders.*;

public class AnalogSignal extends AbstractSignal {

    private analogType m_analoglType;
    private int m_nbBits = 1;

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
    protected AbstractSignal updateSignal() {
        return this;
    }

    private AnalogSignal encodeMessage() {
        System.out.println("m_signalType: " + m_analoglType);

        switch (m_analoglType) {
            case NORMAL:
                m_encodedSignal = baseSignal(m_message);
                break;
            case FREQUENCE:
                m_encodedSignal = frequence(m_message, m_nbBits, -1);
                break;
            case AMPLITUDE:
                m_encodedSignal = amplitude(m_message, m_nbBits, -1);
                break;
            case PHASE:
                m_encodedSignal = phase(m_message, m_nbBits, -1);
                break;

        }

        return this;
    }

    public AnalogSignal setType(analogType type) {
        m_analoglType = type;
        encodeMessage();
        return this;
    }

    public AnalogSignal setNbBits(int nbBits) {
        m_nbBits = nbBits;
        encodeMessage();
        return this;
    }

    @Override
    public AnalogSignal setMessage(String message) {
        m_message = message;
        encodeMessage();

        return this;
    }

    public enum analogType {

        NORMAL, FREQUENCE, AMPLITUDE, PHASE
    }

}
