package fr.micropole.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.micropole.exception.DAOException;
import fr.micropole.exception.ServiceException;
import fr.micropole.helper.ExportToFileHelper;
import fr.micropole.helper.ImportationHelper;
import fr.micropole.pojo.CategorisationLibelle;
import fr.micropole.pojo.Category;
import fr.micropole.service.ServiceCategorisationLibelle;
import fr.micropole.service.ServiceCategory;

@Controller
@RequestMapping( value = "/categorisationLibelle" )
public class CategorisationLibelleController {

    public final static Logger   LOGGER                 = Logger.getLogger( CategorisationLibelleController.class );
    private final static String  RESOURCES_PATH         = "src/main/resources";

    @Autowired
    ServiceCategorisationLibelle serviceCategorisationLibelle;

    @Autowired
    ServiceCategory              serviceCategory;

    List<CategorisationLibelle>  categorisationLibelles = new ArrayList<CategorisationLibelle>();

    List<Category>               categories             = new ArrayList<Category>();

    @RequestMapping( value = "/initForm", method = RequestMethod.GET )
    public ModelAndView initForm() throws IOException {

        ModelAndView modelAndView = new ModelAndView( "categorisationLibelle" );

        try {
            categorisationLibelles = serviceCategorisationLibelle.readAll();
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "Impossible de lire les associations entre les libellés et les catégories", e );
        }

        modelAndView.addObject( "categorisationLibelles", categorisationLibelles );
        return modelAndView;

    }

    @RequestMapping( value = "/export", method = RequestMethod.GET )
    public ModelAndView export() throws IOException {

        ModelAndView modelAndView = new ModelAndView( "categorisationLibelle" );

        try {
            categorisationLibelles = serviceCategorisationLibelle.readAll();
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "Impossible de lire les associations entre les libellés et les catégories", e );
        }

        ExportToFileHelper.exportCategorisationLibelleDataToCSVFile( categorisationLibelles );
        modelAndView.addObject( "categorisationLibelles", categorisationLibelles );
        return modelAndView;

    }

    @RequestMapping( value = "/import", method = RequestMethod.GET )
    public ModelAndView importCategorisationlibelle() throws IOException, ServiceException, DAOException {

        ModelAndView modelAndView = new ModelAndView( "categorisationLibelle" );
        List<CategorisationLibelle> categorisationLibellesFromImportation = new ArrayList<CategorisationLibelle>();
        String rootPath = System.getProperty( "catalina.home" );
        String path = rootPath + "/../../test/test/" + RESOURCES_PATH + "/categorisationLibelle.csv";

        try {
            categories = serviceCategory.readAll();
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "Impossible de lire les categories", e );
        }
        try {
            categorisationLibelles = serviceCategorisationLibelle.readAll();
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "Impossible de lire les associations entre les libellés et les catégories", e );
        }

        categorisationLibellesFromImportation = ImportationHelper
                .importationCSVCategorisationLibelle( path, categories );

        for ( CategorisationLibelle categorisationLibelleFromImportation : categorisationLibellesFromImportation ) {
            if ( !categorisationLibelles.contains( categorisationLibelleFromImportation ) ) {
                categorisationLibelleFromImportation = serviceCategorisationLibelle
                        .create( categorisationLibelleFromImportation );
            }
        }

        try {
            categorisationLibelles = serviceCategorisationLibelle.readAll();
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "Impossible de lire les associations entre les libellés et les catégories", e );
        }
        ExportToFileHelper.exportCategorisationLibelleDataToCSVFile( categorisationLibelles );
        modelAndView.addObject( "categorisationLibelles", categorisationLibelles );
        return modelAndView;

    }

}
