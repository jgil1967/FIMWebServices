function logout()
{//recibir de parametro a donde se dirigirá
	 $.ajax({
	        type: "GET"
	        , url: "../ServletUsers?task=logout"
	        , async: true
	        , cache: false
	        , success: function (resp) {
	        	//cambiar a index.jsp y poner en todas de info menu info normal
	        	var page = "../index.jsp";
	        	windowSwitch(page);
	        	
	        }

	    });
}



//Document ready, verifica si existe, llamada de ajax a servletUsers
//$(document).ready(function (){
//
//	   $.ajax({
//	        type: "GET"
//	        , url: "../ServletUsers?task=verifica"
//	        , async: true
//	        , cache: false
//	        , success: function (resp) {
//	        	//alert (resp);
//	        	
//	        	if (resp)
//        		{
//	        	//alert ("Si hay una sesión");
//        		//$("<div> Hola").addClass('cobertura').appendTo( "#top__enlaces-mini" ).Attr("id","cerrarsesion");
//	        	
//	        		 $("<a> ", {text: "Infoexpress"}).addClass('underline blue-paquetexpress padding-left-01').appendTo("#top__enlaces-mini").attr("id", "cerrarsesion").attr("href", "menuinfo.jsp");
//	        		 texto = "     |  "
//	        				$("#top__enlaces-mini").append(texto);
//		        		var texto = "Cerrar sesi\u00F3n"
//	        	    $("<a> ", {text: texto}).addClass('underline blue-paquetexpress padding-left-01').appendTo("#top__enlaces-mini").attr("id", "cerrarsesion").attr("href", /*""*/"javascript:logout()");
//		        }
//        	else
//        		{
//        		//alert ("No hay una sesión");
//        		}
//	        	
//	        
//	        	
//	        }
//
//	    });
//	
//	});
