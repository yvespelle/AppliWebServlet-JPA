/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controleur;

import com.solutec.EmployesConstantes;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author esic
 */
@Stateless
public class InfoConn {
    
    Employes employes;

    @PersistenceContext(unitName = "AppliWebServlet_JPAPU")
    private EntityManager em;

    public Collection getUtilisateur() {
        Query q = em.createQuery(EmployesConstantes.GET_UTILISATEUR);
        return q.getResultList();
    }

        
    public Collection getEmployes() {
        Query q = em.createQuery(EmployesConstantes.REQ_SELECT_TOUS);
        return q.getResultList();

    }

    public void modifierEmployes(employes) {
        employes = new Employes();
        em.persist(employes);
    }
    
    public void persist(Object object) {
        em.persist(object);
    }
}
