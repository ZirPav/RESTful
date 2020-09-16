function openModalWindow(id) {
        document.getElementById('IdEditUser').value = id;
        document.getElementById("firstNameEditUser").value = $('#userFirstNameGod-' + id).text();
        document.getElementById("lastNameEditUser").value = $('#userLastNameGod-' + id).text();
        document.getElementById("ageEditUser").value = $('#userAgeGod-' + id).text();
        document.getElementById("emailEditUser").value = $('#userEmailGod-' + id).text();
        document.getElementById("passwordEditUser").value = "";
}

function openModalWindowDel(id) {
        document.getElementById('IdDeleteUser').value = id;
        document.getElementById("firstNameDeleteUser").value = $('#userFirstNameGod-' + id).text();
        document.getElementById("lastNameDeleteUser").value = $('#userLastNameGod-' + id).text();
        document.getElementById("ageDeleteUser").value = $('#userAgeGod-' + id).text();
        document.getElementById("emailDeleteUser").value = $('#userEmailGod-' + id).text();
        document.getElementById("passwordDeleteUser").value = "";
}




