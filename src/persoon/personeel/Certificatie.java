package persoon.personeel;

/**
 * Certificatie is een class die gebruikt wordt om certificatie in de Euromoon applicatie voor te stellen
 *
 * @author Ilias Ben-Fodda
 *
 */
public class Certificatie {
    private final String naam;

    public Certificatie(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

}
