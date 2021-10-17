package com.example.ehdqsv2.AnswersRoomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = AnswerModel.class,version = 1)
public abstract class AnswersDatabase extends RoomDatabase {

    private static AnswersDatabase instance;

    public abstract AnswersDao answersDao();

    public static synchronized AnswersDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AnswersDatabase.class,"answer_table")
                    .fallbackToDestructiveMigration()
//                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }


}
