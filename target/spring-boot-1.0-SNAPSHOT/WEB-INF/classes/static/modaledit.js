document.getElementById('modalEditForm').addEventListener('submit', editPost)

function editPost(e){
    e.preventDefault();

    let id = document.getElementById('IdEditUser').value;
    let firstName = document.getElementById('firstNameEditUser').value;
    let lastName = document.getElementById('lastNameEditUser').value;
    let age = document.getElementById('ageEditUser').value;
    let email = document.getElementById('emailEditUser').value;
    let password = document.getElementById('passwordEditUser').value;
    let roles = setRoles(Array.from(document.getElementById('roleEditUser').selectedOptions)
        .map(option => option.value));

    fetch('http://localhost:8080/userEdit', {
        method:'PUT',
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-type':'application/json'
        },
        body:JSON.stringify({
            id:id,
            firstName:firstName,
            lastName:lastName,
            age:age,
            email:email,
            password:password,
            roles:roles
        })
    })
        .finally(() => {
            $('#modalEdit').modal('hide')
            getUsers();
        })
}