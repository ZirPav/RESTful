function getUsers() {
    fetch('http://localhost:8080/adminAllUsers')
        .then((res) => res.json())
        .then((data) => {
            let output = '';
            data.forEach(function (user) {

                let userRoles = '';
                for (let i = 0; i < user.roles.length; i++){
                    userRoles+=`${user.roles[i].name} `
                }

                output += `
                <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${userRoles}</td>
                                                
                <td>
                <a class="btn btn-info" role="button"
                data-toggle="modal" data-target="#modalEdit" id="edit-post">Edit</a>                              
                </td>

                <td>
                <a class="btn btn-danger" role="button"
                data-toggle="modal" data-target="#modalDelete" id="delete-post">Delete</a>
                </td>
                
              </tr>
          `;
            });
            document.getElementById('bodyAllUser').innerHTML = output;
        })
}

getUsers()