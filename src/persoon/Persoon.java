package persoon;

import java.time.LocalDate;

/**
 * Persoon is een class die gebruikt wordt om personen in de Euromoon applicatie voor te stellen
 *
 * @author Ilias Ben-Fodda
 *
 */
public abstract class Persoon {
    private final String naam;
    private final String achternaam;
    private final String rijkregisternummer;
    private final LocalDate geboortedatum;

    public Persoon(String naam, String achternaam, String rijkregisternummer, LocalDate geboortedatum) {
        this.naam = naam;
        this.achternaam = achternaam;
        this.rijkregisternummer = rijkregisternummer;
        this.geboortedatum = geboortedatum;
    }

    public String getNaam() {
        return naam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getRijkregisternummer() {
        return rijkregisternummer;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }
}
