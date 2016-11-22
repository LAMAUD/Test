function update(url) {


	
	
	
	var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
    var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

    var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
    var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;
	
    var w = 250;
    var h = 500;
    var left = ((width / 2) - (w / 2)) + dualScreenLeft;
    var top = ((height / 2) - (h / 2)) + dualScreenTop;
    var newWindow = window.open(url, "Transaction", 'scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
    $(newWindow).on("beforeunload", function(){
    	// Sert à attendre que la base soit chargée
    	setTimeout(function(){ ajax2();},100);
    })

    

}


function ajax ()
    {
		var elmt = document.getElementById("output");
		if (elmt.style.display == "block") {
			elmt.style.display = "none";
		}else {
			elmt.style.display = "block";
		}

		
    	var regex = new RegExp('-', 'g');
    	var dateDebut = $('#ip').val().replace(regex,"");
    	var dateFin = $('#op').val().replace(regex,"");
    	var category = $('#filtre_categorie').val();
    	console.log(category);
        $.ajax({
            type: "GET",
            url: "/test/transaction/ajax",   
            data: "input=" +dateDebut+"&output="+dateFin+"&category="+category,
            success: function(msg){      
                    $('#output').html(msg);
            }
        });
    }

function ajax2 ()
{
	
	var regex = new RegExp('-', 'g');
	var dateDebut = $('#ip').val().replace(regex,"");
	var dateFin = $('#op').val().replace(regex,"");
	var category = $('#filtre_categorie').val();
	console.log(category);
    $.ajax({
        type: "GET",
        url: "/test/transaction/ajax",   
        data: "input=" +dateDebut+"&output="+dateFin+"&category="+category,
        success: function(msg){      
                $('#output').html(msg);
        }
    });
}