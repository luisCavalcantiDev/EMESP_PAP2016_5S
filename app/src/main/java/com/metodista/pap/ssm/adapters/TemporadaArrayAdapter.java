package com.metodista.pap.ssm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.metodista.pap.ssm.R;
import com.metodista.pap.ssm.model.Temporada;

import java.util.List;

public class TemporadaArrayAdapter extends ArrayAdapter<Temporada> {

    private Context context;
    private List<Temporada> temporadas;

    public TemporadaArrayAdapter(Context context, int resource, List<Temporada> objects) {
        super(context, resource, objects);
        this.context = context;
        this.temporadas = objects;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linha = inflater.inflate(R.layout.activity_temporada, parent, false);
        TextView nome = (TextView) linha.findViewById(R.id.tempListNome);
        TextView status = (TextView) linha.findViewById(R.id.tempListStatus);

        Temporada temp = temporadas.get(position);
        nome.setText(temp.getName());
        if (temp.getIdTemporada().equals("") == true) {
            status.setText("Não sincronizado");
        } else {
            status.setText("Sincronizado");
        }
        return linha;
    }
}
