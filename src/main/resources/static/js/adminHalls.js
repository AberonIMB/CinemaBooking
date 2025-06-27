let currentActiveHallFilter = true;

function loadHalls(active) {
  currentActiveHallFilter = active;
  const url = active ? '/api/halls/active' : '/api/halls/inactive';
  fetch(url)
    .then(res => res.json())
    .then(renderHalls)
    .catch(err => alert('Ошибка загрузки залов: ' + err));
}

function renderHalls(halls) {
  const tbody = document.getElementById('hallTableBody');
  tbody.innerHTML = '';
  halls.forEach(hall => {
    const statusClass = currentActiveHallFilter ? 'status-active' : 'status-inactive';
    const statusText = currentActiveHallFilter ? 'Активен' : 'Неактивен';
    const totalSeats = hall.numberOfRows * hall.seatsInRow;

    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td>${hall.id}</td>
      <td>${hall.name}</td>
      <td>${hall.numberOfRows}</td>
      <td>${hall.seatsInRow}</td>
      <td>${totalSeats}</td>
      <td class="${statusClass}">${statusText}</td>
      <td>
        <button class="btn btn-edit" onclick="window.location.href='/admin/halls/edit/${hall.id}'">Редактировать</button>
      </td>
    `;
    tbody.appendChild(tr);
  });
}