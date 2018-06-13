package com.layoutfermeri.fermeri;

public class Delja {

    private int id;
    private  String shifra;
    private String emri;
    private  String gjinia;
    private String ngjyra;



    public Delja(String SHIFRA, String EMRI, String GJINIA, String NGJYRA)
    {

        shifra = SHIFRA;
        emri = EMRI;
        gjinia = GJINIA;
        ngjyra = NGJYRA;
    }

    public String getShifra() {
        return shifra;
    }

    public String getEmri() {
        return emri;
    }

    public String getGjinia() {
        return gjinia;
    }

    public String getNgjyra() {
        return ngjyra;
    }
}
