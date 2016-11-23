package fr.micropole.controller;

import fr.micropole.helper.LibelleHelper;

public class test {

    public static void main( String[] args ) {
        String test = "CB 0344 MONOPRIX FACT 221016";

        System.out.println( test );

        test = LibelleHelper.troncageLibelle( test );

        System.out.println( test );
    }

}
