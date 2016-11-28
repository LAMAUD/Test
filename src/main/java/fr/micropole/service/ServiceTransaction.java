package fr.micropole.service;

import java.text.ParseException;
import java.util.List;

import fr.micropole.exception.DAOException;
import fr.micropole.exception.ServiceException;
import fr.micropole.pojo.Transaction;

public interface ServiceTransaction {

    public Transaction create( Transaction transaction ) throws ServiceException;

    public List<Transaction> readAll() throws ServiceException, DAOException;

    public List<Transaction> readTransactionBetweenDate( String dateDebut, String dateFin ) throws DAOException,
            ParseException;

    public Double sumOfExpenses( List<Transaction> transactions );

    public Double sumOfIncome( List<Transaction> transactions );

    public Double rateExpensesIncome( List<Transaction> transactions );

    public List<Transaction> readTransactionBetweenDateAndByCategory( String dateDebut, String dateFin, String category )
            throws ParseException;

    public Transaction findById( Integer id ) throws DAOException;

    public List<Transaction> readTransactionByMonth( String month );
}
