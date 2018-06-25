package com.ifsp.rai.cadastropessoafisica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainFormActivity extends AppCompatActivity {

    Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);

        btVoltar = findViewById(R.id.btvoltar);

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });
    }

    private void voltar() {
        Intent intent = new Intent();
        intent.setClass(MainFormActivity.this, TelaInicialActivity.class);
        startActivity(intent);
        finish();
    }


}
