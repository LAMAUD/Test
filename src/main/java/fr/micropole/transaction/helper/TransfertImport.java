package fr.micropole.transaction.helper;

import java.util.List;

import fr.micropole.enumeration.SpecifiedCategory;
import fr.micropole.pojo.CategorisationLibelle;
import fr.micropole.pojo.Category;
import fr.micropole.pojo.ImportCSVTransaction;
import fr.micropole.pojo.Transaction;

public class TransfertImport {

    public static Transaction TransfertImportTransaction( ImportCSVTransaction importCSVTransaction,
            List<Category> categories, List<CategorisationLibelle> categorisationLibelle ) {

        Transaction transaction = new Transaction();

        Category category = TransfertImport.TransfertImportCategory( importCSVTransaction, categories,
                categorisationLibelle );
        transaction.setCategory( category );
        transaction.setDate( importCSVTransaction.getDate() );
        transaction.setDepense( Math.abs( importCSVTransaction.getDebit() ) );
        transaction.setRecette( importCSVTransaction.getCredit() );
        transaction.setDescription( importCSVTransaction.getLibelle() );

        return transaction;
    }

    public static Category TransfertImportCategory( ImportCSVTransaction importCSVTransaction,
            List<Category> categories, List<CategorisationLibelle> categorisationLibelles ) {

        Category cat = SpecifiedCategory.AUCUNE_CATEGORIE.getCategory();
        Category catOut = new Category();

        String libelle = importCSVTransaction.getLibelle();
        libelle = libelle.toUpperCase();

        for ( CategorisationLibelle categorisationLibelle : categorisationLibelles ) {

            if ( libelle.contains( categorisationLibelle.getLibelle().toUpperCase() ) ) {
                cat = categorisationLibelle.getCategory();
                break;
            }

        }

        // if ( libelle.contains( "U EXPRESS" ) || libelle.contains( "MONOPRIX"
        // ) || libelle.contains( "AUCHAN" )
        // || libelle.contains( "CARREFOUR AIX" ) || libelle.contains(
        // "PROMOCASH" )
        // || libelle.contains( "CARREFOUR BEDOI" ) ) {
        //
        // cat = SpecifiedCategory.COURSES.getCategory();
        //
        // } else if ( libelle.contains( "QUICK" ) || libelle.contains( "MMG" )
        // || libelle.contains( "MARSHEL" ) ) {
        //
        // cat = SpecifiedCategory.FASTFOOD.getCategory();
        //
        // } else if ( libelle.contains( "AUTO ESPACE MIL" ) ||
        // libelle.contains( "RELAIS MILLES" )
        // || libelle.contains( "SARL KRH2" ) || libelle.contains(
        // "ASF AVIGNON-N" )
        // || libelle.contains( "CARREFOUR DAC" ) || libelle.contains( "DRIVY" )
        // || libelle.contains( "ASF" ) ) {
        //
        // cat = SpecifiedCategory.VOITURE.getCategory();
        //
        // } else if ( libelle.contains( "LOYER" ) ) {
        //
        // cat = SpecifiedCategory.LOYER.getCategory();
        //
        // } else if ( libelle.contains( "DARTY" ) || libelle.contains( "FNAC" )
        // ) {
        //
        // cat = SpecifiedCategory.TECHNOLOGIES.getCategory();
        //
        // } else if ( libelle.contains( "AMAZON" ) || libelle.contains( "CELIO"
        // ) || libelle.contains( "NATURE DECOUV" )
        // || libelle.contains( "FETER Et RECEVO" ) || libelle.contains(
        // "NATURE & DECOUV" ) ) {
        //
        // cat = SpecifiedCategory.SHOPPING.getCategory();
        //
        // } else if ( libelle.contains( "CADEAU" ) ) {
        //
        // cat = SpecifiedCategory.CADEAU.getCategory();
        //
        // } else if ( libelle.contains( "THE SHAMROCK" ) || libelle.contains(
        // "SANTONI" ) ) {
        //
        // cat = SpecifiedCategory.BAR.getCategory();
        //
        // } else if ( libelle.contains( "AIRBNB" ) ) {
        //
        // cat = SpecifiedCategory.AIRBNB.getCategory();
        //
        // } else if ( libelle.contains( "LE L" ) ) {
        //
        // cat = SpecifiedCategory.COIFFEUR.getCategory();
        //
        // } else if ( libelle.contains( "Z 5" ) ) {
        //
        // cat = SpecifiedCategory.SPORT.getCategory();
        //
        // } else if ( libelle.contains( "GARANTIE-PRIVEE" ) ||
        // libelle.contains( "FILIA-MAIF" ) ) {
        //
        // cat = SpecifiedCategory.ASSURANCE.getCategory();
        //
        // } else if ( libelle.contains( "ORANGE" ) || libelle.contains(
        // "SPOTIFY" ) ) {
        //
        // cat = SpecifiedCategory.FORFAIT.getCategory();
        //
        // } else if ( libelle.contains( "MICROPOLE" ) ) {
        //
        // cat = SpecifiedCategory.SALAIRE.getCategory();
        //
        // } else if ( libelle.contains( "SCI 3 L" ) ) {
        //
        // cat = SpecifiedCategory.SCI.getCategory();
        //
        // } else if ( libelle.contains( "LA POSTE" ) ) {
        //
        // cat = SpecifiedCategory.VIE_QUOTIDIENNE.getCategory();
        //
        // } else if ( libelle.contains( "UN P'TIT COIN" ) || libelle.contains(
        // "MRN" ) || libelle.contains( "MAZEL" ) ) {
        //
        // cat = SpecifiedCategory.RESTAURANT.getCategory();
        //
        // } else if ( libelle.contains( "RTM" ) || libelle.contains( "SNCF" ) )
        // {
        //
        // cat = SpecifiedCategory.TRANSPORTCOMMUN.getCategory();
        //
        // } else if ( libelle.contains( "FIESTA DES SUDS" ) ||
        // libelle.contains( "FRANCE BILLETVA" ) ) {
        //
        // cat = SpecifiedCategory.SPECTACLE.getCategory();
        //
        // } else if ( libelle.contains( "DENEUVE TABAC" ) ) {
        //
        // cat = SpecifiedCategory.CIGARETTES.getCategory();
        //
        // } else if ( libelle.contains( "WIKIMEDIAFOUNDA" ) ) {
        //
        // cat = SpecifiedCategory.DON.getCategory();
        //
        // } else if ( libelle.contains( "CPAM" ) ) {
        //
        // cat = SpecifiedCategory.SECU.getCategory();
        //
        // } else if ( libelle.contains( "VIR SEPA MONSIEUR CEDRIC LAMAUD" ) ) {
        //
        // cat = SpecifiedCategory.PEL.getCategory();
        //
        // } else if ( libelle.contains( "FRAIS" ) || libelle.contains(
        // "COTIS FORFAIT SATELLIS ESS" ) ) {
        //
        // cat = SpecifiedCategory.FRAIS.getCategory();
        //
        // } else if ( libelle.contains( "ECONOMIES" ) ) {
        //
        // cat = SpecifiedCategory.ECONOMIES.getCategory();
        //
        // } else if ( libelle.contains( "PRLV DIRECTION GENERALE DES FINA" ) )
        // {
        //
        // cat = SpecifiedCategory.ECONOMIES.getCategory();
        //
        // } else {
        //
        // cat = SpecifiedCategory.AUCUNE_CATEGORIE.getCategory();
        // }

        for ( Category category : categories ) {
            if ( category.equals( cat ) ) {
                catOut = category;
            }
        }

        return catOut;
    }
}
