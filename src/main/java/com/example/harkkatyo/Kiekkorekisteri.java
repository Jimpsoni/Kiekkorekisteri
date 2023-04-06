package com.example.harkkatyo;
import java.util.regex.Pattern;


/**
 * TODO
 * Suodatus tarkastaKiekko();
 * järkeväksi
 *
 */


public class Kiekkorekisteri {
    private final Suodatin suodatin = new Suodatin();
    private final Muovit muovit = new Muovit();
    private final Kiekot kiekotLuokka = new Kiekot();
    private final ValmistajaJaMallit valmistajaJaMalli = new ValmistajaJaMallit();


    /**
     * Pääohjelma testausta varten
     *
     * @param args Ei käytössä
     */
    public static void main(String[] args)  {
        Kiekkorekisteri k = new Kiekkorekisteri();
        k.annaKiekoilleOtsikko();
    }


    /**
     * Pistää kiekot ja valmistajaJaMalli luokat kirjoittamaan tietonsa tekstitiedostoihin
     */
    public void tallenna() {
        kiekotLuokka.kirjoitaTaulukkoTiedostoon();
        valmistajaJaMalli.kirjoitaTaulukkoTiedostoon();
    }


    /**
     * Palauttaa muovin haetulla ID:llä
     */
    public String getMuoviByID(int ID){ return muovit.getByID(ID); }


    /**
     * Muokataan saatujen tietojen perusteella kiekkoa
     * @param kiekko mitä kiekkoa muokataan
     * @param tiedot tiedot, jotka siihen päivitetään
     */
    public void muokkaaKiekkoa(Kiekko kiekko, String[] tiedot) {
        kiekko.setPaino(tiedot[0]);
        kiekko.setValmistusvuosi(tiedot[1]);
        kiekko.setKunto(tiedot[2]);
        kiekko.setTussit(tiedot[3]);
        kiekko.setErikoisera(tiedot[4]);
        kiekko.setMuovi(muovit.getByMuovi(tiedot[5]));
        kiekko.setVari(tiedot[6]);
    }


    /**
     * Antaa tiedot eteenpäin alaluokille, jotta ne voivat kirjoittaa
     * uudet tiedot
     *
     * @param s merkkijonotaulukko, joka sisältää kaiken tiedon mitä kiekkoa varten tarvitsee
     */
    public void lisaaUusiKiekko(String[] s) {
        // tiedot, jotka annetaan valmistajaJaMalli luokalle
        String[] tiedot = new String[] {s[0], s[1], s[4], s[5], s[6], s[7]};
        String valmistajaID = String.valueOf(valmistajaJaMalli.onkoOlemassa(tiedot));

        // haetaan muoville oma ID
        String muoviID = String.valueOf(muovit.getByMuovi(s[11]));

        // Kasataan loput taulukkoon ja annetaan kiekot luokalle, joka kirjoittaa tiedot ylös
        String[] kiekkoTiedot = new String[] { valmistajaID, s[12], s[2], s[3], muoviID, s[8], s[9], s[10]};
        Kiekko uusi = kiekotLuokka.lisaaUusi(kiekkoTiedot);
        setKiekkoValues(uusi);
    }


    /**
     * Kutsuu kiekot luokan poista metodia
     * @param ID ID joka halutaan poistaa
     */
    public void poistaKiekko(int ID) {
        kiekotLuokka.deleteByID(ID);
    }


    /**
     * Hakee Muovit luokalta kaikki muovi oloiden arvot ja palauttaa ne Merkkijono taulukkona
     * @return merkkijonotaulukon, jossa kaikki muovit ovat
     */
    public String[] getMuovit() {
        return muovit.getMuovit();
    }


    /**
     * Käy läpi kaikki kiekotLuokan kiekot ja antaa niille sopivan Valmistajan ja mallin
     */
    public void annaKiekoilleOtsikko() {
        for (Kiekko kiekko: kiekotLuokka.getKiekot()) {
            setKiekkoValues(kiekko);
        }
    }


