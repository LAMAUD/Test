package fr.micropole.dao;

import java.text.ParseException;
import java.util.List;

import fr.micropole.pojo.Transaction;

public interface DAOTransaction extends GenericDAO<Transaction> {

    public List<Transaction> readTransactionBetweenDateAndByCategory( String dateDebut, String dateFin, String category )
            throws ParseException;

    public List<Transaction> readTransactionByMonth( String month );

}
