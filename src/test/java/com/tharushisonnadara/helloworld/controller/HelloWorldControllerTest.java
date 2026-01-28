package com.tharushisonnadara.helloworld.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("Successful requests (HTTP 200)")
    class SuccessfulRequests {

        @Test
        @DisplayName("should return 200 OK with greeting for name starting with 'A'")
        void shouldReturnOkForNameStartingWithA() throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", "alice"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.message").value("Hello Alice"));
        }

        @Test
        @DisplayName("should return 200 OK with greeting for name starting with 'M'")
        void shouldReturnOkForNameStartingWithM() throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", "michael"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.message").value("Hello Michael"));
        }

        @ParameterizedTest
        @ValueSource(strings = {"Adam", "Bob", "Charlie", "Diana", "Emma", "Frank",
                "Grace", "Henry", "Irene", "Jack", "Kate", "Lisa", "Mike"})
        @DisplayName("should return 200 OK for all names starting with A-M")
        void shouldReturnOkForFirstHalfNames(String name) throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", name))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.message").exists());
        }

        @Test
        @DisplayName("should handle uppercase names correctly")
        void shouldHandleUppercaseNames() throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", "ALICE"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.message").value("Hello Alice"));
        }

        @Test
        @DisplayName("should handle mixed case names correctly")
        void shouldHandleMixedCaseNames() throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", "aLiCe"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.message").value("Hello Alice"));
        }
    }

    @Nested
    @DisplayName("Bad requests (HTTP 400)")
    class BadRequests {

        @Test
        @DisplayName("should return 400 Bad Request for name starting with 'N'")
        void shouldReturnBadRequestForNameStartingWithN() throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", "nancy"))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.error").value("Invalid Input"));
        }

        @Test
        @DisplayName("should return 400 Bad Request for name starting with 'Z'")
        void shouldReturnBadRequestForNameStartingWithZ() throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", "zachary"))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.error").value("Invalid Input"));
        }

        @ParameterizedTest
        @ValueSource(strings = {"Nancy", "Oliver", "Peter", "Quinn", "Robert", "Steve",
                "Tom", "Uma", "Victor", "William", "Xavier", "Yolanda", "Zachary"})
        @DisplayName("should return 400 Bad Request for all names starting with N-Z")
        void shouldReturnBadRequestForSecondHalfNames(String name) throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", name))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.error").value("Invalid Input"));
        }

        @Test
        @DisplayName("should return 400 Bad Request when name parameter is missing")
        void shouldReturnBadRequestWhenNameMissing() throws Exception {
            mockMvc.perform(get("/hello-world"))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.error").value("Invalid Input"));
        }

        @Test
        @DisplayName("should return 400 Bad Request when name parameter is empty")
        void shouldReturnBadRequestWhenNameEmpty() throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", ""))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.error").value("Invalid Input"));
        }

        @Test
        @DisplayName("should return 400 Bad Request when name is only whitespace")
        void shouldReturnBadRequestWhenNameIsWhitespace() throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", "   "))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.error").value("Invalid Input"));
        }

        @Test
        @DisplayName("should return 400 Bad Request when name starts with a number")
        void shouldReturnBadRequestWhenNameStartsWithNumber() throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", "123abc"))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.error").value("Invalid Input"));
        }

        @Test
        @DisplayName("should return 400 Bad Request when name starts with special character")
        void shouldReturnBadRequestWhenNameStartsWithSpecialChar() throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", "@alice"))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.error").value("Invalid Input"));
        }
    }

    @Nested
    @DisplayName("Edge cases")
    class EdgeCases {

        @Test
        @DisplayName("should handle single character 'a' correctly")
        void shouldHandleSingleCharA() throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", "a"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.message").value("Hello A"));
        }

        @Test
        @DisplayName("should handle single character 'm' correctly")
        void shouldHandleSingleCharM() throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", "m"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.message").value("Hello M"));
        }

        @Test
        @DisplayName("should reject single character 'n'")
        void shouldRejectSingleCharN() throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", "n"))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.error").value("Invalid Input"));
        }

        @Test
        @DisplayName("should reject single character 'z'")
        void shouldRejectSingleCharZ() throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", "z"))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.error").value("Invalid Input"));
        }

        @Test
        @DisplayName("should handle names with leading spaces")
        void shouldHandleNamesWithLeadingSpaces() throws Exception {
            mockMvc.perform(get("/hello-world")
                            .param("name", "  alice"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.message").value("Hello Alice"));
        }
    }
}
