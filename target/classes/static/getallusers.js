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
                <td id="userIdGod-${user.id}">${user.id}</td>
                <td id="userFirstNameGod-${user.id}">${user.firstName}</td>
                <td id="userLastNameGod-${user.id}">${user.lastName}</td>
                <td id="userAgeGod-${user.id}">${user.age}</td>
                <td id="userEmailGod-${user.id}">${user.email}</td>
                <td id="userRolesGod-${user.id}">${userRoles}</td>
                                                
                <td>
                <a class="btn btn-info" role="button"
                data-toggle="modal" data-target="#modalEdit" id="callModalEdit"
                onclick="openModalWindow(${user.id})">Edit</a>        
                </td>

                <td>
                <a class="btn btn-danger" role="button"
                data-toggle="modal" data-target="#modalDelete" id="delete-post"
                onclick="openModalWindowDel(${user.id})">Delete</a>
                </td>
              </tr>
              
          `;
            });
            document.getElementById('bodyAllUser').innerHTML = output;
        })
}
getUsers()


