package com.ds.vo;

public class RN_Member {
	
	private String mem_id    = null;
	private String mem_pw    = null;
	private String mem_name  = null;
	private String mem_email = null;
	private int mem_age      = 0;
	private String mem_addr1 = null;
	private String mem_addr2 = null;
	private String mem_regdt = null;
	private String mem_moddt = null;
	
	@Override
	public String toString() {
		return "RN_Member [mem_id=" + mem_id + ", mem_pw=" + mem_pw + ", mem_name=" + mem_name + ", mem_email="
				+ mem_email + ", mem_age=" + mem_age + ", mem_addr1=" + mem_addr1 + ", mem_addr2=" + mem_addr2
				+ ", mem_regdt=" + mem_regdt + ", mem_moddt=" + mem_moddt + "]";
	}

	public RN_Member() {
		super();
	}
	
	public RN_Member(String mem_id, String mem_pw) {
		super();
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
	}
	
	public RN_Member(String mem_id, String mem_pw, String mem_name, String mem_email, int mem_age, String mem_addr1,
			String mem_addr2) {
		super();
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.mem_name = mem_name;
		this.mem_email = mem_email;
		this.mem_age = mem_age;
		this.mem_addr1 = mem_addr1;
		this.mem_addr2 = mem_addr2;
	}

	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public int getMem_age() {
		return mem_age;
	}
	public void setMem_age(int mem_age) {
		this.mem_age = mem_age;
	}
	public String getMem_addr1() {
		return mem_addr1;
	}
	public void setMem_addr1(String mem_addr1) {
		this.mem_addr1 = mem_addr1;
	}
	public String getMem_addr2() {
		return mem_addr2;
	}
	public void setMem_addr2(String mem_addr2) {
		this.mem_addr2 = mem_addr2;
	}
	public String getMem_regdt() {
		return mem_regdt;
	}
	public void setMem_regdt(String mem_regdt) {
		this.mem_regdt = mem_regdt;
	}
	public String getMem_moddt() {
		return mem_moddt;
	}
	public void setMem_moddt(String mem_moddt) {
		this.mem_moddt = mem_moddt;
	}	
}
