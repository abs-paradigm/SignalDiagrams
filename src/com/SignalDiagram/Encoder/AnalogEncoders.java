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

            if (currentBit == '1') {
                encodedSignal.add(new Point2D(last_xValue, 0));
                encodedSignal.add(CubicBezier.getControlPointTangentTo(new Point2D(last_xValue, 0), new Point2D(last_xValue + (Math.PI), 0), y));
                encodedSignal.add(new Point2D(last_xValue + (Math.PI), 0));
                encodedSignal.add(CubicBezier.getControlPointTangentTo(new Point2D(last_xValue + (Math.PI), 0), new Point2D(last_xValue + (Math.PI * 2), 0), -y));
                encodedSignal.add(new Point2D(last_xValue + (2 * Math.PI), 0));

                last_xValue = last_xValue + (Math.PI * 2);

            } else if (currentBit == '0') {
                encodedSignal.add(new Point2D(last_xValue, 0));
                encodedSignal.add(CubicBezier.getControlPointTangentTo(new Point2D(last_xValue, 0), new Point2D(last_xValue + (Math.PI), 0), -y));
                encodedSignal.add(new Point2D(last_xValue + (Math.PI), 0));
                encodedSignal.add(CubicBezier.getControlPointTangentTo(new Point2D(last_xValue + (Math.PI), 0), new Point2D(last_xValue + (Math.PI * 2), 0), y));
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

        int outcomesNb = (int) Math.pow(2, nbBits);
        //double frequenceNb = 1;

        int[] orderArray;
        orderArray = initArray(outcomesNb);
        orderArray = seed != -1 ? MathUtility.FisherYatesShuffle(orderArray, seed) : orderArray;

        int subIndex = 0;
        String partialMessage = "";

        for (int i = 0; i < (message.length() / nbBits); i++) {

            partialMessage = message.substring(subIndex, subIndex + nbBits);
            int intValOfParialMessage = Integer.parseInt(partialMessage, 2);

            double d = Math.PI / orderArray[intValOfParialMessage];

            for (int j = 0; j < intValOfParialMessage + 1; j++) {
                encodedSignal.add(new Point2D(last_xValue, 0));
                encodedSignal.add(CubicBezier.getControlPointTangentTo(new Point2D(last_xValue, 0), new Point2D(last_xValue + d, 0), y));
                encodedSignal.add(new Point2D(last_xValue + d, 0));

                encodedSignal.add(CubicBezier.getControlPointTangentTo(new Point2D(last_xValue + d, 0), new Point2D(last_xValue + 2 * d, 0), -y));
                encodedSignal.add(new Point2D(last_xValue + (2 * d), 0));

                last_xValue = last_xValue + (2 * d);
            }

        }

        int messageEnd = message.length() % nbBits;

        if (messageEnd > 0) {

            partialMessage = message.substring(subIndex, subIndex + nbBits);
            int intValOfParialMessage = Integer.parseInt(partialMessage, 2);

            double d = Math.PI / orderArray[intValOfParialMessage];

            for (int j = 0; j < intValOfParialMessage + 1; j++) {
                encodedSignal.add(new Point2D(last_xValue, 0));
                encodedSignal.add(CubicBezier.getControlPointTangentTo(new Point2D(last_xValue, 0), new Point2D(last_xValue + d, 0), y));
                encodedSignal.add(new Point2D(last_xValue + d, 0));

                encodedSignal.add(CubicBezier.getControlPointTangentTo(new Point2D(last_xValue + d, 0), new Point2D(last_xValue + 2 * d, 0), -y));
                encodedSignal.add(new Point2D(last_xValue + (2 * d), 0));

                last_xValue = last_xValue + (2 * d);
            }
        }
        return encodedSignal;
    }

    public static List<Point2D> amplitude(String message, int nbBits, int seed) {

        List<Point2D> encodedSignal = new ArrayList();
        double y = 1;
        double last_xValue = 0;

        int outcomesNb = (int) Math.pow(2, nbBits);
        double AmplitudeNb = 1;

        int[] orderArray;
        orderArray = initArray(outcomesNb);
        orderArray = seed != -1 ? MathUtility.FisherYatesShuffle(orderArray, seed) : orderArray;

        int subIndex = 0;
        String partialMessage = "";

        for (int i = 0; i < (message.length() / nbBits); i++) {

            partialMessage = message.substring(subIndex, subIndex + nbBits);
            int intValOfParialMessage = Integer.parseInt(partialMessage, 2);
            double h = orderArray[intValOfParialMessage] / (double) outcomesNb;

            encodedSignal.add(new Point2D(last_xValue, 0));
            encodedSignal.add(CubicBezier.getControlPointTangentTo(new Point2D(last_xValue, 0), new Point2D(last_xValue + (Math.PI), 0), h));
            encodedSignal.add(new Point2D(last_xValue + (Math.PI), 0));
            encodedSignal.add(CubicBezier.getControlPointTangentTo(new Point2D(last_xValue + (Math.PI), 0), new Point2D(last_xValue + (Math.PI * 2), 0), -h));
            encodedSignal.add(new Point2D(last_xValue + (2 * Math.PI), 0));

            last_xValue = last_xValue + (Math.PI * 2);
            subIndex = subIndex + nbBits;

        }
        int messageEnd = message.length() % nbBits;

        if (messageEnd > 0) {

            partialMessage = message.substring(subIndex, message.length());

            int intValOfParialMessage = Integer.parseInt(partialMessage, 2);
            double h = orderArray[intValOfParialMessage] / outcomesNb;

            encodedSignal.add(new Point2D(last_xValue, 0));
            encodedSignal.add(CubicBezier.getControlPointTangentTo(new Point2D(last_xValue, 0), new Point2D(last_xValue + (Math.PI), 0), h));
            encodedSignal.add(new Point2D(last_xValue + (Math.PI), 0));
            encodedSignal.add(CubicBezier.getControlPointTangentTo(new Point2D(last_xValue + (Math.PI), 0), new Point2D(last_xValue + (Math.PI * 2), 0), -h));
            encodedSignal.add(new Point2D(last_xValue + (2 * Math.PI), 0));
        }
        return encodedSignal;
    }

    private static int[] initArray(int arraySize) {
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = i + 1;
        }
        return array;
    }

}
