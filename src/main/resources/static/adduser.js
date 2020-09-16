document.getElementById('addFormNewUser').addEventListener('submit', addNewUser);

function addNewUser(e){
    e.preventDefault();

    let firstName = document.getElementById('firstNameNewUser').value;
    let lastName = document.getElementById('lastNameNewUser').value;
    let age = document.getElementById('ageNewUser').value;
    let email = document.getElementById('emailNewUser').value;
    let password = document.getElementById('passwordNewUser').value;
    let roles = setRoles(Array.from(document.getElementById('roleNewUser').selectedOptions)
        .map(option => option.value));

    fetch('http://localhost:8080/addUser', {
        method: 'POST',
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            firstName: firstName,
            lastName: lastName,
            age: age,
            email: email,
            password: password,
            roles: roles
        })
    })
        .finally(() => {
            document.getElementById('idUsersTable').click();
            getUsers();
            document.getElementById('addFormNewUser').reset();
        })
}

function setRoles(someRoles) {
    let roles = [];
    if (someRoles.indexOf('USER') >= 0) {
        roles.push({'id': 1, 'name': 'USER'});
    }
    if (someRoles.indexOf('ADMIN') >= 0) {
        roles.push({'id': 2, 'name': 'ADMIN'});
    }
    return roles;
}

