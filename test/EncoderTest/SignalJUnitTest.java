package EncoderTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class SignalJUnitTest {

    public SignalJUnitTest() {
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
    public void CheckPointQuantity() {
        // TODO
        List<Point2D> test = Encoder.SignalEncoders.bipolar("101010101010");
        System.out.println("Bipolar");
        System.out.println(test.size());
        test = Encoder.SignalEncoders.nrz("101010101010");
        System.out.println("nrz");
        System.out.println(test.size());
        test = Encoder.SignalEncoders.rz("101010101010");
        System.out.println("rz");
        System.out.println(test.size());
        test = Encoder.SignalEncoders.manchester("101010101010");
        System.out.println("manchester");
        System.out.println(test.size());
    }

}
