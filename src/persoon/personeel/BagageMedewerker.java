package persoon.personeel;

import persoon.Persoon;

import java.time.LocalDate;

public class BagageMedewerker extends Personeelslid {
    public BagageMedewerker(String naam, String achternaam, String rijkregisternummer, LocalDate geboortedatum) {
        super(naam, achternaam, rijkregisternummer, geboortedatum);
    }
}
