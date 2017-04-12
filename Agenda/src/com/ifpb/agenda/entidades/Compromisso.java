package com.ifpb.agenda.entidades;

import com.ifpb.agenda.dao.ConverteData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 * Essa classe contém dados referentes aos compromissos e seus respectivos métodos
 * @author ricarte
 */
public class Compromisso {
   
    private LocalDate data;
    private LocalTime hora;
    private String descricao;
    private String local;
    
    /**
     * Contrutor de Compromisso
     * @param data A data do compromisso
     * @param hora A hora do compromisso
     * @param descricao A descrição do compromisso
     * @param local O local do compromisso
     */
    public Compromisso (String data, LocalTime hora, String descricao, String local){
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        formater.setLenient(false);
        try {
            Date dataComp = formater.parse(data);
            if(LocalDate.now().isBefore(ConverteData.toLocalDate(dataComp))){
                this.data = ConverteData.toLocalDate(formater.parse(data));
            }else{
                System.out.println("Data Inválida");
            }
            this.hora = hora;
            this.descricao = descricao;
            this.local = local;
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(String data) {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        formater.setLenient(false);
        try {
            Date dataComp = formater.parse(data);
            if(LocalDate.now().isBefore(ConverteData.toLocalDate(dataComp))){
                this.data = ConverteData.toLocalDate(formater.parse(data));
            }else{
                System.out.println("Data Inválida");
            }
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Compromisso{" + "data=" + data + ", hora=" + hora + ", descricao=" + descricao + ", local=" + local +  '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.data);
        hash = 89 * hash + Objects.hashCode(this.hora);
        hash = 89 * hash + Objects.hashCode(this.descricao);
        hash = 89 * hash + Objects.hashCode(this.local);
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
        final Compromisso other = (Compromisso) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.local, other.local)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        return true;
    }
    
    
}
