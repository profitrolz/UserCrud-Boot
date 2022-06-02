$(async function () {
    let url = 'http://localhost:8080/api/v1/users';
    let promise = await fetch(url, {
        credentials: 'same-origin'
    });

    if (promise.ok) {
        let objects = await promise.json();
        createTable(objects);
    } else {
        $('#error-text').text("HTTP error " + promise.status);
        $('#myModal').modal('show');
    }

});


function createTable(objects) {
    let table = document.getElementById("userTable");
    let root = 'http://localhost:8080/';
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
        td5.appendChild(document.createTextNode("roles here"));
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
        warButton.setAttribute("data-target", "#deleteUser");
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
    $('#editUser').modal('show');
    let url = root + 'api/v1/users/' + userId;

    let response = await fetch(url);
    if (response.ok) {
        let user = await response.json();
        let id = user.id;
        let firstName = user.firstName;
        let lastName = user.lastName;
        let login = user.login;
        let password = user.password;

        $('#formGroupIdEdit').val(id);
        $('#formGroupFirstnameEdit').val(firstName);
        $('#formGroupLastnameEdit').val(lastName);
        $('#formGroupLoginEdit').val(login);
        $('#formGroupPassEdit').val(password);
    }
}

async function updateUser(url) {
    let body = {};
    const id = document.getElementById("formGroupIdEdit").value;
    url = url + id;
    body.firstName = document.getElementById("formGroupFirstnameEdit").value;
    body.lastName = document.getElementById("formGroupLastnameEdit").value;
    body.login = document.getElementById("formGroupLoginEdit").value;
    body.password = document.getElementById("formGroupPassEdit").value;
    let res = await fetch(url, {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
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

    } else {
        $('#error-text').text("HTTP error " + promise.status);
        $('#myModal').modal('show');
    }



}