package br.com.cderecords.activity;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cderecords.R;
import br.com.cderecords.dao.EventosDao;
import br.com.cderecords.model.Evento;

public class NovoActivity extends ActionBarActivity {
	
	private EditText et_nome_evento;
	private static Button bt_definir_data;
	private static String nome_evento, data_evento;
	private Intent i;
	private Evento e = new Evento();
	private int year, month, day;
	private Calendar cal;
	private static final int DILOG_ID = 0;
	private String TEXT_BUTTON;
	private Toolbar mToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
		setContentView(R.layout.activity_novo);
		referenciarViews();
		mToolbar.setTitle(getResources().getString(R.string.title_activity_novo));
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		cal = Calendar.getInstance();
		this.year = cal.get(Calendar.YEAR);
		this.month = cal.get(Calendar.MONTH);
		this.day = cal.get(Calendar.DAY_OF_MONTH);
		TEXT_BUTTON = getResources().getString(R.string.button_definir_data);
	}
	
	private void referenciarViews() {
		mToolbar = (Toolbar) findViewById(R.id.tb_main);
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
		
			Toast.makeText(this, getResources().getString(R.string.text_evento_salvo), Toast.LENGTH_SHORT).show();
		
			i = new Intent(this, EventosActivity.class);
			startActivity(i);
			finish();
		} else{
			Toast.makeText(this, getResources().getString(R.string.text_verifique_campos), Toast.LENGTH_SHORT).show();
		}
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
			salvar(new View(this));
		} else if (id == R.id.mn_sobre) {
			i = new Intent(this, SobreActivity.class);
			startActivity(i);
		} else {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
