function enableEditMode(section) {
    if (section === 'profile') {
        const profileName = document.querySelector('.profile__name');
        const profileLocation = document.querySelector('.profile__location');

        // Loại bỏ thuộc tính readonly để cho phép chỉnh sửa
        profileName.removeAttribute('readonly');
        profileLocation.removeAttribute('readonly');

        // Thêm nút Save nếu chưa có trong form profile
        const profileForm = document.querySelector('.personal-profile__form'); // Sửa lại nếu cần
        if (!profileForm.querySelector('input[type="submit"]')) {
            const saveInput = document.createElement('input');
            saveInput.type = 'submit';  // Tạo nút submit
            saveInput.value = 'Save';
            saveInput.classList.add('save-btn');  // Thêm class CSS cho nút Save
            profileForm.appendChild(saveInput); // Thêm nút Save vào form profile
        }
    } else if (section === 'personal-info') {
        const username = document.querySelector('.username');
        const email = document.querySelector('.email');
        const phone = document.querySelector('.phone');
        const address = document.querySelector('.address');

        // Loại bỏ thuộc tính readonly để cho phép chỉnh sửa
        username.removeAttribute('readonly');
        email.removeAttribute('readonly');
        phone.removeAttribute('readonly');
        address.removeAttribute('readonly');

        // Thêm nút Save nếu chưa có trong form personal-info
        const personalInfoForm = document.querySelector('.personal-info__form');
        if (!personalInfoForm.querySelector('input[type="submit"]')) {
            const saveInput = document.createElement('input');
            saveInput.type = 'submit';  // Tạo nút submit
            saveInput.value = 'Save';
            saveInput.classList.add('save-btn');  // Thêm class CSS cho nút Save
            personalInfoForm.appendChild(saveInput); // Thêm nút Save vào form personal-info
        }
    }
}

function saveChanges(section) {
    if (section === 'profile') {
        const profileName = document.querySelector('.profile__name');
        const profileLocation = document.querySelector('.profile__location');

        // Thêm lại thuộc tính readonly sau khi lưu
        profileName.setAttribute('readonly', true);
        profileLocation.setAttribute('readonly', true);

        // Hiển thị thông báo thành công (không cần điều hướng thủ công)
        alert('Profile changes have been saved!');
    } else if (section === 'personal-info') {
        const username = document.querySelector('.username');
        const email = document.querySelector('.email');
        const phone = document.querySelector('.phone');
        const address = document.querySelector('.address');

        // Thêm lại thuộc tính readonly sau khi lưu
        username.setAttribute('readonly', true);
        email.setAttribute('readonly', true);
        phone.setAttribute('readonly', true);
        address.setAttribute('readonly', true);
    }
}
