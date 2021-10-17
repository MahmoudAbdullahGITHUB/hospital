package com.example.ehdqsv2.pojo;

import java.util.ArrayList;

public class DepartmentModel {
    public String success;
    public ArrayList<DataModel> data = new ArrayList<>();
    public String message;

    public class DataModel {
        public String id;
        public String name;
        public String address;
        public int hospital_type_id;
        public String user_id;
        public String created_at;
        public String updated_at;
    }
}
