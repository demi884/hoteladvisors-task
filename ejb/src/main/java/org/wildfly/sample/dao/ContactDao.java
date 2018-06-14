package org.wildfly.sample.dao;


import org.wildfly.sample.dto.Contact;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ContactDao {
    @PersistenceContext
    private EntityManager em;

    public List<Contact> findAll() {
        return em.createNamedQuery(Contact.Query.FIND_ALL, Contact.class).getResultList();
    }

    public void create(final Contact contact) {
        em.persist(contact);
    }

}

