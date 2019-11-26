function setMsg(val)
{

    document.getElementById('msg').innerHTML = "<div class='alert alert-info' role='alert'>" + val + "</div>";
    setTimeout(function () {
        document.getElementById('msg').innerHTML = "";
    }, 5000);//;
     document.getElementById('msg').setAttribute("style", "padding-right:50px; padding-left:50px; height:5px;");
}
function setError(val)
{
    document.getElementById('msg').innerHTML = "<div class='alert alert-danger' role='alert'>" + val + "</div>";
    setTimeout(function () {
        document.getElementById('msg').innerHTML = "";
    }, 5000);//;
    document.getElementById('msg').setAttribute("style", "padding-right:360px; padding-left:360px;");
}

function checkbox(val)
{
    var defaultCheckBox = document.getElementById("defaultCheckBox").checked;
    var el_collection = eval("document.forms." + val)
    for (c = 0; c < el_collection.length; c++)
    {
        if (defaultCheckBox === true)
        {
            if (el_collection[c].type == 'checkbox')
            {
                el_collection[c].checked = true;
            }
        } else
        {
            if (el_collection[c].type == 'checkbox')
            {
                el_collection[c].checked = false;
            }
        }
    }
    checkboxValidator(val);
}
function checkboxClass(val)
{
    echo(val);
    var defaultCheckBox = document.getElementById(val).checked;
    var el_collection = eval("document.forms." + val)
    for (c = 0; c < el_collection.length; c++)
    {
        if (defaultCheckBox === true)
        {
            if (el_collection[c].type == 'checkbox')
            {
                el_collection[c].checked = true;
            }
        } else
        {
            if (el_collection[c].type == 'checkbox')
            {
                el_collection[c].checked = false;
            }
        }
    }
    checkboxValidator("form1");
}
function validatedate(inputText)
{
    var dateformat = /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/;
    // Match the date format through regular expression
    if (inputText.value.match(dateformat))
    {
        var pdate = inputText.value.split('-');
        var dd = parseInt(pdate[2]);
        var mm = parseInt(pdate[1]);
        var yy = parseInt(pdate[0]);
        // Create list of days of a month [assume there is no leap year by default]
        var ListofDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        if (mm == 1 || mm > 2)
        {
            if (dd > ListofDays[mm - 1])
            {
                return false;
            }
        }
        if (mm == 2)
        {
            var lyear = false;
            if ((!(yy % 4) && yy % 100) || !(yy % 400))
            {
                lyear = true;
            }
            if ((lyear == false) && (dd >= 29))
            {
                return false;
            }
            if ((lyear == true) && (dd > 29))
            {
                return false;
            }
        }
    } else
    {
        return false;
    }
    return true;
}

 function sortSelect()
{
    $(".selectpicker").selectpicker();
    //document.getElementsByClassName("selectpicker").selectpicker();
}   

 $(".selectpicker").selectpicker();
 
 /***********************************************************************************************************/
 /*                         date functions                                                                  */
 /***********************************************************************************************************/
 $(function () {
     $("#startDate").datepicker({
         maxDate: '0',
         beforeShow: function ()
         {
             jQuery(this).datepicker('option', 'maxDate', jQuery('#endDate').val());
         },
         dateFormat: 'yy-mm-dd',
         changeMonth: true,
         changeYear: true
     });
 });

 $(function () {
     $("#endDate").datepicker({
         maxDate: '0',
         beforeShow: function ()
         {
             jQuery(this).datepicker('option', 'minDate', jQuery('#startDate').val());
         },
         dateFormat: 'yy-mm-dd',
         changeMonth: true,
         changeYear: true
     });
 });

 $(function () {
     $(".hmsDate").datepicker({
         dateFormat: 'yy-mm-dd',
         changeMonth: true,
         changeYear: true
     });
 });
 $(function () {
     $(".dateField").datepicker({
         minDate: '0',
         dateFormat: 'yy-mm-dd',
         changeMonth: true,
         changeYear: true
     });
 });

 $(function () {
     $(".expiryDate").datepicker({
         minDate: +1,
         dateFormat: 'yy-mm-dd',
         changeMonth: true,
         changeYear: true
     });
 });
 
 $(function () {
	    $(".dateOfBirth").datepicker({	
	        yearRange: "1910:"+ new Date().getFullYear().toString(),
	        maxDate: '-1',
	        dateFormat: 'yy-mm-dd',
	        changeMonth: true,
	        changeYear: true
	    });
	});
 
 function setDates() {
	    $(function () {
	        $(".dateField").datepicker({
	            minDate: '0',
	            dateFormat: 'yy-mm-dd',
	            changeMonth: true,
	            changeYear: true
	        });
	    });
	}
