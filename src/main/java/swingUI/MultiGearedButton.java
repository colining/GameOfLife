package swingUI;

import javax.swing.*;

//翻译：gear-（汽车的）档位。例：倒档-reverse gear
public class MultiGearedButton<ValueType> extends JButton {
    private int gear;
    private int maxGear;
    private ValueType[] values;

    public MultiGearedButton(ValueType... values) {
        gear = 1;
        maxGear = values.length;
        this.values = values;

        setText(getDisplayedText());
    }

    public ValueType getValue() {
        return values[gear - 1];
    }

    public void nextGear() {
        increaseGear();
        setText(getDisplayedText());
    }

    private void increaseGear(){
        gear++;
        if (gear > maxGear) {
            gear = 1;
        }
    }

    private String getDisplayedText() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < gear; i++) {
            result.append(">");
        }
        return result.toString();
    }
}
