/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junit_tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Denis Imeri
 */
public class CalcBLTest {
    
    public CalcBLTest() {
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

    /**
     * Test of add method, of class CalcBL.
     */
    @Test
    public void testAdd() {
        System.out.println("Test-1 add()");
        int num1 = 1;
        int num2 = 2;
        int expResult = 3;
        int result = CalcBL.add(num1, num2);
        assertEquals(expResult, result);
    }

    /**
     * Test of divide method, of class CalcBL.
     */
    @Test(expected = ArithmeticException.class)
    public void testDivide() {
        System.out.println("Test-2 divide()");
        double num1 = 10.0;
        double num2 = 0.0;
        double expResult = 5.0;
        double result = CalcBL.divide(num1, num2);
        assertEquals(expResult, result, 0.1E-5);
    }
    
}
