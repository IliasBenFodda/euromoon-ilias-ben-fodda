package persoon.personeel;

import java.time.LocalDate;

/**
 * Conducteur is een class die gebruikt wordt om conducteurs in de Euromoon applicatie voor te stellen
 *
 * @author Ilias Ben-Fodda
 *
 */
public class Conducteur extends Personeelslid {

    public Conducteur(String naam, String achternaam, String rijkregisternummer, LocalDate geboortedatum) {
        super(naam, achternaam, rijkregisternummer, geboortedatum);
    }
}
