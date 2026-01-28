package com.tharushisonnadara.helloworld.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HelloWorldServiceTest {
    private HelloWorldService service;

    @BeforeEach
    void setUp() {
        service = new HelloWorldService();
    }

    @Nested
    @DisplayName("isValidName tests")
    class IsValidNameTests {

        @Test
        @DisplayName("should return false for null name")
        void shouldReturnFalseForNullName() {
            assertFalse(service.isValidName(null));
        }

        @Test
        @DisplayName("should return false for empty name")
        void shouldReturnFalseForEmptyName() {
            assertFalse(service.isValidName(""));
        }

        @Test
        @DisplayName("should return false for blank name")
        void shouldReturnFalseForBlankName() {
            assertFalse(service.isValidName("   "));
        }

        @ParameterizedTest
        @ValueSource(strings = {"Alice", "alice", "ALICE", "Bob", "bob", "Charlie", "David",
                "Eva", "Frank", "George", "Henry", "Ivan", "Jack", "Kate", "Lisa", "Mike",
                "Mary", "mark", "MICHAEL"})
        @DisplayName("should return true for names starting with A-M (case insensitive)")
        void shouldReturnTrueForFirstHalfNames(String name) {
            assertTrue(service.isValidName(name));
        }

        @ParameterizedTest
        @ValueSource(strings = {"Nancy", "nancy", "NANCY", "Oliver", "Peter", "Quinn",
                "Robert", "Steve", "Tom", "Uma", "Victor", "William", "Xavier", "Yolanda",
                "Zachary", "zoe", "NICK"})
        @DisplayName("should return false for names starting with N-Z (case insensitive)")
        void shouldReturnFalseForSecondHalfNames(String name) {
            assertFalse(service.isValidName(name));
        }

        @ParameterizedTest
        @ValueSource(strings = {"123abc", "9test", "0name"})
        @DisplayName("should return false for names starting with numbers")
        void shouldReturnFalseForNamesStartingWithNumbers(String name) {
            assertFalse(service.isValidName(name));
        }

        @ParameterizedTest
        @ValueSource(strings = {"@special", "#hash", "$dollar", "!exclaim"})
        @DisplayName("should return false for names starting with special characters")
        void shouldReturnFalseForNamesStartingWithSpecialChars(String name) {
            assertFalse(service.isValidName(name));
        }

        @Test
        @DisplayName("should return true for single character 'A'")
        void shouldReturnTrueForSingleCharA() {
            assertTrue(service.isValidName("A"));
        }

        @Test
        @DisplayName("should return true for single character 'M'")
        void shouldReturnTrueForSingleCharM() {
            assertTrue(service.isValidName("M"));
        }

        @Test
        @DisplayName("should return false for single character 'N'")
        void shouldReturnFalseForSingleCharN() {
            assertFalse(service.isValidName("N"));
        }

        @Test
        @DisplayName("should return false for single character 'Z'")
        void shouldReturnFalseForSingleCharZ() {
            assertFalse(service.isValidName("Z"));
        }
    }

    @Nested
    @DisplayName("formatName tests")
    class FormatNameTests {

        @Test
        @DisplayName("should capitalize first letter and lowercase rest")
        void shouldFormatNameCorrectly() {
            assertEquals("Alice", service.formatName("alice"));
            assertEquals("Alice", service.formatName("ALICE"));
            assertEquals("Alice", service.formatName("aLiCe"));
        }

        @Test
        @DisplayName("should handle single character names")
        void shouldHandleSingleCharacterNames() {
            assertEquals("A", service.formatName("a"));
            assertEquals("B", service.formatName("B"));
        }

        @Test
        @DisplayName("should return null for null input")
        void shouldReturnNullForNullInput() {
            assertNull(service.formatName(null));
        }

        @Test
        @DisplayName("should return empty for empty input")
        void shouldReturnEmptyForEmptyInput() {
            assertEquals("", service.formatName(""));
        }

        @Test
        @DisplayName("should trim whitespace")
        void shouldTrimWhitespace() {
            assertEquals("Alice", service.formatName("  alice  "));
        }
    }
}
