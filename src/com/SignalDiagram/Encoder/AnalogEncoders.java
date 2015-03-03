/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram.Encoder;

import com.SignalDiagram.Util.MathUtility;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

/**
 *
 * @author Dom
 */
public class AnalogEncoders {

    public static List<Point2D> baseSignal(String message) {

        List<Point2D> encodedSignal = new ArrayList();
        double y = 1;
        double last_xValue = 0;

        for (int i = 0; i < message.length(); i++) {
            char currentBit = message.charAt(i);
            List<Point2D> controlPoints;
            if (currentBit == '1') {
                encodedSignal.add(new Point2D(last_xValue, 0));
                controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue, 0), new Point2D(last_xValue + (Math.PI), 0), y);
                for (Point2D p : controlPoints) {
                    encodedSignal.add(p);
                }
                encodedSignal.add(new Point2D(last_xValue + (Math.PI), 0));
                controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue + (Math.PI), 0), new Point2D(last_xValue + (Math.PI * 2), 0), -y);
                for (Point2D p : controlPoints) {
                    encodedSignal.add(p);
                }
                encodedSignal.add(new Point2D(last_xValue + (2 * Math.PI), 0));

                last_xValue = last_xValue + (Math.PI * 2);

            } else if (currentBit == '0') {
                encodedSignal.add(new Point2D(last_xValue, 0));
                controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue, 0), new Point2D(last_xValue + (Math.PI), 0), -y);
                for (Point2D p : controlPoints) {
                    encodedSignal.add(p);
                }
                encodedSignal.add(new Point2D(last_xValue + (Math.PI), 0));
                controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue + (Math.PI), 0), new Point2D(last_xValue + (Math.PI * 2), 0), y);
                for (Point2D p : controlPoints) {
                    encodedSignal.add(p);
                }
                encodedSignal.add(new Point2D(last_xValue + (2 * Math.PI), 0));

                last_xValue = last_xValue + (Math.PI * 2);
            }
        }
        return encodedSignal;
    }

    public static List<Point2D> frequence(String message, int nbBits, int seed) {

        List<Point2D> encodedSignal = new ArrayList();
        double y = 1;
        double last_xValue = 0;
        int harmonic = (int) Math.pow(2, nbBits);

        int[] orderArray;
        orderArray = initArray(harmonic);

        orderArray = seed == -1 ? orderArray : MathUtility.FisherYatesShuffle(orderArray, seed);

        int subIndex = 0;
        String partialMessage = "";

        for (int i = 0; i < (message.length() / nbBits); i++) {

            partialMessage = message.substring(subIndex, subIndex + nbBits);
            int intValOfParialMessage = Integer.parseInt(partialMessage, 2);
            double d = Math.PI / (double) orderArray[intValOfParialMessage];
            List<Point2D> controlPoints;

            for (int j = 0; j < (intValOfParialMessage + 1); j++) {
                encodedSignal.add(new Point2D(last_xValue, 0));
                controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue, 0), new Point2D(last_xValue + (.5 * d), 0), y);
                for (Point2D p : controlPoints) {
                    encodedSignal.add(p);
                }
                encodedSignal.add(new Point2D(last_xValue + (.5 * d), 0));
                controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue + (.5 * d), 0), new Point2D(last_xValue + d, 0), -y);
                for (Point2D p : controlPoints) {
                    encodedSignal.add(p);
                }
                encodedSignal.add(new Point2D(last_xValue + (d), 0));

                last_xValue = last_xValue + (d);

            }
            subIndex = subIndex + nbBits;

        }

        int messageEnd = message.length() % nbBits;

        if (messageEnd > 0) {

            partialMessage = message.substring(subIndex, subIndex + messageEnd);
            int intValOfParialMessage = Integer.parseInt(partialMessage, 2);

            double d = Math.PI / orderArray[intValOfParialMessage];
            List<Point2D> controlPoints;

            for (int j = 0; j < intValOfParialMessage + 1; j++) {
                encodedSignal.add(new Point2D(last_xValue, 0));
                controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue, 0), new Point2D(last_xValue + (d), 0), y);
                for (Point2D p : controlPoints) {
                    encodedSignal.add(p);
                }
                encodedSignal.add(new Point2D(last_xValue + (d), 0));
                controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue + (d), 0), new Point2D(last_xValue + d * 2, 0), -y);
                for (Point2D p : controlPoints) {
                    encodedSignal.add(p);
                }
                encodedSignal.add(new Point2D(last_xValue + (d * 2), 0));

                last_xValue = last_xValue + (d * 2);
            }
        }

        return encodedSignal;
    }

    public static List<Point2D> amplitude(String message, int nbBits, int seed) {

        List<Point2D> encodedSignal = new ArrayList();
        double last_xValue = 0;

        int harmonic = (int) Math.pow(2, nbBits);

        int[] orderArray;
        orderArray = initArray(harmonic);
        orderArray = seed != -1 ? MathUtility.FisherYatesShuffle(orderArray, seed) : orderArray;

        int subIndex = 0;
        String partialMessage = "";
        List<Point2D> controlPoints;

        for (int i = 0; i < (message.length() / nbBits); i++) {

            partialMessage = message.substring(subIndex, subIndex + nbBits);
            int intValOfParialMessage = Integer.parseInt(partialMessage, 2);
            double h = orderArray[intValOfParialMessage] / (double) harmonic;

            encodedSignal.add(new Point2D(last_xValue, 0));
            controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue, 0), new Point2D(last_xValue + (Math.PI), 0), h);
            for (Point2D p : controlPoints) {
                encodedSignal.add(p);
            }
            encodedSignal.add(new Point2D(last_xValue + (Math.PI), 0));
            controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue + (Math.PI), 0), new Point2D(last_xValue + (Math.PI * 2), 0), -h);
            for (Point2D p : controlPoints) {
                encodedSignal.add(p);
            }
            encodedSignal.add(new Point2D(last_xValue + (2 * Math.PI), 0));

            last_xValue = last_xValue + (Math.PI * 2);
            subIndex = subIndex + nbBits;

        }
        int messageEnd = message.length() % nbBits;

        if (messageEnd > 0) {

            partialMessage = message.substring(subIndex, message.length());

            int intValOfParialMessage = Integer.parseInt(partialMessage, 2);
            double h = orderArray[intValOfParialMessage] / harmonic;

            encodedSignal.add(new Point2D(last_xValue, 0));
            controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue, 0), new Point2D(last_xValue + (Math.PI), 0), h);
            for (Point2D p : controlPoints) {
                encodedSignal.add(p);
            }
            encodedSignal.add(new Point2D(last_xValue + (Math.PI), 0));
            controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue + (Math.PI), 0), new Point2D(last_xValue + (Math.PI * 2), 0), -h);
            for (Point2D p : controlPoints) {
                encodedSignal.add(p);
            }
            encodedSignal.add(new Point2D(last_xValue + (2 * Math.PI), 0));
        }
        return encodedSignal;
    }

    public static List<Point2D> phase(String message, int nbBits, int seed) {
        List<Point2D> encodedSignal = new ArrayList();

        return encodedSignal;
    }

    private static int[] initArray(int arraySize) {
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    public static List<Point2D> analog(String message) {
        List<Point2D> encodedSignal = new ArrayList();
        int a = 1; // Amplitude
        int f = 1; // frequence
        int p = 1; // phase
        int t = 1; // time
        double inc= 100;
        for (int i = 0; i < message.length()*inc; i++) {

            encodedSignal.add(new Point2D(i/inc, (a * Math.cos(2 * Math.PI * f * i/inc + p))));
            //System.out.println("x: " + i + " y: " + (a * Math.cos(2 * Math.PI * f * i/inc + p)));
        }

        return encodedSignal;
    }

}
