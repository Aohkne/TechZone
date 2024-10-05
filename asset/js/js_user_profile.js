function enableEditMode(section) {
    if (section === 'profile') {
        const profileName = document.querySelector('.profile__name');
        const profileLocation = document.querySelector('.profile__location');

        // Loại bỏ thuộc tính readonly để cho phép chỉnh sửa
        profileName.removeAttribute('readonly');
        profileLocation.removeAttribute('readonly');

        // Thêm input type="submit" cho phần Profile
        if (!document.querySelector('.profile__edit input')) {
            const saveInput = document.createElement('input');
            saveInput.type = 'submit';  // Thay đổi type thành submit
            saveInput.value = 'Save';
            saveInput.classList.add('save-btn');  // Thêm class CSS cho nút Save
            saveInput.onclick = function (event) {
                event.preventDefault();  // Ngăn chặn hành vi mặc định của form
                saveChanges('profile');
            };
            document.querySelectorAll('.profile__edit')[0].appendChild(saveInput);
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

        // Thêm input type="submit" cho phần Personal Information
        if (!document.querySelectorAll('.profile__edit')[1].querySelector('input')) {
            const saveInput = document.createElement('input');
            saveInput.type = 'submit';  // Thay đổi type thành submit
            saveInput.value = 'Save';
            saveInput.classList.add('save-btn');  // Thêm class CSS cho nút Save
            saveInput.onclick = function (event) {
                event.preventDefault();  // Ngăn chặn hành vi mặc định của form
                saveChanges('personal-info');
            };
            document.querySelectorAll('.profile__edit')[1].appendChild(saveInput);
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

        // Xóa nút Save sau khi lưu
        const saveInput = document.querySelectorAll('.profile__edit')[0].querySelector('input');
        saveInput.remove();

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

        // Xóa nút Save sau khi lưu
        const saveInput = document.querySelectorAll('.profile__edit')[1].querySelector('input');
        saveInput.remove();

        alert('Personal information changes have been saved!');
    }
}
