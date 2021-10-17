package com.example.ehdqsv2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ehdqsv2.database.Questions;
import com.example.ehdqsv2.database.QuestionsRepository;

import java.util.List;

public class QuestionsViewModel extends AndroidViewModel {

    private QuestionsRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<Questions>> mAllQuestions;

    public QuestionsViewModel (Application application) {
        super(application);
        mRepository = new QuestionsRepository(application);
        mAllQuestions = mRepository.getmAllQuestions();
    }

    LiveData<List<Questions>> getAllQuestions() { return mAllQuestions; }

    public void insert(Questions word) { mRepository.insert(word); }
}