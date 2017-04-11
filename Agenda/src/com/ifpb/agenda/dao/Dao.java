package com.ifpb.agenda.dao;

public interface Dao <T>{
    
    boolean create(T t);
    boolean delete(T t);
    boolean update(T t);
    T read(String t);
}
