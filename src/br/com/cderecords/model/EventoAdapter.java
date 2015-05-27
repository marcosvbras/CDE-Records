package br.com.cderecords.model;

import java.util.ArrayList;
import java.util.List;

import br.com.cderecords.R;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EventoAdapter extends BaseAdapter{
	
	private Context context;
	private List<Evento> listaEvento;
	private TextView tv_quant_total, tv_quant_homens, tv_quant_mulheres, tv_nome_evento, tv_data_evento;
	Button btn_data;
	
	public EventoAdapter(Context context, List<Evento> listaEvento){
		this.context = context;
		this.listaEvento = listaEvento;
	}
	
	@Override
	public int getCount() {
		return listaEvento.size();
	}

	@Override
	public Object getItem(int position) {
		return listaEvento.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Evento evento = listaEvento.get(position);
		View layout;
		
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if(position%2 == 0) {
				layout = inflater.inflate(R.layout.eventos1, null);				
			} else {
				layout = inflater.inflate(R.layout.eventos2, null);
			}
		}
		else{
			layout = convertView;
		}
		
		referenciarViews(layout);
		tv_nome_evento.setText(evento.getEvento());
		String data = evento.getData();
		tv_data_evento.setText(data);
		String split_data = data.substring(1, 2);
		if(split_data.equals("/")){
			data = data.substring(0, 1);
		} else {
			data = data.substring(0, 2);
		}
		btn_data.setText(data);
		
		int total = evento.getMulheres() + evento.getHomens();
		tv_quant_total.setText("Participantes: " + total);
		tv_quant_mulheres.setText("" + evento.getMulheres());
		tv_quant_homens.setText("" + evento.getHomens());
		
		return layout;
	}
	
	private void referenciarViews(View layout) {
		btn_data = (Button) layout.findViewById(R.id.btn_data);
		tv_quant_total = (TextView) layout.findViewById(R.id.tv_quant_total);
		tv_quant_mulheres = (TextView) layout.findViewById(R.id.tv_quant_mulheres);
		tv_quant_homens = (TextView) layout.findViewById(R.id.tv_quant_homens);
		tv_nome_evento = (TextView) layout.findViewById(R.id.tv_nome_evento);
		tv_data_evento = (TextView) layout.findViewById(R.id.tv_data_evento);
	}
}
