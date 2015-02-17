/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram.EncoderTest;

import com.SignalDiagram.Encoder.AnalogEncoders;
import com.SignalDiagram.Util.MathUtility;
import java.util.List;
import javafx.geometry.Point2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dom
 */
public class AnalogJUnitTest {

    public AnalogJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testBaseSignalEncoder() {
        List<Point2D> test = AnalogEncoders.baseSignal("1");
        System.out.println("size: " + test.size());
        for (Point2D p : test) {
            System.out.println("x: " + p.getX() + " y: " + p.getY());
        }
    }

    @Test
    public void testAmplitudeSignalEncoder() {
        List<Point2D> test = AnalogEncoders.amplitude("0001101011010101101011100010110101", 3, -1);
        for (Point2D p : test) {
            System.out.println("x: " + p.getX() + " y: " + p.getY());
        }
    }

    @Test
    public void testFrquenceSignalEncoder() {
        List<Point2D> test = AnalogEncoders.frequence("0001101011010101101011100010110101", 3, -1);
        for (Point2D p : test) {
            System.out.println("x: " + p.getX() + " y: " + p.getY());
        }
    }

    @Test
    public void testFisherYatesShuffle() {
        int[] test = {0, 1, 2, 3, 4, 5, 6, 7};
        test = MathUtility.FisherYatesShuffle(test, 2);

        for (int i = 0; i < test.length; i++) {
            System.out.println("x: " + test[i]);
        }
        System.out.println("x----: " + 8 / 7);
    }

}
