package com.cinema.cinemabooking.service.impl;

import com.cinema.cinemabooking.exception.seat.SeatNotFoundException;
import com.cinema.cinemabooking.model.Hall;
import com.cinema.cinemabooking.model.Seat;
import com.cinema.cinemabooking.repository.SeatRepository;
import com.cinema.cinemabooking.service.interfaces.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    @Transactional
    public void createSeatsForHall(Hall hall) {
        List<Seat> seats = new ArrayList<>(hall.getSeatsInRow() * hall.getNumberOfRows());

        for (int row = 1; row <= hall.getNumberOfRows(); row++) {
            for (int seatNum = 1; seatNum <= hall.getSeatsInRow(); seatNum++) {
                Seat seat = new Seat(row, seatNum, hall);
                seats.add(seat);
            }
        }

        seatRepository.saveAll(seats);
    }

    @Override
    @Transactional
    public void updateSeatsForHall(Hall hall) {
       deactivateSeatsForHall(hall);
       createSeatsForHall(hall);
    }

    @Override
    public List<Seat> getSeatsForHall(Hall hall) {
        return seatRepository.getSeatsByHallAndIsActive(hall, true);
    }

    @Override
    public Seat getSeatById(Long id) {
        return seatRepository.findById(id).orElseThrow(() -> new SeatNotFoundException(id));
    }

    /**
     * Переводит все места зала из активного состояния в неактивное
     * @param hall зал
     */
    private void deactivateSeatsForHall(Hall hall) {
        List<Seat> seats = seatRepository.getSeatsByHallAndIsActive(hall, true);

        for (Seat seat : seats) {
            seat.setActive(false);
        }

        seatRepository.saveAll(seats);
    }
}