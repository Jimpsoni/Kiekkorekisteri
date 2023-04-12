package com.example.harkkatyo;

public class Kiekko {
    private String ID, vari, tussia, erikoisera, valmistaja, malli;

    private int kunto, vuosimalli, paino, muoviID, valmistajaID;


    public void kiekko() {}

    /**
     * Saa parametrisksi merkkkijono, josta se pilkkoo arvot attribuuteille
     * @param merkkijono suoraan tiedostosta luettu merkkijono
     */
    public void parse(String[] merkkijono) {
        ID = merkkijono[0];
        valmistajaID = Integer.parseInt(merkkijono[1]);
        vari = merkkijono[2];
        vuosimalli = Integer.parseInt(merkkijono[3]);
        paino = Integer.parseInt(merkkijono[4]);
        muoviID = Integer.parseInt(merkkijono[5]);
        kunto = Integer.parseInt(merkkijono[6]);
        tussia = merkkijono[7];
        erikoisera = merkkijono[8];
    }


    public String toString() {
        return "#" + ID + " " +  valmistaja + ", " + malli;
    }

    public int getID() { return Integer.parseInt(this.ID); }

    public int getMuoviID() { return muoviID; }
    public int getValmistajaID() { return valmistajaID; }

    public void setValmistaja(String v) { valmistaja = v; }
    public void setMalli(String v) { malli = v; }

    public String getValmistaja() { return valmistaja; }
    public String getMalli() { return malli; }

    public int getValmistusvuosi() { return vuosimalli; }
    public int getPaino() { return paino; }
    public int getKunto() { return kunto; }
    public String getVari() { return vari; }
    public String getTussit() { return tussia; }
    public String getErikois() { return erikoisera; }
    public void setPaino(int paino) { this.paino = paino; }
    public void setValmistusvuosi(int vuosi) { this.vuosimalli = vuosi; }
    public void setKunto(int kunto) { this.kunto = kunto; }
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
