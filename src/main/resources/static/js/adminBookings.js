document.addEventListener('DOMContentLoaded', () => {
    const emailInput = document.getElementById('emailFilter');
    const movieInput = document.getElementById('movieFilter');
    const dateInput = document.getElementById('dateFilter');
    const statusSelect = document.querySelector('select[name="status"]');
    const btnFilter = document.getElementById('btnFilter');
    const tableBody = document.getElementById('bookingTableBody');

    function loadBookings() {
        const params = new URLSearchParams();

        const email = emailInput.value.trim();
        const movieTitle = movieInput.value.trim();
        const date = dateInput.value;
        const status = statusSelect.value;

        if (email) params.append('email', email);
        if (movieTitle) params.append('movieTitle', movieTitle);
        if (date) params.append('date', date);
        if (status) params.append('status', status);

        fetch('/api/bookings?' + params.toString())
            .then(res => {
                if (!res.ok) throw new Error('Ошибка загрузки бронирований');
                return res.json();
            })
            .then(renderTable)
            .catch(err => {
                tableBody.innerHTML = `<tr><td colspan="10" style="color:red;">${err.message}</td></tr>`;
            });
    }

    function renderTable(bookings) {
        tableBody.innerHTML = '';

        if (bookings.length === 0) {
            tableBody.innerHTML = '<tr><td colspan="10">Бронирования не найдены</td></tr>';
            return;
        }

        bookings.forEach(booking => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${booking.id}</td>
                <td>${booking.email}</td>
                <td>${booking.movieTitle}</td>
                <td>${booking.sessionDate}</td>
                <td>${booking.sessionTime}</td>
                <td>${booking.hallName}</td>
                <td>${booking.rowNumber}</td>
                <td>${booking.seatNumber}</td>
                <td>${booking.price}₽</td>
                <td>${booking.bookingTime.split('T').join(' ').split('.')[0]}</td>
                <td>${booking.statusDescription}</td>
            `;
            tableBody.appendChild(tr);
        });
    }

    function formatDateTimeManual(raw) {
      const [datePart, timePartWithMs] = raw.split('T');
      const timePart = timePartWithMs.split('.')[0]; // отрезаем миллисекунды

      const [year, month, day] = datePart.split('-');
      const [hour, minute] = timePart.split(':');

      return `${day}.${month}.${year} ${hour}:${minute}`;
    }

    btnFilter.addEventListener('click', loadBookings);

    loadBookings(); // Загрузить при первом открытии страницы
});