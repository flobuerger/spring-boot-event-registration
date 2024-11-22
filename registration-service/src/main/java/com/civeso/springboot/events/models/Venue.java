package com.civeso.springboot.events.models;

public record Venue(
        int id,
        String name,
        String street,
        String city,
        String country) {
}
