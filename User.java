package com.model;

public class User {
	private int uacid;
	private String uacname;
	public int getUacid() {
		return uacid;
	}
	public void setUacid(int uacid) {
		this.uacid = uacid;
	}
	public String getUacname() {
		return uacname;
	}
	public void setUacname(String uacname) {
		this.uacname = uacname;
	}
	public String getUactype() {
		return uactype;
	}
	public void setUactype(String uactype) {
		this.uactype = uactype;
	}
	public int getUacpin() {
		return uacpin;
	}
	public void setUacpin(int uacpin) {
		this.uacpin = uacpin;
	}
	@Override
	public String toString() {
		return "User [uacid=" + uacid + ", uacname=" + uacname + ", uactype=" + uactype + ", uacpin=" + uacpin
				+ ", uacbalance=" + uacbalance + "]";
	}
	public double getUacbalance() {
		return uacbalance;
	}
	public void setUacbalance(double uacbalance) {
		this.uacbalance = uacbalance;
	}
	private String  uactype;
	private int uacpin;
	private double uacbalance;
}
