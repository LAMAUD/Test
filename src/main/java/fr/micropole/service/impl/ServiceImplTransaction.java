package fr.micropole.service.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.micropole.dao.DAOTransaction;
import fr.micropole.exception.DAOException;
import fr.micropole.exception.ServiceException;
import fr.micropole.pojo.Transaction;
import fr.micropole.service.ServiceTransaction;

@Service
public class ServiceImplTransaction implements ServiceTransaction {

    private final static Logger LOGGER = Logger.getLogger( ServiceImplTransaction.class );

    @Autowired
    DAOTransaction              dAOTransaction;

    @Override
    @Transactional
    public Transaction create( Transaction transaction ) throws ServiceException {

        try {
            transaction = dAOTransaction.create( transaction );
        } catch ( DAOException e ) {
            LOGGER.error( "La création foire au niveau du service de création de Transaction", e );
        }

        return transaction;
    }

    @Override
    public List<Transaction> readAll() throws ServiceException, DAOException {

        return dAOTransaction.readAll();
    }

    @Override
    public List<Transaction> readTransactionBetweenDate( String dateDebut, String dateFin ) throws DAOException,
            ParseException {

        return dAOTransaction.readTransactionBetweenDate( dateDebut, dateFin );
    }

    @Override
    public Double sumOfExpenses( List<Transaction> transactions ) {
        Double depensesTotales = (double) 0;

        for ( Transaction transaction : transactions ) {
            Double depense = transaction.getDepense();

            if ( depense != null ) {
                depensesTotales += depense;
            }
        }

        return depensesTotales;
    }

    @Override
    public Double sumOfIncome( List<Transaction> transactions ) {

        Double revenusTotaux = (double) 0;

        for ( Transaction transaction : transactions ) {
            Double revenu = transaction.getRecette();

            if ( revenu != null ) {
                revenusTotaux += revenu;
            }
        }

        return revenusTotaux;

    }

    @Override
    public Double rateExpensesIncome( List<Transaction> transactions ) {

        Double rate = 0.0;

        if ( sumOfIncome( transactions ) == null || sumOfIncome( transactions ) == 0.0 ) {

        } else {

            rate = ( sumOfExpenses( transactions ) / sumOfIncome( transactions ) ) * 100;
            rate = ServiceImplTransaction.arrondiNDecimales( rate, 2 );
        }
        return rate;
    }

    private static double arrondiNDecimales( double x, int n )
    {
        double pow = Math.pow( 10, n );
        return ( Math.floor( x * pow ) ) / pow;
    }

    @Override
    public List<Transaction> readTransactionBetweenDateAndByCategory( String dateDebut, String dateFin, String category )
            throws ParseException {

        return dAOTransaction.readTransactionBetweenDateAndByCategory( dateDebut, dateFin, category );
    }

    @Override
    public Transaction findById( Integer id ) throws DAOException {
        // TODO Auto-generated method stub
        return dAOTransaction.findById( id );
    }

    @Override
    public List<Transaction> readTransactionByMonth( String month ) {
        return dAOTransaction.readTransactionByMonth( month );
    }

}
