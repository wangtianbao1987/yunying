package com.wtb.fuwu.enty;

import java.util.List;

public class CaidanData {
	public CaidanData(int idx, String name, List<Price> values) {
		this.idx = idx;
		this.name = name;
		this.values = values;
	}
	private int idx;
	private String name;
	private List<Price> values;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Price> getValues() {
		return values;
	}
	public void setValues(List<Price> values) {
		this.values = values;
	}
}
