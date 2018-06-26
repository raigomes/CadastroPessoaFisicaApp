package com.ifsp.rai.cadastropessoafisica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//******************************************************

//Instituto Federal de São Paulo - Campus Sertãozinho

//Disciplina......: M4DADM

//Programação de Computadores e Dispositivos Móveis

//Aluno...........: Rai Gomes Mesquita

//******************************************************


// DESCRIÇÃO.......: Activity relacionada a tela inicial


public class TelaInicialActivity extends AppCompatActivity {

    Button btInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        //Recupera botão de inserir
        btInsert = (Button) findViewById(R.id.btinsere);
        //Trata o evento de clique do botão
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaSegundaTela();
            }
        });
    }
    //Exibe a tela do formulário de cadastro
    private void chamaSegundaTela() {
        Intent intent = new Intent();
        intent.setClass(TelaInicialActivity.this, MainFormActivity.class);
        startActivity(intent);
        finish();
    }
}
