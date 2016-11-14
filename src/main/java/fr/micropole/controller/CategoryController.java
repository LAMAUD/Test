package fr.micropole.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.micropole.exception.DAOException;
import fr.micropole.exception.ServiceException;
import fr.micropole.pojo.Category;
import fr.micropole.service.ServiceCategory;
import fr.micropole.validation.CategoryValidation;

@Controller
@RequestMapping( value = "/category" )
public class CategoryController {

    private final static Logger LOGGER     = Logger.getLogger( CategoryController.class );

    @Autowired
    private ServiceCategory     serviceCategory;

    @Autowired
    private CategoryValidation  categoryValidation;

    private List<Category>      categories = new ArrayList<Category>();

    @RequestMapping( value = "/initForm", method = RequestMethod.GET )
    public ModelAndView initForm() {
        ModelAndView modelAndView = new ModelAndView( "category" );

        try {
            categories = serviceCategory.readAll();
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "Impossible de sortir la liste des catégories", e );
        }

        modelAndView.addObject( "categories", categories );
        modelAndView.addObject( "category", new Category() );

        return modelAndView;
    }

    @RequestMapping( value = "/save", method = RequestMethod.POST )
    public ModelAndView save( @Valid Category category, BindingResult result ) {
        ModelAndView modelAndView = new ModelAndView( "category" );

        categoryValidation.validate( category, result );

        if ( !result.hasErrors() ) {
            try {
                category = serviceCategory.create( category );
            } catch ( ServiceException e ) {
                LOGGER.error( "La catégorie n'a pas pu être créée", e );
            }
        }

        try {
            categories = serviceCategory.readAll();
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "Impossible de sortir la liste des catégories", e );
        }

        modelAndView.addObject( "categories", categories );

        return modelAndView;
    }

    @RequestMapping( value = "/remove", method = RequestMethod.GET )
    public ModelAndView remove( @RequestParam( "id" ) Integer id ) {
        ModelAndView modelAndView = new ModelAndView( "category" );

        try {
            serviceCategory.remove( id );
        } catch ( ServiceException | DAOException e ) {

            LOGGER.error( "La catégorie n'a pas pu être supprimée", e );
        }

        try {
            categories = serviceCategory.readAll();
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "Impossible de sortir la liste des catégories", e );
        }
        modelAndView.addObject( "categories", categories );
        modelAndView.addObject( "category", new Category() );
        return modelAndView;

    }
}
