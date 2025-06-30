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
            scheduleContainer.innerHTML = "<p>Нет сеансов на выбранную дату.</p>";
            return;
        }

        scheduleData.forEach(entry => {
            const movieBlock = document.createElement("div");
            movieBlock.className = "movie-block";

            const movieHeader = document.createElement("h3");
            movieHeader.textContent = `${entry.movieDTO.title} (${entry.movieDTO.duration} мин)`;

            const genreList = document.createElement("p");
            genreList.textContent = `${entry.movieDTO.genres}`;

            const sessionList = document.createElement("ul");
            sessionList.className = "session-list";

            entry.sessionDTO.forEach(session => {
                const sessionItem = document.createElement("li");
                sessionItem.innerHTML = `
                    <span><strong>Зал:</strong> ${session.hallName}</span>
                    <span><strong>Время:</strong> ${session.time}</span>
                    <span><strong>Цена:</strong> ${session.price}₽</span>
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