package fr.micropole.enumeration;

public enum MonthOfTheYear {

    // Objets directement construits
    JANUARY( "01", "Janvier" ),
    FEBRUARY( "02", "Fevrier" ),
    MARCH( "03", "Mars" ),
    APRIL( "04", "Avril" ),
    MAY( "05", "Mai" ),
    JUNE( "06", "Juin" ),
    JULY( "07", "Juillet" ),
    AUGUST( "08", "Aout" ),
    SEPTEMBER( "09", "Septembre" ),
    OCTOBER( "10", "Octobre" ),
    NOVEMBER( "11", "Novembre" ),
    DECEMBER( "12", "Décembre" );

    private String number = "";
    private String name   = "";

    // Constructeur
    MonthOfTheYear( String number, String name ) {
        this.number = number;
        this.name = name;

    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

}
