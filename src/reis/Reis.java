package reis;

import persoon.personeel.Steward;
import persoon.personeel.TreinBestuurder;
import trein.Trein;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Reis is een class die gebruikt wordt om reizen in de Euromoon applicatie voor te stellen
 *
 * @author Ilias Ben-Fodda
 *
 */
public class Reis {
    private final String vertrekStation;
    private final String eindStation;
    private Trein trein;
    private final LocalDateTime vertrekTijdstip;
    private final Set<TreinBestuurder> bestuurders;
    private final Set<Steward> stewards;

    public Reis(String vertrekStation, String eindStation, LocalDateTime vertrekTijdstip, Set<TreinBestuurder> bestuurders, Set<Steward> stewards) {
        if (bestuurders.isEmpty()) {
            throw new IllegalArgumentException("Er moet ten minste 1 bestuurder zijn");
        }
        if (stewards.size() < 3) {
            throw new IllegalArgumentException("Er moeten ten minste 3 stewards zijn");
        }
        this.vertrekStation = vertrekStation;
        this.eindStation = eindStation;
        this.vertrekTijdstip = vertrekTijdstip;
        this.bestuurders = bestuurders;
        this.stewards = stewards;
    }

    public String getVertrekStation() {
        return vertrekStation;
    }

    public Set<Steward> getStewards() {
        return stewards;
    }

    public String getEindStation() {
        return eindStation;
    }

    public Trein getTrein() {
        return trein;
    }

    public LocalDateTime getVertrekTijdstip() {
        return vertrekTijdstip;
    }

    public Set<TreinBestuurder> getBestuurders() {
        return bestuurders;
    }

    /**
     * Een methode om een trein toe te voegen aan een reis
     *
     * @param trein De trein die je wil toevoegen
     */
    public void voegTreinToe(Trein trein) {
        this.trein = trein;
    }
}
