package persoon.personeel;

import persoon.Persoon;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Personeelslid extends Persoon {
    private final Set<Certificatie> certificaties;

    public Personeelslid(String naam, String achternaam, String rijkregisternummer, LocalDate geboortedatum) {
        super(naam, achternaam, rijkregisternummer, geboortedatum);
        certificaties = new HashSet<>();
    }

    public Set<Certificatie> getCertificaties() {
        return certificaties;
    }

    public void voegCertificatieToe(Certificatie certificatie) {
        certificaties.add(certificatie);
    }

}
