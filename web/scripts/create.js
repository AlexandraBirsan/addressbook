/**
 * Created by birsan on 4/13/2016.
 */
function addPhoneNumber(){
    var table=document.getElementById("phoneNumTable");
    appendRowToTable(table);
    console.log("am ajuns aici");
}

function appendRowToTable(table) {
    var row=table.insertRow(-1);
    row.style.borderWidth="1px";
    row.style.borderStyle="solid";
    var cell1=row.insertCell(0);
    var cell2=row.insertCell(1);
    cell1.innerHTML="<input type='text' name='phoneNumber'/>";
    cell2.innerHTML="";
}
