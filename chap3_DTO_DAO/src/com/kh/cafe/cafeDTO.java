package com.kh.cafe;

public class cafeDTO {

	private int cafe_id;
	private String name;
	private String address;
	private String phone_number;
	private String operating_hours;
	
	public cafeDTO(int cafe_id, String name, String address, String phone_number, String operating_hours) {
		this.cafe_id = cafe_id;
		this.name = name;
		this.address = address;
		this.phone_number = phone_number;
		this.operating_hours = operating_hours;
	}
	
	public int getCafe_id() {
		return cafe_id;
	}

	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhone_number() {
		return phone_number;
	}
	
	public String getOperating_hours() {
		return operating_hours;
	}

	public void setCafe_id(int cafe_id) {
		this.cafe_id = cafe_id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public void setOperating_hours(String operating_hours) {
		this.operating_hours = operating_hours;
	}

	
	
}
