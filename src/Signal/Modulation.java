package Signal;

public class Modulation extends SignalAbstract {

    private signalType[] m_signalType;

    public Modulation(String message, signalType[] signalTypes) {
        m_message = message;
        this.m_signalType = signalTypes;
        //setSignal(type);
    }

    public Modulation() {
    }

    @Override
    protected SignalAbstract updateSignal() {
        return this;
    }

    public final Modulation setSignal(signalType[] types) {

//        switch (types) {
//            case AMPLITUDE:
//                //m_encodedSignal = amplitude(m_message);
//                break;
//            case FREQUENCE:
//                //m_encodedSignal = frequence(m_message);
//                break;
//            case PHASE:
//                //m_encodedSignal = phase(m_message);
//                break;
//        }
        return this;
    }

    public enum signalType {

        FREQUENCE, AMPLITUDE, PHASE
    }

}
