    $(document).ready(function () {
    $('#editUser').on('shown.bs.modal', function (e) {
        let userId = $(e.relatedTarget).data('id');
        let cols = $('#' + userId + ' td');
        let id = $(cols[0]).text();
        let firstName = $(cols[1]).text();
        let lastName = $(cols[2]).text();
        let login = $(cols[3]).text();
        let password = $(cols[4]).text();

        $('#formGroupIdEdit').val(id);
        $('#formGroupFirstnameEdit').val(firstName);
        $('#formGroupLastnameEdit').val(lastName);
        $('#formGroupLoginEdit').val(login);
        $('#formGroupPassEdit').val(password);
    })

    $('#deleteUser').on('shown.bs.modal', function (e) {
    let userId = $(e.relatedTarget).data('id');
    let cols = $('#' + userId + ' td');
    let id = $(cols[0]).text();
    let firstName = $(cols[1]).text();
    let lastName = $(cols[2]).text();
    let login = $(cols[3]).text();
    let password = $(cols[4]).text();

    $('#formGroupIdDelete').val(id);
    $('#formGroupFirstnameDelete').val(firstName);
    $('#formGroupLastnameDelete').val(lastName);
    $('#formGroupLoginDelete').val(login);
    $('#formGroupPassDelete').val(password);
})
});
