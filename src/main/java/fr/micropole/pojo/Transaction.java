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

    public void setDate( Date date ) {
        this.date = date;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

}
