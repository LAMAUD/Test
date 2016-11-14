package fr.micropole.dao.impl;

import org.springframework.stereotype.Repository;

import fr.micropole.dao.DAOUser;
import fr.micropole.pojo.User;

@Repository
public class DAOImplUser extends GenericDAOImpl<User> implements DAOUser {

    public DAOImplUser() {
        super();
        this.type = new User();
        // TODO Auto-generated constructor stub

    }
}
