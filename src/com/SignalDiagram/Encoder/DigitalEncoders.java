package com.SignalDiagram.Encoder;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

public class DigitalEncoders {

    public static List<Point2D> nrz_l(String message, Boolean inverted) {

        List<Point2D> encodedSignal = new ArrayList();
        double volt = inverted ? -1 : 1;

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
                encodedSignal.add(new Point2D(i, -volt * invertFactor));
                encodedSignal.add(new Point2D(i, volt * invertFactor));
                encodedSignal.add(new Point2D(i + 1, volt * invertFactor));

            } else if ((previousBit == '1' && currentBit == '1') || (previousBit == '0' && currentBit == '0')) {
                invertYvalue = currentBit == '0';
                int invertFactor = invertYvalue ? -1 : 1;
                encodedSignal.add(new Point2D(i, volt * invertFactor));
                encodedSignal.add(new Point2D(i + 1, volt * invertFactor));

            }
        }
        return encodedSignal;
    }

    public static List<Point2D> nrz_m(String message) {

        List<Point2D> encodedSignal = new ArrayList();
        double volt = 1;//inverted ? -.5 : .5;
        Boolean change = false;
        Boolean invertYvalue = true;

        for (int i = 0; i < message.length(); i++) {
            char currentBit = message.charAt(i);
            char previousBit;

            if ((i - 1) >= 0) {
                previousBit = message.charAt(i - 1);
            } else {
                previousBit = currentBit == '0' ? '0' : '1';
            }

            if (currentBit == '0') {
                int invertFactor = invertYvalue ? 0 : 1;
                encodedSignal.add(new Point2D(i, volt * invertFactor));
                encodedSignal.add(new Point2D(i + 1, volt * invertFactor));

            } else if (currentBit == '1') {
                int invertFactor = invertYvalue ? 1 : 0;
                encodedSignal.add(new Point2D(i, invertFactor));
                encodedSignal.add(new Point2D(i, -volt * -invertFactor));
                encodedSignal.add(new Point2D(i + 1, -volt * -invertFactor));
                invertYvalue = !invertYvalue;

            }

        }
        return encodedSignal;
    }
    
    public static List<Point2D> nrz_s(String message) {

        List<Point2D> encodedSignal = new ArrayList();
        double volt = 1;//inverted ? -.5 : .5;
        Boolean change = false;
        Boolean invertYvalue = true;

        for (int i = 0; i < message.length(); i++) {
            char currentBit = message.charAt(i);
            char previousBit;

            if ((i - 1) >= 0) {
                previousBit = message.charAt(i - 1);
            } else {
                previousBit = currentBit == '0' ? '0' : '1';
            }

            if (currentBit == '1') {
                int invertFactor = invertYvalue ? 0 : 1;
                encodedSignal.add(new Point2D(i, volt * invertFactor));
                encodedSignal.add(new Point2D(i + 1, volt * invertFactor));

            } else if (currentBit == '0') {
                int invertFactor = invertYvalue ? 1 : 0;
                encodedSignal.add(new Point2D(i, invertFactor));
                encodedSignal.add(new Point2D(i, -volt * -invertFactor));
                encodedSignal.add(new Point2D(i + 1, -volt * -invertFactor));
                invertYvalue = !invertYvalue;

            }

        }
        return encodedSignal;
    }

    public static List<Point2D> manchester(String message) {

        List<Point2D> encodedSignal = new ArrayList();
        double volt = 1;

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
                encodedSignal.add(new Point2D(i, volt * invertFactor));
                encodedSignal.add(new Point2D(i, -volt * invertFactor));
                encodedSignal.add(new Point2D(i + .5, -volt * invertFactor));
                encodedSignal.add(new Point2D(i + .5, volt * invertFactor));
                encodedSignal.add(new Point2D(i + 1, volt * invertFactor));

            } else if ((currentBit == '0' && previousBit == '1') || (currentBit == '1' && previousBit == '0')) {
                invertYvalue = previousBit == '0';
                int invertFactor = invertYvalue ? -1 : 1;
                encodedSignal.add(new Point2D(i, -volt * invertFactor));
                encodedSignal.add(new Point2D(i + .5, -volt * invertFactor));
                encodedSignal.add(new Point2D(i + .5, volt * invertFactor));
                encodedSignal.add(new Point2D(i + 1, volt * invertFactor));

            }
        }

        return encodedSignal;
    }

    public static List<Point2D> rz(String message) {

        List<Point2D> encodedSignal = new ArrayList();
        double volt = 1;

        for (int i = 0; i < message.length(); i++) {
            char currentBit = message.charAt(i);
            if (currentBit == '0') {
                encodedSignal.add(new Point2D(i, 0));
                encodedSignal.add(new Point2D(i + 1, 0));

            } else if (currentBit == '1') {
                encodedSignal.add(new Point2D(i, 0));
                encodedSignal.add(new Point2D(i, volt));
                encodedSignal.add(new Point2D(i + .5, volt));
                encodedSignal.add(new Point2D(i + .5, 0));
                encodedSignal.add(new Point2D(i + 1, 0));
            }
        }

        return encodedSignal;
    }

    public static List<Point2D> cmi(String message) {

        List<Point2D> encodedSignal = new ArrayList();
        double volt = 1;

        for (int i = 0; i < message.length(); i++) {
            char currentBit = message.charAt(i);
            if (currentBit == '1') {
                encodedSignal.add(new Point2D(i, volt));
                encodedSignal.add(new Point2D(i + 1, volt));

            } else if (currentBit == '0') {
                encodedSignal.add(new Point2D(i, 0));
                encodedSignal.add(new Point2D(i + .5, 0));
                encodedSignal.add(new Point2D(i + .5, volt));
                encodedSignal.add(new Point2D(i + 1, volt));
            }
        }

        return encodedSignal;
    }

    // AMI
    public static List<Point2D> bipolar(String message) {

        List<Point2D> encodedSignal = new ArrayList();
        Boolean positif = true;
        for (int i = 0; i < message.length(); i++) {
            char currentBit = message.charAt(i);
            if (currentBit == '1') {
                double volt = positif == true ? 1 : -1;
                positif = positif != true;

                encodedSignal.add(new Point2D(i, 0));
                encodedSignal.add(new Point2D(i, volt));
                encodedSignal.add(new Point2D(i + 1, volt));
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
        double volt = 1;

        for (int i = 0; i < message.length(); i++) {
            char previousBit = i - 1 >= 0 ? message.charAt(i - 1) : '0';
            char currentBit = message.charAt(i);

            if (previousBit == '0' && currentBit == '1') {
                encodedSignal.add(new Point2D(i, 0));
                encodedSignal.add(new Point2D(i, volt));
                encodedSignal.add(new Point2D(i + 1, volt));

            } else if (previousBit == '1' && currentBit == '1') {
                encodedSignal.add(new Point2D(i, volt));
                encodedSignal.add(new Point2D(i + 1, volt));

            } else if (previousBit == '1' && currentBit == '0') {
                encodedSignal.add(new Point2D(i, volt));
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
        double volt = 1;

        for (int i = 0; i < message.length(); i++) {

            char currentBit = message.charAt(i);
            char previousBit = '-';
            char nextBit;
            Boolean isNotFirstPoint = i > 0;
            Point2D lastPoint = new Point2D(i, -volt);

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

    public static List<Point2D> mlt3(String message) {

        List<Point2D> encodedSignal = new ArrayList();
        Boolean progressing = true;
        double lastVolt = 0;

        for (int i = 0; i < message.length(); i++) {
            char currentBit = message.charAt(i);

            if (currentBit == '1' && progressing && (lastVolt == -1 || lastVolt == 0)) {
                encodedSignal.add(new Point2D(i, lastVolt));
                lastVolt += 1;
                encodedSignal.add(new Point2D(i, lastVolt));
                encodedSignal.add(new Point2D(i + 1, lastVolt));
                progressing = lastVolt != 1;

            } else if (currentBit == '1' && !progressing && (lastVolt == 1 || lastVolt == 0)) {

                encodedSignal.add(new Point2D(i, lastVolt));
                lastVolt += -1;
                encodedSignal.add(new Point2D(i, lastVolt));
                encodedSignal.add(new Point2D(i + 1, lastVolt));
                progressing = lastVolt == -1;

            } else if (currentBit == '0') {
                encodedSignal.add(new Point2D(i, lastVolt));
                encodedSignal.add(new Point2D(i + 1, lastVolt));
            }
        }

        return encodedSignal;
    }

    public static List<Point2D> manchesterDifferential(String message) {

        List<Point2D> encodedSignal = new ArrayList();
        double volt = 1;
        int invertFactor = 1;

        for (int i = 0; i < message.length(); i++) {
            char currentBit = message.charAt(i);

            Boolean invertYvalue;

            if (currentBit == '1') {
                invertFactor = invertFactor * -1;
                // remove first point
                encodedSignal.add(new Point2D(i, -volt * invertFactor));
                encodedSignal.add(new Point2D(i + .5, -volt * invertFactor));
                encodedSignal.add(new Point2D(i + .5, volt * invertFactor));
                encodedSignal.add(new Point2D(i + 1, volt * invertFactor));

            } else if (currentBit == '0') {
                // remove first point
                encodedSignal.add(new Point2D(i, volt * invertFactor));
                encodedSignal.add(new Point2D(i, -volt * invertFactor));
                encodedSignal.add(new Point2D(i + .5, -volt * invertFactor));
                encodedSignal.add(new Point2D(i + .5, volt * invertFactor));
                encodedSignal.add(new Point2D(i + 1, volt * invertFactor));

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
