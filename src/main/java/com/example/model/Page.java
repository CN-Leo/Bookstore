package com.example.model;

import java.util.List;

public class Page<T> {
	
	
	private List<T> rows;				// list result of this page
	private int total;				// page number
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
}
