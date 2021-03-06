/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutec;

/**
 *
 * @author esic
 */
public class EmployesConstantes {

    public static final String GET_UTILISATEUR = "SELECT u FROM Utilisateur u";

    public static final String REQ_SELECT_TOUS = "SELECT e from Employes e";

    public static final String REQ_SUPPRIMER = "DELETE from Employes e WHERE e.id = :idClient";
    public static final String REQ_SELECT_EMPLOYE = "SELECT e FROM Employes e WHERE e.id = :idClient";
    public static final String REQ_MODIF_EMPLOYE = "UPDATE Employes e SET e.nom = :nomEmp, e.prenom = :prenomEmp, e.adresse = :adresseEmp, e.email = :emailEmp WHERE e.id = :idClient";

    public static final String REQ_ADD_EMPLOYE = "INSERT INTO EMPLOYES(NOM, PRENOM, TELDOM,  TELPRO, ADRESSE, CODEPOSTAL, VILLE, EMAIL) VALUES (?,?,?,?,?,?,?,?)";
    
    public static final String REQ_ADD_UTILISATEUR = "INSERT INTO UTILISATEUR(LOGIN, PASSWORD) VALUES (?,?)";

    public static final String ACTION = "action";
    public static final String ACTION_SUPPRIMER = "Supprimer";
    public static final String ACTION_DETAILS = "Details";
    public static final String ACTION_MODIFIER = "Modifier";
    public static final String ACTION_VOIR_LISTE = "Voir liste";
    public static final String ACTION_LOGIN = "Login";
    public static final String ACTION_QUITTER = "Quitter";
    public static final String ACTION_AJOUTER = "Ajouter";
    public static final String ACTION_ANNULER = "Annuler";
    public static final String ACTION_INSERER = "Inserer";

    public static final String PAGE_INDEX = "/WEB-INF/vue/accueil.jsp";
    public static final String PAGE_CONTROLEUR = "Controleur";
    public static final String PAGE_TABLEAU = "/WEB-INF/vue/tableau.jsp";
    public static final String PAGE_DETAIL_EMPLOYE = "/WEB-INF/vue/DetailEmploye.jsp";
    public static final String PAGE_AJOUTER="/WEB-INF/vue/ajouter.jsp";

    //Messages d'erreur
    public static final String ERREUR_SAISIE_VIDE = "Vous devez renseigner les deux champs";
    public static final String ERREUR_INFOS_CONN_KO = "Echec de la connexion! Verifiez votre login et/ou mot de passe et essayez à nouveau.";
    public static final String ERREUR_SUPPR = "Echec lors de la suppression! Vous avez bien sélectionné un membre?";
    public static final String SUCCES_SUPPR = "Suppression réussie!";
    public static final String ERREUR_AUCUNE_LIGNE_SELECT = "Vous n'avez sélectionné aucun employé!";

}
