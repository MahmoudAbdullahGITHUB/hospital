package com.example.ehdqsv2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionsDao {


    @Query("SELECT * from questions_table")
    LiveData<List<Questions>> getAllQuestions();


    @Insert
    void insert(Questions questions);

    @Query("DELETE FROM questions_table")
    void deleteAll();
}