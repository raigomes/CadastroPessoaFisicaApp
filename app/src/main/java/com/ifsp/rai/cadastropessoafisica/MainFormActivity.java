package com.ifsp.rai.cadastropessoafisica;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainFormActivity extends AppCompatActivity {

    Button btInsere, btListar, btVoltar;
    EditText etNome, etIdade, etCPF, etTelefone, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);

        //Recuperando campos da tela no Java
        btInsere = (Button) findViewById(R.id.btinserir);
        btListar = (Button) findViewById(R.id.btlistar);
        btVoltar = (Button) findViewById(R.id.btvoltar);
        etNome = (EditText) findViewById(R.id.etnome);
        etCPF = (EditText) findViewById(R.id.etcpf);
        etIdade = (EditText) findViewById(R.id.etidade);
        etTelefone = (EditText) findViewById(R.id.ettelefone);
        etEmail = (EditText) findViewById(R.id.etemail);

        //Tratando o evento do botão "Inserir Dados"
        btInsere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    inserir();
                }
                catch (Exception ex) {
                    String titulo = "ERRO";
                    String mensagem = "Insira campos válidos";
                    exibeAlerta(titulo, mensagem);
                    ex.printStackTrace();
                }
            }
        });

        //Tratando o evento do botão "Listar Registros"
        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listar();
            }
        });

        //Tratando o evento do botão "Voltar"
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });
    }

    private void inserir() {
        String nome = etNome.getText().toString();
        String cpf = etCPF.getText().toString();
        String idade = etIdade.getText().toString();
        String telefone = etTelefone.getText().toString();
        String email = etEmail.getText().toString();

        //Valida se os campos foram preenchidos corretamente
        if(camposValidos(nome, cpf, idade, telefone, email)) {
            //Se sim, cria instância de Cadastro e insere no banco
            Cadastro cadastro = new Cadastro(nome, cpf, Integer.parseInt(idade), telefone, email);
            DBHelper dbh = new DBHelper(MainFormActivity.this);
            dbh.insert(nome, cpf, Integer.parseInt(idade), telefone, email);
            exibeAlerta(cadastro.getNome() + " foi inserido com sucesso", cadastro.toString());
        }
        else {
            //Senão, exibe mensagem de erro
            String titulo = "ERRO";
            String mensagem = "Campos vazios!";
            exibeAlerta(titulo, mensagem);
        }
    }

    private void listar() {
    }

    private void voltar() {
        Intent intent = new Intent();
        intent.setClass(MainFormActivity.this, TelaInicialActivity.class);
        startActivity(intent);
        finish();
    }

    private void exibeAlerta(String titulo, String mensagem) {
        //Exibe mensagem de alerta
        AlertDialog.Builder caixaAlerta = new AlertDialog.Builder(MainFormActivity.this);
        caixaAlerta.setTitle(titulo);
        caixaAlerta.setMessage(mensagem);
        caixaAlerta.show();
    }

    private boolean camposValidos(String nome, String cpf, String idade, String telefone, String email) {
        return !nome.isEmpty()
                && !cpf.isEmpty()
                && !idade.isEmpty()
                && !telefone.isEmpty()
                && !email.isEmpty();
    }

}
