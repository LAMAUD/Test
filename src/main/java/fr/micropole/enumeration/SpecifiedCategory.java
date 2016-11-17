package fr.micropole.enumeration;

import fr.micropole.pojo.Category;

public enum SpecifiedCategory {

    // Objets directement construits
    VOITURE( "Voiture", "Dépenses liées à la voiture" ),
    COURSES( "Courses supermarchés", "Dépenses concernant tout ce qui est dépensé au supermarché" ),
    BAR( "Bar", "Sortie Bar" ),
    CIGARETTES( "Cigarettes", "Achat Cigarettes" ),
    TECHNOLOGIES( "Technologie", "Achat lié à la technologie" ),
    AIRBNB( "AirBNB", "WE AirBNB" ),
    TRANSPORTCOMMUN( "Transport en Commun", "Transport en Commun" ),
    CADEAU( "Cadeau", "Cadeau faits" ),
    COIFFEUR( "Coiffeur", "Coiffeur" ),
    ASSURANCE( "Assurance", "Assurance Habitation - Voiture - Surface" ),
    FORFAIT( "Forfait", "Telephone" ),
    LOYER( "Loyer", "Loyer Habitation" ),
    RESTAURANT( "Restaurant", "Restauration" ),
    SHOPPING( "Shopping", "Tout achat externe aux supermarchés et aux achats technologiques" ),
    SPORT( "Activité Sportive", "Activité Sportive" ),
    AUCUNE_CATEGORIE( "Aucune Catégorie", "Aucune Catégorie" ),
    SALAIRE( "Salaire Mensuel", "Salaire Mensuel" ),
    SCI( "SCI 3L", "SCI 3L" ),
    VIE_QUOTIDIENNE( "Vie Quotidienne", "La Poste - fournitures" ),
    FASTFOOD( "Fast Food", "Truc de gros" ),
    SPECTACLE( "Spectacle", "Festival - Théâtre" );

    private String   name        = "";
    private String   description = "";
    private Category category    = new Category();

    private SpecifiedCategory( String name, String description ) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;

    }

    public String toString() {
        return name;
    }

    public Category getCategory() {
        if ( category.getName() == null ) {
            String nameBis = this.name;
            category.setName( nameBis );
        }
        return category;
    }
}
