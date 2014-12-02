package Outils;


import java.util.LinkedList;
import java.util.Vector;

import Modele.*;


public class Dijkstra {
	private Noeud origine;
	private Noeud destination;
	private Plan plan;
	public static double MatAdjacence[][];
	
	

	// calcul de la matrice d'adjacence 
	
	public static double [][] calculeMatriceAdjacence(Plan plan)
	{ 
		 int Size_Matrice = plan.getListeNoeuds().size();
		  MatAdjacence = new double [Size_Matrice][Size_Matrice] ; 
		 for(int i=0; i<Size_Matrice; i++)
		 {
			 MatAdjacence[i][i] = 0;  
		 }
		
		 int Nb_Troncon = plan.getListeTroncons().size();
		 Vector<Troncon> liste_Troncons = plan.getListeTroncons();
		 
		 // remplissage de la matrice par la valeur des dur�es des troncons ;
		 
		 for(int i =0; i<liste_Troncons.size();i++)
		 {
			 MatAdjacence[liste_Troncons.get(i).getDepart().getIdNoeud()][liste_Troncons.get(i).getDepart().getIdNoeud()]=liste_Troncons.get(i).getTemps(); ;
			 
		 }
		 return MatAdjacence;
	}
	
	
	// extraire le min 
	
	public static int ExtraireMin(double [] distancecc, boolean [] marque){
        double x =Integer.MAX_VALUE; 
        int y=0;
 
        for (int i = 0; i < distancecc.length; i++) {
             if (!marque[i] && distancecc[i]< x) {
 
                y=i;
                x=distancecc[i];
 
            }
 
        }      
 
        return y;
    }
	
	
	// retroune un tableau int des sommets adjacents d'un sommet 
	
	  public static int[] ChercheVoisin(int idnoeud, Plan plan){				//construit un tableau des sommets adjacents d'un sommet 
	         
          int count=0;
         
         // double [][] MatAdjacence = calculeMatriceAdjacence(plan);
          
          
          for (int i = 0; i < MatAdjacence[idnoeud].length; i++) {
              if (MatAdjacence[idnoeud][i]>0 ) {count ++; }
          }
              final int[]rep = new int [count];      
         
          count=0;    
          for (int i = 0; i < MatAdjacence[idnoeud].length; i++){
               if(MatAdjacence[idnoeud][i]>0){rep[count++]=i;}        
          }     
          return rep;
        }
         
	//////// cherche voisin 
	/*
	public Noeud[] ChercherVoisins (Noeud noeudCourant)
	{
		Noeud [] listeVoisins  = new Noeud[plan.getListeNoeuds().size()];
		Vector<Troncon> listeSortants = noeudCourant.getTronconSortant();
		for(int i=0;i<listeSortants.size();i++)
		{
			listeVoisins[i] = listeSortants.get(i).getArrivee();
		}
		
		return listeVoisins;
	}
	
	*/
	  
	  
	  // avec destination 
	  
	  
	  public static int[] calculer_cours_chemins(Plan plan, int Source){
	         
          final double [] distancecc = new double [plan.getListeNoeuds().size()];
          final int [] pred = new int [plan.getListeNoeuds().size()];
          final boolean [] marque = new boolean [plan.getListeNoeuds().size()];
   
          for (int i = 0; i < distancecc.length; i++) {
              distancecc[i]=Integer.MAX_VALUE;
              }
          distancecc[Source]=0;
   
          for (int i = 0; i < distancecc.length; i++) {
   
              final int U= ExtraireMin (distancecc, marque);
              marque[U]=true;
   
              final int [] V= ChercheVoisin(U,plan);
              for (int j = 0; j < V.length ; j++) {
                  final int NV = V [j];
                  
                  // attention 
                  
                  final double d = distancecc[U] + MatAdjacence[U][NV];   //IG.GetPoids(U, NV);
                  if (d < distancecc[NV]) {
                      distancecc[NV]=d;
                      pred[NV]=U;
                     }
              }
   
           }
          
          
          //return Cour_chemin;
          
         return pred;
   
      }
	
	
	  
