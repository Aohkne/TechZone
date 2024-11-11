

function confirmDeleteAccount() {
    const confirmation = confirm("Are you sure you want to delete your account? This action cannot be undone.");
    if (confirmation) {
        deleteAccount();
    }
}

function deleteAccount() {
    // Xóa tài khoản ở đây
    alert("Your account has been deleted.");
    // window.location.href = 'login.html'; // Chuyển hướng về trang đăng nhập
}