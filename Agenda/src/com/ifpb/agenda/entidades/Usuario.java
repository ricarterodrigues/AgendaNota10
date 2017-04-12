package com.ifpb.agenda.entidades;

import com.ifpb.agenda.dao.ConverteData;
import com.ifpb.agenda.dao.Dao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Essa classe contém dados pertencentes ao usuário e métodos referentes a sua manipulação
 * @author ricarte
 */
public class Usuario implements Dao<Agenda>{

    private String nome;
    private LocalDate nascimento;
    private char sexo;
    private String email;
    private String senha;
    private List<Agenda> agendas;
    
    /**
     * Construtor do usuário
     * @param nome O nome do usuário
     * @param nascimento A data de nascimento do usuário
     * @param sexo O sexo do usuário
     * @param email O email do usuário
     * @param senha A senha do email do usuário
     */
    public Usuario (String nome, String nascimento, char sexo, String email, String senha){
        this.nome = nome;
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        formater.setLenient(false);
        try {
            Date dataNasc = formater.parse(nascimento);
            if(LocalDate.now().isAfter(ConverteData.toLocalDate(dataNasc))){
                this.nascimento = ConverteData.toLocalDate(formater.parse(nascimento));
            }else{
                System.out.println("Data Inválida");
            }
            this.sexo = sexo;
            this.email = email;
            this.senha = senha;
            agendas = new ArrayList<>();
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
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

    public void setNascimento(String nascimento) {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        formater.setLenient(false);
        try {
            Date dataNasc = formater.parse(nascimento);
            if(LocalDate.now().isAfter(ConverteData.toLocalDate(dataNasc))){
                this.nascimento = ConverteData.toLocalDate(formater.parse(nascimento));
            }else{
             System.out.println("Data Inválida");
            }
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
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

    /**
     * Cria uma agenda para o usuário
     * @param t A agenda que será criada
     * @return A confirmação da inserção da agenda criada no usuário
     */
    @Override
    public boolean create(Agenda t) {
        for(int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getNome().equals(t.getNome())){
                return false;
            }
        }
        return agendas.add(t);
    }

    /**
     * Deleta uma agenda do usuário
     * @param t A agenda que será deletada
     * @return A confirmação da exclusão da agenda no usuário
     */
    @Override
    public boolean delete(Agenda t) {
        return agendas.remove(t);
    }

    /**
     * Atualiza a agenda do usuário
     * @param t A agenda nova que substituirá a antiga
     * @return A confirmação da atualização da agenda 
     */
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

    /**
     * Ler os dados de uma agenda
     * @param nome Nome da agenda que será lida
     * @return A agenda selecionada
     */
    @Override
    public Agenda read(String nome) {
        for(int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getNome().equals(nome)){
                return agendas.get(i);
            }
        }
        return null;
    }
    
    /**
     * Lista as agendas do usuário
     * @return As agendas do usuário
     */
    public List<Agenda> listar(){
        return agendas;
    }
    
    /**
     * Listar todos os compromissos de um intervalo determinado pelo usuário
     * @param inicio A primeira data do intervalo
     * @param fim A ultima data do intevalo
     * @return A lista de compromissos no dado intervalo
     */
    public List<Compromisso> listarCompromissos(LocalDate inicio,LocalDate fim){
        List<Compromisso> lista = new ArrayList<>();
        for(int i=0;i<agendas.size();i++){
            for(int j=0;j<agendas.get(i).listar().size();j++){
                if((agendas.get(i).listar().get(j).getData().equals(inicio)||agendas.get(i).listar().get(j).getData().isAfter(inicio))&&(agendas.get(i).listar().get(j).getData().isEqual(fim)||agendas.get(i).listar().get(j).getData().isBefore(fim))){
                    lista.add(agendas.get(i).listar().get(j));
                }
            }
        }
        if(lista.isEmpty()){
            return null;
        }
        return lista;
    }
    
    /**
     * Lista todos os compromissos nos próximos 30 dias
     * @return A lista de compromissos no intervalo de 30 dias 
     */
    public List<Compromisso> listarProximosDias(){
        List<Compromisso> lista = new ArrayList<>();
        LocalDate dataFinal = LocalDate.now();
        dataFinal = dataFinal.withDayOfMonth(LocalDate.now().getDayOfMonth()+30);
        for(int i=0;i<agendas.size();i++){
            for(int j=0;j<agendas.get(i).listar().size();j++){
                if((agendas.get(i).listar().get(j).getData().isEqual(LocalDate.now())||agendas.get(i).listar().get(j).getData().isAfter(LocalDate.now()))&&(agendas.get(i).listar().get(j).getData().isEqual(dataFinal)||agendas.get(i).listar().get(j).getData().isBefore(dataFinal))){
                    lista.add(agendas.get(i).listar().get(j));
                }
            }
        }
        if(lista.isEmpty()){
            return null;
        }
        return lista;
        
    }
}
