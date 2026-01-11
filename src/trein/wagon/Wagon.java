package trein.wagon;

public class Wagon {
    private final WagonKlasse wagonKlasse;

    public Wagon(WagonKlasse wagonKlasse) {
        this.wagonKlasse = wagonKlasse;
    }

    public WagonKlasse getWagonKlasse() {
        return wagonKlasse;
    }
}
