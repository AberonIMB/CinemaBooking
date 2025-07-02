document.addEventListener("DOMContentLoaded", function () {
    const checkboxes = document.querySelectorAll(".select-checkbox");
    const cancelButton = document.getElementById("cancelButton");

    checkboxes.forEach(cb => {
        cb.addEventListener("change", () => {
            const anyChecked = [...checkboxes].some(c => c.checked);
            cancelButton.disabled = !anyChecked;
        });
    });
});