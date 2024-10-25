// CHANGE PROFILE
// Edit Profile Button
function toggleEditProfile() {
  // Enable all inputs and toggle button visibility
  document
    .querySelectorAll("#user-username, #user-email, #user-phone, #user-address")
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
  document.querySelector("#user-password").disabled = false;
  document.querySelector(".change-password-btn").style.display = "none";
  document.querySelector(".save-change-password-btn").style.display = "block";
  document.querySelector(".cancel-change-password-btn").style.display = "block";
}
// Cancel change password button
function cancelChangePassword() {
  document.querySelector("#user-password").disabled = true;
  document.querySelector(".change-password-btn").style.display = "block";
  document.querySelector(".save-change-password-btn").style.display = "none";
  document.querySelector(".cancel-change-password-btn").style.display = "none";
}
