document.addEventListener("DOMContentLoaded", () => {
    // MODAL
    const addButton = document.querySelector(".add-btn");
    const closeButtons = document.querySelectorAll(".close-btn, .cancel-btn");
    let modal = document.querySelector(".modal");

    addButton.onclick = function () {
        modal.style.display = "block";
    };

    closeButtons.forEach(function (button) {
        button.onclick = function () {
            modal.style.display = "none";
        };
    });

    // ACCEPT BUTTON
    const categoryForm = document.querySelector(".category-form");
    const categoriesTable = document.querySelector(".categories-table table");

    // EDIT MODAL FUNCTIONALITY (if needed in this context)
    const editModal = document.getElementById("editModal");
    const closeEditBtn = document.querySelector("#editModal .close-btn");
    const cancelEditBtn = document.querySelector("#editModal .cancel-btn");

    closeEditBtn.addEventListener("click", () => {
        editModal.style.display = "none";
    });

    cancelEditBtn.addEventListener("click", () => {
        editModal.style.display = "none";
    });

    window.addEventListener("click", (event) => {
        if (event.target == editModal) {
            editModal.style.display = "none";
        }
    });

    function attachEditButtonListeners() {
        const editButtons = document.querySelectorAll(".edit-btn");
        editButtons.forEach((button) => {
            button.addEventListener("click", () => {
                editModal.style.display = "block";
            });
        });
    }

    attachEditButtonListeners(); // Initial call to attach listeners
});
