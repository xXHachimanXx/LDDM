package com.example.agenda;

public class Contato
{
    private int id;
    private String nome;
    private String email;
    private String endereco;
    private String cep;

    public Contato(){}

    public Contato (String nome, String email)
    {
        this.nome = nome;
        this.email = email;
    }
    public Contato (String nome, String email, String endereco, String cep)
    {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.cep = cep;
    }

    @Override
    public String toString()
    {
        return nome + "\n" + email + "\n" + "Endereço: " + endereco;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getEndereco() { return endereco; }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCep() { return cep; }

    public void setCep(String cep) { this.cep = cep; }

}
