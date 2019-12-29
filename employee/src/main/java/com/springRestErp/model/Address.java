package com.springRestErp.model;

import org.dozer.DozerBeanMapper;

import com.springRestErp.entity.AddressEntity;

public class Address {
	private Integer addressId;
	private String street;
	private String city;
	private String state;
	private String country;
	private String pincode;
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	public static Address converFromEntity(AddressEntity ad){
		Address a = new DozerBeanMapper().map(ad,Address.class);
		a.setAddressId(ad.getAddressID());
		
		return a;
	}
	public static void printAddress(Address a) {
		System.out.println(a.getStreet()+a.getCity()+a.getState()+a.getCountry()+a.getPincode());
	}
	public static AddressEntity converFromModelToEntity(Address ad) {
		AddressEntity a = new DozerBeanMapper().map(ad,AddressEntity.class);
		return a;
	}
	
	
}
