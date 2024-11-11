document.addEventListener("DOMContentLoaded", () => {
  const buttons = document.querySelectorAll(".block-btn");

  buttons.forEach((button) => {
    button.addEventListener("click", () => {
      const userId = button.id.split("-")[1]; // Lấy user_id từ ID nút
      const currentStatus = button.textContent.trim(); // Lấy nội dung hiện tại của nút

      let newStatus = false; // Mặc định là false (Block)
      if (currentStatus === "Unblock") {
        newStatus = true; // Nếu nút hiện tại là Unblock, chuyển sang trạng thái Block
      }

      // Gửi yêu cầu cập nhật trạng thái lên server
      fetch(`/Admin?userId=${userId}&verified=${newStatus}`, {
        method: "POST"
      }).then(response => {
        if (response.ok) {
          // Sau khi server phản hồi thành công, cập nhật giao diện nút
          if (newStatus) {
            button.textContent = "Block"; // Chuyển thành nút Block
            button.classList.remove("btn-success");
            button.classList.add("btn-danger");
          } else {
            button.textContent = "Unblock"; // Chuyển thành nút Unblock
            button.classList.remove("btn-danger");
            button.classList.add("btn-success");
          }
        } else {
          console.error("Failed to update status");
        }
      }).catch(error => {
        console.error("Error:", error);
      });
    });
  });
});
