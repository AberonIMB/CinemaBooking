document.addEventListener('DOMContentLoaded', () => {
    const filterMovie = document.getElementById('movieFilter');
    const filterHall = document.getElementById('hallFilter');
    const filterDate = document.getElementById('dateFilter');
    const btnFilter = document.getElementById('btnFilter');
    const tableBody = document.getElementById('sessionTableBody');

    // Загрузка залов (активных сначала, потом неактивных)
    function loadHalls() {
        fetch('/api/halls')
            .then(res => res.json())
            .then(halls => {
                // сортируем: активные вначале
                halls.sort((a, b) => (b.active === true) - (a.active === true));
                filterHall.innerHTML = '<option value="">Все залы</option>';
                halls.forEach(hall => {
                    const option = document.createElement('option');
                    option.value = hall.id;
                    option.textContent = hall.name + (hall.active ? '' : ' (неактивный)');
                    filterHall.appendChild(option);
                });
            })
            .catch(console.error);
    }

    // Загрузка сеансов с фильтрами
    function loadSessions() {
        const movieQuery = filterMovie.value.trim();
        const hallId = filterHall.value;
        const date = filterDate.value;

        const params = new URLSearchParams();
        if (movieQuery) params.append('movieTitle', movieQuery);
        if (hallId) params.append('hallId', hallId);
        if (date) params.append('date', date);

        fetch('/api/sessions?' + params.toString())
            .then(res => {
                if (!res.ok) throw new Error('Ошибка при загрузке сеансов');
                return res.json();
            })
            .then(sessions => {
                renderTable(sessions);
            })
            .catch(e => alert(e.message));
    }

    function renderTable(sessions) {
        tableBody.innerHTML = '';
        if (sessions.length === 0) {
            const tr = document.createElement('tr');
            const td = document.createElement('td');
            td.colSpan = 6;
            td.textContent = 'Сеансы не найдены';
            tr.appendChild(td);
            tableBody.appendChild(tr);
            return;
        }

        sessions.forEach(session => {
             const tr = document.createElement('tr');

            tr.innerHTML = `
                <td>${session.id}</td>
                <td>${session.movieTitle}</td>
                <td>${session.hallName}</td>
                <td>${session.date}</td>
                <td>${session.time}</td>
                <td>${session.price}</td>
                <td>${session.active ? 'Активный' : 'Неактивный'}</td>
                <td>
                    <button class="btn btn-edit" onclick="window.location.href='/admin/sessions/edit/${session.id}'">Редактировать</button>
                    <button class="btn btn-info">Информация</button>
                </td>
            `;
            tableBody.appendChild(tr);
        });
    }

    btnFilter.addEventListener('click', loadSessions);

    loadHalls();
    loadSessions();
});