package com.example.ehdqsv2.AnswersRoomDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface AnswersDao {

    @Insert
    Completable insertAnswer(AnswerModel answerModel);

    @Query("select * from answer_table WHERE userId = :userId AND hospitalId = :hospitalId AND departmentId = :departmentId")
    Single<List<AnswerModel>> getAnswers(String userId, String hospitalId, String departmentId);

    @Query("UPDATE answer_table SET choiceId = :choiceId WHERE userId = :userId AND hospitalId = :hospitalId AND departmentId = :departmentId AND questionId = :qId")
    Completable updateAnswer(String userId, String hospitalId, String departmentId, String qId, String choiceId);

//    WHERE id = :userId

}
