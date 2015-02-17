package com.SignalDiagram.Encoder;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

public class DigitalEncoders {

    public static List<Point2D> nrz(String message, Boolean inverted) {

        List<Point2D> encodedSignal = new ArrayList();
        double y = inverted ? -.5 : .5;

        for (int i = 0; i < message.length(); i++) {
            char currentBit = message.charAt(i);
            char previousBit;
            Boolean isFirstBit = true;

            if ((i - 1) >= 0) {
                previousBit = message.charAt(i - 1);
            } else {
                previousBit = currentBit == '0' ? '0' : '1';
            }

            Boolean invertYvalue;

            if ((previousBit == '0' && currentBit == '1') || (previousBit == '1' && currentBit == '0')) {
                invertYvalue = currentBit == '0';
                int invertFactor = invertYvalue ? -1 : 1;
                encodedSignal.add(new Point2D(i, -y * invertFactor));
                encodedSignal.add(new Point2D(i, y * invertFactor));
                encodedSignal.add(new Point2D(i + 1, y * invertFactor));

            } else if ((previousBit == '1' && currentBit == '1') || (previousBit == '0' && currentBit == '0')) {
                invertYvalue = currentBit == '0';
                int invertFactor = invertYvalue ? -1 : 1;
                encodedSignal.add(new Point2D(i, y * invertFactor));
                encodedSignal.add(new Point2D(i + 1, y * invertFactor));

            }
        }
        return encodedSignal;
    }

    public static List<Point2D> manchester(String message) {

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
            Boolean invertYvalue;

            if ((currentBit == '0' && previousBit == '0') || (currentBit == '1' && previousBit == '1')) {
                invertYvalue = previousBit == '1';
                int invertFactor = invertYvalue ? -1 : 1;
                encodedSignal.add(new Point2D(i, y * invertFactor));
                encodedSignal.add(new Point2D(i, -y * invertFactor));
                encodedSignal.add(new Point2D(i + .5, -y * invertFactor));
                encodedSignal.add(new Point2D(i + .5, y * invertFactor));
                encodedSignal.add(new Point2D(i + 1, y * invertFactor));

            } else if ((currentBit == '0' && previousBit == '1') || (currentBit == '1' && previousBit == '0')) {
                invertYvalue = previousBit == '0';
                int invertFactor = invertYvalue ? -1 : 1;
                encodedSignal.add(new Point2D(i, -y * invertFactor));
                encodedSignal.add(new Point2D(i + .5, -y * invertFactor));
                encodedSignal.add(new Point2D(i + .5, y * invertFactor));
                encodedSignal.add(new Point2D(i + 1, y * invertFactor));

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
            char previousBit = i - 1 >= 0 ? message.charAt(i - 1) : '0';
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

    private Boolean isZeroAndOne(String message) {
        for (int i = 0; i < message.length(); i++) {
            if (!(message.charAt(i) == '0' || message.charAt(i) == '1')) {
                return false;
            }
        }
        return true;
    }

}
