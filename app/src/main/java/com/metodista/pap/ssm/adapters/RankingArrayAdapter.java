package com.metodista.pap.ssm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.metodista.pap.ssm.R;
import com.metodista.pap.ssm.model.Ranking;

import java.util.List;

public class RankingArrayAdapter extends ArrayAdapter<Ranking> {

    private Context context;
    private List<Ranking> rankings;

    public RankingArrayAdapter(Context context, int resource, List<Ranking> objects) {
        super(context, resource, objects);
        this.context = context;
        this.rankings = objects;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linha = inflater.inflate(R.layout.activity_ranking, parent, false);
        TextView campeonato = (TextView) linha.findViewById(R.id.rnkCampeonato);
        TextView time = (TextView) linha.findViewById(R.id.rnkTime);
        TextView pontos = (TextView) linha.findViewById(R.id.rnkPontos);

        Ranking rnk = rankings.get(position);
        campeonato.setText(rnk.getCampeonato());
        time.setText(rnk.getTime());
        pontos.setText(String.valueOf(rnk.getPontos()));

        return linha;
    }
}
