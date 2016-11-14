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
