package com.cinema.cinemabooking.mapper.impl;

import com.cinema.cinemabooking.dto.hall.AdminHallDTO;
import com.cinema.cinemabooking.dto.hall.EditHallDTO;
import com.cinema.cinemabooking.dto.hall.ShortHallDTO;
import com.cinema.cinemabooking.mapper.interfaces.HallMapper;
import com.cinema.cinemabooking.model.Hall;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HallMapperImpl implements HallMapper {

    @Override
    public List<ShortHallDTO> mapToShortHallDTOList(List<Hall> hallList) {
        return hallList.stream()
                .map(this::mapToShortHallDTO)
                .toList();
    }

    @Override
    public List<AdminHallDTO> mapToAdminHallDTOList(List<Hall> hallList) {
        return hallList.stream()
                .map(this::mapToAdminHallDTO)
                .toList();
    }

    @Override
    public ShortHallDTO mapToShortHallDTO(Hall hall) {
        ShortHallDTO shortHallDTO = new ShortHallDTO();
        shortHallDTO.setId(hall.getId());
        shortHallDTO.setName(hall.getName());
        shortHallDTO.setActive(hall.isActive());
        return shortHallDTO;
    }

    @Override
    public AdminHallDTO mapToAdminHallDTO(Hall hall) {
        AdminHallDTO adminHallDTO = new AdminHallDTO();
        adminHallDTO.setId(hall.getId());
        adminHallDTO.setName(hall.getName());
        adminHallDTO.setNumberOfRows(hall.getNumberOfRows());
        adminHallDTO.setSeatsInRow(hall.getSeatsInRow());
        adminHallDTO.setActive(hall.isActive());
        return adminHallDTO;
    }

    @Override
    public EditHallDTO mapToEditHallDTO(Hall hall) {
        EditHallDTO editHallDTO = new EditHallDTO();
        editHallDTO.setName(hall.getName());
        editHallDTO.setNumberOfRows(hall.getNumberOfRows());
        editHallDTO.setSeatsInRow(hall.getSeatsInRow());
        editHallDTO.setActive(hall.isActive());
        return editHallDTO;
    }
}
