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
import org.springframework.web.servlet.ModelAndView;

import fr.micropole.exception.DAOException;
import fr.micropole.pojo.ImportCSVTransaction;
import fr.micropole.service.ServiceCsv;

@Controller
@RequestMapping( value = "/csv" )
public class CSVController {

    private final static Logger LOGGER                = Logger.getLogger( CSVController.class );

    @Autowired
    ServiceCsv                  serviceCsv;

    List<ImportCSVTransaction>  importCSVTransactions = new ArrayList<ImportCSVTransaction>();

    @RequestMapping( value = "/initForm", method = RequestMethod.GET )
    public ModelAndView initForm() {
        ModelAndView modelAndView = new ModelAndView( "csv" );

        try {
            importCSVTransactions = serviceCsv.readTransactions();
        } catch ( DAOException | IOException | ParseException e ) {

            LOGGER.error( "Impossible d'importer les transactions du fichier CSV", e );
        }

        System.out.println( "C'est OK !!!" );
        return modelAndView;
    }
}
