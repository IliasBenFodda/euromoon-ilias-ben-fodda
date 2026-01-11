package ticket;

import persoon.passagier.Passagier;
import reis.Reis;
import trein.wagon.Wagon;
import trein.wagon.WagonKlasse;

/**
 * Ticket is een class die gebruikt wordt om tickets in de Euromoon applicatie voor te stellen
 *
 * @author Ilias Ben-Fodda
 *
 */
public class Ticket {
    private final Passagier ticketHouder;
    private final Reis reis;
    private final WagonKlasse wagonKlasse;

    public Ticket(Passagier ticketHouder, Reis reis, WagonKlasse wagonKlasse) {
        this.ticketHouder = ticketHouder;
        this.reis = reis;
        this.wagonKlasse = wagonKlasse;
        this.reis.voegTicketToe(this);
    }

    public Passagier getTicketHouder() {
        return ticketHouder;
    }

    public Reis getReis() {
        return reis;
    }

    public WagonKlasse getWagonKlasse() {
        return wagonKlasse;
    }
}
