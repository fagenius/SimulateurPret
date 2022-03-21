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

//Notre servlet doit h�riter de HttpServlet
//L'annotation @WebServlet permet de d�ployer un servlet
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
		 * Lire les donn�es de la requ�te
		 * **/		
		double montant = Double.parseDouble(request.getParameter("montant"));
		double taux = Double.parseDouble(request.getParameter("taux"));
		int duree = Integer.parseInt(request.getParameter("duree"));
		/*
		 * Valider les donn�es
		 * **/
		
		/*
		 * Stocker les donn�es dans mod�les
		 * **/
		CreditModel model = new CreditModel();
		model.setMontant(montant);
		model.setTaux(taux);
		model.setDuree(duree);
		/*
		 * Faire un appel � la couche metier pour effectuer les traitements 
		 * **/
		double resultat = metier.calculerMensualiteCredit(montant, taux, duree);
		/*
		 * Stocker les r�sultats dans le mod�le
		 * **/
		model.setMensualite(resultat);
		/*
		 * Stocker le mod�le dans l'objet request
		 * **/
		
		request.setAttribute("creditModel", model);
		
		/*
		 * Faire un forword vers la vue JSP
		 * **/
		request.getRequestDispatcher("VueCredit.jsp").forward(request, response);
	}
}

