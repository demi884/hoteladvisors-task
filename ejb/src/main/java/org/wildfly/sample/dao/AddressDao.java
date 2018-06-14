package org.wildfly.sample.dao;

import org.wildfly.sample.dto.Address;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AddressDao {
    @PersistenceContext
    private EntityManager em;

    public List<Address> findAll() {
        return em.createNamedQuery(Address.Query.FIND_ALL, Address.class).getResultList();
    }

    public void create(final Address address) {
        em.persist(address);
    }
}
