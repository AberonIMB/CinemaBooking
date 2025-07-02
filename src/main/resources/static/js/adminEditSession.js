document.addEventListener("DOMContentLoaded", () => {
    const searchInput = document.getElementById("movieSearch");
    const hiddenInput = document.getElementById("movieId");
    const suggestions = document.getElementById("suggestions");

    // При вводе текста - сбрасываем movieId (т.к. пользователь пока не выбрал фильм)
    searchInput.addEventListener("input", () => {
        const query = searchInput.value;

        // Если пользователь начал вводить, значит потенциально меняется фильм,
        // поэтому пока сбросим hidden movieId
        hiddenInput.value = '';

        if (query.length < 2) {
            suggestions.innerHTML = "";
            return;
        }

        fetch(`/api/movies/search?title=${encodeURIComponent(query)}`)
            .then(res => res.json())
            .then(data => {
                suggestions.innerHTML = "";
                data.forEach(movie => {
                    const li = document.createElement("li");
                    li.textContent = movie.title;
                    li.dataset.id = movie.id;
                    li.addEventListener("click", () => {
                        searchInput.value = movie.title;
                        hiddenInput.value = movie.id; // Тут выставляем id выбранного фильма
                        suggestions.innerHTML = "";
                    });
                    suggestions.appendChild(li);
                });
            });
    });

    // Закрываем подсказки при клике вне
    document.addEventListener("click", (e) => {
        if (!suggestions.contains(e.target) && e.target !== searchInput) {
            suggestions.innerHTML = "";
        }
    });
});