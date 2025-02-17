package com.example.examenu3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView_plantas;
    private SQLiteOpenHelper admin;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Manipular listView de plantas que hicimos
        listView_plantas =
                findViewById(R.id.listView_plantas);

        cursor = cargarDatos();

        // Configura el adaptador con el cursor
        Adaptador adaptador = new Adaptador(this, cursor);

        listView_plantas.setAdapter(adaptador);


        listView_plantas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                int idPlanta = cursor.getInt(cursor.getColumnIndexOrThrow("idplanta")); // Supongamos que "_id" es el nombre de la columna en tu cursor

                Intent intent = new Intent(MainActivity.this, DetallePlanta.class);
                intent.putExtra("idplanta", idPlanta);
                intent.putExtra("posicion_imagen", position); // Pasa la posición de la imagen
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cerrarBaseDeDatosYCursor();
    }

    private Cursor cargarDatos() {

        admin = new AdminSQLiteOpenHelper(this,
                "plantasdb",
                null,
                1);

        db = admin.getReadableDatabase();

        String query = "SELECT nombrecomun, idplanta, idplanta AS _id FROM plantas";

        // Realiza la consulta
        Cursor cursor = db.rawQuery(query, null);

        // En este punto, el 'cursor' contiene los datos de ambas tablas
        return cursor;
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
