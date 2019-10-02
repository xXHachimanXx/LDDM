package com.example.agenda;

public class Contato
{
    private int id;
    private String nome;
    private String email;

    public Contato (String nome, String email)
    {
        this.nome = nome;
        this.email = email;
    }

    @Override
    public String toString()
    {
        return nome + "\n" + email + "\n" + id;
    }

    public void setId (int id) { this.id = id; }

    public int getId() { return this.id; }

    public String getNome()
    {
        return this.nome;
    }

    public String getEmail()
    {
        return this.email;
    }
}
