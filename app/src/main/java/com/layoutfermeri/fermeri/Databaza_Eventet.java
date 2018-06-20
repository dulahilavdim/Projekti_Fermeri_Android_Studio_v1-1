package com.layoutfermeri.fermeri;

/**
 * Created by LED-COM on 5/26/2018.
 */

public class Databaza_Eventet {

    String idData;
    String data;
    String kafshet;
    String idKafshes;

    Databaza_Eventet()
    {

    }

    public Databaza_Eventet(String idData, String data, String kafshet,String idKafshes) {
        this.idData = idData;
        this.data = data;
        this.kafshet = kafshet;
        this.idKafshes=idKafshes;
    }

    public String getIdData() {
        return idData;
    }

    public String getData() {
        return data;
    }

    public String getKafshet() {
        return kafshet;
    }

    public String getIdKafshes() {
        return idKafshes;
    }
}
