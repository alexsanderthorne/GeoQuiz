package com.example.quizactivity.quizactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private boolean respostaQuestao;
    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN =
            "Com.bignerdranch.android.geoquiz.answer_shown";
    private TextView txtQuestao;
    private Button btMostrarResposta;

    public static String getExtraAnswerIsTrue() {
        return EXTRA_ANSWER_IS_TRUE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        respostaQuestao =
                getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        txtQuestao = (TextView) findViewById(R.id.answer_text_view);
        btMostrarResposta = (Button) findViewById(R.id.show_answer_button);
        btMostrarResposta.setOnClickListener(e->
                mostrarResposta(respostaQuestao));
    }
    private void mostrarResposta(boolean respostaQuestao) {
        txtQuestao.setText(respostaQuestao == true ?
                R.string.true_button :
                R.string.false_button);
        setAnswerShownResult(true);
    }

    private void setAnswerShownResult(boolean respostaApareceu) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,respostaApareceu);
        setResult(RESULT_OK, data);
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

}
