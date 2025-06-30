package com.cinema.cinemabooking.mapper.interfaces;

import com.cinema.cinemabooking.dto.session.*;
import com.cinema.cinemabooking.model.Session;

/**
 * Маппер для сеансов
 */
public interface SessionMapper {

    /**
     * Конвертирует полученные данные для создания сеанса в SessionCreateData - данные для передачи в сервис
     * @param sessionDTO данные для создания сеанса
     * @return SessionCreateData данные для передачи в сервис
     */
    SessionCreateData mapToCreateData(CreateSessionDTO sessionDTO);

    /**
     * Конвертирует полученные данные для обновления сеанса в SessionUpdateData - данные для передачи в сервис
     * @param sessionDTO данные для обновления сеанса
     * @return SessionUpdateData данные для передачи в сервис
     */
    SessionUpdateData mapToUpdateData(EditSessionDTO sessionDTO);

    /**
     *  Конвертирует сеанс в EditSessionDTO
     * @param session сеанс
     * @return EditSessionDTO данные для редактирования сеанса
     */
    EditSessionDTO mapToEditSessionDTO(Session session);

    /**
     * Конвертирует сеанс в AdminSessionDTO
     * @param session сеанс
     * @return AdminSessionDTO полная информация о сеансе
     */
    AdminSessionDTO mapToAdminSessionDTO(Session session);

    ScheduleSessionDTO mapToScheduleSessionDTO(Session session);
}
