package br.com.cderecords;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import br.com.cderecords.dao.EventosDao;
import br.com.cderecords.model.Evento;

public class NovoActivity extends Activity {
	
	private EditText et_nome_evento;
	private static Button bt_definir_data;
	private static String nome_evento, data_evento;
	private Intent i;
	private Evento e = new Evento();
	private int year, month, day;
	private Calendar cal;
	private static final int DILOG_ID = 0;
	private String TEXT_BUTTON;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novo);
		referenciarViews();
		cal = Calendar.getInstance();
		this.year = cal.get(Calendar.YEAR);
		this.month = cal.get(Calendar.MONTH);
		this.day = cal.get(Calendar.DAY_OF_MONTH);
		TEXT_BUTTON = getResources().getString(R.string.button_definir_data);
	}
	
	private void referenciarViews() {
		et_nome_evento = (EditText)findViewById(R.id.et_nome_evento);
		bt_definir_data = (Button)findViewById(R.id.btn_definir_data);
		bt_definir_data.setOnClickListener(
				new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						showDialog(DILOG_ID);
					}
				});
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		if(id == DILOG_ID) 
			return new DatePickerDialog(this, dpickerListner, year, month, day);
		
		return null;
	}
	
	private DatePickerDialog.OnDateSetListener dpickerListner = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			monthOfYear += 1;
			String data_evento = "" + dayOfMonth + "/" + monthOfYear + "/" + year;
			NovoActivity.bt_definir_data.setText(data_evento);
		}
	};
	
	private void pegarValores() {
		nome_evento = et_nome_evento.getText().toString();
		data_evento = (String)bt_definir_data.getText();
		e.setEvento(nome_evento);
		e.setData(data_evento);
		e.setHomens(0);
		e.setMulheres(0);		
	}
	
	private boolean validarCampos() {
		
		boolean resultado = false;
		
		if(!nome_evento.equals("")){
			if(!data_evento.equals(TEXT_BUTTON)){
				resultado = true;
			}
		}
		
		return resultado;
	}
	
	public void salvar(View view) {
		pegarValores();
		
		if(validarCampos()) {
			EventosDao dao = new EventosDao(this);
			dao.salvar(e);
		
			Toast.makeText(this, "Evento salvo com sucesso", Toast.LENGTH_SHORT).show();
		
			i = new Intent(this, EventosActivity.class);
			startActivity(i);
			finish();
		} else{
			Toast.makeText(this, "Verifique se os campos foram preenchidos corretamente", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void cancelar(View view) {
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
			finish();
		} else if (id == R.id.mn_sobre) {
			i = new Intent(this, SobreActivity.class);
			startActivity(i);
		} else if (id == R.id.mn_voltar) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
