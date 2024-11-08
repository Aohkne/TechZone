//Quantity Product
let productQuantity = document.querySelector(".product__quantity");
let increase = productQuantity.querySelector(".fa-plus");
let decrease = productQuantity.querySelector(".fa-minus");
let numOrder = productQuantity.querySelector(".product__num");

increase.onclick = () => {
    numOrder.innerHTML = +numOrder.textContent + 1;
};

decrease.onclick = () => {
    if (+numOrder.textContent <= 1) {
        return;
    }
    numOrder.innerHTML = +numOrder.textContent - 1;
};

// Open - close notification

let userItem = document.querySelectorAll('.user__item');
let notificationBtn = userItem[0].querySelectorAll('.user__link')[0];
let notificationPopup = document.querySelector('.notification__popup');

show(notificationBtn, notificationPopup);

//show hide Function
/**
 * Click : object that click
 * Effect : object that show or hide
 */
function show(Click, Effect) {

    Click.onclick = () => {

        if (Effect.style.display == '' || Effect.style.display == 'none') {
            Effect.style.display = 'block';
        } else {
            Effect.style.display = 'none';
        }

    }

    //check if onclick != element before click or child of element
    document.onclick = (event) => {
        if (event.target != document.querySelector('#button_next') && !userItem[0].contains(event.target)) {
            Effect.style.display = 'none';
        }
    }


}




function toggleReplyBox(replyButton) {
    // Tìm vị trí chèn reply-box
    const replyContent = replyButton.closest('.reply-content');

    // Kiểm tra xem reply-box đã tồn tại chưa
    let replyBox = replyContent.nextElementSibling;
    if (!replyBox || !replyBox.classList.contains('reply-box')) {
        // Tạo reply-box nếu chưa tồn tại
        replyBox = document.createElement('div');
        replyBox.className = 'reply-box';
        replyBox.style.display = 'none';

        // Tạo input và button bên trong reply-box
        const input = document.createElement('input');
        input.type = 'text';
        input.className = 'reply-input';
        input.placeholder = 'Type your reply...';

        const button = document.createElement('button');
        button.className = 'reply-send-btn';
        button.innerText = 'Send';

        // Thêm input và button vào reply-box
        replyBox.appendChild(input);
        replyBox.appendChild(button);

        // Chèn reply-box vào sau replyContent
        replyContent.insertAdjacentElement('afterend', replyBox);
    }

    // Đóng khung chỉnh sửa bình luận nếu đang mở
    const editCommentBox = replyContent.parentElement.querySelector('.edit-comment');
    if (editCommentBox && editCommentBox.style.display === 'block') {
        editCommentBox.style.display = 'none';
    }

    // Đóng reply-box nếu đang mở
    if (replyBox.style.display === 'block') {
        replyBox.style.display = 'none';
    } else {
        // Nếu reply-box đang đóng, mở nó lên
        replyBox.style.display = 'block';
    }
}

function toggleEditComment(editButton) {
    // Tìm vị trí chứa nội dung cần chỉnh sửa
    const replyContent = editButton.closest('.reply-content');
    const userContent = replyContent.parentElement.querySelector('.cmt__user-content');

    // Kiểm tra xem edit-comment đã tồn tại chưa
    let editCommentBox = replyContent.querySelector('.edit-comment');

    // Đóng reply-box nếu đang mở
    const replyBox = replyContent.nextElementSibling;
    if (replyBox && replyBox.classList.contains('reply-box') && replyBox.style.display === 'block') {
        replyBox.style.display = 'none';
    }

    if (!editCommentBox) {
        // Tạo edit-comment nếu chưa tồn tại
        editCommentBox = document.createElement('div');
        editCommentBox.className = 'edit-comment';
        editCommentBox.innerText = 'Edit comment';
        editCommentBox.style.cursor = 'pointer';

        // Chèn edit-comment vào replyContent
        replyContent.appendChild(editCommentBox);


        // Thêm sự kiện click để mở chế độ chỉnh sửa
        editCommentBox.onclick = function () {
            // Mở chế độ chỉnh sửa cho nội dung
            userContent.contentEditable = "true";
            userContent.focus(); // Đặt con trỏ vào nội dung

            // Tạo nút Save nếu chưa tồn tại
            let saveButton = replyContent.querySelector('.save-btn');
            if (!saveButton) {
                saveButton = document.createElement('button');
                saveButton.innerText = 'Save';
                saveButton.className = 'save-btn';
                saveButton.type = 'submit'; // Đặt type là submit
                saveButton.onclick = function (event) {
                    event.preventDefault(); // Ngăn chặn hành động submit mặc định
                    // Lưu thay đổi và đóng chế độ chỉnh sửa
                    userContent.contentEditable = "false";
                    userContent.style.border = "none"; // Không thêm đường viền
                    saveButton.remove(); // Xóa nút Save
                };
                replyContent.appendChild(saveButton); // Thêm nút save vào replyContent
            }
            // Ẩn nút Edit comment sau khi mở chế độ chỉnh sửa
            editCommentBox.style.display = 'none';
        };
    } else {
        // Nếu edit-comment đã tồn tại và đang hiển thị
        if (editCommentBox.style.display === 'none') {
            // Hiển thị lại edit-comment
            editCommentBox.style.display = 'block';
        } else {
            // Ẩn edit-comment
            editCommentBox.style.display = 'none';
            userContent.contentEditable = "false"; // Đóng chế độ chỉnh sửa
            userContent.style.border = "none"; // Không thêm đường viền

            // Xóa nút Save nếu đang mở
            const saveButton = replyContent.querySelector('.save-btn');
            if (saveButton) {
                saveButton.remove(); // Xóa nút Save nếu đã tồn tại
            }
        }
    }
}




