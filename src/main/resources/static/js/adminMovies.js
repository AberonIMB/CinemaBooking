let currentActiveFilter = true;

function loadMovies(active) {
  currentActiveFilter = active;
  const url = active ? '/api/movies/active' : '/api/movies/inactive';
  fetch(url)
    .then(res => res.json())
    .then(renderMovies)
    .catch(err => alert('Ошибка загрузки фильмов: ' + err));
}

function renderMovies(movies) {
  const tbody = document.getElementById('movieTableBody');
  tbody.innerHTML = '';
  movies.forEach(movie => {
    const tr = document.createElement('tr');
    const statusClass = currentActiveFilter ? 'status-active' : 'status-inactive';
    const statusText = currentActiveFilter ? 'Активный' : 'Неактивный';

    tr.innerHTML = `
      <td>${movie.id}</td>
      <td>${movie.title}</td>
      <td>${movie.description}</td>
      <td>${movie.durationInMinutes}</td>
      <td>${movie.genres}</td>
      <td>${movie.actors}</td>
      <td>${movie.director}</td>
      <td>${movie.releaseYear}</td>
      <td class="${statusClass}">${statusText}</td>
      <td>
        <button class="btn btn-edit" onclick="window.location.href='/admin/movies/edit/${movie.id}'">Редактировать</button>
      </td>
    `;
    tbody.appendChild(tr);
  });
}