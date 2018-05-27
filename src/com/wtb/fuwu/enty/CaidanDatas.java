package com.wtb.fuwu.enty;

import java.util.List;

public class CaidanDatas {
	private String key;
	private List<CaidanData> values;
	public CaidanDatas(String key, List<CaidanData> values) {
		this.key = key;
		this.values = values;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<CaidanData> getValues() {
		return values;
	}
	public void setValues(List<CaidanData> values) {
		this.values = values;
	}
}
