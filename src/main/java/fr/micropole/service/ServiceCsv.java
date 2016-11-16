package fr.micropole.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import fr.micropole.exception.DAOException;
import fr.micropole.pojo.ImportCSVTransaction;

public interface ServiceCsv {

    public ImportCSVTransaction create( ImportCSVTransaction importCSVTransaction ) throws DAOException;

    public List<ImportCSVTransaction> readAll() throws DAOException;

    public void remove( ImportCSVTransaction importCSVTransaction ) throws DAOException;

    public ImportCSVTransaction findById( Integer id ) throws DAOException;

    public List<ImportCSVTransaction> readTransactionsFromImportation( String path ) throws DAOException, IOException,
            ParseException;

    public List<ImportCSVTransaction> readTransactionBetweenDate( String dateDebut, String dateFin )
            throws DAOException,
            ParseException;

}
