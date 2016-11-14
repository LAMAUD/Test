package fr.micropole.dao;

import fr.micropole.exception.DAOException;
import fr.micropole.pojo.Category;

public interface DAOCategory extends GenericDAO<Category> {

    public Category findByName( String name ) throws DAOException;

}
