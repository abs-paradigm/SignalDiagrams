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

    //private Enum<Integer> encodingType;
    public Signal() {
    }

    public Signal(String message, int type) {
        m_message = message;
        //m_type = type;
        m_encodedSignal = nrz(message);
        //m_encodedSignal = bipolar(message);
        //m_encodedSignal = rz(message);
        //m_encodedSignal = allOrNothing(message);
        //m_encodedSignal = manchester(message);
    }

    @Override
    protected SignalAbstract updateSignal() {
        return this;
    }
}
