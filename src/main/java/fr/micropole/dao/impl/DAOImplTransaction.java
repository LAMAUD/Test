package fr.micropole.dao.impl;

import org.springframework.stereotype.Repository;

import fr.micropole.dao.DAOTransaction;
import fr.micropole.pojo.Transaction;

@Repository
public class DAOImplTransaction extends GenericDAOImpl<Transaction> implements DAOTransaction {

    public DAOImplTransaction() {
        super();
        this.type = new Transaction();
        // TODO Auto-generated constructor stub
    }

}
