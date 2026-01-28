//28-01-2026 TharushiCS - TypeB Digital - HelloWorld Application

package com.tharushisonnadara.helloworld.service;

import org.springframework.stereotype.Service;
/**
 * Contains business logic for the hello-world endpoint.
 */
@Service
public class HelloWorldService {
    private static final char FIRST_HALF_START = 'A';
    private static final char FIRST_HALF_END = 'M';

    /**
     * Validates if the name is acceptable.
     * A name is valid if:
     * - It is not null
     * - It is not empty or blank - after trim
     * - Its first character is a letter in the first half of the alphabet (A-M)
     *
     * @param name the name to validate
     * @return true if valid, false otherwise
     */

    public boolean isValidName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }

        String trimmedName = name.trim();
        char firstChar = Character.toUpperCase(trimmedName.charAt(0));

        // Check if first character is a letter
        if (!Character.isLetter(firstChar)) {
            return false;
        }

        // Check if first letter is in the first half of the alphabet (A-M)
        return firstChar >= FIRST_HALF_START && firstChar <= FIRST_HALF_END;
    }

    /**
     * Formats the name with proper letter case.
     * The first letter is capitalized, the rest are lowercase.
     *
     * @param name the name to format
     * @return the formatted name
     */

    public String formatName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }

        String trimmed = name.trim();
        if (trimmed.isEmpty()) {
            return trimmed;
        }

        return Character.toUpperCase(trimmed.charAt(0))
                + trimmed.substring(1).toLowerCase();
    }
}
