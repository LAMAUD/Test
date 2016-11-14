package fr.micropole.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import fr.micropole.exception.DAOException;
import fr.micropole.exception.ServiceException;
import fr.micropole.pojo.Category;
import fr.micropole.pojo.Transaction;
import fr.micropole.service.ServiceCategory;
import fr.micropole.service.ServiceTransaction;
import fr.micropole.validation.TransactionValidation;

@Controller
@RequestMapping( value = "/transaction" )
public class TransactionController {

    private final static Logger LOGGER       = Logger.getLogger( TransactionController.class );

    @Autowired
    ServiceTransaction          serviceTransaction;

    @Autowired
    ServiceCategory             serviceCategory;

    @Autowired
    TransactionValidation       transactionValidation;

    List<Category>              categories   = new ArrayList<Category>();
    List<Transaction>           transactions = new ArrayList<Transaction>();

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
    public ModelAndView ajax( @RequestParam( "input" ) String dateDebut, @RequestParam( "output" ) String dateFin )
            throws ParseException {
        ModelAndView modelAndView = new ModelAndView( "presentation" );

        SimpleDateFormat sm = new SimpleDateFormat( "yyyyMMdd", Locale.ENGLISH );

        if ( StringUtils.isEmpty( dateDebut ) ) {
            dateDebut = "19700101";
        }
        if ( StringUtils.isEmpty( dateFin ) ) {
            dateFin = "20500101";
        }
        Date dateDeb = sm.parse( dateDebut );
        Date dateF = sm.parse( dateFin );
        transactions = serviceTransaction.readTransactionBetweenDate( dateDeb, dateF );

        modelAndView.addObject( "transactions", transactions );
        return modelAndView;
    }
}
