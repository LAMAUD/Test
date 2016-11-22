package fr.micropole.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CategorisationLibelle implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6325752856537359157L;

    public CategorisationLibelle() {
        super();
    }

    public CategorisationLibelle( Integer id, String libelle, Category category ) {
        super();
        this.id = id;
        this.libelle = libelle;
        this.category = category;
    }

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;

    @Override
    public String toString() {
        return "CategorisationLibelle [id=" + id + ", libelle=" + libelle + ", category=" + category + "]";
    }

    private String   libelle;

    @JoinColumn( name = "category_id", referencedColumnName = "id", nullable
            = false )
    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle( String libelle ) {
        this.libelle = libelle;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory( Category category ) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( category == null ) ? 0 : category.hashCode() );
        result = prime * result + ( ( libelle == null ) ? 0 : libelle.hashCode() );
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
        CategorisationLibelle other = (CategorisationLibelle) obj;
        if ( category == null ) {
            if ( other.category != null )
                return false;
        } else if ( !category.equals( other.category ) )
            return false;
        if ( libelle == null ) {
            if ( other.libelle != null )
                return false;
        } else if ( !libelle.equals( other.libelle ) )
            return false;
        return true;
    }

}
