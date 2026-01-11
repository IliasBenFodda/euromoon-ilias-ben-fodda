package persoon.personeel;

import java.time.LocalDate;

public class Conducteur extends Personeelslid {

    public Conducteur(String naam, String achternaam, String rijkregisternummer, LocalDate geboortedatum) {
        super(naam, achternaam, rijkregisternummer, geboortedatum);
    }
}
