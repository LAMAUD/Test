package fr.micropole.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import fr.micropole.dao.DAOTransaction;
import fr.micropole.pojo.Transaction;

@Repository
public class DAOImplTransaction extends GenericDAOImpl<Transaction> implements DAOTransaction {

    public DAOImplTransaction() {
        super();
        this.type = new Transaction();
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public List<Transaction> readTransactionBetweenDate( Date dateDebut, Date dateFin ) {

        return this.entityManager
                .createQuery( "SELECT t FROM Transaction t WHERE t.date BETWEEN :dateDebut AND :dateFin" )
                .setParameter( "dateDebut", dateDebut )
                .setParameter( "dateFin", dateFin )
                .getResultList();
    }

}
