package persoon.passagier;

import persoon.Persoon;

import java.time.LocalDate;

/**
 * Passagier is een class die gebruikt wordt om passagiers in de Euromoon applicatie voor te stellen
 *
 * @author Ilias Ben-Fodda
 *
 */
public class Passagier extends Persoon {

    public Passagier(String naam, String achternaam, String rijkregisternummer, LocalDate geboortedatum) {
        super(naam, achternaam, rijkregisternummer, geboortedatum);
    }
}
