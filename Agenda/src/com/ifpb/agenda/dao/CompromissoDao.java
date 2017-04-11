package com.ifpb.agenda.dao;

import com.ifpb.agenda.entidades.Compromisso;
import java.time.LocalDate;
import java.time.LocalTime;

public interface CompromissoDao {
    
    boolean create(Compromisso c);
    boolean delete(Compromisso c);
    boolean update(Compromisso c);    
    Compromisso read(LocalDate data, LocalTime hora);
    
}
