/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutec;

import com.controleur.Employes;
import com.controleur.InfoConn;
import com.controleur.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;

/**
 *
 * @author esic
 */
public class Controleur extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private InfoConn infoConn;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            String action;
            String cleEmp;
            Employes employes;

            String loginSaisie = request.getParameter("login");
            String mdpSaisie = request.getParameter("mdp");
            String mdp1Saisie = request.getParameter("mdp1");
            String mdp2Saisie = request.getParameter("mdp2");

            if (loginSaisie != null && mdpSaisie != null) {

                ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<>();
                listeUtilisateurs.addAll(infoConn.getUtilisateur());

                for (Utilisateur user : listeUtilisateurs) {

                    if (loginSaisie.equals(user.getLogin()) && mdpSaisie.equals(user.getPassword())) {

                        ArrayList<Employes> listeEmployes = new ArrayList<>();
                        listeEmployes.addAll(infoConn.getEmployes());
                        request.setAttribute("cleListe", listeEmployes);
                        request.getRequestDispatcher(EmployesConstantes.PAGE_TABLEAU).forward(request, response);

                    } else if (loginSaisie.equals("") || mdpSaisie.equals("")) {
                        request.setAttribute("cleMessageErreur", EmployesConstantes.ERREUR_SAISIE_VIDE);
                        request.getRequestDispatcher(EmployesConstantes.PAGE_INDEX).forward(request, response);

                    } else {
                        request.setAttribute("cleMessageErreur", EmployesConstantes.ERREUR_INFOS_CONN_KO);
                        request.getRequestDispatcher(EmployesConstantes.PAGE_INDEX).forward(request, response);

                    }
                }

            }

            String btn = request.getParameter("bouton");
            //String cleId = request.getParameter("idClient");

            if (btn == null) {
                request.getRequestDispatcher(EmployesConstantes.PAGE_INDEX).forward(request, response);
            }
            if (btn != null) {
                switch (btn) {

                    case ("Inscription"):
                        //Nouvelle inscription
                        if (mdp1Saisie.equals(request.getParameter("mdp2"))) {
                            Utilisateur user = new Utilisateur();
                            user.setLogin(request.getParameter("login"));
                            user.setPassword(request.getParameter("mdp1"));
                            infoConn.ajouterUtilisateur(user);
                            request.getRequestDispatcher(EmployesConstantes.PAGE_INDEX).forward(request, response);
                            break;

                        }

                    case (EmployesConstantes.ACTION_SUPPRIMER):
                        cleEmp = request.getParameter("idClient");
                        infoConn.supprimerEmploye(cleEmp);

                        ArrayList<Employes> listeEmployes = new ArrayList<>();
                        listeEmployes.addAll(infoConn.getEmployes());
                        request.setAttribute("cleListe", listeEmployes);

                        request.getRequestDispatcher(EmployesConstantes.PAGE_TABLEAU).forward(request, response);
                        break;

                    case (EmployesConstantes.ACTION_AJOUTER):
                        request.getRequestDispatcher(EmployesConstantes.PAGE_AJOUTER).forward(request, response);
                        break;

                    case (EmployesConstantes.ACTION_ANNULER):
                        ArrayList<Employes> listeEmployes3 = new ArrayList<>();
                        listeEmployes3.addAll(infoConn.getEmployes());
                        request.setAttribute("cleListe", listeEmployes3);
                        request.getRequestDispatcher(EmployesConstantes.PAGE_TABLEAU).forward(request, response);
                        break;

                    case (EmployesConstantes.ACTION_INSERER):
                        employes = new Employes();

                        //cleEmp = (String) session.getAttribute("cleEmpSession");
                        employes.setNom(request.getParameter("nom"));
                        employes.setPrenom(request.getParameter("prenom"));
                        employes.setTeldom(request.getParameter("teldom"));
//                        employes.setTelport(request.getParameter("telport"));
                        employes.setTelpro(request.getParameter("telpro"));
                        employes.setAdresse(request.getParameter("adresse"));
                        employes.setCodepostal(request.getParameter("codepostal"));
                        employes.setVille(request.getParameter("ville"));
                        employes.setEmail(request.getParameter("email"));

                        //Integer cleEmpInteger2 = Integer.valueOf(cleEmp);
                        infoConn.ajouterEmployes(employes);
                        ArrayList<Employes> listeEmployes4 = new ArrayList<>();
                        listeEmployes4.addAll(infoConn.getEmployes());
                        request.setAttribute("cleListe", listeEmployes4);
                        request.getRequestDispatcher(EmployesConstantes.PAGE_TABLEAU).forward(request, response);

                        break;

                    case (EmployesConstantes.ACTION_DETAILS):
                        employes = new Employes();

                        cleEmp = request.getParameter("idClient");
                        session.setAttribute("cleEmpSession", cleEmp);
                        employes = infoConn.getDetails(cleEmp);
                        request.setAttribute("cleEmp", employes);
                        request.getRequestDispatcher(EmployesConstantes.PAGE_DETAIL_EMPLOYE).forward(request, response);
                        break;

                    case (EmployesConstantes.ACTION_VOIR_LISTE):
                        ArrayList<Employes> listeEmployes2 = new ArrayList<>();
                        listeEmployes2.addAll(infoConn.getEmployes());
                        request.setAttribute("cleListe", listeEmployes2);
                        request.getRequestDispatcher(EmployesConstantes.PAGE_TABLEAU).forward(request, response);
                        break;

                    case (EmployesConstantes.ACTION_MODIFIER):
                        employes = new Employes();

                        cleEmp = (String) session.getAttribute("cleEmpSession");
                        employes.setNom(request.getParameter("nom"));
                        employes.setPrenom(request.getParameter("prenom"));
                        employes.setAdresse(request.getParameter("adresse"));
                        employes.setEmail(request.getParameter("email"));
                        employes.setTeldom(request.getParameter("teldom"));
                        employes.setTelpro(request.getParameter("telpro"));
                        employes.setCodepostal(request.getParameter("codepostal"));
                        employes.setTelport(request.getParameter("telport"));
                        employes.setVille(request.getParameter("ville"));

                        Integer cleEmpInteger = Integer.valueOf(cleEmp);
                        infoConn.modifierEmployes(employes, cleEmpInteger);

                        employes = infoConn.getDetails(cleEmp);
                        request.setAttribute("cleEmp", employes);
                        request.getRequestDispatcher(EmployesConstantes.PAGE_DETAIL_EMPLOYE).forward(request, response);
                        break;

                    case (EmployesConstantes.ACTION_QUITTER):
                        request.getRequestDispatcher(EmployesConstantes.PAGE_INDEX).forward(request, response);
                        break;

                }
            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(Controleur.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(Controleur.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
