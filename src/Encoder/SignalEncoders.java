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

            if ((i - 1) >= 0) {
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
            char previousBit;

            if ((i - 1) >= 0) {
                previousBit = message.charAt(i - 1);
            } else {
                previousBit = currentBit == '0' ? '0' : '1';
            }

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

            } else if (currentBit == '1' && previousBit == '1') {
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
        double y = .5;

        for (int i = 0; i < message.length(); i++) {

            char currentBit = message.charAt(i);
            char previousBit = '-';
            char nextBit;
            Boolean isNotFirstPoint = i > 0;
            Point2D lastPoint = new Point2D(i, -y);

            if ((i - 1) >= 0) {
                previousBit = message.charAt(i - 1);
            }

            if (isNotFirstPoint) {
                lastPoint = encodedSignal.get(encodedSignal.size() - 1);
            }

            if ((i + 1) >= message.length()) {

                nextBit = currentBit == '0' ? '0' : '1';
            } else {
                nextBit = message.charAt(i + 1);
            }

            if (currentBit == '0' && !isNotFirstPoint) {
                encodedSignal.add(new Point2D(i, lastPoint.getY()));
                encodedSignal.add(new Point2D(i + 1, lastPoint.getY()));
            }

            if (currentBit == '0' && previousBit == '0') {
                encodedSignal.add(new Point2D(i, lastPoint.getY()));
                encodedSignal.add(new Point2D(i, -lastPoint.getY()));
                encodedSignal.add(new Point2D(i + 1, -lastPoint.getY()));
            }
            if (currentBit == '0' && previousBit == '1') {
                encodedSignal.add(new Point2D(i, lastPoint.getY()));
                encodedSignal.add(new Point2D(i + 1, lastPoint.getY()));
            }
            if (currentBit == '1') {
                encodedSignal.add(new Point2D(i, lastPoint.getY()));
                encodedSignal.add(new Point2D(i + .5, lastPoint.getY()));
                encodedSignal.add(new Point2D(i + .5, -lastPoint.getY()));
                encodedSignal.add(new Point2D(i + 1, -lastPoint.getY()));

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
