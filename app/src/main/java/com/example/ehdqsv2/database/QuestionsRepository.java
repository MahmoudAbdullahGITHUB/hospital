package com.example.ehdqsv2.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionsRepository {

    private QuestionsDao mQuestionsDao;
    private LiveData<List<Questions>> mAllQuestions;


    public QuestionsRepository(Application application) {
        QuestionsRoomDatabase db = QuestionsRoomDatabase.getDatabase(application);
        mQuestionsDao = db.wordDao();
        mAllQuestions = mQuestionsDao.getAllQuestions();
    }


    public  LiveData<List<Questions>> getmAllQuestions() {
        return mAllQuestions;
    }


    public void insert (Questions word) {
        new insertAsyncTask(mQuestionsDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Questions, Void, Void> {

        private QuestionsDao mAsyncTaskDao;

        insertAsyncTask(QuestionsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Questions... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
