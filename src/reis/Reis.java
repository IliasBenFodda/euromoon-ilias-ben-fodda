package reis;

import persoon.personeel.Steward;
import persoon.personeel.TreinBestuurder;
import ticket.Ticket;
import trein.Trein;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private final List<Ticket> verkochteTickets;

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
        this.verkochteTickets = new ArrayList<>();
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

    /**
     * Een methode om een ticket toe te voegen aan een reis
     *
     * @param ticket Het ticket dat je wil toevoegen
     * @throws IllegalArgumentException wanneer het maximum aantal tickets al verkocht is
     */
    public void voegTicketToe(Ticket ticket) {
        if (this.verkochteTickets.size() == this.trein.getLocomotief().getLocomotiefType().getCapaciteit()) {
            throw new IllegalArgumentException("Deze trein is volgeboekt");
        }
        this.verkochteTickets.add(ticket);
    }
}
