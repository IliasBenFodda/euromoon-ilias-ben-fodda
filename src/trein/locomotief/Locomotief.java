package trein.locomotief;

/**
 * Locomotief is een class die gebruikt wordt om locomotief in de Euromoon applicatie voor te stellen
 *
 * @author Ilias Ben-Fodda
 *
 */
public class Locomotief {
    private final LocomotiefType locomotiefType;

    public Locomotief(LocomotiefType locomotiefType) {
        this.locomotiefType = locomotiefType;
    }

    public LocomotiefType getLocomotiefType() {
        return locomotiefType;
    }
}
