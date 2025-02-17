package com.example.examenu3;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adaptador extends CursorAdapter {

    private int[] imageResources = {
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

    public Adaptador(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflar el diseño de elemento_lista.xml
        return LayoutInflater.from(context).inflate(R.layout.elemento_lista,
                parent,
                false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Enlazar los datos a las vistas en elemento_lista.xml
        TextView textView_planta =
                view.findViewById(R.id.textView_comun);

        ImageView imageView_planta =
                view.findViewById(R.id.imageView_planta);

        String nombreComun =
                cursor.getString(cursor.getColumnIndexOrThrow(
                        "nombrecomun"));

        int posicion = cursor.getPosition();

        textView_planta.setText(nombreComun);

        if (posicion >= 0 && posicion < imageResources.length) {
            imageView_planta.setImageResource(imageResources[posicion]);
        }

    }
}

