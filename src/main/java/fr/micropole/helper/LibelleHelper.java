package fr.micropole.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibelleHelper {

    public static String troncageLibelle( String libelle ) {

        List<Integer> listIndex = new ArrayList<Integer>();

        listIndex = LibelleHelper.findIndex( libelle, 0, listIndex );
        int MoitieLibelle = libelle.length() / 2;
        int troncage = 0;

        for ( int i = 0; i < listIndex.size(); i++ ) {
            listIndex.set( i, listIndex.get( i ) - 1 );
            if ( listIndex.get( i ) > MoitieLibelle ) {
                troncage = listIndex.get( i ) - 1;
                libelle = libelle.substring( 0, troncage );
                break;

            }
        }

        return libelle;
    }

    // Trouver les index du String où il y a les chiffres
    public static List<Integer> findIndex( String libelle, int index, List<Integer> listIndex ) {

        String libelleInitial = libelle;
        libelle = libelle.substring( index );
        int indexInitial = index;

        String patternString = "[0-9]{1}";
        Pattern pattern = Pattern.compile( patternString );
        Matcher matcher = pattern.matcher( libelle );

        if ( matcher.find() ) {
            index = matcher.start();

            listIndex.add( index + indexInitial + 1 );

            LibelleHelper.findIndex( libelleInitial, index + indexInitial + 1, listIndex );

        }

        return listIndex;

    }

}
