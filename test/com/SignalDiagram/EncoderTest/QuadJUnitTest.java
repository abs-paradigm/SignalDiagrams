package com.SignalDiagram.EncoderTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class QuadJUnitTest {

    public QuadJUnitTest() {
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

    @Test
    public void checkControlPointHeight() {
        int x0 = 0;
        int y0 = 1;
        int x2 = 10;
        int y2 = 1;
        Point2D firstQuad = com.SignalDiagram.Encoder.QuadCurve.getControlPointTangentTo(new Point2D(x0, y0), new Point2D(x2, y2), 6);
        System.out.println("GetTangentPoint");
        System.out.println("P1: (" + x0 + ", " + y0 + ")" + "  P2: " + "(" + x2 + ", " + y2 + ")");
        System.out.println("x: " + firstQuad.getX() + "y: " + firstQuad.getY());

        x0 = 0;
        y0 = 0;
        x2 = 10;
        y2 = 0;
        Point2D secondQuad = com.SignalDiagram.Encoder.QuadCurve.getControlPointTangentTo(new Point2D(x0, y0), new Point2D(x2, y2), 5);
        System.out.println("P1: (" + x0 + ", " + y0 + ")" + "  P2: " + "(" + x2 + ", " + y2 + ")");
        System.out.println("x: " + secondQuad.getX() + "y: " + secondQuad.getY());
    }

    @Test
    public void checkControlTangentHeight() {
        int x0 = 0;
        int y0 = 0;
        int x1 = 5;
        int y1 = 10;
        int x2 = 10;
        int y2 = 0;
        Point2D test = com.SignalDiagram.Encoder.QuadCurve.getPoint(new Point2D(x0, y0), new Point2D(x1, y1), new Point2D(x2, y2), .5);
        System.out.println("checkControlTangentHeight");
        System.out.println("P1: (" + x0 + ", " + y0 + ")" + "  P2: " + "(" + x2 + ", " + y2 + ")");
        System.out.println("x: " + test.getX() + "y: " + test.getY());

        x0 = 0;
        y0 = 1;
        x1 = 5;
        y1 = 11;
        x2 = 10;
        y2 = 1;
        test = com.SignalDiagram.Encoder.QuadCurve.getPoint(new Point2D(x0, y0), new Point2D(x1, y1), new Point2D(x2, y2), .5);
        System.out.println("P1: (" + x0 + ", " + y0 + ")" + "  P2: " + "(" + x2 + ", " + y2 + ")");

        System.out.println("x: " + test.getX() + "y: " + test.getY());
    }
}
