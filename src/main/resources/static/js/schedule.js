document.addEventListener("DOMContentLoaded", () => {
    const dateInput = document.getElementById("dateFilter");
    const scheduleContainer = document.getElementById("scheduleContainer");

    // Получение текущей даты в формате YYYY-MM-DD
    function getTodayDate() {
        const today = new Date();
        const year = today.getFullYear();
        const month = String(today.getMonth() + 1).padStart(2, '0');
        const day = String(today.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    }

    // Установка текущей даты, если поле пустое
    function ensureDateSelected() {
        if (!dateInput.value) {
            dateInput.value = getTodayDate();
        }
    }

    // Установка значения по умолчанию при запуске
    ensureDateSelected();
    loadSchedule(dateInput.value);

    // Отслеживание изменения даты
    dateInput.addEventListener("change", () => {
        ensureDateSelected();
        loadSchedule(dateInput.value);
    });

    function loadSchedule(date) {
        fetch(`/api/schedule?date=${date}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error("Ошибка при загрузке расписания");
                }
                return response.json();
            })
            .then(data => {
                renderSchedule(data);
            })
            .catch(error => {
                scheduleContainer.innerHTML = `<p class="error">${error.message}</p>`;
            });
    }

    function renderSchedule(scheduleData) {
        scheduleContainer.innerHTML = "";

        if (scheduleData.length === 0) {
            return;
        }

        scheduleData.forEach(entry => {
            const movieBlock = document.createElement("div");
            movieBlock.className = "movie-block";

            const movieHeader = document.createElement("h3");
            movieHeader.innerHTML = `<a href="/movies/${entry.movieDTO.id}" class="movie-link">
                                        ${entry.movieDTO.title} (${entry.movieDTO.duration} мин)
                                     </a>`;

            const genreList = document.createElement("p");
            genreList.textContent = `${entry.movieDTO.genres}`;

            const sessionList = document.createElement("ul");
            sessionList.className = "session-list";

            entry.sessionDTO.forEach(session => {
                const sessionItem = document.createElement("li");

                sessionItem.innerHTML = `
                    <a href="/sessions/${session.id}" class="session-link">
                        <span><strong>Зал:</strong> ${session.hallName}</span>
                        <span><strong>Время:</strong> ${session.time}</span>
                        <span><strong>Цена:</strong> ${session.price}₽</span>
                    </a>
                `;

                sessionList.appendChild(sessionItem);
            });

            movieBlock.appendChild(movieHeader);
            movieBlock.appendChild(genreList);
            movieBlock.appendChild(sessionList);
            scheduleContainer.appendChild(movieBlock);
        });
    }
});