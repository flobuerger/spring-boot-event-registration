package com.civeso.springboot.registration.controllers;

import com.civeso.springboot.events.models.Event;
import com.civeso.springboot.events.clients.EventsClient;
import com.civeso.springboot.events.models.Product;
import com.civeso.springboot.registration.data.RegistrationRepository;
import com.civeso.springboot.registration.models.Registration;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/registrations")
public class RegistrationController {

    private final EventsClient eventsClient;
    private final RegistrationRepository registrationRepository;

    public RegistrationController(EventsClient eventsClient, RegistrationRepository registrationRepository) {
        this.eventsClient = eventsClient;
        this.registrationRepository = registrationRepository;
    }

    @PostMapping
    public Registration create(@RequestBody Registration registration) {
        Product product = eventsClient.getProductById(registration.productId());
        Event event = eventsClient.getEventById(product.eventId());

        String ticketCode = UUID.randomUUID().toString();

        return registrationRepository.save(new Registration(
                null, registration.productId(),
                event.name(), product.price(),
                ticketCode, registration.attendeeName()));
    }

    @GetMapping(path = "/{ticketCode}")
    public Registration get(@PathVariable("ticketCode") String ticketCode) {
        return registrationRepository.findByTicketCode(ticketCode)
                .orElseThrow(() -> new NoSuchElementException("Registration with ticket code " + ticketCode + " not found"));
    }

    @PutMapping
    public Registration update(@RequestBody Registration registration) {
        // Lookup the existing registration by ticket code
        String ticketCode = registration.ticketCode();
        var existing = registrationRepository.findByTicketCode(ticketCode)
                .orElseThrow(() -> new NoSuchElementException("Registration with ticket code " + ticketCode + " not found"));

        // Only update the attendee name
        return registrationRepository.save(new Registration(
                existing.id(), existing.productId(),
                existing.eventName(), existing.amount(),
                ticketCode, registration.attendeeName()));
    }

    @DeleteMapping(path = "/{ticketCode}")
    public void delete(@PathVariable("ticketCode") String ticketCode) {
        registrationRepository.deleteByTicketCode(ticketCode);
    }
}
