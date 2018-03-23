package pl.martapiatek.bmi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void mass_and_height_are_valid_for_kg_m(){
        BMICounter bmiCounter = new BMICounterForKgM(60,1.70);
        double bmi = bmiCounter.count();
        assertEquals(20.761,bmi, 0.001);
    }
    @Test(expected = IllegalArgumentException.class)
    public void mass_and_height_are_zero_for_kg_m(){
        BMICounter bmiCounter = new BMICounterForKgM(0,0);
        double bmi = bmiCounter.count();

    }

    @Test
    public void mass_and_height_are_valid_for_lb_in(){
        BMICounter bmiCounter = new BMICounterForLbIn(140,68);
        double bmi = bmiCounter.count();
        assertEquals(21.284,bmi, 0.001);
    }
    @Test(expected = IllegalArgumentException.class)
    public void mass_and_height_are_zero_for_lb_in(){
        BMICounter bmiCounter = new BMICounterForLbIn(0,0);
        double bmi = bmiCounter.count();

    }

    @Test
    public void mass_is_valid_for_kg_m(){
        BMICounter bmiCounter = new BMICounterForKgM(50,1.70) ;
        boolean isMassValid = bmiCounter.isMassValid();
        assertTrue(isMassValid);
    }
    @Test
    public void mass_is_not_valid_for_kg_m(){
        BMICounter bmiCounter = new BMICounterForKgM(0,1.70) ;
        boolean isMassValid = bmiCounter.isMassValid();
        assertFalse(isMassValid);
    }
    @Test
    public void data_is_not_valid_for_kg_m(){
        BMICounter bmiCounter = new BMICounterForKgM(0,-1.70) ;
        boolean isDataValid = bmiCounter.isDataValid();
        assertFalse(isDataValid);
    }

    @Test
    public void data_is_valid_for_kg_m(){
        BMICounter bmiCounter = new BMICounterForKgM(50,1.70) ;
        boolean isDataValid = bmiCounter.isDataValid();
        assertTrue(isDataValid);
    }

    @Test
    public void mass_is_valid_for_lb_in(){
        BMICounter bmiCounter = new BMICounterForLbIn(50,68) ;
        boolean isMassValid = bmiCounter.isMassValid();
        assertTrue(isMassValid);
    }
    @Test
    public void mass_is_not_valid_for_lb_in(){
        BMICounter bmiCounter = new BMICounterForLbIn(0,68) ;
        boolean isMassValid = bmiCounter.isMassValid();
        assertFalse(isMassValid);
    }
    @Test
    public void data_is_not_valid_for_lb_in(){
        BMICounter bmiCounter = new BMICounterForLbIn(0,0) ;
        boolean isDataValid = bmiCounter.isDataValid();
        assertFalse(isDataValid);
    }

    @Test
    public void data_is_valid_for_lb_in(){
        BMICounter bmiCounter = new BMICounterForLbIn(50,68) ;
        boolean isDataValid = bmiCounter.isDataValid();
        assertTrue(isDataValid);
    }

}