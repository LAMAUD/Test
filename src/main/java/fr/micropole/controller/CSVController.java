package fr.micropole.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fr.micropole.exception.DAOException;
import fr.micropole.helper.UploadFileHelper;
import fr.micropole.pojo.ImportCSVTransaction;
import fr.micropole.service.ServiceCsv;

@Controller
@RequestMapping( value = "/csv" )
public class CSVController {

    private final static Logger LOGGER                              = Logger.getLogger( CSVController.class );

    @Autowired
    ServiceCsv                  serviceCsv;

    List<ImportCSVTransaction>  importCSVTransactions               = new ArrayList<ImportCSVTransaction>();
    List<ImportCSVTransaction>  importCSVTransactionsPresentsEnBase = new ArrayList<ImportCSVTransaction>();

    @RequestMapping( value = "/initForm", method = RequestMethod.GET )
    public ModelAndView initForm() {
        ModelAndView modelAndView = new ModelAndView( "csv" );

        return modelAndView;
    }

    @RequestMapping( value = "/upload", method = RequestMethod.POST )
    public ModelAndView upload( @RequestParam( "name" ) String name,
            @RequestParam( "file" ) MultipartFile file ) {
        ModelAndView modelAndView = new ModelAndView( "csv" );

        String[] message = UploadFileHelper.uploadFile( name, file );
        try {
            importCSVTransactions = serviceCsv.readTransactionsFromImportation( message[1] );
        } catch ( DAOException | IOException | ParseException e ) {

            LOGGER.error( "Impossible d'importer les transactions du fichier CSV", e );
        }

        try {
            importCSVTransactionsPresentsEnBase = serviceCsv.readAll();
        } catch ( DAOException e1 ) {
            LOGGER.error( "Impossible de lire les transactions présentes en base", e1 );
        }

        for ( ImportCSVTransaction importCSVTransaction : importCSVTransactions ) {

            try {
                if ( !importCSVTransactionsPresentsEnBase.contains( importCSVTransaction ) ) {

                    importCSVTransaction = serviceCsv.create( importCSVTransaction );
                }
            } catch ( DAOException e ) {
                LOGGER.error( "Impossible d'enregistrer la transaction " + importCSVTransaction.getNumeroOperation(), e );
            }

        }

        modelAndView.addObject( "message", message );

        return modelAndView;
    }

    @RequestMapping( value = "/ajax", method = RequestMethod.GET )
    public ModelAndView ajax( @RequestParam( "input" ) String dateDebut, @RequestParam( "output" ) String dateFin )
            throws ParseException {
        ModelAndView modelAndView = new ModelAndView( "transactionsImporteesEnBase" );

        try {
            importCSVTransactionsPresentsEnBase = serviceCsv.readTransactionBetweenDate( dateDebut, dateFin );
        } catch ( DAOException e ) {
            LOGGER.error( "Impossible de lire les transactions présentes en base", e );
        }

        modelAndView.addObject( "importCSVTransactionsPresentsEnBase", importCSVTransactionsPresentsEnBase );
        return modelAndView;
    }
}
