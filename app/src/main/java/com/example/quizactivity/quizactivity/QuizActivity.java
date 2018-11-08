package com.example.quizactivity.quizactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

import static android.app.Activity.*;


public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;

    private Button mTrueButton,mFalseButton,mNextButton,mPrevButton,mCheatButton;
    private TextView mTextView;
    private int mCurrentIndex;
    private boolean usuarioTrapaceou;
    private TextView mQuestionTextView;

    Question[] mQuestions = new Question[]{
            new Question(R.string.pergunta1, false),
            new Question(R.string.pergunta2, true),
            new Question(R.string.pergunta3, false),
            new Question(R.string.pergunta4, false),
            new Question(R.string.pergunta5, true),
    };

    private void updateQuestion() {
        int question = mQuestions[mCurrentIndex].getId_da_respota();

        mTextView.setText(question);

    }

    int total = 0;

    public void somaPonto (boolean soma){
        if(soma){
            total++;
        }else{
            total--;
        }
        Toast.makeText(QuizActivity.this,"pontos : " + total,Toast.LENGTH_SHORT).show();
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestions[mCurrentIndex]
                .isResposta_correta();

        if(answerIsTrue == userPressedTrue) {
            Toast.makeText(QuizActivity.this,R.string.true_button,Toast.LENGTH_SHORT).show();
            mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
            updateQuestion();
            somaPonto(true);
        }else {
            Toast.makeText(QuizActivity.this,R.string.false_button,Toast.LENGTH_SHORT).show();//toast ou button?
            mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
            updateQuestion();
            somaPonto(false);
        }
    }


    //public QuizActivity() {
    //  super();
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        setContentView(R.layout.activity_quiz);

        savedInstanceState.putInt(KEY_INDEX,
                mCurrentIndex);


        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mPrevButton = (Button) findViewById(R.id.prev_button);
        mTextView = (TextView) findViewById(R.id.textoid);
        mNextButton = (Button) findViewById(R.id.next_button);
        mCheatButton = (Button)findViewById(R.id.cheat_button);

        // int question = mQuestions[mCurrentIndex].getId_da_respota();

        // mTextView.setText(question);

        Collections.shuffle(Arrays.asList(mQuestions));


        mTrueButton.setOnClickListener(e ->{
            checkAnswer(true);
        });

        mFalseButton.setOnClickListener(e ->{
            checkAnswer(false);

        });


        mTextView.setOnClickListener(e ->{
            mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
            updateQuestion();

        });

        mNextButton.setOnClickListener(e ->{
            mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
            updateQuestion();
            //é necessário um contador para mostrar a pontuação?
        });

        mPrevButton.setOnClickListener(e ->{ // setando listener
            mCurrentIndex = (mCurrentIndex - 1) % mQuestions.length;
            updateQuestion();

        });

        mCheatButton.setOnClickListener(e ->
                startCheatActivity());

        mCheatButton.setOnClickListener(e -> {
            Intent intent = new Intent(QuizActivity.this,
                    CheatActivity.class);
            startActivity(intent);
        });

        mCheatButton.setOnClickListener(e -> {
            Intent intent = new Intent(QuizActivity.this,
                    CheatActivity.class);
            intent.putExtra(CheatActivity.getExtraAnswerIsTrue(),
                    mQuestions[mCurrentIndex].isResposta_correta());
            startActivity(intent);
        });

        // private void startCheatActivity(){

        //}

        //mPrevButton.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //public void onClick(View v) {
        //  mCurrentIndex = (mCurrentIndex - 1) % mQuestions.length;
        //updateQuestion();
        //}
        //});

    }

    public void startActivity(Intent intent){

    }

    private void startCheatActivity() {
    }

    public void startActivityForResult(Intent intent,
                                       int requestCode){

    }

    //protected void onActivityResult(int requestCode,
       //                             int resultCode, Intent data){

    //}

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            usuarioTrapaceou =
                    CheatActivity.wasAnswerShown(data);
        }
    }

    private void checkAnswer2(boolean respostaDoUsuario){
        boolean gabarito = true;// ???
        int messageResId = usuarioTrapaceou ? R.string.judgment_toast :
                respostaDoUsuario == gabarito ?
                        R.string.correct_toast : R.string.incorrect_toast;
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }


    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart() called");
    }

}
