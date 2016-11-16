package fr.micropole.helper;

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
import org.springframework.util.StringUtils;

import au.com.bytecode.opencsv.CSVReader;
import fr.micropole.pojo.ImportCSVTransaction;

public class ImportationHelper {

    private final static Logger LOGGER    = Logger.getLogger( ImportationHelper.class );
    private final static char   SEPARATOR = ';';

    public static List<ImportCSVTransaction> importationCSV( String path ) throws IOException, ParseException {
        File file = new File( path );
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

            LOGGER.error( "Impossible de lire le fichier à l'emplacement suivant : " + path, e );
        }
        return importCSVTransactions;
    }

}
