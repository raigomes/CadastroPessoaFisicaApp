package com.ifsp.rai.cadastropessoafisica;

class Cadastro {
    private String nome;
    private String cpf;
    private int idade;
    private String telefone;
    private String email;

    public Cadastro(String nome, String cpf, int idade, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome
            + "\nCPF: " + this.cpf
            + "\nIdade: " + this.idade
            + "\nTelefone: " + this.telefone
            + "\nEmail: " + this.email
            ;
    }
}
