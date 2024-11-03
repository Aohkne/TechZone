// Accept Order button, when clicked change the innerText to On Going
function acceptOrder(button) {
    const orderCard = button.closest(".order-card");
    const statusElement = orderCard.querySelector(".order-card-status");
    statusElement.innerText = "On Going";
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

window.onload = function () {
    showOrdersByStatus("New");
};