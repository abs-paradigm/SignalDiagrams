package com.SignalDiagram.Signal;

import static com.SignalDiagram.Encoder.AnalogEncoders.*;
import java.util.List;
import javafx.geometry.Point2D;

public class AnalogSignal extends AbstractSignal {

    private analogType m_analoglType;
    private int m_seed = 0;
    private int m_nbBits = 1;
    protected List<List<Point2D>> m_encodedSignal;

    public AnalogSignal(String message, analogType signalTypes) {
        m_message = message;
        m_analoglType = signalTypes;
        initMessage(message);

        //setSignal(type);
    }

    public AnalogSignal() {
    }

    public List<List<Point2D>> getPoints() {
        return m_encodedSignal;
    }

    @Override
    protected AnalogSignal encodeMessage() {
        System.out.println("m_signalType: " + m_analoglType);

        switch (m_analoglType) {
            case NORMAL:
                m_encodedSignal = baseSignal(m_message);
                return this;
            case FREQUENCE:
                m_encodedSignal = frequence(m_message, m_nbBits, m_seed);
                return this;
            case AMPLITUDE:
                m_encodedSignal = amplitude(m_message, m_nbBits, m_seed);
                return this;
            case PHASE:
                m_encodedSignal = phase(m_message, m_nbBits, m_seed);
                return this;

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

    public AnalogSignal setSeed(int seed) {
        m_seed = seed;
        encodeMessage();
        return this;
    }

    public int getSeed() {
        return m_seed;
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
