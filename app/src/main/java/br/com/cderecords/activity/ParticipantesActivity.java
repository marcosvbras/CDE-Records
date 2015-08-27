package br.com.cderecords.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import br.com.cderecords.R;
import br.com.cderecords.dao.EventosDao;
import br.com.cderecords.model.Evento;

public class ParticipantesActivity extends ActionBarActivity {

	private Toolbar mToolbar;
	private Intent i;
	private Bundle params;
	private int item_id;
	private Evento evento;
	private final int HOMEM = 1;
	private final int MULHER = 2;
	private TextView tv_homem, tv_mulher, tv_total, tv_evento;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
		setContentView(R.layout.activity_participantes);
		mToolbar = (Toolbar) findViewById(R.id.tb_main);
		mToolbar.setTitle(getResources().getString(R.string.title_activity_participantes));
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		pegarValores();
		refenciarView();
		exibirValores();
	}

	private void pegarValores() {
		i = getIntent();
		this.params = i.getExtras();
		this.item_id = params.getInt("item_id");
		EventosDao dao = new EventosDao(this);
		this.evento = dao.buscarEventoPorId(this.item_id);
	}

	private void refenciarView() {
		tv_mulher = (TextView) findViewById(R.id.tv_mulheres);
		tv_homem = (TextView) findViewById(R.id.tv_homens);
		tv_total = (TextView) findViewById(R.id.tv_total);
		tv_evento = (TextView) findViewById(R.id.tv_evento);
	}

	public void controlarHomens(View view) {
		Button bnt_homem = (Button) view;
		String texto = (String) bnt_homem.getText();
		
		if (texto.equals("+")) {
			aumentarParticipantes(HOMEM);
		} else {
			reduzirParticipantes(HOMEM);
		}
		atualizarEvento();
	}

	public void controlarMulheres(View view) {
		Button bt_mulher = (Button) view;
		String texto = (String) bt_mulher.getText();
		
		if (texto.equals("+")) {
			aumentarParticipantes(MULHER);
		} else {
			reduzirParticipantes(MULHER);
		}
		atualizarEvento();
	}

	private void reduzirParticipantes(int participantes) {
		if (participantes == HOMEM) {
			int homens = this.evento.getHomens();
			if(homens > 0) {
				homens -= 1;
				this.evento.setHomens(homens);				
			}
		} else {
			int mulheres = this.evento.getMulheres();
			if(mulheres > 0) {
				mulheres -= 1;
				this.evento.setMulheres(mulheres);				
			}
		}
		exibirValores();
	}

	private void aumentarParticipantes(int participantes) {
		if (participantes == HOMEM) {
			int homens = this.evento.getHomens();
			homens += 1;
			this.evento.setHomens(homens);
		} else {
			int mulheres = this.evento.getMulheres();
			mulheres += 1;
			this.evento.setMulheres(mulheres);
		}
		exibirValores();
	}

	private void exibirValores() {
		tv_evento.setText(this.evento.getEvento());
		tv_homem.setText("" + this.evento.getHomens());
		tv_mulher.setText("" + this.evento.getMulheres());
		int total = this.evento.getHomens() + this.evento.getMulheres();
		tv_total.setText("" + total + " " + getResources().getString(R.string.title_pessoas));
	}
	
	private void atualizarEvento() {
		EventosDao dao = new EventosDao(this);
		dao.atualizarEvento(this.evento);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.participantes, menu);
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
		} else {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
