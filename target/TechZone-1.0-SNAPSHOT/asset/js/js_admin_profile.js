// CHANGE PROFILE
// Edit Profile Button
function toggleEditProfile() {
  // Enable all inputs and toggle button visibility
  document
    .querySelectorAll("#user-username, #user-phone, #user-address")
    .forEach((input) => {
      input.disabled = false;
    });
  document.querySelector(".edit-profile-btn").style.display = "none";
  document.querySelector(".save-edit-profile-btn").style.display =
    "inline-block";
  document.querySelector(".cancel-edit-profile-btn").style.display =
    "inline-block";
}
// Cancel Edit Profile Button
function cancelEditProfile() {
  // Disable all inputs and reset to original values
  document
    .querySelectorAll("#user-username, #user-email, #user-phone, #user-address")
    .forEach((input) => {
      input.disabled = true;
    });
  document.querySelector(".edit-profile-btn").style.display = "inline-block";
  document.querySelector(".save-edit-profile-btn").style.display = "none";
  document.querySelector(".cancel-edit-profile-btn").style.display = "none";
}

// CHANGE PASSWORD
// Change password button
function toggleEditPassword() {
  document.querySelector(".change-password-btn").style.display = "none";
  document.querySelector(".save-change-password-btn").style.display = "block";
  document.querySelector(".cancel-change-password-btn").style.display = "block";
  // Hide Password
  document.querySelector(".user-password-container").style.display = "none";
  // Show Old Password, New Password, Confirm New Password
  document.querySelector(".user-old-password-container").style.display = "flex";
  document.querySelector(".user-new-password-container").style.display = "flex";
  document.querySelector(".user-confirm-new-password-container").style.display =
    "flex";
}
// Cancel change password button
function cancelChangePassword() {
  document.querySelector("#user-password").disabled = true;
  document.querySelector(".change-password-btn").style.display = "block";
  document.querySelector(".save-change-password-btn").style.display = "none";
  document.querySelector(".cancel-change-password-btn").style.display = "none";
  // Show Password
  document.querySelector(".user-password-container").style.display = "flex";
  // Hide Old Password, New Password, Confirm New Password
  document.querySelector(".user-old-password-container").style.display = "none";
  document.querySelector(".user-new-password-container").style.display = "none";
  document.querySelector(".user-confirm-new-password-container").style.display =
    "none";
}

// ON CHANGE
function updateProfileField(inputId, targetClass) {
  const inputElement = document.getElementById(inputId);
  const targetElement = document.querySelector(`.${targetClass}`);
  if (inputElement && targetElement) {
    targetElement.textContent = inputElement.value;
  }
}

// Initialize fields on page load
function initializeProfileFields() {
  updateProfileField("user-username", "profile-username");
  updateProfileField("user-address", "profile-address");
}

// Run initialize function on page load
document.addEventListener("DOMContentLoaded", initializeProfileFields);
