package com.eoficina.api.dto;
import java.util.List;

public class DtoReturnPaginated<T> {
	public int recordsTotal;
	public List<T> lista;
	
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public List<T> getLista() {
		return lista;
	}
	public void setLista(List<T> lista) {
		this.lista = lista;
	}
}