package trein;

import trein.locomotief.Locomotief;
import trein.wagon.Wagon;

import java.util.ArrayList;
import java.util.List;

/**
 * Trein is een class die gebruikt wordt om treinen in de Euromoon applicatie voor te stellen
 *
 * @author Ilias Ben-Fodda
 *
 */
public class Trein {
    private final Locomotief locomotief;
    private final List<Wagon> wagons;

    public Trein(Locomotief locomotief) {
        this.locomotief = locomotief;
        this.wagons = new ArrayList<>();
    }

    public Trein(Locomotief locomotief, List<Wagon> wagons) {
        if (wagons.size() > locomotief.getLocomotiefType().getMaximumAantalWagons()) {
            throw new IllegalArgumentException("Het aantal wagons is meer dan toegestaan, maximium aantal is: " + locomotief.getLocomotiefType().getMaximumAantalWagons());
        }
        this.locomotief = locomotief;
        this.wagons = wagons;
    }

    public Locomotief getLocomotief() {
        return locomotief;
    }

    public List<Wagon> getWagons() {
        return wagons;
    }

    /**
     * Een methode om een wagon toe te voegen aan een trein
     *
     * @param wagon De wagon die je wil toevoegen
     * @throws IllegalArgumentException wanneer het maximum aantal wagons bereikt is
     */
    public void voegWagonToe(Wagon wagon) {
        if (wagons.size() == locomotief.getLocomotiefType().getMaximumAantalWagons()) {
            throw new IllegalArgumentException("Deze locomotief heeft al het maximum aantal wagons.");
        }
        this.wagons.add(wagon);
    }
}
