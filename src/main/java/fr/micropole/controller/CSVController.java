package fr.micropole.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fr.micropole.exception.DAOException;
import fr.micropole.exception.ServiceException;
import fr.micropole.helper.UploadFileHelper;
import fr.micropole.pojo.Category;
import fr.micropole.pojo.ImportCSVTransaction;
import fr.micropole.pojo.Transaction;
import fr.micropole.service.ServiceCategory;
import fr.micropole.service.ServiceCsv;
import fr.micropole.service.ServiceTransaction;
import fr.micropole.transaction.helper.TransfertImport;

@Controller
@RequestMapping( value = "/csv" )
public class CSVController {

    private final static Logger LOGGER                              = Logger.getLogger( CSVController.class );

    @Autowired
    ServiceCsv                  serviceCsv;

    @Autowired
    ServiceCategory             serviceCategory;

    @Autowired
    ServiceTransaction          serviceTransaction;

    List<ImportCSVTransaction>  importCSVTransactions               = new ArrayList<ImportCSVTransaction>();
    List<ImportCSVTransaction>  importCSVTransactionsPresentsEnBase = new ArrayList<ImportCSVTransaction>();
    List<Category>              categories                          = new ArrayList<Category>();
    List<Transaction>           transactions                        = new ArrayList<Transaction>();

    @RequestMapping( value = "/initForm", method = RequestMethod.GET )
    public ModelAndView initForm() {
        ModelAndView modelAndView = new ModelAndView( "csv" );

        return modelAndView;
    }

    @RequestMapping( value = "/upload", method = RequestMethod.POST )
    public ModelAndView upload(@RequestParam( "file" ) MultipartFile file ) {
        ModelAndView modelAndView = new ModelAndView( "csv" );

        String[] message = UploadFileHelper.uploadFile( file );
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

    @RequestMapping( value = "/transfert", method = RequestMethod.GET )
    public ModelAndView transfert() {

        ModelAndView modelAndView = new ModelAndView( "csv" );
        String messageTransfertTransaction = "";
        try {
            importCSVTransactionsPresentsEnBase = serviceCsv.readAll();
        } catch ( DAOException e ) {
            LOGGER.error( "Impossible de lire les transactions présentes en base", e );
        }

        try {
            categories = serviceCategory.readAll();
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "Impossible de lire toutes les catégories", e );
        }

        try {
            transactions = serviceTransaction.readAll();
        } catch ( ServiceException | DAOException e1 ) {
            LOGGER.error( "Impossible de lire les transactions présentes en base", e1 );
        }

        for ( ImportCSVTransaction importCSVTransactionPresentsEnBase : importCSVTransactionsPresentsEnBase ) {
            Transaction transaction = new Transaction();

            transaction = TransfertImport.TransfertImportTransaction( importCSVTransactionPresentsEnBase, categories );
            try {
                if ( !transactions.contains( transaction ) ) {

                    transaction = serviceTransaction.create( transaction );
                } else {
                    System.out.println( "Pas de transaction" );
                }
            } catch ( ServiceException e ) {
                messageTransfertTransaction = "Le transfert a échoué";
                LOGGER.error( "la transaction " + transaction.getDescription() + " n'a pas p être créée", e );
            }

            if ( StringUtils.isEmpty( messageTransfertTransaction ) ) {
                messageTransfertTransaction = "Le transfert a réussi";
            }
        }
        modelAndView.addObject( "messageTransfertTransaction", messageTransfertTransaction );
        return modelAndView;
    }
}
