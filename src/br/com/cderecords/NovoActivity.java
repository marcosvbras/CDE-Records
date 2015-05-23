package br.com.cderecords;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.cderecords.dao.EventosDao;
import br.com.cderecords.model.Evento;

public class NovoActivity extends Activity {
	
	EditText et_nome_evento, et_data_evento;
	String nome_Evento, data_Evento;
	Intent i;
	Evento e = new Evento();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novo);
		referenciarViews();
	}
	
	private void referenciarViews() {
		et_nome_evento = (EditText)findViewById(R.id.et_nome_evento);
		et_data_evento = (EditText)findViewById(R.id.et_data_evento);
	}
	
	private void pegarValores() {
		nome_Evento = et_nome_evento.getText().toString();
		data_Evento = et_data_evento.getText().toString();
		e.setEvento(nome_Evento);
		e.setData(data_Evento);
		e.setHomens(0);
		e.setMulheres(0);		
	}
	
	private boolean validarCampos() {
		
		boolean resultado = false;
		
		if(!nome_Evento.equals("")){
			if(!data_Evento.equals("")){
					resultado = true;
			}
		}
		
		return resultado;
	}
	
	public void salvar(View view) {
		pegarValores();
		if(validarCampos()){
		EventosDao dao = new EventosDao(this);
		dao.salvar(e);
		
		Toast.makeText(this, "Cadastro salvo com sucesso", Toast.LENGTH_SHORT).show();
		
		i = new Intent(this, EventosActivity.class);
		startActivity(i);
		finish();
		}
		else{
			Toast.makeText(this, "Verifique se os campos foram preenchidos corretamente", Toast.LENGTH_SHORT).show();
		}

	}
	
	public void cancelar(View view) {
		i = new Intent(this, EventosActivity.class);
		startActivity(i);
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.novo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		Intent i;
		if (id == R.id.mn_salvar) {
			i = new Intent(this, NovoActivity.class);
			startActivity(i);
			finish();
		} else if (id == R.id.mn_cancelar) {
			i = new Intent(this, EventosActivity.class);
			startActivity(i);
		} else if (id == R.id.mn_sobre) {
			i = new Intent(this, SobreActivity.class);
			startActivity(i);
		} else if (id == R.id.mn_voltar) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
