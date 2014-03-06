package com.udec.vista;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import org.jdesktop.beansbinding.Converter;

public class DoubleConverter extends Converter<Double, String> {

    public DoubleConverter() {
        simbolos.setDecimalSeparator('.');
        formatter = new DecimalFormat("0.00", simbolos);
    }
    private final DecimalFormat formatter;
    private final DecimalFormatSymbols simbolos = new DecimalFormatSymbols();

    @Override
    public String convertForward(Double value) {
        if (value == null) {
            value = 0.0;
        }
        return formatter.format(value);
    }

    @Override
    public Double convertReverse(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public Double round(Double value) {
        return convertReverse(convertForward(value));
    }
}
