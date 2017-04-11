package com.ifpb.agenda.entidades;

import com.ifpb.agenda.dao.CompromissoDao;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Agenda implements CompromissoDao{
    private String nome;
    private List<Compromisso> compromissos;
    
    public Agenda(){
        
    }
    
    public Agenda(String nome){
        this.nome = nome;
        compromissos = new ArrayList<>();        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Agenda{" + "nome=" + nome + ", compromissos=" + compromissos + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.compromissos);
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
        final Agenda other = (Agenda) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.compromissos, other.compromissos)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean create(Compromisso c) {
        for(int i=0; i<compromissos.size(); i++){
            if(compromissos.get(i).getData().equals(c.getData())&&(compromissos.get(i).getHora().equals(c.getHora()))){
                return false;
            }
        }
        return compromissos.add(c);
    }

    @Override
    public boolean delete(Compromisso c) {
        return compromissos.remove(c);
    }

    @Override
    public boolean update(Compromisso c) {
        for(int i=0; i<compromissos.size(); i++){
            if(compromissos.get(i).getData().equals(c.getData())&&(compromissos.get(i).getHora().equals(c.getHora()))){
                compromissos.set(i, c);
                return true;
            }
        }
        return false;
    }

    @Override
    public Compromisso read(LocalDate data, LocalTime hora) {
        for(int i=0; i<compromissos.size(); i++){
            if(compromissos.get(i).getData().equals(data)&&(compromissos.get(i).getHora().equals(hora))){
                return compromissos.get(i);
            }
        }
        return null;
    }
    
    public List<Compromisso> listar(){
        return compromissos;
    }
}
