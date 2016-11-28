package fr.micropole.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Transaction implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2965890120001411372L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer           id;

    private Double            depense;

    private Double            recette;

    @JoinColumn( name = "category_id", referencedColumnName = "id", nullable
            = false )
    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    private Category          category;

    private String            description;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date              date;

    public Double getDepense() {
        return depense;
    }

    public void setDepense( Double depense ) {
        this.depense = depense;
    }

    public Double getRecette() {
        return recette;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( date == null ) ? 0 : date.hashCode() );
        result = prime * result + ( ( depense == null ) ? 0 : depense.hashCode() );
        result = prime * result + ( ( description == null ) ? 0 : description.hashCode() );
        result = prime * result + ( ( recette == null ) ? 0 : recette.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        Transaction other = (Transaction) obj;
        if ( date == null ) {
            if ( other.date != null )
                return false;
        } else if ( !date.equals( other.date ) )
            return false;
        if ( depense == null ) {
            if ( other.depense != null )
                return false;
        } else if ( !depense.equals( other.depense ) )
            return false;
        if ( description == null ) {
            if ( other.description != null )
                return false;
        } else if ( !description.equals( other.description ) )
            return false;
        if ( recette == null ) {
            if ( other.recette != null )
                return false;
        } else if ( !recette.equals( other.recette ) )
            return false;
        return true;
    }

    public void setRecette( Double recette ) {
        this.recette = recette;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory( Category category ) {
        this.category = category;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy" );
        return sdf.format( date );
    }

    @SuppressWarnings( "deprecation" )
    public String getMois() {
        return String.valueOf( this.date.getMonth() );

    }

    public void setDate( Date date ) {
        this.date = date;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

}
