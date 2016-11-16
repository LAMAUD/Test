package fr.micropole.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ImportCSVTransaction implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2915054633829657547L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer           id;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date              date;

    private String            numeroOperation;

    private String            libelle;

    private Double            debit;

    private Double            credit;

    private String            Detail;

    public ImportCSVTransaction() {
        super();
    }

    public ImportCSVTransaction( Date date, String numeroOperation, String libelle, Double debit,
            Double credit, String detail ) {
        super();

        this.date = date;
        this.numeroOperation = numeroOperation;
        this.libelle = libelle;
        this.debit = debit;
        this.credit = credit;
        Detail = detail;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( Detail == null ) ? 0 : Detail.hashCode() );
        result = prime * result + ( ( credit == null ) ? 0 : credit.hashCode() );
        result = prime * result + ( ( date == null ) ? 0 : date.hashCode() );
        result = prime * result + ( ( debit == null ) ? 0 : debit.hashCode() );
        result = prime * result + ( ( libelle == null ) ? 0 : libelle.hashCode() );
        result = prime * result + ( ( numeroOperation == null ) ? 0 : numeroOperation.hashCode() );
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
        ImportCSVTransaction other = (ImportCSVTransaction) obj;
        if ( Detail == null ) {
            if ( other.Detail != null )
                return false;
        } else if ( !Detail.equals( other.Detail ) )
            return false;
        if ( credit == null ) {
            if ( other.credit != null )
                return false;
        } else if ( !credit.equals( other.credit ) )
            return false;
        if ( date == null ) {
            if ( other.date != null )
                return false;
        } else if ( !date.equals( other.date ) )
            return false;
        if ( debit == null ) {
            if ( other.debit != null )
                return false;
        } else if ( !debit.equals( other.debit ) )
            return false;
        if ( libelle == null ) {
            if ( other.libelle != null )
                return false;
        } else if ( !libelle.equals( other.libelle ) )
            return false;
        if ( numeroOperation == null ) {
            if ( other.numeroOperation != null )
                return false;
        } else if ( !numeroOperation.equals( other.numeroOperation ) )
            return false;
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    public String getNumeroOperation() {
        return numeroOperation;
    }

    public void setNumeroOperation( String numeroOperation ) {
        this.numeroOperation = numeroOperation;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle( String libelle ) {
        this.libelle = libelle;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit( Double credit ) {
        this.credit = credit;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail( String detail ) {
        Detail = detail;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit( Double debit ) {
        this.debit = debit;
    }

}
