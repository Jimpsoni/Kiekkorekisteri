package com.example.harkkatyo;

import java.util.ArrayList;

public class Suodatin {
    protected Kiekko kiekko;
    private ArrayList<Kenno> kennot = new ArrayList<>();

    public Suodatin() {}

    public void lisaaNumeerinenSuodatin(int min, int max, int arvo) {
        kennot.add(new Kenno(min, max, arvo));
    }
    public void lisaaMerkkijonoSuodatin(String haluttu, String arvo) {
        kennot.add(new Kenno(haluttu, arvo));
    }

    public boolean tarkastaKiekko(Kiekko kiekko) {
        this.kiekko = kiekko;
        for (Kenno kenno: kennot) {

        }
        return true;
    }

    public void tyhjennaSuodatin() { kennot.clear(); }

    static class Kenno{
        private String viesti;

        // Jos verrataan merkkijonoja
        private String haluttu = null;
        private String merkkijonoArvo;

        // Jos verrataan numeerisia arvoja
        private int min;
        private int max;
        private int arvo;

        public Kenno(String arvo, String haluttu) {
            merkkijonoArvo = arvo;
            this.haluttu = haluttu;
        }

        public Kenno(int min, int max, int arvo) {
            this.min = min;
            this.max = max;
            this.arvo = arvo;
        }

        public void setViesti(String viesti) {
            this.viesti = viesti;
        }

        public String getViesti() {
            return viesti;
        }

        public boolean suodata() {
            if (haluttu == null ) {
                return testInteger();
            } else {
                return testString();
            }
        }

        private boolean testInteger() { return arvo >= min && arvo <= max; }

        private boolean testString() { return merkkijonoArvo.equals(haluttu);}

    }
}
