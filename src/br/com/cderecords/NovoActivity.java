package br.com.cderecords;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NovoActivity extends Activity {
	
	TextView et_nome_evento, et_data_evento;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novo);
	}
}
