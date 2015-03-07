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

    public static List<List<Point2D>> amplitude(String message, int nbBits, int seed) {
        List<Point2D> partialSignal = new ArrayList();
        List<List<Point2D>> encodedSignal = new ArrayList();
        double last_xValue = 0;
        
        int harmonic = (int) Math.pow(2, nbBits);
        
        int[] orderArray;
        orderArray = initOrderedArray(harmonic);
        orderArray = seed != 0 ? MathUtility.FisherYatesShuffle(orderArray, seed) : orderArray;
        
        int subIndex = 0;
        String partialMessage = "";
        
        for (int i = 0; i < (message.length() / nbBits); i++) {
            
            partialMessage = message.substring(subIndex, subIndex + nbBits);
            int intValOfParialMessage = Integer.parseInt(partialMessage, 2);
            double h = orderArray[intValOfParialMessage] / (double) harmonic;
            
            partialSignal.addAll(analog(h, 1, 1, i));
            
            last_xValue = last_xValue + (Math.PI * 2);
            subIndex = subIndex + nbBits;
            
        }
        int messageEnd = message.length() % nbBits;
        
        if (messageEnd > 0) {
            
            partialMessage = message.substring(subIndex, message.length());
            
            int intValOfParialMessage = Integer.parseInt(partialMessage, 2);
            double h = orderArray[intValOfParialMessage] / harmonic;
            partialSignal.addAll(analog(h, 1, 1, (message.length() - messageEnd)));
            
        }
        encodedSignal.add(partialSignal);
        return encodedSignal;
    }
    
    private static List<Point2D> analog(double amplitude, double frequence, double phase, double startingX) {
        List<Point2D> encodedSignal = new ArrayList();
        double a = amplitude;
        double f = frequence;
        double p = 1.5 * Math.PI; // phase
        int subdivision = 100;
        for (int i = 0; i <= 1 * subdivision; i++) {
            // i = time
            encodedSignal.add(new Point2D(startingX + (i / (double) subdivision), (a * Math.cos(2 * Math.PI * f * i / subdivision + p))));
            // System.out.println("x: " + i + " y: " + (a * Math.cos(2 * Math.PI * f * i/inc + p)));
        }
        
        return encodedSignal;
    }
    
    public static List<List<Point2D>> baseSignal(String message) {
        List<List<Point2D>> encodedSignal = new ArrayList();
        List<Point2D> partialSignal = new ArrayList();
        double y = 1;
        double last_xValue = 0;

        for (int i = 0; i < message.length(); i++) {
            char currentBit = message.charAt(i);
            List<Point2D> controlPoints;
            if (currentBit == '1') {
                partialSignal.add(new Point2D(last_xValue, 0));
                controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue, 0), new Point2D(last_xValue + (Math.PI), 0), y);
                for (Point2D p : controlPoints) {
                    partialSignal.add(p);
                }
                partialSignal.add(new Point2D(last_xValue + (Math.PI), 0));
                controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue + (Math.PI), 0), new Point2D(last_xValue + (Math.PI * 2), 0), -y);
                for (Point2D p : controlPoints) {
                    partialSignal.add(p);
                }
                partialSignal.add(new Point2D(last_xValue + (2 * Math.PI), 0));

                last_xValue = last_xValue + (Math.PI * 2);

            } else if (currentBit == '0') {
                partialSignal.add(new Point2D(last_xValue, 0));
                controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue, 0), new Point2D(last_xValue + (Math.PI), 0), -y);
                for (Point2D p : controlPoints) {
                    partialSignal.add(p);
                }
                partialSignal.add(new Point2D(last_xValue + (Math.PI), 0));
                controlPoints = CubicBezier.getControlPointTangentTo(new Point2D(last_xValue + (Math.PI), 0), new Point2D(last_xValue + (Math.PI * 2), 0), y);
                for (Point2D p : controlPoints) {
                    partialSignal.add(p);
                }
                partialSignal.add(new Point2D(last_xValue + (2 * Math.PI), 0));

                last_xValue = last_xValue + (Math.PI * 2);
            }
        }
        encodedSignal.add(partialSignal);

        return encodedSignal;
    }

    public static List<List<Point2D>> frequence(String message, int nbBits, int seed) {
        List<Point2D> partialSignal = new ArrayList();
        List<List<Point2D>> encodedSignal = new ArrayList();
        double amplitude = 1;
        double last_xValue = 0;
        int harmonic = (int) Math.pow(2, nbBits);

        int[] orderArray;
        orderArray = initOrderedArray(harmonic);

        orderArray = seed == 0 ? orderArray : MathUtility.FisherYatesShuffle(orderArray, seed);

        int subIndex = 0;
        String partialMessage = "";

        for (int i = 0; i < (message.length() / nbBits); i++) {
            partialSignal = new ArrayList();
            partialMessage = message.substring(subIndex, subIndex + nbBits);
            int intValOfParialMessage = Integer.parseInt(partialMessage, 2);
            double frequence = (double) orderArray[intValOfParialMessage];

            partialSignal.addAll(analog(1, frequence, 1, last_xValue));

            last_xValue = last_xValue + 1;;
            encodedSignal.add(partialSignal);
            subIndex = subIndex + nbBits;
            encodedSignal.add(partialSignal);
        }

        int messageEnd = message.length() % nbBits;

        if (messageEnd > 0) {
            partialMessage = message.substring(subIndex, subIndex + messageEnd);
            int intValOfParialMessage = Integer.parseInt(partialMessage, 2);
            double frequence = Math.PI / orderArray[intValOfParialMessage];
            partialSignal.addAll(analog(1, frequence, 1, last_xValue));
        }
        encodedSignal.add(partialSignal);

        return encodedSignal;
    }

    private static int[] initOrderedArray(int arraySize) {
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    public static List<List<Point2D>> phase(String message, int nbBits, int seed) {
        List<Point2D> partialSignal = new ArrayList();
        List<List<Point2D>> encodedSignal = new ArrayList();

        encodedSignal.add(partialSignal);
        return encodedSignal;
    }

}
