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
import fr.micropole.pojo.User;
import fr.micropole.service.ServiceUser;
import fr.micropole.validation.UserValidation;

@Controller
@RequestMapping( value = "/user" )
public class UserController {

    @Autowired
    ServiceUser                 serviceUser;

    @Autowired
    UserValidation              userValidation;

    private List<User>          users  = new ArrayList<User>();

    private final static Logger LOGGER = Logger.getLogger( UserController.class );

    @RequestMapping( value = "/initForm", method = RequestMethod.GET )
    public ModelAndView initForm() {
        ModelAndView modelAndView = new ModelAndView( "user" );

        try {
            users = serviceUser.readAll();
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "La suppression d'un utilisateur a échoué", e );
        }
        modelAndView.addObject( "user", new User() );
        modelAndView.addObject( "users", users );
        return modelAndView;
    }

    @RequestMapping( value = "/save", method = RequestMethod.POST )
    public ModelAndView save( @Valid User user, BindingResult result ) {
        ModelAndView modelAndView = new ModelAndView( "user" );

        userValidation.validate( user, result );

        if ( !result.hasErrors() ) {

            try {
                user = serviceUser.create( user );
            } catch ( ServiceException e ) {

                LOGGER.error( "L'utilisateur n'a pas été créé", e );
            }
        }
        return modelAndView;
    }

    @RequestMapping( value = "/remove", method = RequestMethod.GET )
    public ModelAndView remove( @RequestParam( "id" ) Integer id ) {
        ModelAndView modelAndView = new ModelAndView( "user" );

        try {
            serviceUser.remove( id );
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "La Suppression d'un utilisateur ne s'est pas faite", e );
        }

        try {
            users = serviceUser.readAll();
        } catch ( ServiceException | DAOException e ) {
            LOGGER.error( "La suppression d'un utilisateur a échoué", e );
        }
        modelAndView.addObject( "user", new User() );
        modelAndView.addObject( "users", users );
        return modelAndView;
    }

    @RequestMapping( value = "/list", method = RequestMethod.GET )
    public ModelAndView list() {

        ModelAndView modelAndView = new ModelAndView( "user" );

        return modelAndView;
    }

}
