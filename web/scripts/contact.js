/**
 * Created by birsan on 4/13/2016.
 */
function addPhoneNumber() {
    appendRowToTable("phoneNumTable");
}

function appendRowToTable(table) {
    $('#' + table + ' tr:last').after(
        "<tr><td><input type='text' class='phoneNumber' name='phoneNumber'/></td><td></td>"
    );
    //var row = table.insertRow(-1);
    //row.style.borderWidth = "1px";
    //row.style.borderStyle = "solid";
    //var cell1 = row.insertCell(0);
    //var cell2 = row.insertCell(1);
    //cell1.innerHTML = "<input type='text' class='phoneNumber' name='phoneNumber'/>";
    //cell2.innerHTML = "";
}

function appendRowToTablePhoneNumbers(table, text) {
    $('#' + table + ' tr:last').after(
        "<tr>" +
            "<td>" +
                "<input type='text' class='phoneNumber' name='phoneNumber' value='" + text + "'/>" +
            "</td>" +
            "<td></td>" +
        "</tr>");
    //var row = table.insertRow(-1);
    //row.style.borderWidth = "1px";
    //row.style.borderStyle = "solid";
    //var cell1 = row.insertCell(0);
    //var cell2 = row.insertCell(1);
    //cell1.innerHTML = "<input type='text' class='phoneNumber' name='phoneNumber' value=" + text + "</input";
    //cell2.innerHTML = "";
}

function reloadDataTable() {
    $('#listTable').DataTable().ajax.reload();
}
function createContact() {
    var firstName = $('#firstName').val();
    var lastName = $('#lastName').val();
    var company = $('#company').val();
    var id = $('#id').val();
    var rawPhoneNumbers = getPhoneNumbers();
    var phoneNumbers = [];
    for (var i = 0; i < rawPhoneNumbers.length; i++) {
        phoneNumbers[i] = {"number": rawPhoneNumbers[i]}
    }
    var data = {
        "id": id, "firstName": firstName, "lastName": lastName,
        "company": company, "phoneNumbers": phoneNumbers
    };

    if (id === "") {
        $.ajax({
            url: "api/contacts",
            type: "POST",
            data: JSON.stringify(data),
            processData: false,
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                $('#listTable').DataTable().ajax.reload();
                //var my_array = [];
                //$(":input[class^=phoneNumber]").each(function (index) {
                //    my_array.push("");
                //});
                $("#phoneNumTable tr").remove();
                $('#phoneNumTable').after(
                    "<tr><td><input type='text' class='phoneNumber' name='phoneNumber'/></td><td></td>"
                );
                $('#ContactDiv').hide();
            },
            complete: function (xhr, textStatus) {
                $('#errors').text(xhr.responseText);
                $('#errorsDiv').show();
            }
        });
    }
    else {
        $.ajax({
            url: "api/contacts",
            type: "PUT",
            data: JSON.stringify(data),
            processData: false,
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                $('#ContactDiv').hide();
                var my_array = [];
                $("#phoneNumTable tr").remove();
                 $('#phoneNumTable').after(
                    "<tr><td><input type='text' class='phoneNumber' name='phoneNumber'/></td><td></td>"
                );
                reloadDataTable();
            },
            complete: function (xhr, textStatus) {
                $('#errors').text(xhr.responseText);
                $('#errorsDiv').show();
            }
        });
    }
}

function getPhoneNumbers() {
    var my_array = [];
    $(":input[class^=phoneNumber]").each(function (index, element) {
        my_array.push(element.value);
    });
    return my_array;
}

function loadContact(object) {
    var company = object.getAttribute("data-company");
    var firstName = object.getAttribute("data-first-name");
    var lastName = object.getAttribute("data-last-name");
    var id = object.getAttribute("data-id");
    var phoneNumber = object.getAttribute("data-phone-number");
    $('#company').val(company);
    $('#firstName').val(firstName);
    $('#lastName').val(lastName);
    $('#id').val(id);
    $('.phoneNumber').val(phoneNumber);
    // split la phoneNumber dupa ,
    // pentru fiecare phone number creezi <td><input class="phoneNumber" type="text" name="phoneNumber"/>phoneNumber value</td>
    // adaugi in phoneNumTable fiecare input de mai sus
    if(phoneNumber!=null) {
        var phoneNumbers = phoneNumber.split(',');
        for (var i = 0; i < phoneNumbers.length; i++) {
            appendRowToTablePhoneNumbers("phoneNumTable", phoneNumbers[i]);
            console.log(i);
        }
        console.log(phoneNumbers);
    }
    $('#ContactDiv').toggle();
    $('errorsDiv').hide();
}

function deleteContact(object) {
    var id = object.getAttribute("data-id");
    $.ajax({
        url: "api/contacts/" + id,
        type: "DELETE",
        success: function (response) {
            reloadDataTable();
        },
        complete: function (xhr, textStatus) {
            alert(xhr.responseText);
        }
    });

}

function loadDataTable() {
    $('#listTable').DataTable({
        "processing": true,
        "serverSide": false,
        "ajax": "api/contacts",
        "columns": [
            {"data": "company"},
            {"data": "firstName"},
            {"data": "id"},
            {"data": "lastName"},
            {"data": "phoneNumber"},
            {
                "data": null,
                "render": function (object) {
                    return '<button data-company="' + object.company + '" data-first-name="' + object.firstName + '" data-last-name="' + object.lastName + '" data-id="' + object.id +
                        '"data-phone-number="' + object.phoneNumber + '" onclick="loadContact(this)" class=' + "editButton" + '>' + 'Edit' + '</button>';
                }
            },
            {
                "data": null,
                "render": function (object) {
                    return '<button  data-id="' + object.id + '" onclick="deleteContact(this)">' + 'Delete' + '</button>';
                }
            }
        ],
        "order": [[0, "asc"], [2, "asc"]],
        "columnDefs": [
            {
                "targets": 'no-sort',
                "orderable": false
            },
            {
                "targets": 'sort',
                "orderable": true,
                "searchable": true
            }],
        "pagingType": "simple"
    });
}

function gotThis() {
    $('#errors').text("");
    $('#errorsDiv').hide();
}

$(document).ready(function () {
    $('#visibleCreate').click(function () {
        loadContact(this)
    });
    loadDataTable();
});