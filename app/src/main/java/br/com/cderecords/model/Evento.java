package br.com.cderecords.model;

public class Evento {
	
	private int id, total, homens, mulheres;
	private String evento, data;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getHomens() {
		return homens;
	}
	public void setHomens(int homens) {
		this.homens = homens;
	}
	public int getMulheres() {
		return mulheres;
	}
	public void setMulheres(int mulheres) {
		this.mulheres = mulheres;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
