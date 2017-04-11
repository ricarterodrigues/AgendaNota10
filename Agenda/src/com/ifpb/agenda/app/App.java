/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.agenda.app;

import com.ifpb.agenda.dao.UsuarioDao;
import com.ifpb.agenda.entidades.Agenda;
import com.ifpb.agenda.entidades.Compromisso;
import com.ifpb.agenda.entidades.Usuario;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author ricar
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //usuario
        UsuarioDao user = new UsuarioDao();
        
        user.create(new Usuario("Ana", LocalDate.now(), 'F', "123@gmail.com", "123"));
        user.create(new Usuario("Thiago", LocalDate.now(), 'M', "thigoyure@gmail.com", "pera"));
        
        System.out.println(user.read("thigoyure@gmail.com"));
        System.out.println(user.listar());
        user.delete(new Usuario ("Thiago", LocalDate.now(), 'M', "thigoyure@gmail.com", "pera"));
        System.out.println(user.listar());
        
        user.update(new Usuario("Rafael", LocalDate.now(), 'M', "123@gmail.com", "123"));
        System.out.println(user.listar());
        
        //agendas
        user.listar().get(0).create(new Agenda("Faculdade"));
        user.listar().get(0).create(new Agenda("Pessoal"));
        
        //compromisso
        user.listar().get(0).listar().get(0).create(new Compromisso(LocalDate.now(), LocalTime.now(), "aniversario", "igreja"));
        
        System.out.println(user.listar().get(0).listar());
    }
    
}
