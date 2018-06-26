package com.ifsp.rai.cadastropessoafisica;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

//******************************************************

//Instituto Federal de São Paulo - Campus Sertãozinho

//Disciplina......: M4DADM

//Programação de Computadores e Dispositivos Móveis

//Aluno...........: Rai Gomes Mesquita

//******************************************************


// DESCRIÇÃO.......: Activity relacionada a tela do formulário


public class MainFormActivity extends AppCompatActivity {

    Button btInsere, btListar, btVoltar;
    EditText etNome, etIdade, etCPF, etTelefone, etEmail;

    private DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);

        //Instanciando DBHelper
        dbh = new DBHelper(this);

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
        //Recupera valores dos campos do formulário
        String nome = etNome.getText().toString();
        String cpf = etCPF.getText().toString();
        String idade = etIdade.getText().toString();
        String telefone = etTelefone.getText().toString();
        String email = etEmail.getText().toString();

        //Valida se os campos foram preenchidos corretamente
        if(camposValidos(nome, cpf, idade, telefone, email)) {
            //Se sim, cria instância de Cadastro e insere no banco
            Cadastro cadastro = new Cadastro(nome, cpf, Integer.parseInt(idade), telefone, email);
            dbh.insert(nome, cpf, Integer.parseInt(idade), telefone, email);
            exibeAlerta(cadastro.getNome() + " foi inserido com sucesso", cadastro.toString());
            clear();
        }
        else {
            //Senão, exibe mensagem de erro
            String titulo = "ERRO";
            String mensagem = "Campos vazios!";
            exibeAlerta(titulo, mensagem);
        }
    }

    private void listar() {
        //Cria lista com a consulta dos cadastros no bd
        List<Cadastro> cadastros = dbh.recuperaDados();
        String mensagem = "", titulo = "Cadastros";

        //Caso retorne algum registro, varre a lista e atribui os cadastros na mensagem de alerta
        if (cadastros != null) {
            mensagem += "Foram retornados " + cadastros.size() + " registros:\n\n";

            for(Cadastro cadastro: cadastros) {
                mensagem += cadastro.toString() + "\n\n";
            }

        }
        //Caso contrário, atribui a mensagem que a consulta não retornou resultado
        else {
            mensagem += "A consulta não retornou resultado!";
        }
        //Exibe o alerta
        exibeAlerta(titulo, mensagem);
    }

    private void voltar() {
        Intent intent = new Intent();
        intent.setClass(MainFormActivity.this, TelaInicialActivity.class);
        startActivity(intent);
        finish();
    }

    private void clear() {
        etNome.setText("");
        etCPF.setText("");
        etIdade.setText("");
        etEmail.setText("");
        etTelefone.setText("");
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
