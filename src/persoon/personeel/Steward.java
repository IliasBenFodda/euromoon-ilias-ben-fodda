package persoon.personeel;

import java.time.LocalDate;

public class Steward extends Personeelslid {
    public Steward(String naam, String achternaam, String rijkregisternummer, LocalDate geboortedatum) {
        super(naam, achternaam, rijkregisternummer, geboortedatum);
    }
}
