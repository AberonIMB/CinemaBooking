package com.cinema.cinemabooking.service.impl;

import com.cinema.cinemabooking.dto.hall.CreateHallDTO;
import com.cinema.cinemabooking.dto.hall.EditHallDTO;
import com.cinema.cinemabooking.exception.hall.HallAlreadyExistsException;
import com.cinema.cinemabooking.exception.hall.HallHasActiveSessionsException;
import com.cinema.cinemabooking.exception.hall.HallNotFoundException;
import com.cinema.cinemabooking.model.Hall;
import com.cinema.cinemabooking.model.Session;
import com.cinema.cinemabooking.repository.HallRepository;
import com.cinema.cinemabooking.service.interfaces.HallService;
import com.cinema.cinemabooking.service.interfaces.SeatService;
import com.cinema.cinemabooking.service.interfaces.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HallServiceImpl implements HallService {

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private SeatService seatService;

    @Autowired
    private SessionService sessionService;

    @Override
    public List<Hall> getHalls() {
        return hallRepository.findAll();
    }

    @Override
    public List<Hall> getHallsByActive(boolean isActive) {
        return hallRepository.getHallsByIsActive(isActive);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createHallFromDTO(CreateHallDTO createHallDTO) {
        String name = createHallDTO.getName().trim();
        if (hallRepository.existsByName(name)) {
            throw new HallAlreadyExistsException(name);
        }

        Hall hall = new Hall(
                name,
                createHallDTO.getNumberOfRows(),
                createHallDTO.getSeatsInRow());

        hallRepository.save(hall);
        seatService.createSeatsForHall(hall);
    }

    @Override
    public Hall getHallById(Long id) {
        return hallRepository.findById(id).orElseThrow(() -> new HallNotFoundException(id));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void updateHall(Long id, EditHallDTO hallDTO) {
        Hall hall = hallRepository.findById(id).orElseThrow(() -> new HallNotFoundException(id));
        Hall existingHall = hallRepository.getHallByName(hallDTO.getName());

        if (existingHall != null && !hall.equals(existingHall)) {
            throw new HallAlreadyExistsException(hallDTO.getName());
        }

        hall.setName(hallDTO.getName());

        if (hall.getNumberOfRows() != hallDTO.getNumberOfRows() || hall.getSeatsInRow() != hallDTO.getSeatsInRow()) {
            List<Session> activeSessions = sessionService.getActiveSessionsByHall(hall);

            if (!activeSessions.isEmpty()) {
                throw new HallHasActiveSessionsException(hall, activeSessions.size());
            }

            hall.setName(hallDTO.getName());
            hall.setNumberOfRows(hallDTO.getNumberOfRows());
            hall.setSeatsInRow(hallDTO.getSeatsInRow());
            seatService.updateSeatsForHall(hall);
        }

        hallRepository.save(hall);
    }

    @Override
    @Transactional
    public void deactivateHall(Long id) {
        Hall hall = hallRepository.findById(id).orElseThrow(() -> new HallNotFoundException(id));

        List<Session> activeSessions = sessionService.getActiveSessionsByHall(hall);

        if (!activeSessions.isEmpty()) {
            throw new HallHasActiveSessionsException(hall, activeSessions.size());
        }

        hall.setActive(false);
        hallRepository.save(hall);
    }

    @Override
    @Transactional
    public void activateHall(Long id) {
        Hall hall = hallRepository.findById(id).orElseThrow(() -> new HallNotFoundException(id));
        hall.setActive(true);
        hallRepository.save(hall);
    }
}