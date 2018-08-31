package com.example.quizactivity.quizactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private ImageButton mTrueButton;
    private ImageButton mFalseButton;
    private ImageButton mNextButton;
    private TextView mTextView;
    private int mCurrentIndex;

    Question[] mQuestions = new Question[]{
            new Question(R.string.pergunta1, false),
            new Question(R.string.pergunta2, false),
    };

    private void updateQuestion() {
        int question = mQuestions[mCurrentIndex].getId_da_respota();

        mTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestions[mCurrentIndex]
                .isResposta_correta();
        int messageResId = (answerIsTrue == userPressedTrue) ?
                R.string.correct_toast :
                R.string.incorrect_toast;
        Toast.makeText(this, messageResId,
                Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = (ImageButton) findViewById(R.id.true_button);
        mFalseButton = (ImageButton) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mTextView = (TextView) findViewById(R.id.textoid);


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
                updateQuestion();
            }
        });


    }


}
