package br.com.cderecords.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.cderecords.model.Evento;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class EventosDao {
	
	private SQLiteDatabase database;
	private AppDatabase dbHelper;
	private String[] colunas = { AppDatabase.COLUNA_ID, AppDatabase.COLUNA_EVENTO, AppDatabase.COLUNA_DATA, AppDatabase.COLUNA_HOMENS, 
			AppDatabase.COLUNA_MULHERES};
	
	public EventosDao(Context context) {
		dbHelper = new AppDatabase(context);
		database = dbHelper.getDatabase();
	}
	
	public void open() throws SQLException {
		database = dbHelper.getDatabase();
	}

	public void close() {
		dbHelper.closeConnection();
	}
	
	public void salvar(Evento e) {
		ContentValues values = new ContentValues();

		values.put(AppDatabase.COLUNA_EVENTO, e.getEvento());
		values.put(AppDatabase.COLUNA_DATA, e.getData());
		values.put(AppDatabase.COLUNA_HOMENS, e.getHomens());
		values.put(AppDatabase.COLUNA_MULHERES, e.getMulheres());

		long insertId = database.insert(AppDatabase.TABELA_EVENTOS, null, values);
	}
	
	public Evento buscarEventoPorId(int id) {
		Evento e = new Evento();
		Cursor cursor = database.query(AppDatabase.TABELA_EVENTOS, colunas, "_id = ?", new String[] {String.valueOf(id)}, null, null, null);
		
		if(cursor.getCount() > 0) {
			cursor.moveToFirst();
			e = new Evento();
			e.setId(cursor.getInt(0));
			e.setEvento(cursor.getString(1));
			e.setData(cursor.getString(2));
			e.setHomens(cursor.getInt(3));
			e.setMulheres(cursor.getInt(4));			
		}

		cursor.close();

		return e;
	}
	
	public List<Evento> buscarEventos() {
		Evento e;
		List<Evento> listaEventos = new ArrayList<Evento>();
		
		Cursor cursor = database.query(AppDatabase.TABELA_EVENTOS, colunas, null, null, null, null, null);
		if(cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				e = new Evento();
				e.setId(cursor.getInt(0));
				e.setEvento(cursor.getString(1));
				e.setData(cursor.getString(2));
				e.setHomens(cursor.getInt(3));
				e.setMulheres(cursor.getInt(4));
				listaEventos.add(e);			
			} while(cursor.moveToNext());

			cursor.close();

			return listaEventos;
		} else {
			return null;
		}
	}
}
