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

//    public static final String URL = "jdbc:derby://localhost:1527/BaseTP";
//    public static final String USER = "jee";
//    public static final String MDP = "jee";
    public static final String GET_UTILISATEUR = "SELECT u FROM Utilisateur u";

    public static final String REQ_SELECT_TOUS = "SELECT e from Employes e";
    public static final String URL2 = "jdbc:derby://localhost:1527/solutec";
    public static final String USER2 = "test";
    public static final String MDP2 = "test";

    public static final String REQ_SUPPRIMER = "DELETE from EMPLOYES where ID=?";
    public static final String REQ_4 = "INSERT INTO EMPLOYES (NOM, PRENOM, ADRESSE) VALUES '?','?','?','?'";
    public static final String REQ_SELECT_EMPLOYE = "SELECT * from EMPLOYES where ID=?";
    public static final String REQ_MODIF_EMPLOYE = "UPDATE EMPLOYES SET NOM=?, PRENOM=?, ADRESSE=?, EMAIL=? where ID =?";
    public static final String REQ_ADD_EMPLOYE = "INSERT INTO EMPLOYES(NOM, PRENOM, TELDOM, TELPORT, TELPRO, ADRESSE, CODEPOSTAL, VILLE, EMAIL) VALUES (?,?,?,?,?,?,?,?,?)";

    public static final String ACTION = "action";
    public static final String ACTION_SUPPRIMER = "Supprimer";
    public static final String ACTION_DETAILS = "Details";
    public static final String ACTION_MODIFIER = "Modifier";
    public static final String ACTION_VOIR_LISTE = "Voir liste";
    public static final String ACTION_LOGIN = "Login";
    public static final String ACTION_QUITTER = "Quitter";
    public static final String ACTION_AJOUTER = "Ajouter";
    public static final String ACTION_ANNULER = "Annuler";
    public static final String ACTION_INSERER= "Inserer";

    public static final String PAGE_INDEX = "accueil.jsp";
    public static final String PAGE_CONTROLEUR = "Controleur";
    public static final String PAGE_TABLEAU = "tableau.jsp";
    public static final String PAGE_DETAIL_EMPLOYE = "DetailEmploye.jsp";
    
     //Messages d'erreur
    public static final String ERREUR_SAISIE_VIDE = "Vous devez renseigner les deux champs";
    public static final String ERREUR_INFOS_CONN_KO = "Echec de la connexion! Verifiez votre login et/ou mot de passe et essayez à nouveau.";
    public static final String ERREUR_SUPPR = "Echec lors de la suppression! Vous avez bien sélectionné un membre?";
    public static final String SUCCES_SUPPR = "Suppression réussie!";
    public static final String ERREUR_AUCUNE_LIGNE_SELECT = "Vous n'avez sélectionné aucun employé!";

}
