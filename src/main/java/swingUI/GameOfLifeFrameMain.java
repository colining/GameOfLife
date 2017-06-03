package swingUI;

public class GameOfLifeFrameMain {
    public static void main(String[] args) {
        final int PETRI_DEFAULT_SIZE = 6;

        PetriSingleton.makePetri(PETRI_DEFAULT_SIZE);
        new GameOfLifeFrame(PetriSingleton.petri, false, new PresetPetri("空", new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        }), new PresetPetri("八边形", new int[][]{
                {0, 0, 1, 1, 0, 0},
                {0, 1, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 1, 0},
                {0, 0, 1, 1, 0, 0}
        }), new PresetPetri("滑翔者", new int[][]{
                {0, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 0},
                {0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        }), new PresetPetri("飞船", new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0, 0},
                {1, 1, 0, 1, 1, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0}
        }), new PresetPetri("脉冲星", new int[][]{
                {0, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 0},
                {1, 0, 0, 1, 0, 1},
                {1, 1, 1, 0, 1, 1},
                {0, 1, 0, 1, 0, 1},
                {0, 0, 1, 1, 1, 0}
        }));
    }
}
