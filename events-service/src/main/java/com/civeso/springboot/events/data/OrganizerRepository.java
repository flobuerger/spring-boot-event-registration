package com.civeso.springboot.events.data;

import com.civeso.springboot.events.models.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
}
