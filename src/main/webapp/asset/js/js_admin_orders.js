// Accept Order button, when clicked change the innerText to On Going
// Accept Order button, when clicked change the innerText to On Going
function acceptOrder(button) {
  const orderCard = button.closest(".order-card");
  const statusElement = orderCard.querySelector(".order-card-status");
  const completeButton = orderCard.querySelector(".order-card-complete-btn");
  // Update the order status
  // Hide the Accept button
  button.style.display = "none";
  // Show the complete button
  completeButton.style.display = "block";
  // Show the order list by Status New
}

function showOrdersByStatus(status) {
    // Lấy tất cả các thẻ đơn hàng
    const orders = document.querySelectorAll(".order-card");

    // Duyệt qua từng đơn hàng và lọc theo trạng thái
    orders.forEach(order => {
        const statusText = order.querySelector(".order-status-text").innerText.trim();

        // So sánh trạng thái và hiển thị hoặc ẩn đơn hàng
        if (statusText === status) {
            order.style.display = "block";
        } else {
            order.style.display = "none";
        }
    });
}

function selectButton(button, status) {
    // Xóa lớp 'selected' khỏi tất cả các nút
    document.querySelectorAll(".change-status-container button").forEach(btn => btn.classList.remove("selected"));
    button.classList.add("selected");

    // Gọi hàm lọc theo trạng thái
    showOrdersByStatus(status);
}



window.onload = function () {
    showOrdersByStatus("Not Yet");
};

//// Complete Order button
function completeOrder(button) {
    const orderCard = button.closest(".order-card");
    const statusElement = orderCard.querySelector(".order-card-status");
    statusElement.innerText = "Completed";
    button.style.display = "none";
}
