function getRoles() {
    fetch('http://localhost:8080/adminAllRoles')
        .then((res) => res.json())
        .then((data) => {
            let output = '';
            data.forEach(function (role) {
                output += `
                   <option>${role.name}</option>
          `;
            });
            document.getElementById('roleNewUser').innerHTML = output;
            document.getElementById('roleEditUser').innerHTML = output;
            document.getElementById('roleDeleteUser').innerHTML = output;
        })
}

getRoles()