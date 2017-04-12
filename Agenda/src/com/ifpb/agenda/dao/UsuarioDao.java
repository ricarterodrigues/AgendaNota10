package com.ifpb.agenda.dao;

import com.ifpb.agenda.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 * Essa classe contém os métodos referentes a construção do usuário
 * @author ricarte
 */
public class UsuarioDao implements Dao<Usuario>, Validavel {

    private List <Usuario> usuarios;
    
    /**
     * Construtor da classe
     */
    public UsuarioDao(){
        usuarios = new ArrayList<>();
    }
    
    /**
     * Cria um usuário
     * @param t Os dados do usuário que será criado
     * @return A confirmação da criação do usuário
     */
    @Override
    public boolean create(Usuario t) {
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(t.getEmail())){
                return false;
            }
        }
        return usuarios.add(t);
    }

    /**
     * Deleta um usuário
     * @param t Os dados do usuário que será deletado
     * @return A confirmação da exclusão do usuário
     */
    @Override
    public boolean delete(Usuario t) {
        return usuarios.remove(t);
    }

    /**
     * Atualiza o usuário
     * @param t O usuário novo que substituirá o antigo
     * @return A confirmação da atualização do usuário 
     */
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

    /**
     * Lista um usuário
     * @param email O email do usuário que será listado
     * @return O usuário indicado
     */
    @Override
    public Usuario read(String email) {
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(email)){
                return usuarios.get(i);
            }
        }
        return null;
    }

    /**
     * Valida um usuário
     * @param email O email do usuário
     * @param senha A senha do usuário
     * @return A confirmação da validação do usuário
     */
    @Override
    public boolean validar(String email, String senha) {
        for(int i=0; i<usuarios.size(); i++){
            if((usuarios.get(i).getEmail().equals(email))&&(usuarios.get(i).getSenha().equals(senha))){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Lista os usuário
     * @return Todos os usuários
     */
    public List<Usuario> listar(){
        return usuarios;
    }
    
}
