package com.ocado.basket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasketSplitterTest {

    BasketSplitter basketSplitter;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        basketSplitter = new BasketSplitter("src/test/resources/config.json");
    }


    @Test
    @DisplayName("Splitting test")
    public void splittingTest() {
        // Given
        List<String> items = new ArrayList<>();
        Map<String, List<String>> result;
        Map<String, List<String>> expected = new HashMap<>();

        expected.put("Express Delivery", new ArrayList<>());
        expected.get("Express Delivery").add("Steak (300g)");
        expected.get("Express Delivery").add("Carrots (1kg)");
        expected.get("Express Delivery").add("Cold Beer (330ml)");
        expected.get("Express Delivery").add("AA Battery (4 Pcs.)");

        expected.put("Courier", new ArrayList<>());
        expected.get("Courier").add("Espresso Machine");
        expected.get("Courier").add("Garden Chair");


        items.add("Steak (300g)");
        items.add("Carrots (1kg)");
        items.add("Cold Beer (330ml)");
        items.add("AA Battery (4 Pcs.)");
        items.add("Espresso Machine");
        items.add("Garden Chair");

        result = basketSplitter.split(items);
        assert result.equals(expected);
    }

    @Test
    @DisplayName("Empty basket test")
    public void emptyBasketTest() {
        // Given
        List<String> items = new ArrayList<>();
        Map<String, List<String>> result;
        Map<String, List<String>> expected = new HashMap<>();

        result = basketSplitter.split(items);

        assert result.equals(expected);
    }

    @Test
    @DisplayName("One item basket test")
    public void oneItemBasketTest() {
        // Given
        List<String> items = new ArrayList<>();
        Map<String, List<String>> result;
        Map<String, List<String>> expected = new HashMap<>();

        expected.put("Express Delivery", new ArrayList<>());
        expected.get("Express Delivery").add("Cold Beer (330ml)");

        items.add("Cold Beer (330ml)");

        result = basketSplitter.split(items);

        assert result.equals(expected);
    }

    @Test
    @DisplayName("Two items different category basket test")
    public void twoItemsDifferentCategoryBasketTest() {
        // Given
        List<String> items = new ArrayList<>();
        Map<String, List<String>> result;
        Map<String, List<String>> expected = new HashMap<>();

        expected.put("Express Delivery", new ArrayList<>());
        expected.get("Express Delivery").add("Cold Beer (330ml)");
        expected.put("Courier", new ArrayList<>());
        expected.get("Courier").add("Garden Chair");

        items.add("Cold Beer (330ml)");
        items.add("Garden Chair");

        result = basketSplitter.split(items);

        assert result.equals(expected);
    }

    @Test
    @DisplayName("Two items 1 category is the same test")
    public void twoItemsOneCategoryIsTheSameTest() {
        // Given
        List<String> items = new ArrayList<>();
        Map<String, List<String>> result;
        Map<String, List<String>> expected = new HashMap<>();

        expected.put("Express Delivery", new ArrayList<>());
        expected.get("Express Delivery").add("Cold Beer (330ml)");
        expected.get("Express Delivery").add("AA Battery (4 Pcs.)");

        items.add("Cold Beer (330ml)");
        items.add("AA Battery (4 Pcs.)");

        result = basketSplitter.split(items);

        assert result.equals(expected);
    }

    @Test
    @DisplayName("Item not found in config test")
    public void itemNotFoundInConfigTest() {
        // Given
        List<String> items = new ArrayList<>();
        Map<String, List<String>> result;
        Map<String, List<String>> expected = new HashMap<>();

        expected.put("Express Delivery", new ArrayList<>());
        expected.get("Express Delivery").add("Steak (300g)");
        expected.get("Express Delivery").add("Cold Beer (330ml)");

        items.add("Steak (300g)");
        items.add("Cold Beer (330ml)");
        items.add("Not Found Item");

        result = basketSplitter.split(items);

        assert result.equals(expected);
    }


}
