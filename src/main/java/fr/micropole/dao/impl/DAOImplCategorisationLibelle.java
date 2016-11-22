package fr.micropole.dao.impl;

import org.springframework.stereotype.Repository;

import fr.micropole.dao.DAOCategorisationLibelle;
import fr.micropole.pojo.CategorisationLibelle;

@Repository
public class DAOImplCategorisationLibelle extends GenericDAOImpl<CategorisationLibelle> implements
        DAOCategorisationLibelle {

    public DAOImplCategorisationLibelle() {
        super();
        this.type = new CategorisationLibelle();
        // TODO Auto-generated constructor stub
    }

}
