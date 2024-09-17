// Operations Delete
document.addEventListener("DOMContentLoaded", () => {
  const deleteButtons = document.querySelectorAll("button");

  deleteButtons.forEach((button) => {
    button.addEventListener("click", () => {
      // Check if the button is a "Delete" button
      if (button.textContent.trim() === "Delete") {
        // Find the row containing the button and remove it
        const row = button.closest("tr");
        row.remove();
      }
    });
  });
});
