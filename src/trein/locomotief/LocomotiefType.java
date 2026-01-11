package trein.locomotief;

public enum LocomotiefType {
    CLASS_373(12),
    CLASS_374(14);

    private final int maximumAantalWagons;
    private final int capaciteit;

    LocomotiefType(int maximumAantalWagons) {
        this.maximumAantalWagons = maximumAantalWagons;
        this.capaciteit = 80;
    }

    public int getMaximumAantalWagons() {
        return maximumAantalWagons;
    }

    public int getCapaciteit() {
        return capaciteit;
    }
}
