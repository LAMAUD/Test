package fr.micropole.dao;

import java.util.Date;
import java.util.List;

import fr.micropole.pojo.Transaction;

public interface DAOTransaction extends GenericDAO<Transaction> {

    public List<Transaction> readTransactionBetweenDate( Date dateDebut, Date dateFin );

}
