import com.example.harkkatyo.Kiekkorekisteri;
import com.example.harkkatyo.Kiekot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestKiekot {
    Kiekot kiekot = new Kiekot("testKiekot.dat");

    @Test
    public void testLataaKiekot() {
        Assertions.assertNotEquals(null, kiekot.taulukko[0]);
        Assertions.assertNotEquals(null, kiekot.taulukko[1]);
        Assertions.assertNull(kiekot.taulukko[2]);
    }

    @Test
    public void testGetKiekot() {
        Assertions.assertEquals(2, kiekot.getKiekot().length);
    }

    @Test
    public void testDeleteByID() {
        kiekot.deleteByID(2);
        Assertions.assertNotEquals(null, kiekot.taulukko[0]);
        Assertions.assertNull(kiekot.taulukko[1]);
        kiekot.deleteByID(1);
        Assertions.assertNull(kiekot.taulukko[0]);
    }

    @Test
    public void testLisaaUusi() {
        kiekot.lisaaUusi(new String[] {"1", "Valkoinen", "2019", "188", "2", "7", "ei", "ei"});
        Assertions.assertNotEquals(null, kiekot.taulukko[2]);
    }

    @Test
    public void testClearArray() {
        kiekot.clearArray();
        Assertions.assertNull(kiekot.taulukko[0]);
    }
}
