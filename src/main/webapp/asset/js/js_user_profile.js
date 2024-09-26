function enableEditMode(section) {
    // Tùy theo phần đang chỉnh sửa, tìm và kích hoạt chế độ chỉnh sửa cho phần tương ứng
    if (section === 'profile') {
        const profileName = document.querySelector('.profile__name');
        const profileLocation = document.querySelector('.profile__location');

        profileName.setAttribute('contenteditable', true);
        profileLocation.setAttribute('contenteditable', true);

        // Thêm nút lưu cho phần Profile
        if (!document.querySelector('.profile__edit button')) {
            const saveButton = document.createElement('button');
            saveButton.textContent = 'Save';
            saveButton.classList.add('save-btn');  // Thêm class CSS cho nút Save
            saveButton.onclick = function () { saveChanges('profile'); };
            document.querySelectorAll('.profile__edit')[0].appendChild(saveButton);
        }
    } else if (section === 'personal-info') {
        const username = document.querySelector('.username');
        const email = document.querySelector('.email');
        const phone = document.querySelector('.phone');
        const address = document.querySelector('.address');

        username.setAttribute('contenteditable', true);
        email.setAttribute('contenteditable', true);
        phone.setAttribute('contenteditable', true);
        address.setAttribute('contenteditable', true);

        // Thêm nút lưu cho phần Personal Information
        if (!document.querySelectorAll('.profile__edit')[1].querySelector('button')) {
            const saveButton = document.createElement('button');
            saveButton.textContent = 'Save';
            saveButton.classList.add('save-btn');  // Thêm class CSS cho nút Save
            saveButton.onclick = function () { saveChanges('personal-info'); };
            document.querySelectorAll('.profile__edit')[1].appendChild(saveButton);
        }
    }
}

function saveChanges(section) {
    // Tùy theo phần đang lưu, tắt chế độ chỉnh sửa cho phần tương ứng
    if (section === 'profile') {
        const profileName = document.querySelector('.profile__name');
        const profileLocation = document.querySelector('.profile__location');

        profileName.setAttribute('contenteditable', false);
        profileLocation.setAttribute('contenteditable', false);

        // Xóa nút Save sau khi lưu
        const saveButton = document.querySelectorAll('.profile__edit')[0].querySelector('button');
        saveButton.remove();

        alert('Profile changes have been saved!');
    } else if (section === 'personal-info') {
        const username = document.querySelector('.username');
        const email = document.querySelector('.email');
        const phone = document.querySelector('.phone');
        const address = document.querySelector('.address');

        username.setAttribute('contenteditable', false);
        email.setAttribute('contenteditable', false);
        phone.setAttribute('contenteditable', false);
        address.setAttribute('contenteditable', false);

        // Xóa nút Save sau khi lưu
        const saveButton = document.querySelectorAll('.profile__edit')[1].querySelector('button');
        saveButton.remove();

        alert('Personal information changes have been saved!');
    }
}


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