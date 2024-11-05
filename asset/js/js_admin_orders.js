// Accept Order button, when clicked change the innerText to On Going
function acceptOrder(button) {
  const orderCard = button.closest(".order-card");
  const statusElement = orderCard.querySelector(".order-card-status");
  const completeButton = orderCard.querySelector(".order-card-complete-btn");
  // Update the order status
  statusElement.innerText = "On Going";
  // Hide the Accept button
  button.style.display = "none";
  // Show the complete button
  completeButton.style.display = "block";
  // Show the order list by Status New
  showOrdersByStatus("New");
}

function showOrdersByStatus(status) {
  // Get all order cards
  const orders = document.querySelectorAll(".order-card");

  // Loop through each order card
  orders.forEach((order) => {
    const statusElement = order.querySelector(".order-card-status");
    if (statusElement.innerText === status) {
      order.style.display = "block";
    } else {
      order.style.display = "none";
    }
  });
}

// Complete Order button
function completeOrder(button) {
  const orderCard = button.closest(".order-card");
  const statusElement = orderCard.querySelector(".order-card-status");
  statusElement.innerText = "Completed";
  button.style.display = "none";
  showOrdersByStatus("On Going");
}

// Default view is Status New
window.onload = function () {
  showOrdersByStatus("New");
};
