// Đợi cho DOM đã được tải hoàn toàn
document.addEventListener("DOMContentLoaded", function () {
    // Fetch dữ liệu từ file JSON
    fetch('./asset/json/user_help_question.json') // Đường dẫn đến file JSON
        .then(response => {
            // Kiểm tra nếu phản hồi OK
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Tạo phần tử chứa FAQ
            const faqContainer = document.querySelector('.faq-container'); // Lấy phần tử chứa FAQ

            // Lặp qua từng câu hỏi trong dữ liệu
            data.faq.forEach(item => {
                const questionTitle = document.createElement('div');
                questionTitle.className = 'question-title';
                questionTitle.innerHTML = `
                    <div class="question-head" onclick="toggleAnswer(this)">
                        <div class="question">${item.question}</div>
                        <i class="more-arrow fa-solid fa-angle-down"></i>
                    </div>
                    <div class="answer" style="display: none;">${item.answer}</div>
                `;
                faqContainer.appendChild(questionTitle); // Thêm câu hỏi vào FAQ container
            });
        })
        .catch(error => {
            console.error('Error loading JSON:', error);
        });
});

// Hàm toggle để hiển thị hoặc ẩn câu trả lời
function toggleAnswer(element) {
    const answer = element.nextElementSibling; // Lấy phần tử sibling (câu trả lời)
    const arrow = element.querySelector('.more-arrow'); // Lấy icon bên trong question-head

    // Toggle hiển thị answer
    if (answer.style.display === 'none' || answer.style.display === '') {
        answer.style.display = 'block'; // Hiển thị câu trả lời
        arrow.classList.remove('fa-angle-down'); // Thay đổi icon thành angle-up
        arrow.classList.add('fa-angle-up'); // Thêm icon angle-up
    } else {
        answer.style.display = 'none'; // Ẩn câu trả lời
        arrow.classList.remove('fa-angle-up'); // Thay đổi icon thành angle-down
        arrow.classList.add('fa-angle-down'); // Thêm icon angle-down
    }
}
