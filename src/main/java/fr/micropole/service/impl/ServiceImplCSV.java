package fr.micropole.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import au.com.bytecode.opencsv.CSVReader;
import fr.micropole.dao.DAOcsv;
import fr.micropole.exception.DAOException;
import fr.micropole.pojo.ImportCSVTransaction;
import fr.micropole.service.ServiceCsv;

@Service
public class ServiceImplCSV implements ServiceCsv {

    private final static Logger LOGGER                 = Logger.getLogger( ServiceImplCSV.class );

    private final static String RESOURCES_PATH         = "src/main/resources/";
    private final static String TRANSACTIONS_FILE_NAME = "octobre.csv";
    private final static char   SEPARATOR              = ';';

    @Autowired
    DAOcsv                      dAOcsv;

    @Override
    @Transactional
    public ImportCSVTransaction create( ImportCSVTransaction importCSVTransaction ) throws DAOException {

        return dAOcsv.create( importCSVTransaction );
    }

    @Override
    public List<ImportCSVTransaction> readAll() throws DAOException {

        return dAOcsv.readAll();
    }

    @Override
    @Transactional
    public void remove( ImportCSVTransaction importCSVTransaction ) throws DAOException {
        dAOcsv.remove( importCSVTransaction );

    }

    @Override
    public ImportCSVTransaction findById( Integer id ) throws DAOException {

        return dAOcsv.findById( id );
    }

    public List<ImportCSVTransaction> readTransactions() throws DAOException, IOException, ParseException {

        File file = new File( "../../CLA/CLA/test/" + RESOURCES_PATH + TRANSACTIONS_FILE_NAME );
        System.out.println( file.getAbsolutePath() );
        List<ImportCSVTransaction> importCSVTransactions = new ArrayList<ImportCSVTransaction>();
        List<String[]> data = new ArrayList<String[]>();
        try {
            FileReader fr = new FileReader( file );
            CSVReader csvReader = new CSVReader( fr, SEPARATOR );

            String[] nextLine = null;
            while ( ( nextLine = csvReader.readNext() ) != null ) {
                int size = nextLine.length;

                if ( size == 0 ) {
                    continue;
                }
                String debut = nextLine[0].trim();
                if ( debut.length() == 0 && size == 1 ) {
                    continue;
                }

                if ( debut.startsWith( "Date" ) ) {
                    continue;
                }
                if ( debut.startsWith( "Code" ) ) {
                    continue;
                }
                if ( debut.startsWith( "Numéro" ) ) {
                    continue;
                }
                if ( debut.startsWith( "Solde" ) ) {
                    continue;
                }
                data.add( nextLine );
            }

            DateFormat dateFormat = new SimpleDateFormat( "dd/MM/yy" );
            for ( String[] oneData : data ) {
                String dateOperation = oneData[0];
                String numeroOperation = oneData[1];
                String libelle = oneData[2];
                String debit = oneData[3];
                String credit = oneData[4];
                String detail = oneData[5];

                if ( StringUtils.isEmpty( debit ) ) {
                    debit = "0.0";
                } else {

                    debit = debit.replace( ",", "." );
                }

                if ( StringUtils.isEmpty( credit ) ) {
                    credit = "0.0";
                } else {

                    credit = credit.replace( ",", "." );
                }
                Date dateTransaction = dateFormat.parse( dateOperation );

                ImportCSVTransaction importCSVTransaction = new ImportCSVTransaction( dateTransaction, numeroOperation,
                        libelle,
                        Double.parseDouble( debit ), Double.parseDouble( credit ), detail );
                importCSVTransactions.add( importCSVTransaction );
            }

        } catch ( FileNotFoundException e ) {

            LOGGER.error( "Impossible de lire le fichier à l'emplacement suivant : " + RESOURCES_PATH
                    + TRANSACTIONS_FILE_NAME, e );
        }

        return importCSVTransactions;
    }
}
