package persoon.personeel;

import persoon.Persoon;

import java.time.LocalDate;

/**
 * BagageMedewerker is een class die gebruikt wordt om bagagemedewerkers in de Euromoon applicatie voor te stellen
 *
 * @author Ilias Ben-Fodda
 *
 */
public class BagageMedewerker extends Personeelslid {
    public BagageMedewerker(String naam, String achternaam, String rijkregisternummer, LocalDate geboortedatum) {
        super(naam, achternaam, rijkregisternummer, geboortedatum);
    }
}
