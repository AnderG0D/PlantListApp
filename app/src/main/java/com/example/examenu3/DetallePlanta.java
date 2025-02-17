package com.example.examenu3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetallePlanta extends AppCompatActivity {

    int[] recursosImagenes = {
            R.drawable.rosa,
            R.drawable.roble,
            R.drawable.girasol,
            R.drawable.tulipan,
            R.drawable.cactus,
            R.drawable.orquidea,
            R.drawable.arce,
            R.drawable.plantacarnivora,
            R.drawable.lavanda,
            R.drawable.helecho
    };

    TextView textView_comun;
    TextView textView_cientifico;
    TextView textView_descripcion;
    ImageView imageView_planta;
    Button button_regresar;

    private SQLiteOpenHelper admin;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_planta);

        TextView textView_comun = findViewById(R.id.textView_comun);
        TextView textView_cientifico = findViewById(R.id.textView_cientifico);
        TextView textView_descripcion = findViewById(R.id.textView_descripcion);
        ImageView imageView_planta = findViewById(R.id.imageView_planta);
        button_regresar = findViewById(R.id.button_regresar);

        // Recoge la posición de la imagen desde el Intent
        int posicionImagen = getIntent().getIntExtra("posicion_imagen", 0);

        // Muestra la imagen correspondiente en el ImageView
        imageView_planta.setImageResource(recursosImagenes[posicionImagen]);

        // Obtener el ID de la planta seleccionada desde la actividad principal
        if (getIntent().hasExtra("idplanta")) {
            int idPlanta = getIntent().getIntExtra("idplanta", -1); // -1 es un valor predeterminado en caso de que no se encuentre "idplanta"

            if (idPlanta != -1) {
                // Realizar la consulta a la base de datos usando el ID de la planta
                cursor = cargarDatos(idPlanta);

                if (cursor != null && cursor.moveToFirst()) {
                    // Obtener los datos de la planta desde el cursor
                    String nombreComun = cursor.getString(cursor.getColumnIndexOrThrow("nombrecomun"));
                    String nombreCientifico = cursor.getString(cursor.getColumnIndexOrThrow("nombrecientifico"));
                    String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));

                    // Configurar los datos en los TextView e ImageView
                    textView_comun.setText(nombreComun);
                    textView_cientifico.setText(nombreCientifico);
                    textView_descripcion.setText(descripcion);
                    //imageView_planta.setImageResource(R.drawable.rosa);
                }
            }
        }

        // Botón regresar
        button_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cerrarBaseDeDatosYCursor();
    }

    private Cursor cargarDatos(int idPlanta) {
        admin = new AdminSQLiteOpenHelper(this, "plantasdb", null, 1);
        db = admin.getReadableDatabase();

        String query = "SELECT * FROM plantas WHERE idplanta = " + idPlanta;

        // Realiza la consulta
        return db.rawQuery(query, null);
    }

    private void cerrarBaseDeDatosYCursor() {
        if (cursor != null) {
            cursor.close();
        }
        if (db != null) {
            db.close();
        }
    }
}