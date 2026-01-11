package persoon.personeel;

import java.time.LocalDate;

/**
 * Steward is een class die gebruikt wordt om stewards in de Euromoon applicatie voor te stellen
 *
 * @author Ilias Ben-Fodda
 *
 */
public class Steward extends Personeelslid {
    public Steward(String naam, String achternaam, String rijkregisternummer, LocalDate geboortedatum) {
        super(naam, achternaam, rijkregisternummer, geboortedatum);
    }
}
