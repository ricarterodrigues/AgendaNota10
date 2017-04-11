package com.ifpb.agenda.dao;

import com.ifpb.agenda.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements Dao<Usuario>, Validavel {

    private List <Usuario> usuarios;
    
    public UsuarioDao(){
        usuarios = new ArrayList<>();
    }
    
    @Override
    public boolean create(Usuario t) {
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(t.getEmail())){
                return false;
            }
        }
        return usuarios.add(t);
    }

    @Override
    public boolean delete(Usuario t) {
        return usuarios.remove(t);
    }

    @Override
    public boolean update(Usuario t) {
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(t.getEmail())){
                usuarios.set(i, t);
                return true;
            }
        }
        return false;
    }

    @Override
    public Usuario read(String email) {
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(email)){
                return usuarios.get(i);
            }
        }
        return null;
    }

    @Override
    public boolean validar(String email, String senha) {
        for(int i=0; i<usuarios.size(); i++){
            if((usuarios.get(i).getEmail().equals(email))&&(usuarios.get(i).getSenha().equals(senha))){
                return true;
            }
        }
        return false;
    }
    
    public List<Usuario> listar(){
        return usuarios;
    }
    
}
