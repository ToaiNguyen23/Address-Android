package com.example.address_android;

public class Address {
    private String name="";
    private String address="";
    private String type="";
    private String country="";
    private String zipcode="";
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return (name);
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getAddress(){
        return (address);
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return (type);
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
