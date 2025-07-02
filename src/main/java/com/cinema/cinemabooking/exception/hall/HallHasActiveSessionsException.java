package com.cinema.cinemabooking.exception.hall;

import com.cinema.cinemabooking.model.Hall;

/**
 * Исключение, которое выбрасывается при попытке изменить/удалить зал с активными сеансами
 */
public class HallHasActiveSessionsException extends HallException {

    public HallHasActiveSessionsException(Hall hall, int numberOfActiveSessions) {
        super("Невозможно, так как зал \""
                + hall.getName() + "\" с id " + hall.getId()
                + " содержит активные сеансы: "
                + numberOfActiveSessions);
    }
}