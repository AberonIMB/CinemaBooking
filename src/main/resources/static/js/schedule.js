document.addEventListener("DOMContentLoaded", () => {
    const dateInput = document.getElementById("dateFilter");
    const scheduleContainer = document.getElementById("scheduleContainer");

    // Загрузка по умолчанию при старте
    loadSchedule(dateInput.value);

    // Отслеживание изменения даты
    dateInput.addEventListener("change", () => {
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

            // Ссылка на фильм
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