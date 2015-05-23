package br.com.cderecords;

import br.com.cderecords.dao.EventosDao;
import br.com.cderecords.model.Evento;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ParticipantesActivity extends Activity {
	
	private Intent i;
	private Bundle params;
	private int item_id;
	private Evento evento;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_participantes);
		pegarValores();
	}
	
	private void pegarValores() {
		i = getIntent();
		this.params = i.getExtras();		
		this.item_id = params.getInt("item_id");
		EventosDao dao = new EventosDao(this);
		this.evento = dao.buscarEventoPorId(this.item_id);
	}
}
