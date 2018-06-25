package com.ifsp.rai.cadastropessoafisica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaInicialActivity extends AppCompatActivity {

    Button btInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        btInsert = (Button) findViewById(R.id.btinsere);

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaSegundaTela();
            }
        });
    }

    private void chamaSegundaTela() {
        Intent intent = new Intent();
        intent.setClass(TelaInicialActivity.this, MainFormActivity.class);
        startActivity(intent);
        finish();
    }
}
