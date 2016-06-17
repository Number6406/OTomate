/**
 *
 */
package Otomate.historique;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alicia Un historique permet tout d'abord d'afficher à l'ecran le
 * résumé des actions, tour par tour Mais permettrait éventuellement de retracer
 * une partie (backwards, move forward) Un historique est une liste d'actions,
 * ordonnées temporellement et par tour... ?
 */
public class Historique {

    // Attributs //
    private List<Tour> partie; // Liste des tours
    private int tourCourant; // Numéro du tour courant

    public Historique() {
        this.tourCourant = 0;
        this.partie = new ArrayList<Tour>();
    }

    public void addTour() {
        tourCourant++;
        partie.add(new Tour(tourCourant));
    }

    public Tour ceTour() {
        return partie.get(tourCourant - 1);
    }

    /**
     *
     * @param i le numéro de tour à récupérer
     * @return LE tour i
     * @require TourExistant : i <= nbTour() && i>0
     */
    public Tour getTour(int i) throws IOException {
        if (!(i <= nbTour() && i > 0)) {
            throw new IOException("TourExistant");
        }
        return partie.get(i - 1);
    }

    public int nbTour() {
        return partie.size();
    }

    public String toString() {
        String s = "Historique : \n";
        int i = 1;
        for (Tour t : partie) {
            s += "Tour " + i + "\n" + t.toString();
            i++;
        }
        return s;
    }

}