    /**
     * Hakee ValmistajaJaMalli sekä muovi luokasta kiekolle arvot
     * ja palauttaa taulukon, jossa nämä arvot ovat
     *
     * @param kiekko kiekko, jonka tiedot halutaan hakea
     */
    public String[] naytaKiekko(Kiekko kiekko) {
        // TODO tee tästä vähemmän kömpelö
        String[] s = new String[11]; // Alustetaan palautettava taulukko

        // haetaan kaikki tarvittavat tiedot kiekosta
        String[] kiekonTiedot = kiekko.getInformation();
        String kiekonMuovi = this.muovit.getByID(kiekko.getMuoviID());
        String[] kiekonValmistaja = this.valmistajaJaMalli.getByID(kiekko.getValmistajaID()).getInformation();

        // Asetetaan kiekon tiedot kokonaisvaltaiseen taulukkoon yksitellen
        s[0] = kiekonTiedot[0];     // väri
        s[1] = kiekonTiedot[1];     // Vuosimalli
        s[2] = kiekonTiedot[2];     // paino
        s[3] = kiekonValmistaja[2]; // Nopeus
        s[4] = kiekonValmistaja[3]; // Liito
        s[5] = kiekonValmistaja[4]; // Vakaus
        s[6] = kiekonValmistaja[5]; // Feidi
        s[7] = kiekonMuovi;         // muovi
        s[8] = kiekonTiedot[3];     // kunto
        s[9] = kiekonTiedot[4];     // tussit
        s[10] = kiekonTiedot[5];    // erikoiserä

        return s;
    }


    /**
     * Hakee ValmistajaJaMalli luokasta kiekolle valmistaja ja malli arvot
     * tämä helpottaa tulevaisuudessa niiden käsittelyä
     *
     * @param kiekko kiekko, jonka otsikko halutaan hakea
     */
    public void setKiekkoValues(Kiekko kiekko) {
        ValmistajaJaMalli vm = valmistajaJaMalli.getByID(kiekko.getValmistajaID());
        kiekko.setMalli(vm.getMalli());
        kiekko.setValmistaja(vm.getValmistaja());
    }


    /**
     * Käy läpi kiekko objektit ja suodattaa niiden valmistajasta ja mallista
     * parametri regex:in pohjalta, palauttaa uuden taulukon, jossa on kiekko
     * objektit jotka sopivat regexiin.
     *
     * @param regex Millä regex-kaavalla metodi suodattaa kiekko-objekteja
     * @return Palauttaa Listan, jossa on kaikki kiekot jotka sopivat regexiin
     */
    public Kiekko[] suodata(String regex) {
        Kiekko[] kiekot = kiekotLuokka.getKiekot();
        // Alustetaan uusi taulukko, johon arvot laitetaan ja palautetaan lopuksi
        Kiekko[] k = new Kiekko[kiekot.length];
        int indeksi = 0;

        // Käydään läpi kaikki kiekot
        for (Kiekko kiekko: kiekot) {
            if (   (filter(kiekko.getMalli(), regex) ||
                    filter(kiekko.getValmistaja(), regex)) &&
                    suodatin.tarkastaKiekko(kiekko)) {

                k[indeksi] = kiekko;
                indeksi++;
            }
        }
        return k;
    }


    /**
     * @param kohde Merkkijono, josta halutaan etsiä tiettyä kirjainyhdistelmää
     * @param regex Säännöllinen lauseke, joka määrittää, mitä etsitään
     * @return True tai False riippuen löytyykö merkkijonosta haluttu kirjainyhdistelmä
     */
    public static boolean filter(String kohde, String regex) {
        Pattern haku = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return haku.matcher(kohde).find();
    }


    public Kiekko[] getKiekot() {
        return kiekotLuokka.getKiekot();
    }
}
