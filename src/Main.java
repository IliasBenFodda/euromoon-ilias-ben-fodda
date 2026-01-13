import persoon.passagier.Passagier;
import persoon.personeel.*;
import reis.Reis;
import ticket.Ticket;
import trein.Trein;
import trein.locomotief.Locomotief;
import trein.locomotief.LocomotiefType;
import trein.wagon.Wagon;
import trein.wagon.WagonKlasse;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Set<Passagier> passagiers = new HashSet<>();
    private static final Set<Personeelslid> personeelsleden = new HashSet<>();
    private static final List<Trein> treinen = new ArrayList<>();
    private static final List<Ticket> tickets = new ArrayList<>();
    private static final List<Reis> reizen = new ArrayList<>();

    public static void main(String[] args) {
        boolean status = true;
        maakBestaandeData();
        while (status) {
            int keuze = leesKeuze();
            switch (keuze) {
                case 1 -> registreerPassagier();
                case 2 -> registreerPersoneelslid();
                case 3 -> maakTrein();
                case 4 -> maakReis();
                case 5 -> verkoopTicketAanPassagier();
                case 6 -> drukBoardingLijstAf();
                case 7 -> status = false;
            }
        }
    }

    private static void drukBoardingLijstAf() {
        System.out.println("Kies voor welke reis je de boarding lijst wil afdrukken:");
        for (int i = 0; i < reizen.size(); i++) {
            System.out.println((i + 1) + ". Reis van " + reizen.get(i).getVertrekStation() + " naar " + reizen.get(i).getEindStation() + " op " + reizen.get(i).getVertrekTijdstip().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        }
        int reisKeuze;
        while (true) {
            reisKeuze = vraagGetal("Voer het nummer van de gekozen reis in:");
            if (reisKeuze >= 1 && reisKeuze <= reizen.size()) {
                break;
            }
            System.out.println("Ongeldige keuze, probeer opnieuw.");
        }
        Reis gekozenReis = reizen.get(reisKeuze - 1);

        String bestandsnaam = gekozenReis.getVertrekStation() + "_" + gekozenReis.getEindStation() + "_" + gekozenReis.getVertrekTijdstip().format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmm")) + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bestandsnaam))) {
            for (Ticket ticket : gekozenReis.getVerkochteTickets()) {
                Passagier passagier = ticket.getTicketHouder();
                writer.write("Naam: " + passagier.getNaam() + " " + passagier.getAchternaam() + ", Rijkregisternummer: " + passagier.getRijkregisternummer() + ", Geboortedatum: " + passagier.getGeboortedatum().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                writer.newLine();
            }
            writer.flush();
            System.out.println("Boarding lijst geprint, het bestand zal verschijnen wanneer je dit programma afsluit of naar een andere tab gaat");
        } catch (Exception e) {
            System.out.println("Fout bij printen");
        }
    }

    private static void maakBestaandeData() {
        personeelsleden.add(new TreinBestuurder("Jan", "Janssens", "12345678901", LocalDate.of(1980, 1, 1)));
        personeelsleden.add(new Steward("Piet", "Pieters", "23456789012", LocalDate.of(1990, 2, 2)));
        personeelsleden.add(new Steward("Klaas", "Klaassen", "34567890123", LocalDate.of(1985, 3, 3)));
        personeelsleden.add(new Steward("Marie", "De Vries", "45678901234", LocalDate.of(1992, 4, 4)));
        personeelsleden.add(new BagageMedewerker("Els", "Peeters", "56789012345", LocalDate.of(1975, 5, 5)));
        personeelsleden.add(new Conducteur("Tom", "Willems", "67890123456", LocalDate.of(1988, 6, 6)));
        passagiers.add(new Passagier("Ilias", "Ben-Fodda", "78901234567", LocalDate.of(1995, 7, 7)));
        passagiers.add(new Passagier("Sara", "Janssens", "89012345678", LocalDate.of(1993, 8, 8)));
    }

    private static void verkoopTicketAanPassagier() {
        System.out.println("Kies een passagier om een ticket aan te verkopen (kies naam):");
        for (Passagier passagier : passagiers) {
            System.out.println(passagier.getNaam());
        }
        String gekozenNaam = scanner.nextLine();
        Passagier gekozenPassagier = null;
        for (Passagier passagier : passagiers) {
            if (passagier.getNaam().equalsIgnoreCase(gekozenNaam)) {
                gekozenPassagier = passagier;
                break;
            }
        }
        if (gekozenPassagier == null) {
            System.out.println("Passagier niet gevonden");
            return;
        }
        if (reizen.isEmpty()) {
            System.out.println("Er zijn geen reizen beschikbaar om een ticket voor te verkopen.");
            return;
        }
        System.out.println("Kies een reis om een ticket voor te verkopen:");
        for (int i = 0; i < reizen.size(); i++) {
            System.out.println((i + 1) + ". Reis van " + reizen.get(i).getVertrekStation() + " naar " + reizen.get(i).getEindStation() + " op " + reizen.get(i).getVertrekTijdstip().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        }
        int reisKeuze;
        while (true) {
            reisKeuze = vraagGetal("Voer het nummer van de gekozen reis in:");
            if (reizen.get(reisKeuze - 1) != null) {
                break;
            }
            System.out.println("Ongeldige keuze, probeer opnieuw.");
        }
        Reis gekozenReis = reizen.get(reisKeuze - 1);
        System.out.println("Kies de wagon klasse voor het ticket (eerste of tweede):");
        String wagonklasse;
        while (true) {
            wagonklasse = scanner.nextLine();
            if (wagonklasse.equalsIgnoreCase("eerste")) {
                try {
                    Ticket ticket = new Ticket(gekozenPassagier, gekozenReis, WagonKlasse.EERSTE_KLASSE);
                    tickets.add(ticket);
                    System.out.println("Ticket verkocht");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            } else if (wagonklasse.equalsIgnoreCase("tweede")) {
                try {
                    Ticket ticket = new Ticket(gekozenPassagier, gekozenReis, WagonKlasse.TWEEDE_KLASSE);
                    tickets.add(ticket);
                    System.out.println("Ticket verkocht");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            } else {
                System.out.println("Ongeldige, probeer opnieuw (eerste of tweede):");
            }
        }
    }

    private static void maakReis() {
        System.out.println("Geef het vertrekstation: ");
        String vertrekStation = scanner.nextLine();
        System.out.println("Geef het eindstation: ");
        String eindStation = scanner.nextLine();

        LocalDateTime vertrekTijdstip;
        while (true) {
            try {
                System.out.println("Geef het vertrek tijdstip (dd-MM-yyyy HH:mm): ");
                String vertrekTijdstipInput = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                vertrekTijdstip = LocalDateTime.parse(vertrekTijdstipInput, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ongeldig formaat voor vertrek tijdstip, gebruik dd-MM-yyyy HH:mm");
            }
        }
        Set<TreinBestuurder> bestuurders = new HashSet<>();
        System.out.println("Kies 1 of meerdere bestuurders");
        for (Personeelslid personeelslid : personeelsleden) {
            if (personeelslid instanceof TreinBestuurder) {
                System.out.println("Wil je " + personeelslid.getNaam() + " " + personeelslid.getAchternaam() + " toevoegen als bestuurder? (ja/nee)");
                String antwoord = scanner.nextLine();
                if (antwoord.equalsIgnoreCase("ja")) {
                    bestuurders.add((TreinBestuurder) personeelslid);
                }
            }
        }
        if (bestuurders.isEmpty()) {
            System.out.println("Er moet ten minste 1 bestuurder zijn om een reis te maken.");
            return;
        }
        Set<Steward> stewards = new HashSet<>();
        System.out.println("Kies 3 of meerdere stewards");
        for (Personeelslid personeelslid : personeelsleden) {
            if (personeelslid instanceof Steward) {
                System.out.println("Wil je " + personeelslid.getNaam() + " " + personeelslid.getAchternaam() + " toevoegen als steward? (ja/nee)");
                String antwoord = scanner.nextLine();
                if (antwoord.equalsIgnoreCase("ja")) {
                    stewards.add((Steward) personeelslid);
                }
            }
        }
        if (stewards.size() < 3) {
            System.out.println("Er moeten ten minste 3 stewards zijn om een reis te maken.");
            return;
        }
        Reis reis = new Reis(vertrekStation, eindStation, vertrekTijdstip, bestuurders, stewards);
        if (treinen.isEmpty()) {
            System.out.println("Er zijn geen treinen beschikbaar om aan de reis toe te voegen.");
            return;
        }
        System.out.println("Kies een trein om aan de reis toe te voegen:");
        for (int i = 0; i < treinen.size(); i++) {
            System.out.println((i + 1) + ". Trein met locomotief " + treinen.get(i).getLocomotief().getLocomotiefType());
        }
        int treinKeuze;
        while (true) {
            treinKeuze = vraagGetal("Voer het nummer van de gekozen trein in:");
            if (treinKeuze >= 1 && treinKeuze <= treinen.size()) {
                break;
            }
            System.out.println("Ongeldige keuze, probeer opnieuw.");
        }
        Trein gekozenTrein = treinen.get(treinKeuze - 1);
        reis.voegTreinToe(gekozenTrein);
        reizen.add(reis);
        System.out.println("Reis succesvol aangemaakt");
    }

    private static void maakTrein() {
        Locomotief locomotief = kiesLocomotief();
        int maximumAantalWagons = locomotief.getLocomotiefType().getMaximumAantalWagons();
        List<Wagon> wagons = maakWagons(maximumAantalWagons);
        Trein trein = new Trein(locomotief, wagons);
        treinen.add(trein);
        System.out.println("Aanmaken trein was succesvol");
    }

    private static List<Wagon> maakWagons(int maximumAantalWagons) {
        while (true) {
            System.out.println("Maximum aantal wagons: " + maximumAantalWagons);
            int eersteKlasseWagons = vraagGetal("Kies het aantal eerste klasse wagons");
            int tweedeKlasseWagons = vraagGetal("Kies het aantal tweede klasse wagons");
            if (eersteKlasseWagons + tweedeKlasseWagons <= maximumAantalWagons) {
                List<Wagon> wagons = new ArrayList<>();
                for (int i = 0; i < eersteKlasseWagons; i++) {
                    wagons.add(new Wagon(WagonKlasse.EERSTE_KLASSE));
                }
                for (int i = 0; i < tweedeKlasseWagons; i++) {
                    wagons.add(new Wagon(WagonKlasse.TWEEDE_KLASSE));
                }
                return wagons;
            }
            System.out.println("Je hebt te veel wagons gekozen, probeer opnieuw (maximum: " + maximumAantalWagons + ")");
        }
    }

    private static int vraagGetal(String boodschap) {
        while (true) {
            System.out.println(boodschap);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value >= 0) {
                    return value;
                }
                System.out.println("Geef een positief getal");
            } else {
                System.out.println("Ongeldige, geef een getal");
                scanner.nextLine();
            }
        }
    }

    private static Locomotief kiesLocomotief() {
        while (true) {
            System.out.print("Kies een locomotief type (373 of 374): ");
            String type = scanner.nextLine();

            if (type.equals("373")) {
                return new Locomotief(LocomotiefType.CLASS_373);
            } else if (type.equals("374")) {
                return new Locomotief(LocomotiefType.CLASS_374);
            } else {
                System.out.println("Ongeldige keuze");
            }
        }
    }

    private static int leesKeuze() {
        while (true) {
            getWelkomMenu();
            if (!scanner.hasNextInt()) {
                System.out.println("Ongeldige invoer, probeer opnieuw");
                scanner.nextLine();
                continue;
            }
            int keuze = scanner.nextInt();
            scanner.nextLine();
            return keuze;
        }
    }

    private static void registreerPersoneelslid() {
        System.out.println("Naam: ");
        String naam = scanner.nextLine();
        System.out.println("Achternaam: ");
        String achternaam = scanner.nextLine();
        System.out.println("Rijkregisternummer: ");
        String rijkregisternummer = scanner.nextLine();
        LocalDate geboortedatum = formatGeboortedatum(scanner);

        while (true) {
            System.out.println("Kies een functie: Bagage, Conducteur, Steward, Bestuurder");
            String functie = scanner.nextLine();
            try {
                Personeelslid personeelslid = maakPersoneelslid(functie, naam, achternaam, rijkregisternummer, geboortedatum);
                personeelsleden.add(personeelslid);
                System.out.println("Successvol geregistreerd");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Onbekende functie, probeer opnieuw");
            }
        }
    }

    private static Personeelslid maakPersoneelslid(String functie, String naam, String achternaam, String rijkregisternummer, LocalDate geboortedatum) {
        return switch (functie.toLowerCase()) {
            case "bagage" -> new BagageMedewerker(naam, achternaam, rijkregisternummer, geboortedatum);
            case "conducteur" -> new Conducteur(naam, achternaam, rijkregisternummer, geboortedatum);
            case "steward" -> new Steward(naam, achternaam, rijkregisternummer, geboortedatum);
            case "bestuurder" -> new TreinBestuurder(naam, achternaam, rijkregisternummer, geboortedatum);
            default -> throw new IllegalArgumentException("Onbekende functie: " + functie.toLowerCase());
        };
    }

    private static void registreerPassagier() {
        System.out.println("Naam: ");
        String naam = scanner.nextLine();
        System.out.println("Achternaam: ");
        String achternaam = scanner.nextLine();
        System.out.println("Rijkregisternummer: ");
        String rijkregisternummer = scanner.nextLine();
        LocalDate geboortedatum = formatGeboortedatum(scanner);
        Passagier passagier = new Passagier(naam, achternaam, rijkregisternummer, geboortedatum);
        passagiers.add(passagier);
        System.out.println("Successvol geregistreerd");
    }

    private static LocalDate formatGeboortedatum(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while (true) {
            System.out.println("Geboortedatum (dd-MM-yyyy): ");
            String geboortedatum = scanner.nextLine();
            try {
                return LocalDate.parse(geboortedatum, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ongeldig formaat, probeer opnieuw");
            }
        }
    }

    private static void getWelkomMenu() {
        System.out.println("Welkom bij de Euromoon applicatie");
        System.out.println("Via het onderstaande menu kan je kiezen wat je wil doen:");
        System.out.println("1. Registreer een passagier");
        System.out.println("2. Registreer een personeelslid");
        System.out.println("3. Maak een trein");
        System.out.println("4. Maak een reis");
        System.out.println("5. Verkoop ticket aan passagier");
        System.out.println("6. Druk boardinglijst af");
        System.out.println("7. Afsluiten");
    }
}