package com.example.ehdqsv2.pojo;

import java.util.ArrayList;

public class QuestionModel {

    public String success;
    public ArrayList<DataModel> data = new ArrayList<>();
    public String message;

    public class DataModel {
        public String id;
        public String name;
        public String content;
        public ArrayList<ChoicesModel> choices;

        public class ChoicesModel {
            public String id;
            public String content;
            public boolean selected;
        }
    }
}