	  public static LinkedList Get_Short_Path(Plan plan,int idsource , int iddestination )
	  { 
		  
            int [] Pred = calculer_cours_chemins(plan , idsource);
	  
	        for (int n = 0; n < plan.getListeNoeuds().size(); n++) 
	  
	      {                          
                int x=n;
                if(x==iddestination)
                {
                 LinkedList cheminpc =new LinkedList();
                while (x!=idsource){
                    cheminpc.add(0,x);
                    x=Pred[x];    
                   //System.out.println("hahowa chemin" +cheminpc +" hahiya valeur de " + x);
                }
                cheminpc.add(0,idsource);
                System.out.println(cheminpc);
              return cheminpc;
                }
                
                LinkedList cheminpc =new LinkedList();
                return cheminpc;
                
                //for (int i = 0; i < pred.length ; i++)
                	// System.out.println("la valeur de pred " + pred[i]);
            }
			return null;
	 
	        	
	       
	  }
	   
		  
	
	  
	  
	public Dijkstra(Plan plan, Noeud origine, Noeud destination)
	{
		this.plan = plan;
		this.origine = origine;
		this.destination = destination;
	}
	
	
}
	
	
	
	
	/*
	
	
	
	////////////////////////////****************************************////////////////////////////////*************************////////////*/
	/*
	public Noeud[] Calcul()
	{
		Noeud [] chemin = new Noeud[plan.getListeTroncons().size()];
		double [] listeDistanceMinEntreSourceEtN = new double [plan.getListeNoeuds().size()];
		
		//Initialisation tableau des distances minimales �  l'infini
		for (int i=0; i<listeDistanceMinEntreSourceEtN.length; i++)
		{
			listeDistanceMinEntreSourceEtN[i] = Integer.MAX_VALUE;
		}
		
		boolean []  distanceMinTrouvee = new boolean [plan.getListeNoeuds().size()];
		for(int i=0; i<listeDistanceMinEntreSourceEtN.length; i++)
		{
			Noeud noeudCourant = plan.getNoeudbyID(i);
			int idNoeudMin = ExtraireMin(listeDistanceMinEntreSourceEtN, distanceMinTrouvee);
			Noeud noeudMin = plan.getNoeudbyID(idNoeudMin);
			distanceMinTrouvee[i]=true;
			
		//	Noeud [] listeVoisins = ChercherVoisins( noeudMin );
		//	for (int j=0; j<listeVoisins.length; j++)
			{
				Noeud voisinCourant = listeVoisins[j];
				double poidsTotal = listeDistanceMinEntreSourceEtN[idNoeudMin] + plan.getTroncon(noeudCourant,noeudMin).getTemps();
				if (poidsTotal < listeDistanceMinEntreSourceEtN[idNoeudMin]) {
					listeDistanceMinEntreSourceEtN[idNoeudMin] = poidsTotal;
				}
			}
		}
		return chemin;
	}
}
	
	
	*/
	
	/*public int ExtraireMin(double[] listeDistanceMinEntreSourceEtN, boolean [] distanceMinTrouvee)
	{
		double distanceMin = Integer.MAX_VALUE;
		int idNoeudMin =0;
		
		for (int i=0; i< listeDistanceMinEntreSourceEtN.length;i++)
		{
			if(!distanceMinTrouvee[i] && listeDistanceMinEntreSourceEtN[i]<idNoeudMin) {
				idNoeudMin=i;
				distanceMin = listeDistanceMinEntreSourceEtN[i];
			}
		}
		return idNoeudMin;
	}
}
*/
///////////////************************

/* sans destination 
 * 
 * 
 * public static int [] djikstra (Plan plan, int Source, int destination){
	         
          final double [] distancecc = new double [plan.getListeNoeuds().size()];
          final int [] pred = new int [plan.getListeNoeuds().size()];
          final boolean [] marque = new boolean [plan.getListeNoeuds().size()];
   
          for (int i = 0; i < distancecc.length; i++) {
              distancecc[i]=Integer.MAX_VALUE;
              }
          distancecc[Source]=0;
   
          for (int i = 0; i < distancecc.length; i++) {
   
              final int U= ExtraireMin (distancecc, marque);
              marque[U]=true;
   
              final int [] V= ChercheVoisin(U,plan);
              for (int j = 0; j < V.length ; j++) {
                  final int NV = V [j];
                  
                  // attention 
                  
                  final double d = distancecc[U] + MatAdjacence[U][NV];   //IG.GetPoids(U, NV);
                  if (d < distancecc[NV]) {
                      distancecc[NV]=d;
                      pred[NV]=U;
                     }
              }
   
           }
   
          return pred;
   
      }
	
	
	public Dijkstra(Plan plan, Noeud origine, Noeud destination)
	{
		this.plan = plan;
		this.origine = origine;
		this.destination = destination;
	}
	
	
}
 */

