//package com.example.ehdqsv2;
//
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import android.Manifest;
//import android.app.DownloadManager;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.content.res.ColorStateList;
//import android.content.res.Resources;
//import android.graphics.Color;
//import android.graphics.drawable.Drawable;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.util.Log;
//import android.util.TypedValue;
//import android.view.View;
//import android.webkit.CookieManager;
//import android.webkit.URLUtil;
//import android.widget.Button;
//import android.widget.LinearLayout;
////import android.widget.RadioButton;
//import android.widget.ProgressBar;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.ScrollView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.ehdqsv2.AnswersRoomDB.AnswerModel;
//import com.example.ehdqsv2.AnswersRoomDB.AnswersDatabase;
//import com.example.ehdqsv2.network.UserService;
//import com.example.ehdqsv2.pojo.QuestionModel;
//import com.example.ehdqsv2.pojo.SubmitModel;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import okhttp3.Interceptor;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import io.reactivex.CompletableObserver;
//
//import io.reactivex.SingleObserver;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.annotations.NonNull;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.schedulers.Schedulers;
//import okhttp3.Interceptor;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//
//
//
//public class HospitaType2Activity extends AppCompatActivity {
//    String token /*= "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiYTRjZDA0Y2RlNzVkZThlYWRmNmY5YjY2ZWJmNTgwYTdkZDc5Y2VmYzkwMzIzZDhmY2FkNzI0M2I3ODYyMzA5YzdlZWMwOTkyOTI4MmZkNGIiLCJpYXQiOiIxNjMwNTk0MzA0LjE4MzExMiIsIm5iZiI6IjE2MzA1OTQzMDQuMTgzMTE5IiwiZXhwIjoiMTY2MjEzMDMwNC4xNzMzMTQiLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.wzyG37yNpS213RS1iiYi9axzOomK1S3g5js_tMlrzh7lVQ13XN8ogRWChms9JoQkirkLbc1DLXKc261FqXnH-8oTW8-8-wLjQElg4mQovJPbwkTF7ZSke4pWZUNU5c56zKz0lEBiahzhiGaftVyj0W9Tj5jLCR4cDHeryROfSD-xPQ25XZuzzopZqtH-7nF2ISmuTEE3BDEGAqHYsuFvonDYkJb_QhvWSMgmAqnt39Sa8mD7mKoIZzvJ7fH3NZYsy-73vOapwL6VCI38OXQ-yYU1AZOQ3vbu2nkcg_cb3vc_0DoOYCZL3R0loxyEq3j2hAWP1jvDtg1mEu_CB9NKPAyOo-e0BhqsJSoTQ49AdPFIDYDALCccx2-Ge2sUQljbF-F_IE4lvD9XGuH_D4p_hmaGSgtHsS-B1StPbr9lAsmMLzpGsh6p06CbQf3M3v0GLFzUK4kR9J7zVuRpl-mnSRVe-kJPPLQEroF79k54_JGVbAUi03FetOgofUnzW9h9dYIsTdcsu1IOr_mzNBTr16LEjItE76u-G44PYE21TBRCFqlKswEZTXgBSfRqZ3sM0zTAAtaPxscmTsCU_AREYRQBFirwdmUnhcA5T1Agmw5ecKESGbmyIdFXEx4C8a4EK_pSnI29kvfnjqQL-CY1yfirezdmRnjeOEpDOBeZBC0";*/;
//    TextView question;
//    LinearLayout myInnerLayout;
//    ArrayList<QuestionModel.DataModel> questionsList;
//    Button nextButton;
//    Button beforeButton;
//    int questionIndex;
//    int questionSize;
//    RadioGroup rg;
//    TextView questionNumber;
//    int choicesSize;
//    RadioButton[] rb;
//    ProgressBar progressBar;
//    Retrofit retrofit;
//    OkHttpClient client;
//    UserService apiInterface;
//
//    int radioButtonIndex = 0;
//    String []myTempChoices;
//    String myChoices = "";
//    ScrollView scrollView;
//    LinearLayout buttons;
//    LinearLayout submittedLayout;
//
//    SharedPreferences prefs;
//
//    String userId;
//    String myurl="https://juventudedesporto.cplp.org/files/sample-pdf_9359.pdf";
//
//    //    ArrayList<AnswerModel> rxList;
//    Map<String,String> answersMapList;
//    AnswersDatabase answersDatabase;
//    Button downloadReportButton;
//    private static int REQUEST_CODE=1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_hospita_type2);
//        question = findViewById(R.id.department_question);
//        questionsList = new ArrayList<>();
//        nextButton = findViewById(R.id.next_button);
//        beforeButton = findViewById(R.id.before_button);
//        myInnerLayout = findViewById(R.id.my_inner_layout);
//        questionNumber = findViewById(R.id.question_number);
//        rg = new RadioGroup(this); //create the RadioGroup
//        questionIndex = 0;
//        questionSize = 0;
//        progressBar = findViewById(R.id.progressbar);
//        scrollView = findViewById(R.id.scroll_view);
//        buttons = findViewById(R.id.buttons);
//        submittedLayout = findViewById(R.id.submitted_layout);
//        downloadReportButton = findViewById(R.id.download_report_id);
//        submittedLayout.setVisibility(View.GONE);
//
//        answersMapList = new HashMap<>();
//        answersDatabase= AnswersDatabase.getInstance(this);
//
//        prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//
//        userId = prefs.getString("userId","empty");
//        token = prefs.getString("userToken","empty");
//
//
//        ActivityCompat.requestPermissions(this, new String[]{
//                Manifest.permission.WRITE_EXTERNAL_STORAGE
//        }, REQUEST_CODE);
//
//        client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request newRequest = chain.request().newBuilder()
//                        .addHeader("Authorization", "Bearer " + token)
//                        .build();
//                return chain.proceed(newRequest);
//            }
//        }).build();
//
//        retrofit = new Retrofit.Builder()
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("https://dev.hospitals.design/api/")
//                .build();
//
//        apiInterface = retrofit.create(UserService.class);
//
//        getFromRoomDatabase();
//        getQuestions();
//
//
//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                if (questionIndex < questionSize - 1) {
//                    beforeButton.setVisibility(View.VISIBLE);
//                    scrollView.setVisibility(View.VISIBLE);
//                    buttons.setVisibility(View.VISIBLE);
//                    submittedLayout.setVisibility(View.GONE);
//
//                    questionIndex += 1;
//                    System.out.println("my index = " + questionIndex + " size " + questionSize);
//
//                    if (questionIndex < 9)
//                        questionNumber.setText("Question 0" + (questionIndex + 1) + " /" + questionSize);
//                    else
//                        questionNumber.setText("Question " + (questionIndex + 1) + " /" + questionSize);
//
//                    question.setText(questionsList.get(questionIndex).content);
//                    choicesSize = questionsList.get(questionIndex).choices.size();
//                    rb = new RadioButton[choicesSize];
//
//                    createRadioButton();
////                    System.out.println("alpoooooooooooooooop******************** " + rg.getCheckedRadioButtonId());
//                    if (questionIndex == questionSize - 1) {
//                        nextButton.setText("Submit");
//                        Drawable drawable = getResources().getDrawable(R.drawable.btn_background_rec_submit);
//                        nextButton.setBackground(drawable);
//                    }
//
//                } else if (questionIndex == questionSize - 1) {
//                    System.out.println("Entered here ya " + questionIndex);
//                    nextButton.setText("Submit");
//                    Drawable drawable = getResources().getDrawable(R.drawable.btn_background_rec_submit);
//                    nextButton.setBackground(drawable);
//
//                    SubmitAnswers();
//                }
//
////                rg = new RadioGroup(getApplicationContext()); //create the RadioGroup
//            }
//        });
//
//        beforeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rg = new RadioGroup(getApplicationContext()); //create the RadioGroup
//
//                if (questionIndex > 0) {
//                    System.out.println("my index1 = " + questionIndex + " size " + questionSize);
//                    questionIndex -= 1;
//                    System.out.println("my index1 = " + questionIndex + " size " + questionSize);
//
//
//                    if (questionIndex <= 9)
//                        questionNumber.setText("Question 0" + (questionIndex + 1) + " /" + questionSize);
//                    else
//                        questionNumber.setText("Question " + (questionIndex + 1) + " /" + questionSize);
//
//                    if (questionIndex == 0) {
//                        beforeButton.setVisibility(View.GONE);
//                    }
//
//                    question.setText(questionsList.get(questionIndex).content);
//                    choicesSize = questionsList.get(questionIndex).choices.size();
//
//                    createRadioButton();
//                }
//            }
//        });
//
//
//        downloadReportButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//                        GenerateReport();
//                        Log.e("Permission error","You have permission");
//
//                    }
//                }
////                haveStoragePermission();
//
////                String pdfUrl = "http://www.africau.edu/images/default/sample.pdf";
////
////                DownloadManager.Request request = new DownloadManager.Request( Uri.parse( pdfUrl ) );
////                String title = URLUtil.guessFileName(pdfUrl,null,null);
////                request.setTitle(title);
////                request.setDescription("Downloading File please wait......");
////                String cookie = CookieManager.getInstance().getCookie(pdfUrl);
////                request.addRequestHeader("cookie",cookie);
////                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
////                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title);
////
////                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
////                downloadManager.enqueue(request);
////                Toast.makeText(HospitaType1Activity.this,"Downloading started.",Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }
//
//
//    void getQuestions() {
//        Call<QuestionModel> call = apiInterface.getQuestions("2", "1");
//
//
//        System.out.println("myUserId is equal = "+prefs.getString("userId","empty"));
//        System.out.println("myToken is equal = "+prefs.getString("userToken","empty"));
//        Log.d("TAGff", "onCreate: "+prefs.getString("userId","empty"));
//
//        call.enqueue(new Callback<QuestionModel>() {
//            @Override
//            public void onResponse(Call<QuestionModel> call, Response<QuestionModel> response) {
//                progressBar.setVisibility(View.GONE);
//
//                questionsList = response.body().data;
//                questionSize = questionsList.size();
//                choicesSize = questionsList.get(questionIndex).choices.size();
//                question.setText(questionsList.get(questionIndex).content);
//                System.out.println("qqq = " + questionIndex);
//                questionNumber.setText("Question 0" + (questionIndex + 1) + " /" + questionSize);
//                System.out.println("qqq2 = " + questionIndex);
//                rb = new RadioButton[choicesSize];
//
//                beforeButton.setVisibility(View.GONE);
//
//                myTempChoices = new String[questionSize];
//
////                getFromRoomDatabase();
//
//
//                //initialize my choices
////                for (int i = 0; i < questionSize; i++) {
////                    myTempChoices.add("-1");
////                }
//
//                createRadioButton();
//            }
//
//            @Override
//            public void onFailure(Call<QuestionModel> call, Throwable t) {
//                progressBar.setVisibility(View.GONE);
//
//                System.out.println("_________________" + "error: " + t.getMessage());
//            }
//        });
//    }
//
//
//
//
//    private void createRadioButton() {
//        System.out.println("alpoفففففففففooooooooooooooop******************** " + rg.getCheckedRadioButtonId());
//
//        getFromRoomDatabase();
//
//        rb = new RadioButton[choicesSize];
//        rg.removeAllViews();
//        myInnerLayout.removeAllViews();
//
//        ColorStateList myColorStateList = setRadioGroupDesign();
//        RadioGroup.LayoutParams params= setRadioButtonsMargins();
//
//        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
//        for (radioButtonIndex = 0; radioButtonIndex < choicesSize; radioButtonIndex++) {
//            System.out.println("radioButtonIndex = " + radioButtonIndex);
//            rb[radioButtonIndex] = new RadioButton(this);
//            rb[radioButtonIndex].setLayoutParams(params);
//            rb[radioButtonIndex].setText(" " + questionsList.get(questionIndex).choices.get(radioButtonIndex).content);
//            rb[radioButtonIndex].setId(Integer.parseInt(questionsList.get(questionIndex).choices.get(radioButtonIndex).id));
//            rb[radioButtonIndex].setButtonTintList(myColorStateList);
//            rb[radioButtonIndex].setBackground(ContextCompat.getDrawable(this, R.drawable.editetext_back));
//            rb[radioButtonIndex].setElevation(5);
//
//            rb[radioButtonIndex].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    if (radioButtonIndex==0){
////                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 0");
////                    }else if(radioButtonIndex == 1){
////                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 1");
////                    }else {
////                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@########################### "+rb[radioButtonIndex].getId());
////                    }
////                    if (myTempChoices.get(questionIndex)!=null)
////                        myTempChoices.add(questionIndex,""+radioButtonIndex);
////                    else
////                        myTempChoices.set(questionIndex,""+radioButtonIndex);
//
////                    if (myChoices.length() != 0) {
////                        myChoices = myChoices + "," + radioButtonIndex;
////                    } else {
////                        myChoices = "" + radioButtonIndex;
////                    }
////                    System.out.println("Okkkkkkkkkkkkkkkk " + myChoices);
//                }
//            });
//
//            rb[radioButtonIndex].invalidate();
//            rg.addView(rb[radioButtonIndex]);
//
//            String answered = answersMapList.get((questionIndex+1)+"");
//
//            System.out.println("yeeeeeeeeeah = "+(questionIndex+1)+" , "+answered);
//
//            if(answered!=null){
//                System.out.println("Dkhal");
//                String rIn = String.valueOf(rb[radioButtonIndex].getId());
//                System.out.println("rIn = "+rIn + "  Ans = "+answered + " qIn = "+(questionIndex+1));
//                if(rIn.equals(answered)){
//                    System.out.println("Dkhal2");
//                    rb[radioButtonIndex].setChecked(true);
//                }
//            }
//
//            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                    System.out.println("alpoooooooooooooooop22*************** " + radioGroup.getCheckedRadioButtonId() +" = "+ questionIndex);
//                    myTempChoices[questionIndex] = radioGroup.getCheckedRadioButtonId()+"";
//
//                    // save in room database
//                    saveInRoomDatabase(questionIndex+1+"", radioGroup.getCheckedRadioButtonId()+"");
//                    updateInRoomDatabase();
////                    getFromRoomDatabase();
////                    myTempChoices.add(questionIndex,radioGroup.getCheckedRadioButtonId()+"");
////                    myTempChoices.set(questionIndex,radioGroup.getCheckedRadioButtonId()+"");
//                }
//            });
//
//
//        }
//
//
//        myInnerLayout.addView(rg);//you add the whole RadioGroup to the layout
//        rg = new RadioGroup(getApplicationContext()); //create the RadioGroup
//
//    }
//
//    private void updateInRoomDatabase(){
//        answersDatabase.answersDao().updateAnswer("1","2")
//                .subscribeOn(Schedulers.computation())
//                .subscribe(new CompletableObserver() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("compeletly updated");
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//                });
//
//    }
//
//    public void saveInRoomDatabase(String myQuestionIndex , String myChoiceId){
//
//        answersDatabase.answersDao().insertAnswer(new AnswerModel(userId,myQuestionIndex,myChoiceId))
////        answersDatabase.answersDao().insertAnswer(new AnswerModel("1","3"))
//                .subscribeOn(Schedulers.computation())
//                .subscribe(new CompletableObserver() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//                });
//
//    }
//
//    public void getFromRoomDatabase(){
////        answersDatabase.answersDao().getAnswers()
////                .subscribeOn(Schedulers.computation())
////                .subscribeOn(AndroidSchedulers.mainThread())
////                .subscribe(userList ->{
////                            for (int i=0;i<userList.size();i++) {
////                            answersMapList.put(userList.get(i).getQuestionId(), userList.get(i).getChoiceId());
////                            System.out.println("=============times============eeeeeee");
////                        }
////                    System.out.println(userList.size());
////                } );
//
//
//
//
////        String tt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNDcxNGFlNzJmNTRhYTliYWQ5ZDcyMjQyOTA0ZjlkMWNlZjNlZjliYzBmMTI2Y2Q3YjIyYjg1MzE4YjFlMmU5ZjhmYTUwZDc5ZjQ0MzllNWUiLCJpYXQiOiIxNjMxNDIyMjkxLjcxNzMxMiIsIm5iZiI6IjE2MzE0MjIyOTEuNzE3MzE2IiwiZXhwIjoiMTY2Mjk1ODI5MS43MTU1NjgiLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.Lns0WBae9JnS-UZMWr84OR21prYfE1loycZcBZatOSpvo0a3TQ1WNRqW1dJM7-Ym-Pv5p0TDXeEhDTrqCbBY_YcfcKToNvu8nH7ssmqJvw1Q_DTkeZSsOIhzVS1qNU_bLOobdNH5SOLc_V6sxEHOzRs7A71oUwhd9S_ZpNTeS3CRiVvwiewZxoJo2jDyNmOG7xuaqB7zrNlGgLaxYYSEm-pCchZSWIQtE2Fu1SRFi2pLaSjwsxZUV01cFnzXaQxNY2hYtzogLqcmkOIMO0DnrDyorLTpPXPsLjbHVE_RwyjX_NQ7Z12o__CUPo5wQRj4FF1TtPTemLAfO0xCvw0D8bXHUeYwLhSVXLZR4waesPHT-4Hks7dFIG_roeanln8o448UqmabMVk78WOuWfuDVD5cl_mI943BkoK0Ja5eq431LkWKTlUHZolqoUej7YHIH97zDZoGmSA57cbGmYPYnbuPMuyfZoFwHydYIj9G0eM6hjMzKJEblyFCyS6UdaB6qPoaNfb3EA35MFGhQT_ShbXrw-eo6KmW8TA5HBkS96YwikqUh8qg3BE0yfZq4YGeMFYJb1GNhiW6kSz88DdJ5cKIIiTVioMFF2Gr6QvafEZsdaR1nzs890eYkNq5JSCEpvtY9S4eHGaSmWj6W_ZEN5Ua8L5mSDxpnukSpJ6b_Qc";
////
////        System.out.println("myToken = " +token);
////        System.out.println("myToken2 = "+tt);
//
//        answersDatabase.answersDao().getAnswers(userId)
//                .subscribeOn(Schedulers.computation())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<List<AnswerModel>>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(@NonNull List<AnswerModel> answerModels) {
////                        progressBar.setVisibility(View.VISIBLE);
//
////                        rxList = (ArrayList<AnswerModel>) answerModels;
////                        System.out.println("rx first check = "+answerModels.get(0).getQuestionId()+" , "+answerModels.get(0).getChoiceId());
//                        for (int i=0;i<answerModels.size();i++) {
//                            answersMapList.put(answerModels.get(i).getQuestionId(), answerModels.get(i).getChoiceId());
//                            System.out.println("=============times============eeeeeee");
//                        }
//                        System.out.println("=============times============"+answersMapList.size()+" , "+answersMapList);
//
////                        getQuestions();
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//                });
//
//    }
//
//
//    ColorStateList setRadioGroupDesign(){
//
//
//        int[][] states = new int[][]{
//                new int[]{android.R.attr.state_checked}, // unchecked
//                new int[]{-android.R.attr.state_checked},
//        };
//
//        int[] colors = new int[]{
//                Color.rgb(27, 125, 172),
//                R.color.appColor,
//        };
//
//        ColorStateList myColorStateList = new ColorStateList(states, colors);
//
//
//        return myColorStateList;
//    }
//
//    RadioGroup.LayoutParams setRadioButtonsMargins(){
//        int radioButtonWidth = Change_db_to_pixel(50);
//        int radioMarginBottom = Change_db_to_pixel(10);
//
//        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
//                RadioGroup.LayoutParams.MATCH_PARENT,
//                radioButtonWidth
//        );
//        params.setMargins(0, 0, 0, radioMarginBottom);
//
//        return params;
//    }
//
//    int Change_db_to_pixel(float db) {
//        float dip = db;
//        Resources r = getResources();
//        float px = TypedValue.applyDimension(
//                TypedValue.COMPLEX_UNIT_DIP,
//                dip,
//                r.getDisplayMetrics()
//        );
//
//        return (int) px;
//    }
//
//
//    void SubmitAnswers() {
//        myChoices="";
//        System.out.println(" my choices =( ) -------------------------------------------------------- ");
//        StringBuilder myString = new StringBuilder();
//
//        for (int i = 0; i < questionSize; i++) {
//            System.out.print(myTempChoices[i] + ",");
//            if (myTempChoices[i] != null){
//
//                myString.append(myTempChoices[i]);
//                myString.append(",");
//
//            }
//        }
//        myChoices = myString.toString();
//        System.out.println("my comma String "+myChoices);
//        myChoices = myChoices.substring(0, myChoices.length() - 1);
//        System.out.println("my last String "+myChoices);
//
//
//        progressBar.setVisibility(View.VISIBLE);
//        scrollView.setVisibility(View.GONE);
//        buttons.setVisibility(View.GONE);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        SubmitModel submitModel = new SubmitModel();
//        submitModel.hospital_id = "1";
////        submitModel.choices = "1,2,3";
//        submitModel.choices = myChoices;
//
//        Call<QuestionModel> call = apiInterface.submitAnswers(submitModel);
//
//        call.enqueue(new Callback<QuestionModel>() {
//            @Override
//            public void onResponse(Call<QuestionModel> call, Response<QuestionModel> response) {
//                System.out.println("\n submit response " + response.body().message);
//                progressBar.setVisibility(View.GONE);
//                submittedLayout.setVisibility(View.VISIBLE);
//
//            }
//
//            @Override
//            public void onFailure(Call<QuestionModel> call, Throwable t) {
//                System.out.println("submit response " + t.getMessage());
//                progressBar.setVisibility(View.GONE);
//            }
//        });
//    }
//
//    void GenerateReport(){
//        String url="https://dev.hospitals.design/api/generateReport?hospital_id=1";
//        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//        String title = URLUtil.guessFileName(url,null,null);
//        request.setTitle(title);
//        request.setDescription("Downloading File Please wait.....");
//        String cookie = CookieManager.getInstance().getCookie(url);
//        request.addRequestHeader("cookie",cookie);
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"HospitalFolder/"+title);
//        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//        downloadManager.enqueue(request);
//
//        Intent intent = new Intent();
//        intent.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
//
//        Toast.makeText(HospitaType2Activity.this,"Downloading started.",Toast.LENGTH_SHORT).show();
//
//    }
//
////    public  boolean haveStoragePermission() {
////        if (Build.VERSION.SDK_INT >= 23) {
////            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
////                    == PackageManager.PERMISSION_GRANTED) {
////                Log.e("Permission error","You have permission");
////                return true;
////            } else {
////
////                Log.e("Permission error","You have asked for permission");
////                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
////                return false;
////            }
////        }
////        else { //you dont need to worry about these stuff below api level 23
////            Log.e("Permission error","You already have the permission");
////            return true;
////        }
////    }
//
////    @Override
////    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
////        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
////            //you have the permission now.
////            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(myurl));
////            request.setTitle("Vertretungsplan");
////            request.setDescription("wird heruntergeladen");
////            request.allowScanningByMediaScanner();
////            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
////            String filename = URLUtil.guessFileName(myurl, null, MimeTypeMap.getFileExtensionFromUrl(myurl));
////            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
////            DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
////            manager.enqueue(request);
////        }
////    }
//}
//
//
//
//
//
//
//
//
//
////
////public class HospitaType2Activity extends AppCompatActivity {
////    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiYTRjZDA0Y2RlNzVkZThlYWRmNmY5YjY2ZWJmNTgwYTdkZDc5Y2VmYzkwMzIzZDhmY2FkNzI0M2I3ODYyMzA5YzdlZWMwOTkyOTI4MmZkNGIiLCJpYXQiOiIxNjMwNTk0MzA0LjE4MzExMiIsIm5iZiI6IjE2MzA1OTQzMDQuMTgzMTE5IiwiZXhwIjoiMTY2MjEzMDMwNC4xNzMzMTQiLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.wzyG37yNpS213RS1iiYi9axzOomK1S3g5js_tMlrzh7lVQ13XN8ogRWChms9JoQkirkLbc1DLXKc261FqXnH-8oTW8-8-wLjQElg4mQovJPbwkTF7ZSke4pWZUNU5c56zKz0lEBiahzhiGaftVyj0W9Tj5jLCR4cDHeryROfSD-xPQ25XZuzzopZqtH-7nF2ISmuTEE3BDEGAqHYsuFvonDYkJb_QhvWSMgmAqnt39Sa8mD7mKoIZzvJ7fH3NZYsy-73vOapwL6VCI38OXQ-yYU1AZOQ3vbu2nkcg_cb3vc_0DoOYCZL3R0loxyEq3j2hAWP1jvDtg1mEu_CB9NKPAyOo-e0BhqsJSoTQ49AdPFIDYDALCccx2-Ge2sUQljbF-F_IE4lvD9XGuH_D4p_hmaGSgtHsS-B1StPbr9lAsmMLzpGsh6p06CbQf3M3v0GLFzUK4kR9J7zVuRpl-mnSRVe-kJPPLQEroF79k54_JGVbAUi03FetOgofUnzW9h9dYIsTdcsu1IOr_mzNBTr16LEjItE76u-G44PYE21TBRCFqlKswEZTXgBSfRqZ3sM0zTAAtaPxscmTsCU_AREYRQBFirwdmUnhcA5T1Agmw5ecKESGbmyIdFXEx4C8a4EK_pSnI29kvfnjqQL-CY1yfirezdmRnjeOEpDOBeZBC0";
////    TextView question;
////    LinearLayout myInnerLayout;
////    ArrayList<QuestionModel.DataModel> questionsList;
////    Button nextButton;
////    Button beforeButton;
////    int questionIndex;
////    int questionSize;
////    RadioGroup rg;
////    TextView questionNumber;
////    int choicesSize;
////    RadioButton[] rb ;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_hospita_type2);
////        question = findViewById(R.id.department_question);
////        questionsList = new ArrayList<>();
////        nextButton = findViewById(R.id.next_button);
////        beforeButton = findViewById(R.id.before_button);
////        myInnerLayout = findViewById(R.id.my_inner_layout);
////        questionNumber = findViewById(R.id.question_number);
////        rg = new RadioGroup(this); //create the RadioGroup
////        questionIndex = 0;
////        questionSize = 0;
////
////        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
////            @Override
////            public okhttp3.Response intercept(Chain chain) throws IOException {
////                Request newRequest = chain.request().newBuilder()
////                        .addHeader("Authorization", "Bearer " + token)
////                        .build();
////                return chain.proceed(newRequest);
////            }
////        }).build();
////
////        Retrofit retrofit = new Retrofit.Builder()
////                .client(client)
////                .addConverterFactory(GsonConverterFactory.create())
////                .baseUrl("https://dev.hospitals.design/api/")
////                .build();
////
////
////        UserService apiInterface = retrofit.create(UserService.class);
////
////
////        Call<QuestionModel> call = apiInterface.getQuestions("2", "1");
////
////        call.enqueue(new Callback<QuestionModel>() {
////            @Override
////            public void onResponse(Call<QuestionModel> call, Response<QuestionModel> response) {
////
////                questionsList = response.body().data;
////                questionSize = questionsList.size();
////                if(questionSize>0){
////                choicesSize = questionsList.get(questionIndex).choices.size();
////                question.setText(questionsList.get(questionIndex).content);
////                questionNumber.setText("Question 0" + (questionIndex + 1) + " /" + questionSize);
////                rb = new RadioButton[choicesSize];
////
////                createRadioButton();
////                }else {
////                    question.setText("No Questions Yet");
////                }
////
////
////            }
////
////            @Override
////            public void onFailure(Call<QuestionModel> call, Throwable t) {
////                System.out.println("_________________" + "error: " + t.getMessage());
////            }
////        });
////
////        nextButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                if (questionIndex < questionSize-1) {
////                    questionNumber.setText("Question 0" + (questionIndex + 1) + " /" + questionSize);
////                    questionIndex+=1;
////                    question.setText(questionsList.get(questionIndex).content);
////                    choicesSize = questionsList.get(questionIndex).choices.size();
////                    rb = new RadioButton[choicesSize];
////
////                    createRadioButton();
////                }
////            }
////        });
////
////        beforeButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                if (questionIndex != 0) {
////                    questionNumber.setText("Question 0" + (questionIndex - 1) + " /" + questionSize);
////
////                    questionIndex-=1;
////                    question.setText(questionsList.get(questionIndex).content);
////                    choicesSize = questionsList.get(questionIndex).choices.size();
////
////                    createRadioButton();
////                }
////            }
////        });
////
////    }
////
////
////    private void createRadioButton() {
////        rb = new RadioButton[choicesSize];
////        rg.removeAllViews();
////        myInnerLayout.removeAllViews();
////
////        int radioButtonWidth = Change_db_to_pixel(50);
////        int radioMarginBottom = Change_db_to_pixel(10);
////
////
////        int[][] states = new int[][] {
////
////                new int[] {android.R.attr.state_checked}, // unchecked
////                new int[] {-android.R.attr.state_checked},
////
////        };
////
////
////        int[] colors = new int[] {
////                Color.rgb (27,125,172),
////                R.color.appColor,
////        };
////
////        ColorStateList myList = new ColorStateList(states, colors);
////
////
////        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
////                RadioGroup.LayoutParams.MATCH_PARENT,
////                radioButtonWidth
////        );
////        params.setMargins(0, 0, 0, radioMarginBottom);
////
////        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
////        for (int i = 0; i < choicesSize; i++) {
////            rb[i] = new RadioButton(this);
////            rb[i].setLayoutParams(params);
////            rb[i].setText(" " + questionsList.get(questionIndex).choices.get(i).content);
////            rb[i].setId(i + 100);
////
////            rb[i].setButtonTintList(myList);
////            rb[i].setBackground(ContextCompat.getDrawable(this, R.drawable.editetext_back));
////            rb[i].setElevation(5);
////
////
////
////            rb[i].invalidate();
////            rg.addView(rb[i]);
////
////        }
////
////
////        myInnerLayout.addView(rg);//you add the whole RadioGroup to the layout
////    }
////
////
////    int Change_db_to_pixel(float db) {
////        float dip = db;
////        Resources r = getResources();
////        float px = TypedValue.applyDimension(
////                TypedValue.COMPLEX_UNIT_DIP,
////                dip,
////                r.getDisplayMetrics()
////        );
////
////        return (int) px;
////    }
////}