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
    public void testSuodata() {
        Assertions.assertEquals(1, kr.suodata("^disc*").length);
        Assertions.assertEquals(1, kr.suodata("^inn*").length);
        Assertions.assertEquals(2, kr.suodata("^*").length);
    }


}
