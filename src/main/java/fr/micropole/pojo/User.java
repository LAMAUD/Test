package fr.micropole.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 845517408699767054L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer           id;

    @NotEmpty
    private String            name;

    @NotEmpty
    private String            password;

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

}
