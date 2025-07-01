package com.cinema.cinemabooking.exception.session;


public class CancelSessionWithActiveBookingsException  extends RuntimeException {

    public CancelSessionWithActiveBookingsException() {
        super("Невозможно отменить сеанс с активными бронями");
    }
}
