package swingUI;

public class GameOfLifeFrameMain {
    public static void main(String[] args) {
        final int PETRI_DEFAULT_SIZE = 5;

        PetriSingleton.makePetri(PETRI_DEFAULT_SIZE);
        new GameOfLifeFrame(PetriSingleton.petri);
    }
}
