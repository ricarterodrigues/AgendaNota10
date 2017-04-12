package com.ifpb.agenda.entidades;

import com.ifpb.agenda.dao.CompromissoDao;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Essa classe contém dados referentes as agendas
 * @author ricarte
 */
public class Agenda implements CompromissoDao{
    private String nome;
    private List<Compromisso> compromissos;
   
    /**
     * Construtor da agenda
     * @param nome O nome da agenda
     */
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

    /**
     * Cria um compromisso para o usuário
     * @param c O compromisso que será criado
     * @return A confirmação do conteúdo criado
     */
    @Override
    public boolean create(Compromisso c) {
        for(int i=0; i<compromissos.size(); i++){
            if(compromissos.get(i).getData().equals(c.getData())&&(compromissos.get(i).getHora().equals(c.getHora()))){
                return false;
            }
        }
        
        if(c.getData()==null){
            return false;
        }
        
        return compromissos.add(c);
    }

    /**
     * Deleta um compromisso do usuário
     * @param c O compromisso que será deletado
     * @return A confirmação da exclusão do compromisso
     */
    @Override
    public boolean delete(Compromisso c) {
        return compromissos.remove(c);
    }

    /**
     * Atualiza um compromisso do usuário
     * @param c O compromisso novo que substituirá o antigo
     * @return A confirmação da atualização do compromisso
     */
    @Override
    public boolean update(Compromisso c) {
        for(int i=0; i<compromissos.size(); i++){
            if(compromissos.get(i).getData().equals(c.getData())&&(compromissos.get(i).getHora().equals(c.getHora()))){
                if(c.getData()==null){
                    return false;
                }
                
                compromissos.set(i, c);
                return true;
            }
        }
        return false;
    }

    /**
     * Ler os dados de um compromisso
     * @param data A data do compromisso
     * @param hora A hora do compromisso
     * @return Os dados do compromisso selecionado
     */
    @Override
    public Compromisso read(LocalDate data, LocalTime hora) {
        for(int i=0; i<compromissos.size(); i++){
            if(compromissos.get(i).getData().equals(data)&&(compromissos.get(i).getHora().equals(hora))){
                return compromissos.get(i);
            }
        }
        return null;
    }
    
    /**
     * Lista os compromissos de uma agenda
     * @return Os compromissos presentes na agenda
     */
    public List<Compromisso> listar(){
        return compromissos;
    }
}
