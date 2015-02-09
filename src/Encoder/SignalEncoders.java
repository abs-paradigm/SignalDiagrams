package Encoder;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

public class SignalEncoders {

    public static List<Point2D> nrz(String message) {

        List<Point2D> encodedSignal = new ArrayList();
        double y = .5;

        for (int i = 0; i < message.length(); i++) {
            char currentBit = message.charAt(i);
            char previousBit;

            if ((i - 1) > 0) {
                previousBit = message.charAt(i - 1);
            } else {
                previousBit = currentBit == '0' ? '0' : '1';
            }

            if (previousBit == '0' && currentBit == '1') {
                encodedSignal.add(new Point2D(i, -y));
                encodedSignal.add(new Point2D(i, y));
                encodedSignal.add(new Point2D(i + 1, y));

            } else if (previousBit == '1' && currentBit == '1') {
                encodedSignal.add(new Point2D(i, y));
                encodedSignal.add(new Point2D(i + 1, y));
            }
            if (previousBit == '1' && currentBit == '0') {
                encodedSignal.add(new Point2D(i, y));
                encodedSignal.add(new Point2D(i, -y));
                encodedSignal.add(new Point2D(i + 1, -y));

            } else if (previousBit == '0' && currentBit == '0') {
                encodedSignal.add(new Point2D(i, -y));
                encodedSignal.add(new Point2D(i + 1, -y));
            }
        }
        return encodedSignal;
    }

    public static List<Point2D> manchester(String message) {

        List<Point2D> encodedSignal = new ArrayList();
        Boolean positif = true;
        double y = .5;

        for (int i = 0; i < message.length(); i++) {
            char currentBit = message.charAt(i);
            char previousBit = i - 1 > 0 ? message.charAt(i - 1) : '0';

            if (currentBit == '0' && previousBit == '0') {
                encodedSignal.add(new Point2D(i, y));
                encodedSignal.add(new Point2D(i, -y));
                encodedSignal.add(new Point2D(i + .5, -y));
                encodedSignal.add(new Point2D(i + .5, y));
                encodedSignal.add(new Point2D(i + 1, y));

            } else if (currentBit == '0' && previousBit == '1') {
                encodedSignal.add(new Point2D(i, -y));
                encodedSignal.add(new Point2D(i + .5, -y));
                encodedSignal.add(new Point2D(i + .5, y));
                encodedSignal.add(new Point2D(i + 1, y));
            }
            if (currentBit == '1' && previousBit == '1') {
                encodedSignal.add(new Point2D(i, -y));
                encodedSignal.add(new Point2D(i, y));
                encodedSignal.add(new Point2D(i + .5, y));
                encodedSignal.add(new Point2D(i + .5, -y));
                encodedSignal.add(new Point2D(i + 1, -y));

            } else if (currentBit == '1' && previousBit == '0') {
                encodedSignal.add(new Point2D(i, y));
                encodedSignal.add(new Point2D(i + .5, y));
                encodedSignal.add(new Point2D(i + .5, -y));
                encodedSignal.add(new Point2D(i + 1, -y));
            }
        }

        return encodedSignal;
    }

    public static List<Point2D> rz(String message) {

        List<Point2D> encodedSignal = new ArrayList();
        double y = 1;

        for (int i = 0; i < message.length(); i++) {
            char currentBit = message.charAt(i);
            if (currentBit == '0') {
                encodedSignal.add(new Point2D(i, 0));
                encodedSignal.add(new Point2D(i + 1, 0));

            } else if (currentBit == '1') {
                encodedSignal.add(new Point2D(i, 0));
                encodedSignal.add(new Point2D(i, y));
                encodedSignal.add(new Point2D(i + .5, y));
                encodedSignal.add(new Point2D(i + .5, 0));
                encodedSignal.add(new Point2D(i + 1, 0));
            }
        }

        return encodedSignal;
    }

    public static List<Point2D> bipolar(String message) {

        List<Point2D> encodedSignal = new ArrayList();
        Boolean positif = true;
        for (int i = 0; i < message.length(); i++) {
            char currentBit = message.charAt(i);
            if (currentBit == '1') {
                double y = positif == true ? .5 : -.5;
                positif = positif != true;

                encodedSignal.add(new Point2D(i, 0));
                encodedSignal.add(new Point2D(i, y));
                encodedSignal.add(new Point2D(i + 1, y));
                encodedSignal.add(new Point2D(i + 1, 0));

            } else {
                encodedSignal.add(new Point2D(i, 0));
                encodedSignal.add(new Point2D(i + 1, 0));
            }
        }

        return encodedSignal;
    }

    public static List<Point2D> unipolar(String message) {

        List<Point2D> encodedSignal = new ArrayList();
        double y = 1;

        for (int i = 0; i < message.length(); i++) {
            char previousBit = i - 1 > 0 ? message.charAt(i - 1) : '0';
            char currentBit = message.charAt(i);

            if (previousBit == '0' && currentBit == '1') {
                encodedSignal.add(new Point2D(i, 0));
                encodedSignal.add(new Point2D(i, y));
                encodedSignal.add(new Point2D(i + 1, y));

            } else if (previousBit == '1' && currentBit == '1') {
                encodedSignal.add(new Point2D(i, y));
                encodedSignal.add(new Point2D(i + 1, y));

            } else if (previousBit == '1' && currentBit == '0') {
                encodedSignal.add(new Point2D(i, y));
                encodedSignal.add(new Point2D(i, 0));
                encodedSignal.add(new Point2D(i + 1, 0));

            } else if (previousBit == '0' && currentBit == '0') {
                encodedSignal.add(new Point2D(i, 0));
                encodedSignal.add(new Point2D(i + 1, 0));
            }
        }

        return encodedSignal;
    }

    public static List<Point2D> miller(String message) {
        List<Point2D> encodedSignal = new ArrayList();
        Boolean positif = false;
        double y = .5;

        for (int i = 0; i < message.length(); i++) {
            char nextBit = i + 1 <= message.length() ? message.charAt(i + 1) : '0';
            char currentBit = message.charAt(i);

            //positif = positif != true; 
            if (currentBit == '0' && !positif) {
                encodedSignal.add(new Point2D(i, -y));
                encodedSignal.add(new Point2D(i + 1, -y));

            }
            if (currentBit == '1' && !positif) {
                encodedSignal.add(new Point2D(i, -y));
                encodedSignal.add(new Point2D(i + .5, -y));
                encodedSignal.add(new Point2D(i + .5, y));
                encodedSignal.add(new Point2D(i + 1, y));

                positif = positif != true;
            }
            if (currentBit == '1' && positif) {
                encodedSignal.add(new Point2D(i, y));
                encodedSignal.add(new Point2D(i + .5, y));
                encodedSignal.add(new Point2D(i + .5, -y));
                encodedSignal.add(new Point2D(i + 1, -y));

                positif = positif != true;
            }
            if (currentBit == '0' && positif) {
                //m_encodedSignal.add(new Point2D(0, 0));
                //m_encodedSignal.add(new Point2D(1, 0));
            }
        }

        return encodedSignal;
    }
    private int nbBits = 1;

    private Boolean isZeroAndOne(String message) {
        for (int i = 0; i < message.length(); i++) {
            if (!(message.charAt(i) == '0' || message.charAt(i) == '1')) {
                return false;
            }
        }
        return true;
    }

}
