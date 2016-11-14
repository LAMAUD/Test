package fr.micropole.service;

import java.util.Date;
import java.util.List;

import fr.micropole.exception.DAOException;
import fr.micropole.exception.ServiceException;
import fr.micropole.pojo.Transaction;

public interface ServiceTransaction {

    public Transaction create( Transaction transaction ) throws ServiceException;

    public List<Transaction> readAll() throws ServiceException, DAOException;

    public List<Transaction> readTransactionBetweenDate( Date dateDebut, Date dateFin );

    public Double sumOfExpenses( List<Transaction> transactions );

    public Double sumOfIncome( List<Transaction> transactions );

    public Double rateExpensesIncome( List<Transaction> transactions );
}
