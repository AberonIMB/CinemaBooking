package com.cinema.cinemabooking.mapper.interfaces;

import com.cinema.cinemabooking.dto.session.*;
import com.cinema.cinemabooking.model.Session;

/**
 * Маппер для сеансов
 */
public interface SessionMapper {

    /**
     * Конвертирует полученные данные для создания сеанса в {@link SessionCreateData} - данные для передачи в сервис
     * @param sessionDTO данные для создания сеанса
     * @return SessionCreateData данные для передачи в сервис
     */
    SessionCreateData mapToCreateData(CreateSessionDTO sessionDTO);

    /**
     * Конвертирует полученные данные для обновления сеанса в {@link SessionUpdateData} - данные для передачи в сервис
     * @param sessionDTO данные для обновления сеанса
     * @return SessionUpdateData данные для передачи в сервис
     */
    SessionUpdateData mapToUpdateData(EditSessionDTO sessionDTO);

    /**
     *  Конвертирует сеанс в {@link EditSessionDTO}
     * @param session сеанс
     * @return {@link EditSessionDTO}
     */
    EditSessionDTO mapToEditSessionDTO(Session session);

    /**
     * Конвертирует сеанс в {@link AdminSessionDTO}
     * @param session сеанс
     * @return {@link AdminSessionDTO}
     */
    AdminSessionDTO mapToAdminSessionDTO(Session session);

    /**
     * Конвертирует сеанс в {@link ScheduleSessionDTO}
     * @param session сеанс
     * @return {@link ScheduleSessionDTO}
     */
    ScheduleSessionDTO mapToScheduleSessionDTO(Session session);
}
