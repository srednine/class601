package com.ds.vo;

import java.util.Date;

public class RN_Board {
	
	private int brd_no = 0;
	private String brd_title = null;
	private String brd_writer = null;
	private String brd_content = null;
	private Date brd_date = null;
	private int brd_hit = 0;
	private String brd_secret = null;
	private int rnum = 0;
	
	@Override
	public String toString() {
		return "RN_Board [brd_no=" + brd_no + ", brd_title=" + brd_title + ", brd_writer=" + brd_writer
				+ ", brd_content=" + brd_content + ", brd_date=" + brd_date + ", brd_hit=" + brd_hit + ", brd_secret="
				+ brd_secret + ", brd_rnum=" + rnum + "]";
	}
	public RN_Board(int brd_no, String brd_title, String brd_writer, String brd_content, Date brd_date, int brd_hit,
			String brd_secret) {
		super();
		this.brd_no = brd_no;
		this.brd_title = brd_title;
		this.brd_writer = brd_writer;
		this.brd_content = brd_content;
		this.brd_date = brd_date;
		this.brd_hit = brd_hit;
		this.brd_secret = brd_secret;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public RN_Board() {
		super();
	}
	public String getBrd_secret() {
		return brd_secret;
	}
	public void setBrd_secret(String brd_secret) {
		this.brd_secret = brd_secret;
	}
	public int getBrd_no() {
		return brd_no;
	}
	public void setBrd_no(int brd_no) {
		this.brd_no = brd_no;
	}
	public String getBrd_title() {
		return brd_title;
	}
	public void setBrd_title(String brd_title) {
		this.brd_title = brd_title;
	}
	public String getBrd_writer() {
		return brd_writer;
	}
	public void setBrd_writer(String brd_writer) {
		this.brd_writer = brd_writer;
	}
	public String getBrd_content() {
		return brd_content;
	}
	public void setBrd_content(String brd_content) {
		this.brd_content = brd_content;
	}
	public Date getBrd_date() {
		return brd_date;
	}
	public void setBrd_date(Date brd_date) {
		this.brd_date = brd_date;
	}
	public int getBrd_hit() {
		return brd_hit;
	}
	public void setBrd_hit(int brd_hit) {
		this.brd_hit = brd_hit;
	}
}
