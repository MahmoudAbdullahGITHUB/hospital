package com.example.ehdqsv2.AnswersRoomDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "answer_table")
public class AnswerModel {

    @PrimaryKey(autoGenerate = true)
    int iterationId;

    @NonNull
    @ColumnInfo(name = "userId")
    String userId;

    @NonNull
    @ColumnInfo(name = "hospitalId")
    String hospitalId;

    @NonNull
    @ColumnInfo(name = "departmentId")
    String departmentId;

    @NonNull
    @ColumnInfo(name = "questionId")
    String questionId;


    @ColumnInfo(name = "choiceId")
    String choiceId;


    public AnswerModel(@NonNull String userId, @NonNull String hospitalId, @NonNull String departmentId, @NonNull String questionId, String choiceId) {
        this.userId = userId;
        this.hospitalId = hospitalId;
        this.departmentId = departmentId;
        this.questionId = questionId;
        this.choiceId = choiceId;
    }

    public int getIterationId() {
        return iterationId;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    @NonNull
    public String getHospitalId() {
        return hospitalId;
    }

    @NonNull
    public String getDepartmentId() {
        return departmentId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getChoiceId() {
        return choiceId;
    }


}
