document.addEventListener("DOMContentLoaded", () => {
  const buttons = document.querySelectorAll(".block-btn");

  buttons.forEach((button) => {
    button.addEventListener("click", () => {
      const currentStatus = button.textContent.trim();

      if (currentStatus === "Block") {
        // Update the text to "Unblock" and the status to "Blocked"
        button.textContent = "Unblock";
        button.style.background = "linear-gradient(60deg, #66bb6a, #43a047)";

        // Update the corresponding row's status (this assumes the status is in the second-to-last column)
        const statusCell = button.parentElement.previousElementSibling;
        statusCell.textContent = "Blocked";
      } else {
        // Update the text to "Block" and the status to "Online/Offline"
        button.textContent = "Block";
        button.style.background = "linear-gradient(60deg, #ef5350, #e53935)";

        // Revert the status to "Online" or "Offline"
        const statusCell = button.parentElement.previousElementSibling;
        statusCell.textContent = "Online"; // or use logic to determine if they should be "Offline"
      }
    });
  });
});
