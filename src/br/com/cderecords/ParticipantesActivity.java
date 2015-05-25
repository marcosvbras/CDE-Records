package br.com.cderecords;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import br.com.cderecords.dao.EventosDao;
import br.com.cderecords.model.Evento;

public class ParticipantesActivity extends Activity {

	private Intent i;
	private Bundle params;
	private int item_id;
	private Evento evento;
	private final int HOMEM = 1;
	private final int MULHER = 2;

	TextView tvhomem;
	TextView tvmulher;
	TextView tvtotal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_participantes);
		pegarValores();
		refenciarView();
	}

	private void pegarValores() {
		i = getIntent();
		this.params = i.getExtras();
		this.item_id = params.getInt("item_id");
		EventosDao dao = new EventosDao(this);
		this.evento = dao.buscarEventoPorId(this.item_id);
	}

	private void refenciarView() {
		// TODO Auto-generated method stub
		tvmulher = (TextView) findViewById(R.id.tv_mulheres);
		tvhomem = (TextView) findViewById(R.id.tv_homens);
		tvtotal = (TextView) findViewById(R.id.tv_total);

	}

	public void controlarHomens(View view) {
		// TODO Auto-generated method stub
		Button bnt_homem = (Button) view;
		String texto = (String) bnt_homem.getText();
		if (bnt_homem.equals("+")) {
			aumentarParticipantes(HOMEM);
		} else {
			reduzirParticipantes(HOMEM);
		}

	}

	public void controlarMulheres(View view) {
		// TODO Auto-generated method stub
		Button bnt_mulher = (Button) view;
		String texto = (String) bnt_mulher.getText();
		if (bnt_mulher.equals("+")) {
			aumentarParticipantes(MULHER);
		} else {
			reduzirParticipantes(MULHER);
		}

	}

	private void reduzirParticipantes(int participantes) {
		// TODO Auto-generated method stub
		int homens = this.evento.getHomens();
		int mulheres = this.evento.getMulheres();
		if (participantes == MULHER) {
			homens -= 1;
			this.evento.setHomens(homens);
		} else {
			mulheres -= 1;
			this.evento.setMulheres(mulheres);
		}
		exibirValores();
	}

	private void aumentarParticipantes(int participantes) {
		// TODO Auto-generated method stub
		int homens = this.evento.getHomens();
		int mulheres = this.evento.getMulheres();

		if (participantes == HOMEM) {
			homens += 1;
			this.evento.setHomens(homens);

		} else {
			mulheres += 1;
			this.evento.setMulheres(mulheres);
		}
		exibirValores();
	}

	private void exibirValores() {
		// TODO Auto-generated method stub
		tvhomem.setText("" + this.evento.getHomens());
		tvmulher.setText("" + this.evento.getMulheres());
		int total = this.evento.getHomens() + this.evento.getMulheres();
		tvtotal.setText("" + total + "pessoas presentes no evento");

	}
}
