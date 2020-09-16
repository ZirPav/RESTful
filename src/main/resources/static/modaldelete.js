document.getElementById('modalDeleteForm').addEventListener('submit', deletePost)

function deletePost(e){
    e.preventDefault();

    let id = document.getElementById('IdDeleteUser').value;
    let firstName = document.getElementById('firstNameDeleteUser').value;
    let lastName = document.getElementById('lastNameDeleteUser').value;
    let age = document.getElementById('ageDeleteUser').value;
    let email = document.getElementById('emailDeleteUser').value;
    let password = document.getElementById('passwordDeleteUser').value;
    let roles = setRoles(Array.from(document.getElementById('roleDeleteUser').selectedOptions)
        .map(option => option.value));

    fetch('http://localhost:8080/userDelete', {
        method:'DELETE',
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
            roles:roles})
    })
        .finally(() => {
            $('#modalDelete').modal('hide')
            getUsers();
        })
}
