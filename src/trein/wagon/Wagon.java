package trein.wagon;

/**
 * Wagon is een class die gebruikt wordt om wagonen in de Euromoon applicatie voor te stellen
 *
 * @author Ilias Ben-Fodda
 *
 */
public class Wagon {
    private final WagonKlasse wagonKlasse;

    public Wagon(WagonKlasse wagonKlasse) {
        this.wagonKlasse = wagonKlasse;
    }

    public WagonKlasse getWagonKlasse() {
        return wagonKlasse;
    }
}
