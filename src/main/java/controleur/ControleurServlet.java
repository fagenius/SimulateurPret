package controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domaine.CreditModel;
import metier.CreditMetierImpl;
import metier.ICreditMetier;

//Notre servlet doit hériter de HttpServlet
//L'annotation @WebServlet permet de déployer un servlet
@WebServlet(name = "cs", urlPatterns = {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet{
	private ICreditMetier metier;
	@Override
	public void init() throws ServletException {
		metier = new CreditMetierImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("creditModel", new CreditModel());		
		request.getRequestDispatcher("VueCredit.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Lire les données de la requête
		 * **/		
		double montant = Double.parseDouble(request.getParameter("montant"));
		double taux = Double.parseDouble(request.getParameter("taux"));
		int duree = Integer.parseInt(request.getParameter("duree"));
		/*
		 * Valider les données
		 * **/
		
		/*
		 * Stocker les données dans modèles
		 * **/
		CreditModel model = new CreditModel();
		model.setMontant(montant);
		model.setTaux(taux);
		model.setDuree(duree);
		/*
		 * Faire un appel à la couche metier pour effectuer les traitements 
		 * **/
		double resultat = metier.calculerMensualiteCredit(montant, taux, duree);
		/*
		 * Stocker les résultats dans le modèle
		 * **/
		model.setMensualite(resultat);
		/*
		 * Stocker le modèle dans l'objet request
		 * **/
		
		request.setAttribute("creditModel", model);
		
		/*
		 * Faire un forword vers la vue JSP
		 * **/
		request.getRequestDispatcher("VueCredit.jsp").forward(request, response);
	}
}

