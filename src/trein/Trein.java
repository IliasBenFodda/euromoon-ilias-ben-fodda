package trein;

import trein.locomotief.Locomotief;
import trein.wagon.Wagon;

import java.util.ArrayList;
import java.util.List;

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

    public void voegWagonToe(Wagon wagon) {
        if (wagons.size() == locomotief.getLocomotiefType().getMaximumAantalWagons()) {
            throw new IllegalArgumentException("Deze locomotief heeft al het maximum aantal wagons.");
        }
        this.wagons.add(wagon);
    }
}
