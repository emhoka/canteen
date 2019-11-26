function printInventoryResultOutput()
{
    $.ajax({method: "GET",
        url:  "/reports/inventoryreport/",
        success: function (result) {
            $("#printOut").html(result);
            dropdown(100);
        }});
}

function printTransactionResultOutput()
{
    $.ajax({method: "GET",
        url:  "/reports/transactionreport/",
        success: function (result) {
            $("#printOut").html(result);
            dropdown(100);
        }});
}

function dropdown(val)
   {
	document.getElementById(val).style.visibility = "visible";   
   }
function dropdownhide(val)
   {
	document.getElementById(val).style.visibility = "hidden";   
   }


//function printRisOutput(val)
//{
//    $.ajax({method: "GET",
//        url:  "/ris/rispopup/"+val,
//        success: function (result) {
//            $("#printOut").html(result);
//            dropdown(100);
//        }});
//}