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
    
    public void ajouterUtilisateur(Utilisateur utilisateur){
        Utilisateur user = new Utilisateur();
        user.setLogin(utilisateur.getLogin());
        user.setPassword(utilisateur.getPassword());
        Query q = em.createNativeQuery(EmployesConstantes.REQ_ADD_UTILISATEUR);
//        this.persist(utilisateur);
        q.executeUpdate();

    }
    
    public Collection getEmployes() {
        Query q = em.createQuery(EmployesConstantes.REQ_SELECT_TOUS);
        return q.getResultList();
        
    }
    
    public void modifierEmployes(Employes emp, int idEmp) {
        Employes employes = (Employes) em.find(Employes.class, idEmp);
        employes.setNom(emp.getNom());
        employes.setPrenom(emp.getPrenom());
        employes.setAdresse(emp.getAdresse());
        employes.setEmail(emp.getEmail());
        employes.setCodepostal(emp.getCodepostal());
        employes.setVille(emp.getVille());
        employes.setTeldom(emp.getTeldom());
        employes.setTelport(emp.getTelport());
        employes.setTelpro(emp.getTelpro());
        em.persist(employes);
    }
    
    public Employes getDetails(String idEmp) {
        Integer idEmpInteger = Integer.valueOf(idEmp); // Conversion de String en Integer
        Query q = em.createQuery(EmployesConstantes.REQ_SELECT_EMPLOYE);
        //on doit faire un set du parametre sur l'objet query
        q.setParameter("idClient", idEmpInteger);
        return (Employes) q.getSingleResult();
    }
    
    public void supprimerEmploye(String idEmp) {
        Integer idEmpInteger = Integer.valueOf(idEmp);
        Query q = em.createQuery(EmployesConstantes.REQ_SUPPRIMER);
        q.setParameter("idClient", idEmpInteger);
        q.executeUpdate();
    }
    
    public void ajouterEmployes(Employes emp) {
        Employes employes = new Employes();
        employes.setNom(emp.getNom());
        employes.setPrenom(emp.getPrenom());
        employes.setAdresse(emp.getAdresse());
        employes.setEmail(emp.getEmail());
        employes.setCodepostal(emp.getCodepostal());
        employes.setVille(emp.getVille());
        employes.setTeldom(emp.getTeldom());
        employes.setTelport(emp.getTelport());
        employes.setTelpro(emp.getTelpro());
        Query q = em.createNativeQuery(EmployesConstantes.REQ_ADD_EMPLOYE);
        this.persist(emp);
        
    }

//    }
    public void persist(Object object) {
        em.persist(object);
    }
}
