/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram.Encoder;

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
        double lastPoint = 0;

        for (int i = 0; i < message.length(); i++) {
            char currentBit = message.charAt(i);

            if (currentBit == '1') {
                encodedSignal.add(new Point2D(lastPoint, 0));
                encodedSignal.add(Bezier.getControlPointTangentTo(new Point2D(lastPoint, 0), new Point2D(lastPoint + (Math.PI), 0), y));
                encodedSignal.add(new Point2D(lastPoint + (Math.PI), 0));
                encodedSignal.add(Bezier.getControlPointTangentTo(new Point2D(lastPoint + (Math.PI), 0), new Point2D(lastPoint + (Math.PI * 2), 0), -y));
                encodedSignal.add(new Point2D(lastPoint + (2 * Math.PI), 0));

                lastPoint = lastPoint + (Math.PI * 2);

            } else if (currentBit == '0') {
                encodedSignal.add(new Point2D(lastPoint, 0));
                encodedSignal.add(Bezier.getControlPointTangentTo(new Point2D(lastPoint, 0), new Point2D(lastPoint + (Math.PI * .5), 0), -y));
                encodedSignal.add(new Point2D(lastPoint + (Math.PI * .5), 0));
                encodedSignal.add(Bezier.getControlPointTangentTo(new Point2D(lastPoint + (Math.PI), 0), new Point2D(lastPoint + (Math.PI * 2), 0), y));
                encodedSignal.add(new Point2D(lastPoint + (2 * Math.PI), 0));

                lastPoint = lastPoint + (Math.PI * 2);
            }
        }
        return encodedSignal;
    }

}
