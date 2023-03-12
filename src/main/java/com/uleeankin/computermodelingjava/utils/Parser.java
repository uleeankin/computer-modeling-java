package com.uleeankin.computermodelingjava.utils;

import javafx.scene.control.TextField;

public class Parser {

    public static int parseTextFieldValueToInt(TextField textField) {
        return Integer.parseInt(textField.getCharacters().toString());
    }

    public static double parseTextFieldValueToDouble(TextField textField) {
        return Double.parseDouble(textField.getCharacters().toString());
    }

}
