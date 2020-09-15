

function openModalWindow(id) {
        document.getElementById('IdEditUser').value = id;
        document.getElementById("firstNameEditUser").value = $('#userFirstNameGod-' + id).text();
}


/* document.getElementById('firstNameEditUser');
         document.getElementById('lastNameEditUser');
         document.getElementById('ageEditUser');
         document.getElementById('emailEditUser');
         document.getElementById('passwordEditUser');
         document.getElementById('roleEditUser');*/


/*  $('#UsersTable').on('click', '#callModalEdit', function () {
    let id = this.id.slice(this.id.lastIndexOf('-') + 1);
    $('#IdEditUser').attr('value', id);
    document.getElementById("firstNameEditUser").value = $('#userFirstNameGod' + id).text();
    document.getElementById("lastNameEditUser").value = $('#userLastNameGod' + id).text();
    document.getElementById("ageEditUser").value = $('#userAgeGod' + id).text();
    document.getElementById("emailEditUser").value = $('#userEmailGod' + id).text();
    document.getElementById("passwordEditUser").value = "";
    let userRow = $("[id=" + id + "]");
    let rolesList = ["ADMIN", "USER"];
    let userRoles = userRow.find('#userRolesGod' + id).text();
    $('#roleEditUser').empty();
    rolesList.forEach(function (value) {
        if (userRoles.includes(value)) {
            $('#roleEditUser').append('<option id="option"' + value + ' value="' + value + '" selected>' + value + '</option>')
        } else {
            $('#roleEditUser').append('<option id="option"' + value + ' value="' + value + '">' + value + '</option>')
        }
    });
});*/


/*
function paintEdit(currentID){

let idPaint = document.getElementById('IdEditUser');
    let firstNamePaint = document.getElementById('firstNameEditUser');
    let lastNamePaint = document.getElementById('lastNameEditUser');
    let agePaint = document.getElementById('ageEditUser');
    let emailPaint = document.getElementById('emailEditUser');
    let passwordPaint = document.getElementById('passwordEditUser');
    let rolesPaint = document.getElementById('roleEditUser');

    fetch('https://localhost:8080/getUser/currentID')
        .then((response) => response.json())
        .then((user) => {

            let userRoles = '';
            for (let i = 0; i < user.roles.length; i++){
                userRoles+=`${user.roles[i].name} `
            }

            idPaint.value = `${user.id}`;
            firstNamePaint.value = `${user.firstName}`;
            lastNamePaint = `${user.lastName}`
            agePaint = `${user.lastName}`
            emailPaint = `${user.email}`
            passwordPaint = ``
            rolesPaint = `${userRoles}`
        })
}*/
