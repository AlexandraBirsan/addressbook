/**
 * Created by birsan on 4/13/2016.
 */
function addPhoneNumber() {
    var table = document.getElementById("phoneNumTable");
    appendRowToTable(table);
}

function appendRowToTable(table) {
    var row = table.insertRow(-1);
    row.style.borderWidth = "1px";
    row.style.borderStyle = "solid";
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    cell1.innerHTML = "<input type='text' class='phoneNumber' name='phoneNumber'/>";
    cell2.innerHTML = "";
}

/**
 * Created by birsan on 5/9/2016.
 */
function createContact() {
    var firstName = $('#firstName').val();
    var lastName = $('#lastName').val();
    var company = $('#company').val();
    var rawPhoneNumbers = getPhoneNumbers();
    var phoneNumbers = [];
    for (var i = 0; i < rawPhoneNumbers.length; i++) {
        phoneNumbers[i] = {"number": rawPhoneNumbers[i]}
    }
    var data = {
        "firstName": firstName, "lastName": lastName,
        "company": company, "phoneNumbers": phoneNumbers
    };

    $.ajax({
        url: "api/contacts",
        type: "POST",
        data: JSON.stringify(data),
        //dataType: "json",
        processData: false,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            $('#listTable').DataTable().ajax.reload();
        },
        complete: function (xhr, textStatus) {
            if (xhr.status === 500) {
                alert(xhr.responseText);
            }

        }
    });
}

function getPhoneNumbers() {
    var my_array = [];
    $(":input[class^=phoneNumber]").each(function (index, element) {
        my_array.push(element.value);
    });
    return my_array;
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
                "render": function () {
                    return '<button class='+"editButton"+'>' + 'sfd' + '</button>';
                }
            },
            {
                "data":null,
                "render":function (object) {
                    return '<button class='+"editButton"+'>' + object.id + '</button>';
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



$(document).ready(function () {

    $('#visibleCreate').click(function () {
        $('#createContactDiv').toggle();
    });

    loadDataTable();

    $('#listTable').on('click','.editButton',function(event){
        /*var cell = $(this).parent("td").index();

        var row = $(cell).parent("tr").index();
        alert(row)*/
       // alert($(this).text())
     //   $('#firstName').innerHTML=$(this).text();
        event.preventDefault();
        $('#createContactDiv').toggle();
    })
});