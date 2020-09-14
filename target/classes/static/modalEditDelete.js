document.getElementById('modalEditForm').addEventListener('submit', editPost)
document.getElementById('modalDeleteForm').addEventListener('submit',)

function editPost(e){
    e.preventDefault();

    let id = document.getElementById('IdEditUser').value;
    let firstName = document.getElementById('firstNameEditUser').value;
    let lastName = document.getElementById('lastNameEditUser').value;
    let age = document.getElementById('ageEditUser').value;
    let email = document.getElementById('emailEditUser').value;
    let password = document.getElementById('passwordEditUser').value;
    let roles = document.getElementById('roleEditUser').value;

    fetch('https://jsonplaceholder.typicode.com/posts', {
        method:'POST',
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-type':'application/json'
        },
        body:JSON.stringify({id:id, firstName:firstName, lastName:lastName, age:age, email:email, password:password, roles:roles})
    })
        .then((res) => res.json())
        .then((data) => console.log(data))
}