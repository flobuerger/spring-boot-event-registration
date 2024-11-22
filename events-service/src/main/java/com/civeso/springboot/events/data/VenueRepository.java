package com.civeso.springboot.events.data;

import com.civeso.springboot.events.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Integer> {
}
