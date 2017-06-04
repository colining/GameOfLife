package swingUI;

import game.Petri;

public class PetriSingleton {
    public static Petri petri;

    public static void makePetri(int size) {
       petri = new Petri(size);
    }

}
