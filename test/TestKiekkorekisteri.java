import com.example.harkkatyo.Kiekkorekisteri;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestKiekkorekisteri {
    Kiekkorekisteri kr = new Kiekkorekisteri("testKiekot.dat", "testValmistajaJaMalli.dat", "testMuovi.dat");

    @Test
    public void testgetMuovit() {
        Assertions.assertArrayEquals(new String[] {"C-line", "Champion", "soft", "jawbreaker"}, kr.getMuovit());
    }

    @Test
    public void testGetMuoviByID() {
        Assertions.assertEquals("jawbreaker", kr.getMuoviByID(4));
        Assertions.assertEquals("C-line", kr.getMuoviByID(1));
    }

    @Test
    public void testgetFlightNumbers() {
        Assertions.assertArrayEquals(new int[] {11, 5, -2, 2}, kr.getFlightNumbers(1));
        Assertions.assertArrayEquals(new int[] {12, 5, -1, 3}, kr.getFlightNumbers(2));
    }

    @Test
    public void testFilter() {
        Assertions.assertTrue(Kiekkorekisteri.filter("Discmania", "^disc"));
        Assertions.assertTrue(Kiekkorekisteri.filter("Discmania", "^"));
        Assertions.assertTrue(Kiekkorekisteri.filter("Logic", "^l"));
        Assertions.assertFalse(Kiekkorekisteri.filter("Logic", "^ogic"));
        Assertions.assertFalse(Kiekkorekisteri.filter("Logic", "^o"));
    }


    @Test
    public void testGetKiekot() {
        Assertions.assertEquals(2, kr.getKiekot().length);
    }


}
