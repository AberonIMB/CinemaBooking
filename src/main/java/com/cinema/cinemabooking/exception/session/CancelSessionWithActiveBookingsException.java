package com.cinema.cinemabooking.exception.session;

/**
 * Исключение, которое выбрасывается при попытке отменить сеанс с активными бронями
 */
public class CancelSessionWithActiveBookingsException extends SessionException {

    public CancelSessionWithActiveBookingsException() {
        super("Невозможно отменить сеанс с активными бронями");
    }

}
