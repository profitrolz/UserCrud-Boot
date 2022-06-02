$(async function () {
    let url = 'http://localhost:8080/api/v1/users';
    let promise = await fetch(url, {
        credentials: 'same-origin'
    });

    if(promise.ok) {
        let objects = await promise.json();
        createTable(objects);
    } else {
        $('#error-text').text("HTTP error " + promise.status);
        $('#myModal').modal('show');
    }

});

function createTable(objects) {
    let table = document.getElementById("userTable");

    for (let i = 0; i < objects.length; i++) {
        let tr = document.createElement("tr");
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
        warButton.setAttribute("class", "btn btn-warning btn-sm");
        warButton.appendChild(document.createTextNode("Edit"));

        let dangerButton = document.createElement("button");
        dangerButton.setAttribute("type", "button");
        dangerButton.setAttribute("class", "btn btn-danger btn-sm");
        dangerButton.appendChild(document.createTextNode("Delete"));

        let td6 = document.createElement("td");
        td6.appendChild(warButton);
        tr.appendChild(td6);

        let td7 = document.createElement("td");
        td7.appendChild(dangerButton);
        tr.appendChild(td7);
        table.appendChild(tr);
    }}