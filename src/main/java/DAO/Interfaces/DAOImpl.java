package DAO.Interfaces;

import DAO.Execptions.DAOException;

import java.util.List;

public interface DAOImpl<T> {
    //create
    boolean add(T t) throws DAOException;

    //read
    List<T> getAll(long... lang_id);

    //update
    boolean update(T t) throws DAOException;

    //delete
    boolean remove(long id) throws DAOException;
}
