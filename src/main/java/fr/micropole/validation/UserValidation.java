package fr.micropole.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.micropole.pojo.User;

@Component
public class UserValidation implements Validator {

    @Override
    public boolean supports( Class<?> arg0 ) {

        return arg0.getClass().equals( User.class );
    }

    @Override
    public void validate( Object user, Errors errors ) {

        User utilisateur = (User) user;

        if ( utilisateur.getName() == null ) {
            errors.rejectValue( "nom", "hack", "le Nom est nul" );
        }
    }

}
