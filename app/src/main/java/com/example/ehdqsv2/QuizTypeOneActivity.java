package com.example.ehdqsv2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ehdqsv2.database.Questions;
import com.example.ehdqsv2.network.ApiClient;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizTypeOneActivity extends AppCompatActivity {
    List<Questions> quesList;
    int score=0;
    int qid=0;
    Questions currentQ;
    TextView txtQuestion;
    RadioButton rda, rdb, rdc;
    Button butNext;
    private QuestionsViewModel questionsViewModel;
    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_type_one);



        relativeLayout = (RelativeLayout) findViewById(R.id.profileLoadingScreen);

        fetchQuestions();


        questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);

        questionsViewModel.getAllQuestions().observe(this, new Observer<List<Questions>>() {
            @Override
            public void onChanged(@Nullable final List<Questions> words) {
                // Update the cached copy of the words in the adapter.
                quesList = words;
                Collections.shuffle(quesList);
            }
        });

    }

    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getQuestion());
        rda.setText(currentQ.getOptA());
        rdb.setText(currentQ.getOptB());
        rdc.setText(currentQ.getOptC());
        qid++;
    }

    private void fetchQuestions(){



    }

    public void takeAction(){
        relativeLayout.setVisibility(View.INVISIBLE);
        currentQ=quesList.get(qid);
        txtQuestion=(TextView)findViewById(R.id.textView1);
        rda=(RadioButton)findViewById(R.id.radio0);
        rdb=(RadioButton)findViewById(R.id.radio1);
        rdc=(RadioButton)findViewById(R.id.radio2);
        butNext=(Button)findViewById(R.id.button1);
        setQuestionView();
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);

                if (grp.getCheckedRadioButtonId() == -1){
                    return;
                }

                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());

                grp.clearCheck();
                //Log.d("yourans", currentQ.getANSWER()+" "+answer.getText());

                if(currentQ.getAnswer().equals(answer.getText()))
                {
                    score++;
                    Log.d("score", "Your score"+score);
                }
                if(qid<5){
                    currentQ=quesList.get(qid);
                    setQuestionView();
                }else{
                    Intent intent = new Intent(QuizTypeOneActivity.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}
