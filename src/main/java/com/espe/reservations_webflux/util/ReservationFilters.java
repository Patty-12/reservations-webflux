package com.espe.reservations_webflux.util;

import com.espe.reservations_webflux.model.ReservationEvent;

import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ReservationFilters {

    private ReservationFilters() {
    }

    public static final Predicate<ReservationEvent> VALID_RESERVATION =
            reservation -> reservation != null
                    && reservation.getPrice() != null
                    && reservation.getPrice() > 0
                    && reservation.getEmails() != null
                    && !reservation.getEmails().isEmpty();

    public static final Consumer<ReservationEvent> PRINT_PROCESSED_EVENT =
            reservation -> System.out.println("Evento procesado correctamente: " + reservation);
}