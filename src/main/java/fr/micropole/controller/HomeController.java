package fr.micropole.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.micropole.enumeration.MonthOfTheYear;
import fr.micropole.exception.DAOException;
import fr.micropole.exception.ServiceException;
import fr.micropole.helper.DepensesParCategoriesHelper;
import fr.micropole.javascript.JavaEnJS;
import fr.micropole.pojo.Category;
import fr.micropole.pojo.Transaction;
import fr.micropole.service.ServiceCategory;
import fr.micropole.service.ServiceTransaction;

@Controller
@RequestMapping( value = "/home" )
public class HomeController {

    private final static Logger LOGGER       = Logger.getLogger( HomeController.class );

    @Autowired
    ServiceTransaction          serviceTransaction;

    @Autowired
    ServiceCategory             serviceCategory;

    List<Transaction>           transactions = new ArrayList<Transaction>();

    @RequestMapping( value = "/initForm", method = RequestMethod.GET )
    public ModelAndView initForm() {

        MonthOfTheYear[] months = MonthOfTheYear.values();

        ModelAndView modelAndView = new ModelAndView( "home" );

        modelAndView.addObject( "months", months );

        return modelAndView;
    }

    @RequestMapping( value = "/ajax", method = RequestMethod.GET )
    public ModelAndView ajax( @RequestParam( "month" ) String month ) {

        HashMap<Category, Double> categorisation = new HashMap<>();

        ModelAndView modelAndView = new ModelAndView( "affichePiechart" );

        if ( month.equals( "all" ) ) {
            try {
                transactions = serviceTransaction.readAll();
            } catch ( ServiceException | DAOException e ) {
                LOGGER.error( "Impossible de lire toutes les transactions", e );
            }
        }
        else {
            transactions = serviceTransaction.readTransactionByMonth( month );
        }

        categorisation = DepensesParCategoriesHelper.DepenseParCategorieTotal( transactions );
        StringBuilder script = JavaEnJS.PieChart( categorisation );
        modelAndView.addObject( "script", script );

        return modelAndView;
    }

    @RequestMapping( value = "/ajaxDepRec", method = RequestMethod.GET )
    public ModelAndView ajaxDepRec( @RequestParam( "month" ) String month ) {

        ModelAndView modelAndView = new ModelAndView( "ajaxHome/progressbar" );

        if ( month.equals( "all" ) ) {
            try {
                transactions = serviceTransaction.readAll();
            } catch ( ServiceException | DAOException e ) {
                LOGGER.error( "Impossible de lire toutes les transactions", e );
            }
        }
        else {
            transactions = serviceTransaction.readTransactionByMonth( month );
        }
        Double rate = serviceTransaction.rateExpensesIncome( transactions );
        modelAndView.addObject( "ratioDepensesRecettes", rate );
        modelAndView.addObject( "Depenses", serviceTransaction.sumOfExpenses( transactions ) );
        modelAndView.addObject( "Recettes", serviceTransaction.sumOfIncome( transactions ) );
        return modelAndView;
    }

}
