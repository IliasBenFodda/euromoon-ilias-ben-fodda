package persoon.personeel;

import persoon.Persoon;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Personeelslid is een class die gebruikt wordt om personeelsleden in de Euromoon applicatie voor te stellen
 *
 * @author Ilias Ben-Fodda
 *
 */
public class Personeelslid extends Persoon {
    private final Set<Certificatie> certificaties;

    public Personeelslid(String naam, String achternaam, String rijkregisternummer, LocalDate geboortedatum) {
        super(naam, achternaam, rijkregisternummer, geboortedatum);
        certificaties = new HashSet<>();
    }

    public Set<Certificatie> getCertificaties() {
        return certificaties;
    }

    /**
     * Een methode om een certificatie toe te voegen aan een personeelslid
     *
     * @param certificatie De certificatie die je wil toevoegen
     */
    public void voegCertificatieToe(Certificatie certificatie) {
        certificaties.add(certificatie);
    }

}
