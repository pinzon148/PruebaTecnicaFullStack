package util;

package com.example.orchestrator.util;

public class CedulaValidator {

    public static boolean isValid(String cedula) {
        if (cedula == null || cedula.length() < 5) return false;

        int sum = 0;
        boolean alternate = false;

        for (int i = cedula.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cedula.substring(i, i + 1));

            if (alternate) {
                n *= 2;
                if (n > 9) n -= 9;
            }

            sum += n;
            alternate = !alternate;
        }

        return (sum % 10 == 0);
    }
}