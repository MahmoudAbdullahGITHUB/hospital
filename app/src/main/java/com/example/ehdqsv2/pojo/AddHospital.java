package com.example.ehdqsv2.pojo;

public class AddHospital {

    private String address;
    private String hospital_type_id;
    private String name;


    public AddHospital(String address, String hospital_type_id, String name) {
        this.address = address;
        this.hospital_type_id = hospital_type_id;
        this.name = name;
    }

    public AddHospital() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHospital_type_id() {
        return hospital_type_id;
    }

    public void setHospital_type_id(String hospital_type_id) {
        this.hospital_type_id = hospital_type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
