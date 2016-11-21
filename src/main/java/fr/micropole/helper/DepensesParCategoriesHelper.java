package fr.micropole.helper;

import java.util.HashMap;
import java.util.List;

import fr.micropole.pojo.Category;
import fr.micropole.pojo.Transaction;

public class DepensesParCategoriesHelper {

    public static HashMap<Category, Double> DepenseParCategorieTotal( List<Transaction> transactions ) {
        HashMap<Category, Double> categorisation = new HashMap<Category, Double>();

        for ( Transaction transaction : transactions ) {

            Category category = transaction.getCategory();
            Double depense = transaction.getDepense();

            if ( categorisation.containsKey( category ) ) {
                Double depenseAfterTransaction = categorisation.get( category ) + depense;
                categorisation.replace( category, depenseAfterTransaction );
            }
            else {

                categorisation.put( category, depense );
            }
        }

        return categorisation;
    }
}
