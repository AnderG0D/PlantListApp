package com.example.examenu3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name,
                                 @Nullable SQLiteDatabase.CursorFactory factory,
                                 int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table plantas" +
                "(idplanta integer primary key autoincrement, " +
                "nombrecomun text, " +
                "nombrecientifico text, " +
                "descripcion text)"
        );

        //Insertar datos de 10 plantas
        insertarDatosPlantas(db);
    }

    private void insertarDatosPlantas(SQLiteDatabase db) {
        String[][] plantas = {
                {"0", "Rosa", "Rosa scientifia", "Una hermosa rosa roja"},
                {"1", "Roble", "Roble scientifia", "Un árbol de roble"},
                {"2", "Girasol", "Girasol scientifia", "Un brillante girasol amarillo"},
                {"3", "Tulipan", "Tulipan scientifia", "Un tulipán de varios colores"},
                {"4", "Cactus", "Cactus scientifia", "Un cactus espinoso"},
                {"5", "Orquidea", "Orquidea scientifia", "Una hermosa orquídea"},
                {"6", "Arce", "Arce scientifia", "Un arce de hojas rojas"},
                {"7", "Planta carnivora", "Planta carnivora scientifia", "Una planta carnívora hambrienta"},
                {"8", "Lavanda", "Lavanda scientifia", "Una fragante planta de lavanda"},
                {"9", "Helecho", "Helecho scientifia", "Un helecho verde y frondoso"}
        };

        for (int i = 0; i < plantas.length; i++) {
            ContentValues plantaValues = new ContentValues();
            plantaValues.put("idplanta", Integer.parseInt(plantas[i][0]));
            plantaValues.put("nombrecomun", plantas[i][1]);
            plantaValues.put("nombrecientifico", plantas[i][2]);
            plantaValues.put("descripcion", plantas[i][3]);

            db.insert("plantas", null, plantaValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Agregar columnas "nombrecomun", "nombrecientifico" e "imagen" a la tabla "plantas"
        db.execSQL("ALTER TABLE plantas ADD COLUMN nombrecomun TEXT;");
        db.execSQL("ALTER TABLE plantas ADD COLUMN nombrecientifico TEXT;");
        db.execSQL("ALTER TABLE plantas ADD COLUMN descripcion TEXT;");
        db.execSQL("ALTER TABLE plantas ADD COLUMN imagen INTEGER;");

    }
}
