package persoon.personeel;

import java.time.LocalDate;

/**
 * TreinBestuurder is een class die gebruikt wordt om treinbestuurdes in de Euromoon applicatie voor te stellen
 *
 * @author Ilias Ben-Fodda
 *
 */
public class TreinBestuurder extends Personeelslid {

    public TreinBestuurder(String naam, String achternaam, String rijkregisternummer, LocalDate geboortedatum) {
        super(naam, achternaam, rijkregisternummer, geboortedatum);
    }
}
