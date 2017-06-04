package swingUI;

import game.Petri;

import javax.swing.*;

public class PresetPetriButton extends JButton {
    private PresetPetri preset;

    PresetPetriButton(PresetPetri preset) {
        super(preset.name);
        this.preset = preset;
    }

    void update(Petri petri){
        petri.update(preset.cells);
    }
}
