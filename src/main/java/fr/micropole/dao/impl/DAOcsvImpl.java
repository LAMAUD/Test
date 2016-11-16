package fr.micropole.dao.impl;

import org.springframework.stereotype.Repository;

import fr.micropole.dao.DAOcsv;
import fr.micropole.pojo.ImportCSVTransaction;

@Repository
public class DAOcsvImpl extends GenericDAOImpl<ImportCSVTransaction> implements DAOcsv {

    public DAOcsvImpl() {
        super();
        this.type = new ImportCSVTransaction();
        // TODO Auto-generated constructor stub
    }

}
