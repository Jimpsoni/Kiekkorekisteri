package com.example.harkkatyo;

public class Kiekko {
    private String ID;
    private String vari;
    private String kunto;
    private String vuosimalli;
    private String paino;

    private String tussia;
    private String erikoisera;

    private int muoviID;
    private int valmistajaID;

    private String valmistaja;
    private String malli;



    public void kiekko() {}

    /**
     * Saa parametrisksi merkkkijono, josta se pilkkoo arvot attribuuteille
     * @param merkkijono suoraan tiedostosta luettu merkkijono
     */
    public void parse(String[] merkkijono) {
        ID = merkkijono[0];
        valmistajaID = Integer.parseInt(merkkijono[1]);
        vari = merkkijono[2];
        vuosimalli = merkkijono[3];
        paino = merkkijono[4];
        muoviID = Integer.parseInt(merkkijono[5]);
        kunto = merkkijono[6];
        tussia = merkkijono[7];
        erikoisera = merkkijono[8];
    }


    public String toString() {
        return "#" + ID + " " +  valmistaja + ", " + malli;
    }

    /**
     * @return palauttaa tärkeimmät tiedot yhdessä merkkijonotaulukossa
     */
    public String[] getInformation() {
        return new String[] {vari, vuosimalli, paino, kunto, tussia, erikoisera};
    }

    public int getID() { return Integer.parseInt(this.ID); }

    public int getMuoviID() { return muoviID; }
    public int getValmistajaID() { return valmistajaID; }

    public void setValmistaja(String v) { valmistaja = v; }
    public void setMalli(String v) { malli = v; }

    public String getValmistaja() { return valmistaja; }
    public String getMalli() { return malli; }

    public String getValmistusvuosi() { return vuosimalli; }
    public String getPaino() { return paino; }
    public String getKunto() { return kunto; }
    public String getVari() { return vari; }
    public String getTussit() { return tussia; }
    public String getErikois() { return erikoisera; }
    public void setPaino(String paino) { this.paino = paino; }
    public void setValmistusvuosi(String vuosi) { this.vuosimalli = vuosi; }
    public void setKunto(String kunto) { this.kunto = kunto; }
    public void setTussit(String tussia) {this.tussia = tussia; }
    public void setErikoisera(String erikoisera) {this.erikoisera = erikoisera;}
    public void setMuovi(int ID) {muoviID = ID;}
    public void setVari(String vari) {this.vari=vari;}
    public int setID(int ID) {
        return ID;
    }

    public String getFileString() {
        return ID + "|" + valmistajaID + "|" + vari + "|" + vuosimalli + "|" +
                paino + "|" + muoviID + "|" + kunto + "|" + tussia + "|" + erikoisera + "\n";
    }


}
