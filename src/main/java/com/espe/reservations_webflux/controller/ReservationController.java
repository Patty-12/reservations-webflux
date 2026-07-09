package com.espe.reservations_webflux.controller;

import com.espe.reservations_webflux.model.ReservationEvent;
import com.espe.reservations_webflux.util.ReservationFilters;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@RestController
public class ReservationController {

    @GetMapping(value = "/api/reservations/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ReservationEvent> streamReservations() {

        ReservationEvent reserva1 = new ReservationEvent(
                "RES-001",
                "Carlos Mendoza",
                150.75,
                List.of("carlos@email.com")
        );

        ReservationEvent reserva2 = new ReservationEvent(
                "RES-002",
                "Ana Torres",
                220.00,
                List.of("ana@email.com", "ana.torres@email.com")
        );

        ReservationEvent reserva3 = new ReservationEvent(
                "RES-003",
                "Luis Ramirez",
                95.50,
                List.of("luis@email.com")
        );

        ReservationEvent reserva4 = new ReservationEvent(
                "RES-004",
                "Reserva Invalida Precio",
                -20.00,
                List.of("invalida.precio@email.com")
        );

        ReservationEvent reserva5 = new ReservationEvent(
                "RES-005",
                "Reserva Invalida Email",
                180.00,
                List.of()
        );

        ReservationEvent reservaPorDefecto = new ReservationEvent(
                "RES-DEFAULT",
                "Reserva Generica",
                1.00,
                List.of("default@email.com")
        );

        return Flux.just(reserva1, reserva2, reserva3, reserva4, reserva5)
                .delayElements(Duration.ofSeconds(1))
                .filter(ReservationFilters.VALID_RESERVATION)
                .doOnNext(ReservationFilters.PRINT_PROCESSED_EVENT)
                .defaultIfEmpty(reservaPorDefecto);
    }
}