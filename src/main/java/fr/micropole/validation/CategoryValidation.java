package fr.micropole.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.micropole.pojo.Category;

@Component
public class CategoryValidation implements Validator {

    @Override
    public boolean supports( Class<?> clazz ) {

        return clazz.getClass().equals( Category.class );
    }

    @Override
    public void validate( Object category, Errors errors ) {

        Category cat = (Category) category;

        String categorieName = cat.getName();

        if ( categorieName == null ) {
            errors.rejectValue( "name", "hack", "La catégorie ne possède pas de nom" );
        }

    }

}
