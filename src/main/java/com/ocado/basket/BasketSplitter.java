package com.ocado.basket;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.List;

/**
 * This class is responsible for splitting a basket of items into different delivery methods.
 */
public class BasketSplitter {
    Gson gson = new Gson();
    Map<String, List<String>> config;

    /**
     * Constructor for the BasketSplitter class.
     * @param absolutePathToConfigFile The absolute path to the configuration file.
     */
    public BasketSplitter(String absolutePathToConfigFile) {
        try {
            JsonReader reader = new JsonReader(new FileReader(absolutePathToConfigFile));
            this.config = gson.fromJson(reader, Map.class);
        }
        catch (FileNotFoundException e) {
            return;
        }
    }

    /**
     * This method splits a list of items into different delivery methods.
     * @param items The list of items to be split.
     * @return A map where the keys are the delivery methods and the values are lists of items for each delivery method.
     */
    public Map<String, List<String>> split(List<String> items) {
        DefaultUndirectedGraph<String, DefaultEdge> graph
                = new DefaultUndirectedGraph<>(DefaultEdge.class);
        Set<String> deliveryMethods = new HashSet<>();
        Map<String, List<String>> result = new HashMap<>();

        // Add items and their delivery methods to the graph
        for (String item : items) {
            graph.addVertex(item);
            List<String> deliveries= config.get(item);
            if (deliveries != null) {
                for (String delivery : deliveries) {
                    deliveryMethods.add(delivery);
                    graph.addVertex(delivery);
                    graph.addEdge(delivery, item);
                }
            }
        }

        // While there are still edges in the graph, find the delivery method with the most items and add it to the result
        while(!graph.edgeSet().isEmpty()) {
            String max = deliveryMethods.stream().max(Comparator.comparing(e1 -> graph.incomingEdgesOf(e1).size())).orElse(null);
            if (max != null) {
                result.put(max, new ArrayList<>());
                result.get(max).addAll(Graphs.neighborListOf(graph, max));
                Graphs.neighborListOf(graph, max).forEach(graph::removeVertex);
            }
        }

        return result;
    }
}