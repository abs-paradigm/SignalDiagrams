/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram.EncoderTest;

import com.SignalDiagram.Encoder.AnalogEncoders;
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
    public void testBaseEncoder() {
        List<Point2D> test = AnalogEncoders.baseSignal("1");
        for(Point2D p : test){
            System.out.println("x: " + p.getX() + " y: " + p.getY());
        }
    }
}
