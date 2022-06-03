$(async function () {
    createTable();
});


async function createTable() {
    const root = window.location.origin;
    const objects = await getUsers(root);
    let table = document.getElementById("userTable");
    table.innerHTML = "";
    for (let i = 0; i < objects.length; i++) {
        let tr = document.createElement("tr");
        tr.setAttribute("id", "lineId" + objects[i].id);
        let th = document.createElement("th");
        th.setAttribute("scope", "row");
        th.appendChild(document.createTextNode(objects[i].id));
        tr.appendChild(th);
        let td1 = document.createElement("td");
        td1.appendChild(document.createTextNode(objects[i].firstName));
        tr.appendChild(td1);
        let td2 = document.createElement("td");
        td2.appendChild(document.createTextNode(objects[i].lastName));
        tr.appendChild(td2);
        let td3 = document.createElement("td");
        td3.appendChild(document.createTextNode(objects[i].login));
        tr.appendChild(td3);
        let td4 = document.createElement("td");
        td4.appendChild(document.createTextNode(objects[i].password));
        tr.appendChild(td4);
        let td5 = document.createElement("td");
        let roles = '';
        for (let j = 0; j < objects[i].roles.length; j++) {
            roles = roles + objects[i].roles[j].name + ' ';
        }
        td5.appendChild(document.createTextNode(roles));
        tr.appendChild(td5);

        let warButton = document.createElement("button");
        warButton.setAttribute("type", "button");
        warButton.setAttribute("class", "btn btn-primary btn-sm");
        warButton.setAttribute("data-target", "#editUser");
        warButton.addEventListener("click", function () {
            editButtonClick(root, objects[i].id)
        });
        warButton.appendChild(document.createTextNode("Edit"));


        let dangerButton = document.createElement("button");
        dangerButton.setAttribute("type", "button");
        dangerButton.setAttribute("class", "btn btn-danger btn-sm");
        dangerButton.setAttribute("data-target", "#deleteUser");
        dangerButton.addEventListener("click", function () {
            deleteButtonClick(root, objects[i].id)
        });
        dangerButton.appendChild(document.createTextNode("Delete"));

        let td6 = document.createElement("td");
        td6.appendChild(warButton);
        tr.appendChild(td6);

        let td7 = document.createElement("td");
        td7.appendChild(dangerButton);
        tr.appendChild(td7);
        table.appendChild(tr);
    }
}

async function editButtonClick(root, userId) {
    const user = await getUserById(root, userId);
    const roles = await getRoles(root);

    let id = user.id;
    let firstName = user.firstName;
    let lastName = user.lastName;
    let login = user.login;
    let password = user.password;

    $('#FormRoleSelect').empty();
    $('#formGroupIdEdit').val(id);
    $('#formGroupFirstnameEdit').val(firstName);
    $('#formGroupLastnameEdit').val(lastName);
    $('#formGroupLoginEdit').val(login);
    $('#formGroupPassEdit').val(password);
    for (let i = 0; i < roles.length; i++) {
        let selected = false;
        if (user.roles.filter(e => e.id === roles[i].id).length > 0) {
            selected = true;
        }
        $('#FormRoleSelect').append(new Option(roles[i].name, roles[i].id, false, selected));
    }

    $('#editUser').modal('show');

}

async function deleteButtonClick(root, userId) {
    const user = await getUserById(root, userId);
    const roles = await getRoles(root);

    let id = user.id;
    let firstName = user.firstName;
    let lastName = user.lastName;
    let login = user.login;
    let password = user.password;

    $('#FormRoleSelectDelete').empty();
    $('#formGroupIdDelete').val(id);
    $('#formGroupFirstnameDelete').val(firstName);
    $('#formGroupLastnameDelete').val(lastName);
    $('#formGroupLoginDelete').val(login);
    $('#formGroupPassDelete').val(password);
    for (let i = 0; i < roles.length; i++) {
        let selected = false;
        if (user.roles.filter(e => e.id === roles[i].id).length > 0) {
            selected = true;
        }
        $('#FormRoleSelectDelete').append(new Option(roles[i].name, roles[i].id, false, selected));
    }

    $('#deleteUser').modal('show');

}

async function saveUser(){
    let body = {};
    let url = window.location.origin + "/api/v1/users";
    body.firstName = document.getElementById("formGroupFirstname").value;
    body.lastName = document.getElementById("formGroupLastname").value;
    body.login = document.getElementById("formGroupLogin").value;
    body.password = document.getElementById("formGroupPass").value;
    let roles = [];

    $('#formGroupRoles option').each(function () {
        let role = {};
        if (this.selected) {
            role.id = this.value;
            roles.push(role);
        }
    });

    body.roles = roles;

    const json = JSON.stringify(body);
    await fetch(url, {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: json
    });

    createTable();
    $('#myTab a[href="#admin"]').tab('show');

    document.getElementById("formGroupFirstname").value = "";
    document.getElementById("formGroupLastname").value = "";
    document.getElementById("formGroupLogin").value = "";
    document.getElementById("formGroupPass").value = "";
    $('#formGroupRoles option').each(function () {
        if (this.selected) {
            this.selected = false;
        }
    });


}

async function updateUser(url) {
    let body = {};
    const id = document.getElementById("formGroupIdEdit").value;
    url = url + id;
    body.firstName = document.getElementById("formGroupFirstnameEdit").value;
    body.lastName = document.getElementById("formGroupLastnameEdit").value;
    body.login = document.getElementById("formGroupLoginEdit").value;
    body.password = document.getElementById("formGroupPassEdit").value;
    let roles = [];

    $('#FormRoleSelect option').each(function () {
        let role = {};
        if (this.selected) {
            role.id = this.value;
            roles.push(role);
        }
    });

    body.roles = roles;

    const json = JSON.stringify(body);

    await fetch(url, {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: json
    });

    $('#editUser').modal('hide');

    let promise = await fetch('http://localhost:8080/api/v1/users/' + id, {
        credentials: 'same-origin'
    });

    if (promise.ok) {
        let user = await promise.json();
        let element = document.getElementById("lineId" + id);
        element.cells[1].innerText = user.firstName;
        element.cells[2].innerText = user.lastName;
        element.cells[3].innerText = user.login;
        element.cells[4].innerText = user.password;
        let roles = '';
        for (let j = 0; j < user.roles.length; j++) {
            roles = roles + user.roles[j].name + ' ';
        }
        element.cells[5].innerText = roles;
    } else {
        $('#error-text').text("HTTP error " + promise.status);
        $('#myModal').modal('show');
    }
}

async function deleteUser() {
    const root = window.location.origin;
    const id = document.getElementById('formGroupIdDelete').value;
    await fetch(root + '/api/v1/users/' + id, {
        method: 'Delete',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
    });

    $('#deleteUser').modal('hide');
    createTable();


}

async function getUserById(root, userId) {
    const userUrl = root + '/api/v1/users/' + userId;

    let response = await fetch(userUrl);
    if (response.ok) {
        return await response.json();
    } else {
        alert("error");
    }
}

async function getUsers(root) {
    const usersUrl = root + '/api/v1/users/';

    let response = await fetch(usersUrl);
    if (response.ok) {
        return await response.json();
    } else {

    }
}

async function getRoles(root) {
    const roleUrl = root + '/api/v1/roles/';
    const resp = await fetch(roleUrl);
    if (resp.ok) {
        return await resp.json();
    } else {
        alert("error");
    }
}
