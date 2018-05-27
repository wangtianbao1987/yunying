package com.wtb.fuwu.enty;

public class Price {
	private Double price;
	private Double minPrice;
	private Double maxPrice;
	private String remark;
	private String unit;
	private Double perPrice;
	public Price(Double price, String remark) {
		this.price = price;
		this.remark = remark;
	}
	public Price(Double minPrice, Double maxPrice, String remark) {
		super();
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.remark = remark;
	}
	public Price(String remark, String unit, Double perPrice) {
		super();
		this.remark = remark;
		this.unit = unit;
		this.perPrice = perPrice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getPerPrice() {
		return perPrice;
	}
	public void setPerPrice(Double perPrice) {
		this.perPrice = perPrice;
	}
}
