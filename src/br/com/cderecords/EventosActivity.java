package br.com.cderecords;

import java.util.ArrayList;
import java.util.List;

import br.com.cderecords.dao.EventosDao;
import br.com.cderecords.model.Evento;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EventosActivity extends ListActivity {
	
	private List<Evento> listaEventos;
	private ArrayList<String> listaItens;
	private ArrayAdapter<String> listaAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		buscarEventos();
	}
	
	private void buscarEventos() {
		EventosDao dao = new EventosDao(this);
		this.listaEventos = dao.buscarEventos();

		if(listaEventos != null) {
			gerarItensLista(listaEventos);
			carregaLista(this.listaItens);			
		}
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		int item_id = this.listaEventos.get(position).getId();
		Intent i = new Intent(this, ParticipantesActivity.class);
		Bundle params = new Bundle();
		params.putInt("item_id", item_id);
		i.putExtras(params);
		startActivity(i);
		finish();
	}
	
	private void carregaLista(ArrayList<String> listaItens) {
		listaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaItens);
		this.setListAdapter(listaAdapter);
	}
	
	private void gerarItensLista(List<Evento> listaEventos) {
		this.listaItens = new ArrayList<String>();
		
		for(int i = 0; i < listaEventos.size(); i++) {
			Evento e = pegarObjeto(i, listaEventos);
			String item = "" + e.getTotal() + " [" + e.getMulheres() + "/"
					+ e.getHomens() + "]" + " - " + e.getData() + " - "
					+ e.getEvento();
			
			this.listaItens.add(i, item);
		}
	}
	
	private Evento pegarObjeto(int i, List<Evento> listaEventos) {
		Evento e = new Evento();
		e.setId(this.listaEventos.get(i).getId());
		e.setEvento(this.listaEventos.get(i).getEvento());
		e.setData(this.listaEventos.get(i).getData());
		e.setHomens(this.listaEventos.get(i).getHomens());
		e.setMulheres(this.listaEventos.get(i).getMulheres());
		int total = e.getHomens() + e.getMulheres();
		e.setTotal(total);

		return e;
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eventos, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		Intent i;
		if (id == R.id.mn_novo_evento) {
			i = new Intent(this, NovoActivity.class);
			startActivity(i);
			finish();
		} else if (id == R.id.mn_sobre) {
			i = new Intent(this, SobreActivity.class);
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}
}
