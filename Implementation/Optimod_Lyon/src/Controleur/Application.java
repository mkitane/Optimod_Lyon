package Controleur;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import Modele.*;
import Vue.*;

/**
 * 
 */
public class Application {

    /**
     * 
     */
    public Application() {
    	this.listeAnnulation = new Vector<Action>();
    	this.listeExecution = new Vector<Action>();
    	this.modele = new DataWareHouse();
    	
    }

    /**
     * 
     */
    	private Vector<Action> listeAnnulation;

    /**
     * 
     */
    	private Vector<Action> listeExecution;

    /**
     * 
     */
    	
    	private DataWareHouse modele;
    	
 
    public void chargerDemandeLivraison() {
    	JFileChooser jFileChooserXML = new JFileChooser();
    	FileFilterApp filter = new FileFilterApp();
    	File fichierData = null;
    	
        filter.addExtension("xml");
        filter.setDescription("Fichier XML");
        jFileChooserXML.setFileFilter(filter);
        jFileChooserXML.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal;
        	returnVal = jFileChooserXML.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) 
               fichierData = new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
        else
        	System.out.println("canceled");
        if (fichierData != null) {
            try {
                // creation d'un constructeur de documents a l'aide d'une fabrique
               DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
               // lecture du contenu d'un fichier XML avec DOM
               Document document = constructeur.parse(fichierData);
               Element racine = document.getDocumentElement();
               if (racine.getNodeName().equals("JourneeType")) 
               {
                  modele.initMapLivraison(racine);
               }
               else
               {
            	   
               }
           // todo : traiter les erreurs
           } catch (ParserConfigurationException pce) {
               System.out.println("Erreur de configuration du parseur DOM");
               System.out.println("lors de l'appel a fabrique.newDocumentBuilder();");
           } catch (SAXException se) {
               System.out.println("Erreur lors du parsing du document");
               System.out.println("lors de l'appel a construteur.parse(xml)");
           } catch (IOException ioe) {
               System.out.println("Erreur d'entree/sortie");
               System.out.println("lors de l'appel a construteur.parse(xml)");
           }
       } 
    }

    /**
     * 
     */
    public void chargerPlan() {
    	JFileChooser jFileChooserXML = new JFileChooser();
    	FileFilterApp filter = new FileFilterApp();
    	File fichierData = null;
    	
        filter.addExtension("xml");
        filter.setDescription("Fichier XML");
        jFileChooserXML.setFileFilter(filter);
        jFileChooserXML.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal;
        	returnVal = jFileChooserXML.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) 
               fichierData = new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
        else
        	System.out.println("canceled");
        if (fichierData != null) {
            try {
                // creation d'un constructeur de documents a l'aide d'une fabrique
               DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
               // lecture du contenu d'un fichier XML avec DOM
               Document document = constructeur.parse(fichierData);
               Element racine = document.getDocumentElement();
               if (racine.getNodeName().equals("Reseau")) 
               {
                   // appel des initialiseurs
            	   modele.initDataPlan(racine);
               }
               else
               {
            	   
               }
           // todo : traiter les erreurs
           } catch (ParserConfigurationException pce) {
               System.out.println("Erreur de configuration du parseur DOM");
               System.out.println("lors de l'appel a fabrique.newDocumentBuilder();");
           } catch (SAXException se) {
               System.out.println("Erreur lors du parsing du document");
               System.out.println("lors de l'appel a construteur.parse(xml)");
           } catch (IOException ioe) {
               System.out.println("Erreur d'entree/sortie");
               System.out.println("lors de l'appel a construteur.parse(xml)");
           }
       } 
    }

    /**
     * 
     */
    public void gererCommande() {
        // TODO implement here
    }

    /**
     * 
     */
    public void initApplication() {
        // TODO implement here
    }
    
    public static void main(String[] arg)
    {
    	Application commandCenter = new Application();
    	commandCenter.initApplication();

    	
    }

	/**
	 * @return the listeAnnulation
	 */
	public Vector<Action> getListeAnnulation() {
		return listeAnnulation;
	}

	/**
	 * @return the listeExecution
	 */
	public Vector<Action> getListeExecution() {
		return listeExecution;
	}

	/**
	 * @return the modele
	 */
	public DataWareHouse getModele() {
		return modele;
	}


}