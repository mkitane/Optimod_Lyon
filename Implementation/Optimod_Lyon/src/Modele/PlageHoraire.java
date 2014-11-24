package Modele;

import org.w3c.dom.Element;

/**
 * 
 */
public class PlageHoraire {

    /**
     * 
     */
    public PlageHoraire() {
    }

    /**
     * 
     */
    private String heureDebut;

    /**
     * 
     */
    private String heureFin;


    /**
     * @param Element XMLnode
     */
    public void initPlage(Element XMLnode) {
        this.heureDebut = XMLnode.getAttribute("heureDebut");
        this.heureFin = XMLnode.getAttribute("heureFin");
    }


	/**
	 * @return the heureDebut
	 */
	public String getHeureDebut() {
		return heureDebut;
	}


	/**
	 * @return the heureFin
	 */
	public String getHeureFin() {
		return heureFin;
	}
    

}