package com.citi.test;

public class Data {
	private Vad first;
	private Vad keyword1;
	private Vad keyword2;
	private Vad keyword3;
	private Vad remark;
	
	public Data(Vad first, Vad keyword1, Vad keyword2, Vad remark) {
		this.first = first;
		this.keyword1 = keyword1;
		this.keyword2 = keyword2;
		this.remark = remark;
	}
	
	public Data(Vad first, Vad keyword1, Vad keyword2, Vad keyword3, Vad remark) {
		this.first = first;
		this.keyword1 = keyword1;
		this.keyword2 = keyword2;
		this.keyword3 = keyword3;
		this.remark = remark;
	}
	
	public Vad getFirst() {
		return first;
	}
	public void setFirst(Vad first) {
		this.first = first;
	}
	public Vad getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(Vad keyword1) {
		this.keyword1 = keyword1;
	}
	public Vad getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(Vad keyword2) {
		this.keyword2 = keyword2;
	}
	public Vad getKeyword3() {
		return keyword3;
	}
	public void setKeyword3(Vad keyword3) {
		this.keyword3 = keyword3;
	}
	public Vad getRemark() {
		return remark;
	}
	public void setRemark(Vad remark) {
		this.remark = remark;
	}
	
}
