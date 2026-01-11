package persoon.passagier;

import persoon.Persoon;

import java.time.LocalDate;

public class Passagier extends Persoon {

    public Passagier(String naam, String achternaam, String rijkregisternummer, LocalDate geboortedatum) {
        super(naam, achternaam, rijkregisternummer, geboortedatum);
    }
}
