// Function để khi nhấn vào sẽ hiện chatbox dựa trên chatbox ID
function openChatbox(chatboxId) {
  // Hide all chatboxes
  document.querySelectorAll(".chatbox").forEach(function (chatbox) {
    chatbox.classList.add("hidden");
  });
  // Show the selected chatbox
  document.getElementById(chatboxId).classList.remove("hidden");
}
