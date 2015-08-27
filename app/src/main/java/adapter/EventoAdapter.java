package adapter;

import java.util.List;

import br.com.cderecords.R;
import br.com.cderecords.model.Evento;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class EventoAdapter extends BaseAdapter{
	
	private Context context;
	private List<Evento> listaEvento;
	private TextView tv_quant_total, tv_quant_homens, tv_quant_mulheres, tv_nome_evento, tv_data_evento;
	
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
			layout = inflater.inflate(R.layout.item_evento, null);
		}
		else{
			layout = convertView;
		}
		
		referenciarViews(layout);
		tv_nome_evento.setText(evento.getEvento());
		String data = evento.getData();
		tv_data_evento.setText(data);
//		String split_data = data.substring(1, 2);
//		if(split_data.equals("/")){
//			data = data.substring(0, 1);
//		} else {
//			data = data.substring(0, 2);
//		}
//		btn_data.setText(data);
		
		int total = evento.getMulheres() + evento.getHomens();
		tv_quant_total.setText(layout.getResources().getString(R.string.text_participantes) + ": " + total);
		tv_quant_mulheres.setText("" + evento.getMulheres());
		tv_quant_homens.setText("" + evento.getHomens());
		
		return layout;
	}
	
	private void referenciarViews(View layout) {
		tv_quant_total = (TextView) layout.findViewById(R.id.tv_quant_total);
		tv_quant_mulheres = (TextView) layout.findViewById(R.id.tv_quant_mulheres);
		tv_quant_homens = (TextView) layout.findViewById(R.id.tv_quant_homens);
		tv_nome_evento = (TextView) layout.findViewById(R.id.tv_nome_evento);
		tv_data_evento = (TextView) layout.findViewById(R.id.tv_data_evento);
	}
}
