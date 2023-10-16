package app.codelabs.roadtrip;

import org.junit.Test;

import app.codelabs.roadtrip.helpers.DateTimeHelper;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String strDate = "2020-04-01 00:00:00";
        System.out.println("diff "+strDate);

        System.out.println(DateTimeHelper.instance(strDate).getTimeElapsed());
        assertEquals(4, 2 + 2);
    }
}