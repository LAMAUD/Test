package fr.micropole.controller;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.micropole.enumeration.SpecifiedCategory;
import fr.micropole.exception.DAOException;
import fr.micropole.exception.ServiceException;
import fr.micropole.pojo.CategorisationLibelle;
import fr.micropole.pojo.Category;
import fr.micropole.pojo.Transaction;
import fr.micropole.service.ServiceCategorisationLibelle;
import fr.micropole.service.ServiceCategory;
import fr.micropole.service.ServiceTransaction;
import fr.micropole.validation.TransactionValidation;

@Controller
@RequestMapping( value = "/transaction" )
public class TransactionController {

    private final static Logger  LOGGER       = Logger.getLogger( TransactionController.class );

    @Autowired
    ServiceTransaction           serviceTransaction;

    @Autowired
    ServiceCategory              serviceCategory;

    @Autowired
    TransactionValidation        transactionValidation;

    @Autowired
    ServiceCategorisationLibelle serviceCategorisationLibelle;

    List<Category>               categories   = new ArrayList<Category>();
    List<Transaction>            transactions = new ArrayList<Transaction>();

    @RequestMapping( value = "/initForm", method = RequestMethod.GET )
    public ModelAndView initForm() {

        ModelAndView modelAndView = new ModelAndView( "transaction" );

        try {
            transactions = serviceTransaction.readAll();
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "Impossible de lire la liste des transactions", e );
        }

        try {
            categories = serviceCategory.readAll();
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "Impossible de lire la liste des catégories à partir de la création de transactions", e );
        }

        for ( SpecifiedCategory specifiedCategory : SpecifiedCategory.values() ) {
            Category cat = new Category();
            cat.setName( specifiedCategory.toString() );
            cat.setDescription( specifiedCategory.getDescription() );
            if ( !categories.contains( cat ) ) {
                try {
                    cat = serviceCategory.create( cat );
                    categories.add( cat );
                } catch ( ServiceException e ) {
                    LOGGER.error( "La catégorie n'a pas été persistée dans la base", e );
                }
            }

        }
        modelAndView.addObject( "transaction", new Transaction() );
        modelAndView.addObject( "category", new Category() );
        modelAndView.addObject( "categories", categories );
        modelAndView.addObject( "transactions", transactions );

        return modelAndView;
    }

    @RequestMapping( value = "/save" )
    public ModelAndView save( @Valid Transaction transaction, BindingResult result ) {
        ModelAndView modelAndView = new ModelAndView( "transaction" );

        transactionValidation.validate( transaction, result );

        Integer id_Category = transaction.getCategory().getId();

        Category cat = new Category();

        try {
            cat = serviceCategory.findById( id_Category );
        } catch ( ServiceException | DAOException e1 ) {
            LOGGER.error( "La catégorie n'a pas été trouvée", e1 );
        }

        transaction.setCategory( cat );

        if ( !result.hasErrors() ) {
            try {
                serviceTransaction.create( transaction );
            } catch ( ServiceException e ) {
                LOGGER.error( "La transaction n'a pas été créé", e );
            }
        }

        try {
            transactions = serviceTransaction.readAll();
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "Impossible de lire la liste des transactions", e );
        }

        modelAndView.addObject( "transactions", transactions );

        return modelAndView;
    }

    @RequestMapping( value = "/ajax", method = RequestMethod.GET )
    public ModelAndView ajax( @RequestParam( "input" ) String dateDebut, @RequestParam( "output" ) String dateFin,
            @RequestParam( "category" ) String category )
            throws ParseException {
        ModelAndView modelAndView = new ModelAndView( "afficheTransaction" );

        Charset charEncodeInit = Charset.forName( "ISO-8859-1" );
        Charset charEncodeFinal = Charset.forName( "UTF-8" );
        byte byteStringCategory[] = category.getBytes( charEncodeInit );
        String categoryEncoded = new String( byteStringCategory, charEncodeFinal );

        if ( StringUtils.isEmpty( category ) ) {
            try {
                transactions = serviceTransaction.readTransactionBetweenDate( dateDebut, dateFin );
            } catch ( DAOException e ) {
                LOGGER.error( "Impossible de lire les transactions sur cette période", e );
            }
        } else {
            transactions = serviceTransaction.readTransactionBetweenDateAndByCategory( dateDebut, dateFin,
                    categoryEncoded );
        }

        modelAndView.addObject( "transactions", transactions );
        return modelAndView;
    }

    @RequestMapping( value = "/update", method = RequestMethod.GET )
    public ModelAndView update( @RequestParam( "id" ) String id )
            throws ParseException {
        ModelAndView modelAndView = new ModelAndView( "updateTransaction" );
        Transaction transaction = new Transaction();
        Integer idConverti = Integer.parseInt( id );

        try {
            transaction = serviceTransaction.findById( idConverti );
        } catch ( DAOException e ) {
            LOGGER.error( "Impossible de trouver la transaction faisant référence à l'id : " + id, e );
        }

        try {
            categories = serviceCategory.readAll();
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "Impossible de lire la liste des catégories à partir de la création de transactions", e );
        }
        // transactions = serviceTransaction.
        modelAndView.addObject( "script", "" );
        modelAndView.addObject( "categories", categories );
        modelAndView.addObject( "transaction", transaction );
        return modelAndView;
    }

    @RequestMapping( value = "/updateCategorie", method = RequestMethod.POST )
    public ModelAndView updateCategorie( @Valid Transaction transaction, BindingResult result ) {

        ModelAndView modelAndView = new ModelAndView( "updateTransaction" );

        transactionValidation.validate( transaction, result );

        Integer id_Category = transaction.getCategory().getId();

        Category cat = new Category();

        try {
            cat = serviceCategory.findById( id_Category );
        } catch ( ServiceException | DAOException e1 ) {
            LOGGER.error( "La catégorie n'a pas été trouvée", e1 );
        }

        transaction.setCategory( cat );

        if ( !result.hasErrors() ) {
            try {
                transaction = serviceTransaction.create( transaction );
            } catch ( ServiceException e ) {
                LOGGER.error( "La transaction n'a pas été créé", e );
            }
        }
        CategorisationLibelle categorisationLibelle = new CategorisationLibelle();
        categorisationLibelle.setCategory( cat );
        categorisationLibelle.setLibelle( transaction.getDescription() );

        try {
            serviceCategorisationLibelle.create( categorisationLibelle );
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "Impossible de créer l'association entre le libelle et la categorie", e );
        }

        StringBuilder script = new StringBuilder();
        script.append( "<script type=\"text/javascript\">window.close();</script>" );
        modelAndView.addObject( "script", script );
        modelAndView.addObject( "transaction", transaction );
        return modelAndView;
    }
}
