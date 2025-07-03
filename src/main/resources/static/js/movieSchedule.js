document.addEventListener('DOMContentLoaded', function () {
    const movieId = document.getElementById('movieId').value;
    const dateInput = document.getElementById('dateFilter');

    // Устанавливаем сегодняшнюю дату по умолчанию
    if (!dateInput.value) {
        dateInput.value = getTodayDate();
    }

    const scheduleSection = document.createElement('div');
    scheduleSection.id = 'scheduleResults';
    document.querySelector('.schedule-section').appendChild(scheduleSection);

    function getTodayDate() {
        const today = new Date();
        const year = today.getFullYear();
        const month = String(today.getMonth() + 1).padStart(2, '0');
        const day = String(today.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    }

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
                <a href="/sessions/${session.id}" class="session-link">
                    <p><strong>Зал:</strong> ${session.hallName}</p>
                    <p><strong>Время:</strong> ${session.time}</p>
                    <p><strong>Цена:</strong> ${session.price}₽</p>
                </a>
                <hr>
            `;

            scheduleSection.appendChild(sessionDiv);
        });
    }

    // Загрузка при старте
    fetchAndRenderSchedule(dateInput.value);

    // При изменении даты
    dateInput.addEventListener('change', function () {
        // Если поле очищено вручную — вернуть сегодняшнюю дату
        if (!this.value) {
            this.value = getTodayDate();
        }
        fetchAndRenderSchedule(this.value);
    });
});