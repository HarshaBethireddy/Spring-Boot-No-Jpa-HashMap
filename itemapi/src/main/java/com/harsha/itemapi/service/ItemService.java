package com.harsha.itemapi.service;

import com.harsha.itemapi.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ItemService {

    private final Map<Integer, Item> items = new HashMap<>();

    public Item addItem(Item item) {
        item.setId(items.size() + 1);
        items.put(item.getId(), item);
        return item;
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }

    public Item getItemById(int id) {
        if(items.containsKey(id)) {
            return items.get(id);
        } else {
            throw new RuntimeException("Item with id: " + id + " not found");
        }
    }

    public List<Item> getItemsByName(String name) {
        return items.values().stream().filter(item -> item.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    public Item updateItem(int id, Item newItem) {
        Item existingItem = getItemById(id);

        existingItem.setName(newItem.getName());
        existingItem.setDescription(newItem.getDescription());
        existingItem.setPrice(newItem.getPrice());

        return existingItem;
    }

    public void deleteItem(int id) {
        try {
            items.remove(id);
        } catch (Exception e) {
            throw new RuntimeException("User with id: " + id + " not found");
        }
    }
}
