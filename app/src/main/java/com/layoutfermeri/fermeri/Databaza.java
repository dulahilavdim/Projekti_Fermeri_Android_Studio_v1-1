package com.layoutfermeri.fermeri;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class Databaza extends SQLiteOpenHelper {

    public static final String EMRI_DATABAZES = "Kafshet.db";
    public static final String EMRI_TABELES = "tabela_gjedhe";
    public static final String EMRI_TABELES1 = "tabela_dele";
    public static final String KOLONA_0 = "ID";
    public static final String KOLONA_1 = "SHIFRA";
    public static final String KOLONA_2 = "EMRI";
    public static final String KOLONA_3 = "GJINIA";
    public static final String KOLONA_4 = "NGJYRA";
    public static final String KOLONA_5 = "FOTO";

    public Databaza(Context context) {
        super(context, EMRI_DATABAZES, null, 1);

        // SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("create table " + EMRI_TABELES +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, SHIFRA TEXT, EMRI TEXT," +
                "GJINIA TEXT, NGJYRA TEXT) ");

        sqLiteDatabase.execSQL("create table " + EMRI_TABELES1 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, SHIFRA TEXT, EMRI TEXT," +
                "GJINIA TEXT, NGJYRA TEXT) ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EMRI_TABELES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EMRI_TABELES1);

        //sqLiteDatabase.execSQL("create table " + EMRI_TABELES1 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, SHIFRA TEXT, EMRI TEXT," +
        //        "GJINIA TEXT, NGJYRA TEXT) ");

        //onCreate(sqLiteDatabase);

    }

    public boolean insertData (String shifra, String emri, String gjinia, String ngjyra)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KOLONA_1, shifra);
        contentValues.put(KOLONA_2, emri);
        contentValues.put(KOLONA_3, gjinia);
        contentValues.put(KOLONA_4, ngjyra);

        long result = sqLiteDatabase.insert(EMRI_TABELES, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+ EMRI_TABELES, null);
        return res;
    }

    //Marrja e ID e perdorur per ndryshim te te dhenave per gjedhe
    public Cursor getId(int position)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor ID = sqLiteDatabase.rawQuery("select * from "+EMRI_TABELES+" where id="+position ,null);
        return ID;
    }

    //Marrja e ID e perdorur per ndryshim te te dhenave per dele
    public Cursor getIdPerDele(int position)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor ID = sqLiteDatabase.rawQuery("select * from "+EMRI_TABELES1+" where id="+position ,null);
        return ID;
    }
    //Insertimi per tabelen tabela_dele
    public boolean insertDataDele (String shifra, String emri, String gjinia, String ngjyra)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KOLONA_1, shifra);
        contentValues.put(KOLONA_2, emri);
        contentValues.put(KOLONA_3, gjinia);
        contentValues.put(KOLONA_4, ngjyra);

        long result = sqLiteDatabase.insert(EMRI_TABELES1, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataDele()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+ EMRI_TABELES1, null);
        return res;
    }
    

/*    public void deleteRecord (int id)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("delete from tabela_gjedhe where id=?", null);
        //sqLiteDatabase.execSQL("DELETE FROM " + EMRI_TABELES +" WHERE id=? ");
    }*/

    //Metoda Delete per tabelen tabela_gjedhe
    public void deleteData(int id){
        SQLiteDatabase database = getWritableDatabase();
        //query to delete record using id
        String sql = "DELETE FROM tabela_gjedhe WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }
    //Metoda Delete per tabelen tabela_dele
    public void deleteDataDele(int id){
        SQLiteDatabase database = getWritableDatabase();
        //query to delete record using id
        String sql = "DELETE FROM tabela_dele WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    //Funksioni per ndryshim te te dhenave ne tabela_gjedhe
    public boolean updateDataGjedhe(long id,String shifra, String emri, String gjinia, String ngjyra)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(KOLONA_0, id);
        args.put(KOLONA_1, shifra);
        args.put(KOLONA_2, emri);
        args.put(KOLONA_3, gjinia);
        args.put(KOLONA_4, ngjyra);
        db.update(EMRI_TABELES, args, KOLONA_0 + "=" + id, null);
        return true;
    }

    //Funksioni per ndryshim te te dhenave ne tabela_dele
    public boolean updateDataDele(long id,String shifra, String emri, String gjinia, String ngjyra)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(KOLONA_0, id);
        args.put(KOLONA_1, shifra);
        args.put(KOLONA_2, emri);
        args.put(KOLONA_3, gjinia);
        args.put(KOLONA_4, ngjyra);
        db.update(EMRI_TABELES1, args, KOLONA_0 + "=" + id, null);
        return true;
        //db
    }


}
