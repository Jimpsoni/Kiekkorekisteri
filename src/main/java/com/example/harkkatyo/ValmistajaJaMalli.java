package com.example.harkkatyo;

public class ValmistajaJaMalli {
    private int ID;
    private String valmistaja;
    private String malli;
    private String nopeus;
    private String liito;
    private String vakaus;
    private String feidi;

    public ValmistajaJaMalli() {}

    public String toString() {
        return ID + " " + valmistaja + " " + malli;
    }

    public void parse(String[] s) {
        // TODO on parempikin tapa tehdä
        ID = Integer.parseInt(s[0]);
        valmistaja = s[1];
        malli = s[2];
        nopeus = s[3];
        liito = s[4];
        vakaus = s[5];
        feidi = s[6];
    }

    public void parseWithOutID(int ID, String[] s) {
        // TODO on parempikin tapa tehdä
        this.ID = ID;
        valmistaja = s[0];
        malli = s[1];
        nopeus = s[2];
        liito = s[3];
        vakaus = s[4];
        feidi = s[5];
    }

    public int getID() { return ID; }


    public String getFileString() {
        return ID + "|" + valmistaja + "|" + malli + "|" + nopeus + "|" + liito + "|" + vakaus +  "|" + feidi + "\n";
    }
    public String[] getInformation() { return new String[] {valmistaja, malli, nopeus, liito, vakaus, feidi}; }
    public String getValmistaja() { return valmistaja; }
    public String getMalli() { return malli; }

}
