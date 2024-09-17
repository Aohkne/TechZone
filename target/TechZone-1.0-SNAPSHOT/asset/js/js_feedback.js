//Feedback


//check
(function getInfomation() {


    //information
    let name = document.querySelectorAll('input[type ="text"]')[0];
    let email = document.querySelectorAll('input[type ="text"]')[1];
    let detail = document.querySelector('textarea');

    //check null
    name.onblur = (e) => {
        checkNullOfFeedback(name, 'Name');
    }

    email.onblur = () => {
        // checkNull(email, 'Email');
        checkMail(email);
    }

    detail.onblur = () => {
        checkNullOfFeedback(detail, 'Detail');
    }

    //send information
    let sendBtn = document.querySelector('.btn__feedback button');
    sendBtn.onclick = () => {
        if (!checkNullOfFeedback(name, 'Name') && !checkMail(email) && !checkNullOfFeedback(detail, 'Detail')) {

            const data = {
                name: name.value,
                email: email.value,
                detail: detail.value,
            };

            postData(data);

            //Show thanks

            let form = document.querySelector('.feedback__container');
            form.classList.add('row')


            let formTitle = document.createElement('div');
            formTitle.classList.add('form__title');
            formTitle.classList.add('col');
            formTitle.classList.add('l-12');
            formTitle.classList.add('m-12');
            formTitle.classList.add('c-12');
            formTitle.textContent = 'Thank you for taking our feedback!';

            let formDescription = document.createElement('div');
            formDescription.classList.add('form__description');
            formDescription.classList.add('col');
            formDescription.classList.add('l-12');
            formDescription.classList.add('m-12');
            formDescription.classList.add('c-12');
            formDescription.textContent = 'Your input is invaluable and will help us improve our services/products';

            form.innerHTML = '';
            form.appendChild(formTitle);
            form.appendChild(formDescription);
        }

    }




})();

//post Data
async function postData(data) {
    const formData = new FormData();

    formData.append("entry.887022189", data.name);
    formData.append("entry.627124877", data.email);
    formData.append("entry.1220380457", data.detail);

    fetch("https://docs.google.com/forms/d/e/1FAIpQLSdk7PXeFaA4aNsI-ljJ9dZLwN--AcDXiYwl2zzM_cyWNlJcJg/formResponse", {
        method: "POST",
        body: formData,
        mode: "no-cors",
    })
}








//function check null
function checkNullOfFeedback(e, type) {
    let formItem = document.querySelectorAll('.form__item');


    //remove  message and validated 
    formItem.forEach((item) => {
        if (item.contains(e) && item.querySelector('.label-message')) {
            item.querySelector('.form__container').classList.remove('validated');
            item.querySelector('.label-message').innerHTML = '';
        }
    });

    var message;
    if (e) {
        if (e.value == null || e.value == '') {
            message = 'Please enter ' + type;
        } else {
            message = false;
        }

        //add message and validated 
        if (message) {
            formItem.forEach((item) => {
                if (item.contains(e)) {
                    item.querySelector('.form__container').classList.add('validated');
                    item.querySelector('.label-message').innerHTML = '<i class="fa-solid fa-triangle-exclamation"></i>' + message;
                }
            });
        }

    }

    return message;
};

//check mail

function checkMail(mail) {
    let formItem = document.querySelectorAll('.form__item');
    var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    var message;


    //remove  message and validated 
    formItem.forEach((item) => {
        if (item.contains(mail) && item.querySelector('.label-message')) {
            item.querySelector('.form__container').classList.remove('validated');
            item.querySelector('.label-message').innerHTML = '';
        }
    });

    if (mail.value.match(regex)) {
        message = false;
    }
    else {
        message = 'Invalid email (eg: email@domain.com)';
    }


    //add message and validated 
    if (message) {
        formItem.forEach((item) => {
            if (item.contains(mail)) {
                item.querySelector('.form__container').classList.add('validated');
                item.querySelector('.label-message').innerHTML = '<i class="fa-solid fa-triangle-exclamation"></i>' + message;
            }
        });
    }

    return message;
};
