package swingUI;

public class GameOfLifeFrameMain {
    public static void main(String[] args) {
        final int PETRI_DEFAULT_SIZE = 5;
        final int DEFAULT_EVOLVE_INTERVAL_IN_MILLS = 1000;

        PetriSingleton.makePetri(PETRI_DEFAULT_SIZE);
        new GameOfLifeFrame(PetriSingleton.petri, DEFAULT_EVOLVE_INTERVAL_IN_MILLS);
    }
}
