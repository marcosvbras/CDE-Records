package br.com.cderecords;

import java.util.ArrayList;

import br.com.cderecords.dao.EventosDao;
import br.com.cderecords.model.Evento;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class EventosActivity extends ListActivity {
	
	private ArrayList<Evento> listaEventos;
	private ArrayList<String> listaItens;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		buscaTodosAbastecimentos();
	}
	
	private void buscaTodosAbastecimentos() {
		EventosDao dao = new EventosDao(this);
		this.listaEventos = dao.buscarEventos();
		if(listaEventos != null) {
			gerarItensLista(listaEventos);
			carregaLista(this.listaItens);			
		}
	}
	
	private void carregaLista(ArrayList<String> listaItens) {
		ArrayAdapter<String> listaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaItens);
		this.setListAdapter(listaAdapter);
	}
	
	private void gerarItensLista(ArrayList<Evento> listaEventos) {
		this.listaItens = new ArrayList<String>();
		
		for(int i = 0; i < listaEventos.size(); i++) {
			Evento e = pegarObjeto(i);
			String item = "" + e.getTotal() + "[" + e.getMulheres() + "/"
					+ e.getHomens() + "]" + " - " + e.getData() + " - "
					+ e.getEvento();
			
			this.listaItens.add(item);
		}
	}
	
	private Evento pegarObjeto(int i) {
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
