const requestURL = 'http://localhost:8080/getUser'

function getUser() {
    fetch(requestURL)
        .then((res) => res.json())
        .then((user) => {

            let userRoles = '';
            for (let i = 0; i < user.roles.length; i++){
               userRoles+=`${user.roles[i].name} `
            }

            let output = '<tr>';
            output += `
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${userRoles}</td>
                `;
            output += '</tr>';
            document.getElementById('bodyUser').innerHTML = output;
        })
}

function getHeader() {
    fetch(requestURL)
        .then((res) => res.json())
        .then((user) => {
            let userRoles = '';
            for (let i = 0; i < user.roles.length; i++){
                userRoles+=`${user.roles[i].name} `
            }
            let output = ''
            output += `
                ${user.email} with roles: ${userRoles}
                `;
            document.getElementById('spanHeader').innerHTML = output;
        })
}

getHeader()
getUser()