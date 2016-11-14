package fr.micropole.dao.impl;

import org.springframework.stereotype.Repository;

import fr.micropole.dao.DAOCategory;
import fr.micropole.exception.DAOException;
import fr.micropole.pojo.Category;

@Repository
public class DAOImplCategory extends GenericDAOImpl<Category> implements DAOCategory {

    public DAOImplCategory() {
        super();
        this.type = new Category();
        // TODO Auto-generated constructor stub
    }

    @Override
    public Category findByName( String name ) throws DAOException {

        Category category = (Category) this.entityManager
                .createQuery( "SELECT c FROM Category c WHERE c.name LIKE :var" ).setParameter( "var", name )
                .getSingleResult();

        return category;
    }

}
