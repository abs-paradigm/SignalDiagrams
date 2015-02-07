package Signal;

public class Modulation extends SignalAbstract {

    // 0 = frequence
    // 1 = amplitude
    // 2 = phase
    //public enum modulationType {FREQUENCE, AMPLITUDE, PHASE};

    private Boolean[] modulationTypes = new Boolean[3];

    public Modulation(String message, Boolean[] modulationTypes) {
        m_message = message;
        this.modulationTypes = modulationTypes;
    }

    public Modulation() {
    }

    @Override
    protected SignalAbstract updateSignal() {
        //encode(m_message);
        return this;
    }


}
