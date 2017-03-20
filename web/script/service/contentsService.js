app.service('contentsService', function()
{
       var contentsRoot = [
              {name: "Todos los documentos",url:"documentos"},
                {name: "Mis documentos",url:"subidospormi"},
              {name: "Areas",url:"areas"},
              {name: "Usuarios",url:"usuarios"},
              {name: "Palabras clave",url:"keywords"},
              {name: "Cambiar mi contraseña",url:"userSettings"},
              {name: "Respaldo de base de datos",url:"databaseBackup"},
              {name: "Respaldo de documentos",url:"documentsBackup"},
               {name: "Transacciones",url:"transactions"}
//              {name: "Búsqueda",url:"busqueda"}/*,
  //            {name: "Búsqueda",url:"busqueda"}/*,
    //          {name: "Tablas",url:"tablas"},
      //        {name: "Servidores",url:"servidores"}*/
          ];
            var contentsAdministrator = [
              {name: "Todos los documentos",url:"documentos"},
                {name: "Mis documentos",url:"subidospormi"},
              {name: "Usuarios",url:"usuarios"},
              {name: "Palabras clave",url:"keywords"},
              {name: "Cambiar mi contraseña",url:"userSettings"}
//              {name: "Búsqueda",url:"busqueda"}/*,
  //            {name: "Búsqueda",url:"busqueda"}/*,
    //          {name: "Tablas",url:"tablas"},
      //        {name: "Servidores",url:"servidores"}*/
          ];
            var contentsNormal = [
              {name: "Todos los documentos",url:"documentos"},
             {name: "Mis documentos",url:"subidospormi"},
              {name: "Palabras clave",url:"keywords"},
              {name: "Cambiar mi contraseña",url:"userSettings"}
//              {name: "Búsqueda",url:"busqueda"}/*,
  //            {name: "Búsqueda",url:"busqueda"}/*,
    //          {name: "Tablas",url:"tablas"},
      //        {name: "Servidores",url:"servidores"}*/
          ];
  var contentsService = {
   
    getContentsRoot :function (){
        return contentsRoot;
    },
     getContentsAdministrator :function (){
        return contentsAdministrator;
    },
     getContentsNormal :function (){
        return contentsNormal;
    }
  };
  return contentsService;
   
});


