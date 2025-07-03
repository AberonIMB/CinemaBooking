document.addEventListener("DOMContentLoaded", () => {
    const searchInput = document.getElementById("movieSearch");
    const hiddenInput = document.getElementById("movieId");
    const suggestions = document.getElementById("suggestions");

    searchInput.addEventListener("input", () => {
        const query = searchInput.value;
        if (query.length < 1) {
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
                        hiddenInput.value = movie.id;
                        suggestions.innerHTML = "";
                    });
                    suggestions.appendChild(li);
                });
            });
    });

    document.addEventListener("click", (e) => {
        if (!suggestions.contains(e.target) && e.target !== searchInput) {
            suggestions.innerHTML = "";
        }
    });
});