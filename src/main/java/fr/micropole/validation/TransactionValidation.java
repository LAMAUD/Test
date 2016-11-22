package fr.micropole.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.micropole.pojo.Transaction;

@Component
public class TransactionValidation implements Validator {

    @Override
    public boolean supports( Class<?> clazz ) {

        return clazz.getClass().equals( Transaction.class );
    }

    @Override
    public void validate( Object target, Errors errors ) {

        Transaction transaction = (Transaction) target;

        if ( transaction.getDepense() == null && transaction.getRecette() == null ) {

            errors.rejectValue( "Depense - recette", "hack", "Un des champs Dépense ou Recette doit être rempli" );
        }

        if ( ( transaction.getDepense() != null && transaction.getRecette() != null )
                && ( transaction.getDepense() != 0.0 && transaction.getRecette() != 0.0 )
                && ( transaction.getDepense() != null && transaction.getRecette() != 0.0 )
                && ( transaction.getDepense() != 0.0 && transaction.getRecette() != null ) ) {
            errors.rejectValue( "Depense - recette", "hack", "Un des champs Dépense ou Recette doit être vide" );
        }

    }

}
