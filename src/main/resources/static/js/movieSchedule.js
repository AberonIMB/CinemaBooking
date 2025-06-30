document.addEventListener('DOMContentLoaded', function () {
    const movieId = document.getElementById('movieId').value;
    const dateInput = document.getElementById('dateFilter');

    const scheduleSection = document.createElement('div');
    scheduleSection.id = 'scheduleResults';
    document.querySelector('.schedule-section').appendChild(scheduleSection);

    function fetchAndRenderSchedule(date) {
        fetch(`/api/schedule/movie/${movieId}?date=${date}`)
            .then(response => response.json())
            .then(data => {
                renderSchedule(data);
            })
            .catch(error => {
                console.error('Ошибка при загрузке расписания:', error);
                scheduleSection.innerHTML = '<p>Не удалось загрузить сеансы.</p>';
            });
    }

    function renderSchedule(sessions) {
        if (!sessions || sessions.length === 0) {
            scheduleSection.innerHTML = '<p>На выбранную дату сеансов нет.</p>';
            return;
        }

        scheduleSection.innerHTML = '';
        sessions.forEach(session => {
            const sessionDiv = document.createElement('div');
            sessionDiv.classList.add('session-item');
            sessionDiv.innerHTML = `
                <p><strong>Зал:</strong> ${session.hallName}</p>
                <p><strong>Время:</strong> ${session.time}</p>
                <p><strong>Цена:</strong> ${session.price}₽</p>
                <hr>
            `;
            scheduleSection.appendChild(sessionDiv);
        });
    }

    // При загрузке страницы
    fetchAndRenderSchedule(dateInput.value);

    // При изменении даты
    dateInput.addEventListener('change', function () {
        fetchAndRenderSchedule(this.value);
    });
});