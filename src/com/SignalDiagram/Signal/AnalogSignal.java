package com.SignalDiagram.Signal;

import static com.SignalDiagram.Encoder.AnalogEncoders.*;
import java.util.List;
import javafx.geometry.Point2D;

public class AnalogSignal extends AbstractSignal {

    private final int MAXARRAYSIZE = 28;

    private analogType m_analoglType;
    protected List<List<Point2D>> m_encodedSignal;
    private int m_nbBits = 1;
    private int m_seed = 0;

    public AnalogSignal(String message, analogType signalTypes) {
        m_message = message;
        m_analoglType = signalTypes;
        initMessage(message);

        //setSignal(type);
    }

    public AnalogSignal() {
    }

    @Override
    protected AnalogSignal encodeMessage() {

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

    public int getNbBits() {

        return m_nbBits;
    }

    public List<List<Point2D>> getPoints() {
        return m_encodedSignal;
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

    public AnalogSignal setNbBits(int nbBits) {
        if (nbBits <= MAXARRAYSIZE) {
            m_nbBits = nbBits;
            encodeMessage();
        }

        return this;
    }

    public AnalogSignal setSeed(int seed) {
        if (seed <= Integer.MAX_VALUE) {
            m_seed = seed;
            encodeMessage();
        }

        return this;
    }

    public AnalogSignal setType(analogType type) {
        m_analoglType = type;
        encodeMessage();
        return this;
    }

    public enum analogType {

        NORMAL, FREQUENCE, AMPLITUDE, PHASE
    }

}
