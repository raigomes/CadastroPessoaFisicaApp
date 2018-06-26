package com.ifsp.rai.cadastropessoafisica;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

//******************************************************

//Instituto Federal de São Paulo - Campus Sertãozinho

//Disciplina......: M4DADM

//Programação de Computadores e Dispositivos Móveis

//Aluno...........: Rai Gomes Mesquita

//******************************************************


// DESCRIÇÃO.......: Classe que manipula o banco de dados


public class DBHelper {

    /*Declarando os atributos da classe*/

    private static final String DATABASE_NAME = "projetofinal.db"; /*Nome do bd*/
    private static final int DATABASE_VERSION = 1; /*Versão do bd*/
    private static final String TABLE_NAME = "cadastro"; /*Nome da tabela do bd*/

    private Context context;
    private SQLiteDatabase db;

    /*Outros atributos necessários para a criação ao suporte para o bd*/
    private SQLiteStatement insertStnt;

    /*Declarando a instrução de INSERT*/
    private static final String INSERT = "insert into " + TABLE_NAME + " (nome, cpf, idade, telefone, email) VALUES (?,?,?,?,?)";

    /*Criando o construtor DBHelper*/
    public DBHelper(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStnt = this.db.compileStatement(INSERT);
    }

    /*Criando instrução de inserir registros no banco*/
    public long insert(String nome, String cpf, int idade, String telefone, String email) {
        this.insertStnt.bindString(1, nome);
        this.insertStnt.bindString(2, cpf);
        this.insertStnt.bindLong(3, idade);
        this.insertStnt.bindString(4, telefone);
        this.insertStnt.bindString(5, email);

        return this.insertStnt.executeInsert();
    }

    /*Criando uma instrução para deletar todos os registros*/
    public void deleteAll() {
        this.db.delete(TABLE_NAME, null, null);
    }

    /*Criando uma instrução para recuperar a lista de informações do bd*/

    public List<Cadastro> recuperaDados() {

        List<Cadastro> list = new ArrayList<Cadastro>();

        try {
            //Define cursor que irá recuperar a lista de registros
            Cursor cursor = this.db.query(TABLE_NAME, new String[]{"nome", "cpf", "idade", "telefone", "email"},
                    null, null, null, null, null, null);
            //Define contador de registros
            int contaRegistros = cursor.getCount();

            //Se houver registros, percorre o cursor e adiciona na lista. Caso contrário, retorna null.
            if (contaRegistros > 0) {
                cursor.moveToFirst();
                do {
                    Cadastro cadastro = new Cadastro(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4));
                    list.add(cadastro);
                } while (cursor.moveToNext());

                if (!cursor.isClosed() && list.size() > 0) {
                    cursor.close();
                    return list;
                }
                else
                    return null;
            }
            else
                return null;
        } catch (Exception err) {
            return null;
        }
    }

    /*Declarando classe auxiliar OpenHelper que vai declarar as instruções sql*/
    private static class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /*Implementando o método que vai criar a tabela, caso ela não exista*/
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id_recebedados INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cpf TEXT, idade INTEGER, telefone TEXT, email TEXT);";
            db.execSQL(sql);
        }

        //Declarando instrução para atualizar a versão da tabela
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}