function toggleReplyBox2(replyButton) {
    // Tìm vị trí chèn reply-box
    const replyContent = replyButton.closest('.reply-content-2');

    // Kiểm tra xem reply-box đã tồn tại chưa
    let replyBox = replyContent.nextElementSibling;
    if (!replyBox || !replyBox.classList.contains('reply-box-2')) {
        // Tạo reply-box nếu chưa tồn tại
        replyBox = document.createElement('div');
        replyBox.className = 'reply-box-2';
        replyBox.style.display = 'none';

        // Tạo input và button bên trong reply-box
        const input = document.createElement('input');
        input.type = 'text';
        input.className = 'reply-input-2';
        input.placeholder = 'Type your reply...';

        const button = document.createElement('button');
        button.className = 'reply-send-btn-2';
        button.innerText = 'Send';

        // Thêm input và button vào reply-box
        replyBox.appendChild(input);
        replyBox.appendChild(button);

        // Chèn reply-box vào sau replyContent
        replyContent.insertAdjacentElement('afterend', replyBox);
    }

    // Đóng khung chỉnh sửa bình luận nếu đang mở
    const editCommentBox = replyContent.parentElement.querySelector('.edit-comment-2');
    if (editCommentBox && editCommentBox.style.display === 'block') {
        editCommentBox.style.display = 'none';
    }

    // Đóng reply-box nếu đang mở
    if (replyBox.style.display === 'block') {
        replyBox.style.display = 'none';
    } else {
        // Nếu reply-box đang đóng, mở nó lên
        replyBox.style.display = 'block';
    }
}

function toggleEditComment2(editButton) {
    // Tìm vị trí chứa nội dung cần chỉnh sửa
    const replyContent = editButton.closest('.reply-content-2');
    const userContent = replyContent.parentElement.querySelector('.reply__user-content');

    // Kiểm tra xem edit-comment đã tồn tại chưa
    let editCommentBox = replyContent.querySelector('.edit-comment-2');

    // Đóng reply-box nếu đang mở
    const replyBox = replyContent.nextElementSibling;
    if (replyBox && replyBox.classList.contains('reply-box-2') && replyBox.style.display === 'block') {
        replyBox.style.display = 'none';
    }

    if (!editCommentBox) {
        // Tạo edit-comment nếu chưa tồn tại
        editCommentBox = document.createElement('div');
        editCommentBox.className = 'edit-comment-2';
        editCommentBox.innerText = 'Edit comment';
        editCommentBox.style.cursor = 'pointer';

        // Chèn edit-comment vào replyContent
        replyContent.appendChild(editCommentBox);


        // Thêm sự kiện click để mở chế độ chỉnh sửa
        editCommentBox.onclick = function () {
            // Mở chế độ chỉnh sửa cho nội dung
            userContent.contentEditable = "true";
            userContent.focus(); // Đặt con trỏ vào nội dung

            // Tạo nút Save nếu chưa tồn tại
            let saveButton = replyContent.querySelector('.save-btn-2');
            if (!saveButton) {
                saveButton = document.createElement('button');
                saveButton.innerText = 'Save';
                saveButton.className = 'save-btn-2';
                saveButton.type = 'submit'; // Đặt type là submit
                saveButton.onclick = function (event) {
                    event.preventDefault(); // Ngăn chặn hành động submit mặc định
                    // Lưu thay đổi và đóng chế độ chỉnh sửa
                    userContent.contentEditable = "false";
                    userContent.style.border = "none"; // Không thêm đường viền
                    saveButton.remove(); // Xóa nút Save
                };
                replyContent.appendChild(saveButton); // Thêm nút save vào replyContent
            }
            // Ẩn nút Edit comment sau khi mở chế độ chỉnh sửa
            editCommentBox.style.display = 'none';
        };
    } else {
        // Nếu edit-comment đã tồn tại và đang hiển thị
        if (editCommentBox.style.display === 'none') {
            // Hiển thị lại edit-comment
            editCommentBox.style.display = 'block';
        } else {
            // Ẩn edit-comment
            editCommentBox.style.display = 'none';
            userContent.contentEditable = "false"; // Đóng chế độ chỉnh sửa
            userContent.style.border = "none"; // Không thêm đường viền

            // Xóa nút Save nếu đang mở
            const saveButton = replyContent.querySelector('.save-btn-2');
            if (saveButton) {
                saveButton.remove(); // Xóa nút Save nếu đã tồn tại
            }
        }
    }
}




function toggleReplyContent(element) {
    const replies = document.querySelectorAll('.reply-user'); // Lấy tất cả các phần tử reply-user
    replies.forEach(reply => {
        if (reply.style.display === "none" || reply.style.display === "") {
            reply.style.display = "block"; // Hiện phần reply
            element.querySelector('.more-arrow').classList.remove('fa-angle-down');
            element.querySelector('.more-arrow').classList.add('fa-angle-up'); // Đổi mũi tên lên
        } else {
            reply.style.display = "none"; // Ẩn phần reply
            element.querySelector('.more-arrow').classList.remove('fa-angle-up');
            element.querySelector('.more-arrow').classList.add('fa-angle-down'); // Đổi mũi tên xuống
        }
    });
}

