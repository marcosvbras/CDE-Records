package br.com.cderecords;

import java.util.ArrayList;
import java.util.List;

import br.com.cderecords.dao.EventosDao;
import br.com.cderecords.model.Evento;
import br.com.cderecords.model.EventoAdapter;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;

public class EventosActivity extends ListActivity {
	
	private List<Evento> listaEventos;
	private ArrayList<String> listaItens;
	private ListView listViewEvento;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		buscarEventos();
	}
	
	private void buscarEventos() {
		EventosDao dao = new EventosDao(this);
		this.listaEventos = dao.buscarEventos();

		if(listaEventos != null) {
			carregaLista(this.listaItens);			
		}
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		this.listViewEvento = getListView();
		this.listViewEvento.setClickable(true);
		int item_id = listaEventos.get(position).getId();
		Intent i = new Intent(getBaseContext(), ParticipantesActivity.class);
		Bundle params = new Bundle();
		params.putInt("item_id", item_id);
		i.putExtras(params);
		startActivity(i);
	}
	
	private void carregaLista(ArrayList<String> listaItens) {
		this.listViewEvento = getListView();
		this.listViewEvento.setClickable(true);
		this.listViewEvento.setAdapter(new EventoAdapter(this, listaEventos));
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		buscarEventos();
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
		} else if (id == R.id.mn_sobre) {
			i = new Intent(this, SobreActivity.class);
			startActivity(i);
		} else if (id == R.id.mn_excluir) {
			
		}
		return super.onOptionsItemSelected(item);
	}
}
