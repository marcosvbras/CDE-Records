package br.com.cderecords.activity;

import java.util.List;

import br.com.cderecords.R;
import br.com.cderecords.dao.EventosDao;
import br.com.cderecords.model.Evento;
import br.com.cderecords.model.EventoAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class EventosActivity extends ActionBarActivity {
	
	private List<Evento> listaEventos;
	private ListView lvEvento;
	private Toolbar mToolbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mToolbar = (Toolbar) findViewById(R.id.tb_main);
		mToolbar.setTitle("Clube do Espeto Records");
		mToolbar.setSubtitle("Eventos Cadastrados");
		mToolbar.setLogo(R.drawable.mini_logo);
		setSupportActionBar(mToolbar);
		buscarEventos();
	}
	
	private void buscarEventos() {
		EventosDao dao = new EventosDao(this);
		this.listaEventos = dao.buscarEventos();
		
		if(listaEventos != null) {
			carregaLista();			
		}
	}
	
	private void carregaLista() {
		lvEvento = (ListView) findViewById(R.id.lv);
		lvEvento.setAdapter(new EventoAdapter(this, listaEventos));
	}
	
	public void onItemClickListener(View view) {
		int position = lvEvento.getPositionForView(view);
		int item_id = listaEventos.get(position).getId();
		Intent i = new Intent(this, ParticipantesActivity.class);
		Bundle params = new Bundle();
		params.putInt("item_id", item_id);
		i.putExtras(params);
		startActivity(i);
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
