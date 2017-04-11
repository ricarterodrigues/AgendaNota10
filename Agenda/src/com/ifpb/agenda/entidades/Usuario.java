package com.ifpb.agenda.entidades;

import com.ifpb.agenda.dao.Dao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario implements Dao<Agenda>{

    private String nome;
    private LocalDate nascimento;
    private char sexo;
    private String email;
    private String senha;
    private List<Agenda> agendas;
    
    public Usuario (String nome, LocalDate nascimento, char sexo, String email, String senha){
        this.nome = nome;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.email = email;
        this.senha = senha;
        agendas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", nascimento=" + nascimento + ", sexo=" + sexo + ", email=" + email + ", senha=" + senha + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.nome);
        hash = 37 * hash + Objects.hashCode(this.nascimento);
        hash = 37 * hash + this.sexo;
        hash = 37 * hash + Objects.hashCode(this.email);
        hash = 37 * hash + Objects.hashCode(this.senha);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.sexo != other.sexo) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.nascimento, other.nascimento)) {
            return false;
        }
        return true;
    }    

    @Override
    public boolean create(Agenda t) {
        for(int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getNome().equals(t.getNome())){
                return false;
            }
        }
        return agendas.add(t);
    }

    @Override
    public boolean delete(Agenda t) {
        return agendas.remove(t);
    }

    @Override
    public boolean update(Agenda t) {
        for(int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getNome().equals(t.getNome())){
                agendas.set(i, t);
                return true;
            }
        }
        return false;
    }

    @Override
    public Agenda read(String nome) {
        for(int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getNome().equals(nome)){
                return agendas.get(i);
            }
        }
        return null;
    }
    
    public List<Agenda> listar(){
        return agendas;
    }
}
