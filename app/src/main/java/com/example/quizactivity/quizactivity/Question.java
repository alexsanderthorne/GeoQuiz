package com.example.quizactivity.quizactivity;

import android.widget.Button;

public class Question {

    private boolean resposta_correta;
    private int id_da_respota;

    public Question(int id_da_respota, boolean resposta_correta) {
        this.resposta_correta = resposta_correta;
        this.id_da_respota = id_da_respota;
    }

    public boolean isResposta_correta() {
        return resposta_correta;
    }

    public void setResposta_correta(boolean resposta_correta) {
        this.resposta_correta = resposta_correta;
    }

    public int getId_da_respota() {
        return id_da_respota;
    }

    public void setId_da_respota(int id_da_respota) {
        this.id_da_respota = id_da_respota;
    }
}
