<%-- 
    Document   : index
    Created on : Sep 29, 2016, 2:51:01 PM
    Author     : jonathangil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%
    
    if(session.getAttribute("user") == null)
   	 
    {	//System.out.println(session.getAttribute("user"));
    	 response.sendRedirect("login.html");
    	 return;
    } 
    
    else
    {  
       
    }
String idUsuario =  session.getAttribute("idUsuario").toString(); 
//String nombreUsuario =  session.getAttribute("user").toString(); 
//
//String a =  session.getAttribute("isAdministrator").toString();
//System.out.println(" Valor isAdministrator : " + a);
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <title>Documentos FIM</title>
          <link href="style/style.css" rel="stylesheet" type="text/css"/>
    <link href="css/prism.css" rel="stylesheet">
    <link href="css/ghpages-materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
    <link href="css/angular-material.min.css" rel="stylesheet" type="text/css"/>
    <link href="icons/icon.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/flaticon.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/styleArticle.css" rel="stylesheet" type="text/css"/>
    <link href="script/shared/loading-bar.min.css" rel="stylesheet" type="text/css"/>
    <link href="script/shared/dist/md-data-table.min.css" rel="stylesheet" type="text/css"/>
    <link href="script/ng-tags-input.min/ng-tags-input.min.css" rel="stylesheet" type="text/css"/>
    <link href="style/styleD3Tree.css" rel="stylesheet" type="text/css"/>
    <script src="assets/js/jquery-3.1.1.min.js" type="text/javascript"></script>
      <script src="assets/js/materialize.js" type="text/javascript"></script>
       <script src="js/jquery.timeago.min.js"></script>
    <script src="js/prism.js"></script>
    <script src="js/materialize.min.js" type="text/javascript"></script>
    <script src="js/init.js"></script>
    <script src="script/shared/d3.v3.min.js" type="text/javascript"></script>
      <script src="script/shared/angular.min.js" type="text/javascript"></script>
      <script src="script/shared/moment.js" type="text/javascript"></script>
    <script src="script/ng-tags-input.min/ng-tags-input.min.js" type="text/javascript"></script>
      <script src="script/shared/angular-route.min.js" type="text/javascript"></script>
       <script src="script/shared/angular-animate.min.js" type="text/javascript"></script>
       <script src="script/shared/angular-aria.min.js" type="text/javascript"></script>
       <script src="script/shared/angular-messages.min.js" type="text/javascript"></script>
       <script src="script/shared/angular-material.min.js" type="text/javascript"></script>
       <script src="script/shared/dist/md-data-table.min.js" type="text/javascript"></script>
       <script src="script/shared/loading-bar.min.js" type="text/javascript"></script>
      <script src="script/app.js" type="text/javascript"></script>
      <script src="script/service/topBannerService.js" type="text/javascript"></script>
      <script src="script/service/areasService.js" type="text/javascript"></script>
      <script src="script/service/contentsService.js" type="text/javascript"></script>
      <script src="script/service/keywordsService.js" type="text/javascript"></script>
      <script src="script/service/objectsService.js" type="text/javascript"></script>
      <script src="script/service/documentosService.js" type="text/javascript"></script>
      <script src="script/service/databaseBackupService.js" type="text/javascript"></script>
      <script src="script/service/documentsBackupService.js" type="text/javascript"></script>
      <script src="script/service/tagsService.js" type="text/javascript"></script>
      <script src="script/service/documentKeywordRelationshipService.js" type="text/javascript"></script>
      <script src="script/service/usuariosService.js" type="text/javascript"></script>
      <script src="script/service/documentsBackupService.js" type="text/javascript"></script>
      <script src="script/service/transactionsService.js" type="text/javascript"></script>
      
      <script src="script/controller/topBannerController.js" type="text/javascript"></script>
      <script src="script/controller/dialogControllers/usuarioDialogController.js" type="text/javascript"></script>
      <script src="script/controller/appController.js" type="text/javascript"></script>
      <script src="script/controller/keywordsController.js" type="text/javascript"></script>
      <script src="script/controller/controllerSideNavBar.js" type="text/javascript"></script>
      <script src="script/controller/documentosController.js" type="text/javascript"></script>
      <script src="script/controller/searchController.js" type="text/javascript"></script>
    <!--Dialog controllers -->
    <script src="script/controller/dialogControllers/documentDialogController.js" type="text/javascript"></script>
    <script src="script/controller/dialogControllers/keywordDialogController.js" type="text/javascript"></script>
    <script src="script/controller/usuariosController.js" type="text/javascript"></script>
    <script src="script/controller/areasController.js" type="text/javascript"></script>
    <script src="script/controller/dialogControllers/areaDialogController.js" type="text/javascript"></script>
    <script src="script/controller/userSettingsController.js" type="text/javascript"></script>
    <script src="script/controller/subidospormiController.js" type="text/javascript"></script>
    <script src="script/controller/databaseBackupController.js" type="text/javascript"></script>
    <script src="script/controller/documentsBackupController.js" type="text/javascript"></script>
    <script src="script/controller/transactionsController.js" type="text/javascript"></script>
    <script src="script/controller/dialogControllers/moveDocumentDialogController.js" type="text/javascript"></script>
    <script src="script/shared/angular-materialize.min.js" type="text/javascript"></script>
    <script>
         $(document).ready(function(){
             setTimeout(function(){ $('.collapsible').collapsible(); 
             
  $('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15 // Creates a dropdown of 15 years to control year
  });
  }, 500);
    
    
  });
    </script>
    <!--Dialog controllers -->
    </head>
    <!--gradient wellText-->
    <body class=" " ng-app="appApp">
        
        
        
        <input type="hidden" value="${idUsuario}" id="idUsuario" name="idUsuario">
        
        <div ng-controller="moveDocumentDialogController"></div>
          <div ng-controller="documentDialogController"></div>
        <div ng-controller="documentsBackupController"></div>
        <div ng-controller="usuarioDialogController"></div>
        <div ng-controller="keywordDialogController"></div>
        <div ng-controller="areaDialogController"></div>
        <div ng-controller="searchController"></div>
   <script type="text/ng-template" id="tag-template">
      <div class="tag-template">
        
        <div class="right-panel">
          <span>{{$getDisplayText()}}</span>
          <a class="remove-button" ng-click="$removeTag()">&#10006;</a>
        </div>
      </div>
    </script>
    
    <script type="text/ng-template" id="autocomplete-template">
      <div class="autocomplete-template">
        
        <div class="right-panel">
          <span ng-bind-html="$highlight($getDisplayText())"></span>
          
           
        </div>
      </div>
    </script>
    
    
    
      <script type="text/ng-template" id="moverDocumento.tmpl.html">
   <md-dialog aria-label="Mover documento" ng-cloak>
        <md-toolbar>
        <div class="md-toolbar-tools">
      <div ng-show="!document.isFolder">
         <h2>Mover documento &quot;{{document.name}}&quot; </h2>
        <span flex></span>
        </div>
       <div ng-show="document.isFolder">
         <h2>Mover carpeta &quot;{{document.name}}&quot; </h2>
        <span flex></span>
        </div>
        </div>
        </md-toolbar>
   <md-dialog-content div="contenidoMover">
     <div class="md-dialog-content" style="min-height:400px;">
     
     
    <div id="tree-container"></div>
      
     </md-dialog-content>
        </md-dialog>
    </script>
    
    
      <script type="text/ng-template" id="documentBackupRequestDialog.tmpl.html">
   <md-dialog aria-label="Google Drive Request" ng-cloak>
        <md-toolbar>
        <div class="md-toolbar-tools">
      
         <h2>Generando respaldo de documentos en Google Drive</h2>
        <span flex></span>
        
        
        
        </div>
        </md-toolbar>
   <md-dialog-content>
     <div class="md-dialog-content" style="min-height:400px;">
     
     
      <div class="preloader-wrapper big active" style=" position: absolute;top :0;left: 0;right: 0;bottom: 0;margin: auto; ">
    <div class="spinner-layer spinner-blue-only">
      <div class="circle-clipper left">
        <div class="circle"></div>
      </div><div class="gap-patch">
        <div class="circle"></div>
      </div><div class="circle-clipper right">
        <div class="circle"></div>
      </div>
    </div>
  </div>
     
   
      
     </md-dialog-content>
        </md-dialog>
    </script>
    
     <script type="text/ng-template" id="keywordDialog.tmpl.html">
   <md-dialog aria-label="Keyword" ng-cloak>
        <md-toolbar>
        <div class="md-toolbar-tools">
        <h2  ng-if="!update">Nueva palabra clave</h2>
         <h2 ng-if="update">Editar palabra clave</h2>
        <span flex></span>
        <md-button class="md-icon-button" ng-click="cancel()">
        <md-icon md-svg-src="icons/ic_close_24px.svg" aria-label="Close dialog"></md-icon>
        </md-button>
        </div>
        </md-toolbar>

        <md-dialog-content>
        <div class="md-dialog-content">
        <form ng-submit="$event.preventDefault()" novalidate name="formKeyword">
        <!-------------------------------------------->

        <div class="input-field col s12" style="padding-top: 5px;padding-bottom: 5px;">
        <input md-autofocus autofocus  required  id="nombre" ng-model="keyword.name" type="text" class="validate">
        <label for="nombre">Nombre (campo obligatorio) </label>
        </div>
  </br>
 
        </md-dialog-content>
        <md-dialog-actions>
        <button ng-if="!update" ng-disabled="formKeyword.$invalid " class="btn waves-effect waves-light" type="submit" name="action"  ng-click="nuevoKeyword()">Registrar
        <i class="material-icons right">send</i>
        </button>
        <button ng-if="update" ng-disabled="formKeyword.$invalid " class="btn waves-effect waves-light" type="submit" name="action"  ng-click="editKeyword()">Cambiar nombre
        <i class="material-icons right">send</i>
        </button>

        </md-dialog-actions>
        </md-dialog>
    </script>
     <script type="text/ng-template" id="areaDialog.tmpl.html">
   <md-dialog aria-label="Area" ng-cloak>
        <md-toolbar>
        <div class="md-toolbar-tools">
        <h2  ng-if="!update">Nueva Area</h2>
         <h2 ng-if="update">Editar área y privilegios</h2>
        <span flex></span>
        <md-button class="md-icon-button" ng-click="cancel()">
        <md-icon md-svg-src="icons/ic_close_24px.svg" aria-label="Close dialog"></md-icon>
        </md-button>
        </div>
        </md-toolbar>

        <md-dialog-content>
        <div class="md-dialog-content">
        <form ng-submit="$event.preventDefault()" novalidate name="formArea">
        <!-------------------------------------------->

        <div class="input-field col s12" style="padding-top: 5px;padding-bottom: 5px;">
        <input md-autofocus autofocus  required  id="nombre" ng-model="area.name" type="text" class="validate">
        <label for="nombre">Nombre (campo obligatorio) </label>
        </div>
{{loggedUser.root}}
<div class="input-field col s12" style="padding-top: 5px;padding-bottom: 5px;" ng-show="!loggedUser.root" >
         <md-switch ng-model="area.superuser" aria-label="Switch 1" > Es de superusuarios
 </md-switch>
    </div>
    
    <div class="input-field col s12" style="padding-top: 5px;padding-bottom: 5px;" ng-hide="" >
         <md-switch ng-model="area.enabled" aria-label="Switch 1" > Habilitada
 </md-switch>
    </div>
    
  </br>
  <div ng-if="update && !area.superuser ">
  <h5>Privilegios</h5>
  <!--{{tables}} -->
  <p>No permitidos</p>
  <md-table-container>
  <table data-md-table data-md-row-select="options.rowSelection" multiple="{{options.multiSelect}}" data-ng-model="selected" md-progress="promise">

    <thead ng-if="!options.decapitate" md-head data-md-order="query.order" md-on-reorder="onReorder">
      <tr md-row>
        <th md-column md:order:by="name"><span>Nombre</span></th>
        <th md-column ><span>Agregar</span></th>
       
        
      </tr>
    </thead>
    <tbody md-body>
      <tr md-row md-select="table" md-select-id="name" data-md-on-select="log" md-on-deselect="deselect" x-md-auto-select="options.autoSelect"  data-ng-repeat="area in possibleAreasByArea| filter:searchAreas | orderBy: query.order | limitTo: query.limit : (query.page - 1) * query.limit">
     <td md-cell>{{ area.name| limitTo: 20 }}{{area.name.length > 20 ? '...' : ''}}</td>
      <td md-cell>    
       <md-button ng-click="createAreaRelationship(area)" class="md-fab md-primary" aria-label="Agregar">
       <i class="material-icons">add</i>
        </md-button>
         </td>   
     
     
    
 
      </tr>
    </tbody>
  </table>
  <p>Permitidos</p>
</md-table-container>
<md-table-container>
  <table data-md-table data-md-row-select="options.rowSelection" multiple="{{options.multiSelect}}" data-ng-model="selected" md-progress="promise">

    <thead ng-if="!options.decapitate" md-head data-md-order="query.order" md-on-reorder="onReorder">
      <tr md-row>
        <th md-column md:order:by="name"><span>Nombre</span></th>
            <th md-column ><span>Puede subir y editar</span></th>
        <th md-column ><span>Quitar</span></th>
     
       
        
      </tr>
    </thead>
    <tbody md-body>
    
      <tr md-row md-select="table" md-select-id="name" data-md-on-select="log" md-on-deselect="deselect" x-md-auto-select="options.autoSelect"  data-ng-repeat="area in areasByArea| filter:searchAreas | orderBy: query.order | limitTo: query.limit : (query.page - 1) * query.limit">
     <td md-cell>{{ area.name| limitTo: 20 }}{{area.name.length > 20 ? '...' : ''}}</td>
           <td md-cell>
                <md-switch ng-model="area.uploadAndEdit" aria-label="Habilitado" ng-change="uploadAndEdit(area)">
     </md-switch>
 </td>
        
            <td md-cell>    
       <md-button ng-click="deleteAreaRelationship(area)" class="md-fab md-primary" aria-label="Quitar">
       <i class="material-icons">clear</i>
        </md-button>
         </td>

        
      </tr>
    </tbody>
  </table>
</md-table-container>
  </div>
  
  <!--{{tables}} -->
        </form>
        </div>
        </md-dialog-content>
        <md-dialog-actions>
        <button ng-if="!update" ng-disabled="formArea.$invalid " class="btn waves-effect waves-light" type="submit" name="action"  ng-click="nuevoArea()">Registrar
        <i class="material-icons right">send</i>
        </button>
        <button ng-if="update" ng-disabled="formArea.$invalid " class="btn waves-effect waves-light" type="submit" name="action"  ng-click="editArea()">Editar
        <i class="material-icons right">send</i>
        </button>

        </md-dialog-actions>
        </md-dialog>
    </script>
    
     <script type="text/ng-template" id="usuarioDialog.tmpl.html">
   <md-dialog aria-label="Usuario" ng-cloak>
        <md-toolbar>
        <div class="md-toolbar-tools">
        <h2  ng-if="!update">Nuevo Usuario</h2>
         <h2 ng-if="update">Editar Usuario</h2>
        <span flex></span>
        <md-button class="md-icon-button" ng-click="cancel()">
        <md-icon md-svg-src="icons/ic_close_24px.svg" aria-label="Close dialog"></md-icon>
        </md-button>
        </div>
        </md-toolbar>

        <md-dialog-content>
          <label  ng-hide="usuario.availability" style="color:red">Ese nombre de usuario ya existe </label>
        <label ng-show="usuario.availability && usuario.name.length >0 " style="color:green">Nombre de usuario válido y disponible </label>
        
        <div class="md-dialog-content">
        <form ng-submit="$event.preventDefault()" novalidate name="formUsuario">
        <!-------------------------------------------->
      

        <div style="clear:both"></div>
        <div class="input-field col s12" style="padding-top: 5px;padding-bottom: 5px;">
        <input md-autofocus autofocus  required  id="nombre" ng-model="usuario.name" type="text" class="validate">
        <label for="nombre">Nombre (campo obligatorio) </label>
        </div>
 
  
  </br>
  <div class="input-field col s12" style="padding-top: 5px;padding-bottom: 5px;">
        <input  required  id="contrasena" ng-model="usuario.contrasena" type="password" class="validate">
        <label for="contrasena">Contraseña (campo obligatorio) </label>
        </div>
</br>
  <div class="input-field col s12" style="padding-top: 5px;padding-bottom: 5px;">
        <input  required  id="contrasenaVerify" ng-model="usuario.contrasenaVerify" type="password" class="validate">
        <label for="contrasenaVerify">Verificar contraseña (campo obligatorio) </label>
        </div>
<br>
<label ng-if="update">Area actual: {{usuario.area.name}}</label>
<br>
  <md-input-container>
          <label>Areas </label>
          <md-select ng-model="usuario.area">
<!--            <md-option><em>None</em></md-option> -->
            <md-option ng-repeat="area in areas" ng-value="area" >
              {{area.name}}
            </md-option>
          </md-select>
        </md-input-container>
 <br>
<!-------------------------------------------->
<div class="input-field col s12" style="padding-top: 5px;padding-bottom: 5px;" ng-hide="!loggedUser.root" >
         <md-switch ng-model="usuario.root" aria-label="Switch 1" > Es root
 </md-switch>
    </div>
  
  
<div class="input-field col s12" style="padding-top: 5px;padding-bottom: 5px;">
         <md-switch ng-model="usuario.isAdministrator" aria-label="Switch 2" ng-hide="!loggedUser.root"> Es administrador
 </md-switch>
    </div>
  
 
<div class="input-field col s12" style="padding-top: 5px;padding-bottom: 5px;">
         <md-switch ng-model="usuario.enabled" aria-label="Switch 3" > Habilitado
 </md-switch>
    </div>
  
 <br>
 
        </form>
        </div>
        </md-dialog-content>
        <md-dialog-actions>
        <button ng-if="!update" ng-disabled="formUsuario.$invalid || !usuario.availability  ||  usuario.area.id==0|| usuario.idCarrera==0 ||usuario.contrasena!=usuario.contrasenaVerify" class="btn waves-effect waves-light" type="submit" name="action"  ng-click="nuevoUsuario()">Registrar
        <i class="material-icons right">send</i>
        </button>
        <button ng-if="update" ng-disabled="formUsuario.$invalid   || !usuario.availability ||  usuario.area.id ==0|| usuario.idCarrera==0 ||usuario.contrasena!=usuario.contrasenaVerify" class="btn waves-effect waves-light" type="submit" name="action"  ng-click="editUsuario()">Editar
        <i class="material-icons right">send</i>
        </button>

        </md-dialog-actions>
        </md-dialog>
    </script>
    
      <!--    ############ INICIO  FOLDER    ########### -->
    
       <script type="text/ng-template" id="updateDocumentDialogFolder.tmpl.html">
   <md-dialog aria-label="Documento" ng-cloak>
        <md-toolbar>
        <div class="md-toolbar-tools">
        
         <h2 ng-if="update">Editar carpeta - &quot;{{carpeta.name}}&quot;</h2>
  <md-button style="color: white;" ng-click="createDocument($event)">
     
          <md-icon>add</md-icon>
          Nuevo documento
    </md-button>
       <md-button style="color: white;" ng-click="createDocumentFolder($event)">
          <md-icon>add</md-icon>
          Nueva carpeta
    </md-button> 
        <span flex></span>
        <md-button class="md-icon-button" ng-click="cancel()">
        <md-icon md-svg-src="icons/ic_close_24px.svg" aria-label="Close dialog"></md-icon>
        </md-button>
        </div>
        </md-toolbar>

        <md-dialog-content>
        <div class="md-dialog-content">
                <!-------------------------------------------->
          <ul class="collapsible" data-collapsible="accordion">
        <li>
      <div class="collapsible-header"><i class="material-icons">info_outline</i>Editar datos de carpeta</div>
      <div class="collapsible-body">
      
        <form ng-submit="$event.preventDefault()" novalidate name="formDocument">
        
<br>
   
        <div class="input-field col s12" style="padding-top: 5px;padding-bottom: 5px;">
        <input md-autofocus autofocus  required  id="name" ng-model="document.name" type="text" class="validate">
        <label for="name">Nombre (campo obligatorio) </label>
        </div>

        <div class="input-field col ">
        <label  ng-if="!update" for="description">Descripción  (campo obligatorio)</label>
        <textarea  id="description" ng-model="document.description"   class="materialize-textarea" class="validate" required></textarea>
          <label for="description">Descripción</label>
        </div>

  <div class="input-field col ">
        
        <input required  id="fileDate" class="datepicker" ng-model="document.fileDate" type="date" class="validate">

        </div>

<br>
  <md-input-container ng-show="document.vengoDeRootYPuedoCambiarDeArea == true">
          <label>Areas</label>
          <md-select ng-model="document.idArea">
            <md-option ng-repeat="area in areas" ng-value="area.id" >
              {{area.name}}
            </md-option>
          </md-select>
        </md-input-container>
 <br>
 <label for="palabrasClave">Palabras clave</label>
 <tags-input ng-model="tags"  display-property="name" placeholder="Palabras clave" 
                replace-spaces-with-dashes="false" template="tag-template">
      <auto-complete source="loadCountries($query)"
                     min-length="0" load-on-focus="true"load-on-empty="true"max-results-to-show="32" template="autocomplete-template"></auto-complete>
    </tags-input>
           <br>
            <md-dialog-actions>
    <button ng-if="update" ng-disabled="document.idArea==0 || trabajando == true" class="btn waves-effect waves-light" type="submit" name="action"   ng-click="editDocumentFolder()">Editar
        <i class="material-icons right">send</i>
        </button>
 </md-dialog-actions>

        </form>
        </div>
        </md-dialog-content>
        
       


      
         </div>
     </li>
    </ul>

<md-table-container>
  <table data-md-table data-md-row-select="options.rowSelection" multiple="{{options.multiSelect}}" data-ng-model="selected" md-progress="promise">

    <thead ng-if="!options.decapitate" md-head data-md-order="query.order" md-on-reorder="onReorder">
      <tr md-row>
           <th md-column md:order:by="name"><span>Tipo</span></th>
        <th md-column md:order:by="name"><span>Nombre</span></th>
        <th md-column md:order:by="description"><span>Descripción</span></th>
        
        <th md-column md:order:by="createdOn"><span>Subido</span></th>
        <th md-column md:order:by="area.name"><span>Area</span></th>
        <th md-column md:order:by="user.name"><span>Creado por</span></th>
<!--Estado del documento-->
     <th md-column  ng-show="loggedUser.root"><span>Borrado</span></th>
     <th md-column ><span>Mover</span></th>
 <th md-column ng-show="loggedUser.root"><span>Respaldar</span></th>
        <th md-column ><span>Editar</span></th>
        <th md-column ><span>Descarga</span></th>
       
      </tr>
    </thead>
    <tbody md-body>
      <tr md-row md-select="table" md-select-id="name" data-md-on-select="log" md-on-deselect="deselect" x-md-auto-select="options.autoSelect"  data-ng-repeat="document in documentosDeCarpeta  | filter:searchDocumentos | orderBy: query.order | limitTo: query.limit : (query.page - 1) * query.limit">
       
          <td md-cell> <span ng-show="document.isFolder == true"><i class="material-icons">folder</i></span> <span ng-show="document.isFolder == false"><i class="material-icons">insert_drive_file</i> </span>  </td>
          <td md-cell>  {{ document.name| limitTo: 30 }}{{document.name.length > 20 ? '...' : ''}}</td>
     <td md-cell>{{ document.description| limitTo: 30 }}{{document.name.length > 20 ? '...' : ''}}</td>
     
     <td md-cell>{{document.createdOn | date:'yyyy-MM-dd HH:mm:ss Z' }}</td>
     <td md-cell>{{document.area.name}}</td>
     <td md-cell>{{document.user.name}}</td>
    
<!--     Estado del documento - checkbox - Activo o inactivo -->
       <td md-cell  ng-show="loggedUser.root">   
          <md-switch ng-model="document.deleted" aria-label="Habilitado" ng-change="borrarORestaurar(document)">
     </md-switch>
             </td>
     
     
     
              <td md-cell  >
     <md-button  ng-click="moverDocumento($event,document)" class="md-fab md-primary" aria-label="Editar">
       <i class="material-icons">low_priority</i>
        </md-button>
     
     </td>
             
              <td md-cell  ng-show="loggedUser.root == true">
     <md-button ng-disabled="document.backedUp==true || uploading==true" ng-click="subirDocumento(document)" class="md-fab md-primary" aria-label="Editar">
       <i class="material-icons">cloud_upload</i>
        </md-button>
     
     </td>
             
     <td md-cell>    
           <md-button ng-show="document.visible" ng-click="updateDocument($event,document)" class="md-fab md-primary" aria-label="Editar">
       <i class="material-icons">border_color</i>
        </md-button>
         </td>
         
 
   <td md-cell>
       <md-button  ng-href="/FIMWebServices/FIMRest/hello/downloadDocumentOrFolder/{{document.id}}" class="md-fab md-primary" aria-label="Ver columnas">
       <i class="material-icons">file_download</i>
        </md-button>
       </td>

      </tr>
    </tbody>
  </table>
</md-table-container>

<data-md-table-pagination md-limit="query.limit" md-limit-options="limitOptions" md-page="query.page" md-total="{{documents.length}}" md-on-paginate="onPaginate" md-page-select="options.pageSelect" md-boundary-links="options.boundaryLinks">
    
</data-md-table-pagination>
    
        </md-dialog>
    </script>
      
      
    <script type="text/ng-template" id="documentDialogFolder.tmpl.html">
   <md-dialog aria-label="Documento" ng-cloak>
        <md-toolbar>
        <div class="md-toolbar-tools">
        <h2  ng-if="!update && document.isInsideFolder == false">Nueva carpeta</h2>
 <h2  ng-if="!update && document.isInsideFolder == true">Nueva carpeta para carpeta &quot;{{document.folder.name}}&quot;</h2>
         <h2 ng-if="update">Editar carpeta</h2>
        <span flex></span>
        <md-button class="md-icon-button" ng-click="cancel()">
        <md-icon md-svg-src="icons/ic_close_24px.svg" aria-label="Close dialog"></md-icon>
        </md-button>
        </div>
        </md-toolbar>

        <md-dialog-content>
        <div class="md-dialog-content">
        <form ng-submit="$event.preventDefault()" novalidate name="formDocument">
        <!-------------------------------------------->

   
        <div class="input-field col s12" style="padding-top: 5px;padding-bottom: 5px;">
        <input md-autofocus autofocus  required  id="name" ng-model="document.name" type="text" class="validate">
        <label for="name">Nombre (campo obligatorio) </label>
        </div>
<!-------------------------------------------->
        <div class="input-field col ">
        <label  ng-if="!update" for="description">Descripción  (campo obligatorio)</label>
        <textarea  id="description" ng-model="document.description"   class="materialize-textarea" class="validate" required></textarea>

        </div>
<!--document.fileDateDate -->
  <div class="input-field col ">
        <p>Fecha de la carpeta</p>
        <input required  id="fileDate" class="datepicker" ng-model="document.fileDate" type="date" class="validate">

        </div>
<!-------------------------------------------->
<br>
  <md-input-container  ng-show="document.vengoDeRootYPuedoCambiarDeArea == true">
          <label>Areas</label>
          <md-select ng-model="document.idArea">
<!--            <md-option><em>None</em></md-option> -->
<!--ng-show="shouldShow(area)"-->
            <md-option ng-repeat="area in areas" ng-value="area.id" >
              {{area.name}}
            </md-option>
          </md-select>
        </md-input-container>
 <br>
 <p><strong>Palabras clave</strong></p>
 <tags-input ng-model="tags"  display-property="name" placeholder="Palabras clave" 
                replace-spaces-with-dashes="false" template="tag-template">
      <auto-complete source="loadCountries($query)"
                     min-length="0" load-on-focus="true"load-on-empty="true"max-results-to-show="32" template="autocomplete-template"></auto-complete>
    </tags-input>
           <br>
   
        </form>
        </div>
        </md-dialog-content>
        <md-dialog-actions>
        <button ng-if="!update" ng-disabled="document.idArea==0 || trabajando == true" class="btn waves-effect waves-light" type="submit" name="action"   ng-click="nuevoDocumentFolder()">Registrar
        <i class="material-icons right">send</i>
        </button>
        <button ng-if="update" ng-disabled="document.idArea==0 || trabajando == true" class="btn waves-effect waves-light" type="submit" name="action"   ng-click="editDocumentFolder()">Editar
        <i class="material-icons right">send</i>
        </button>

        </md-dialog-actions>
        </md-dialog>
    </script>
    <!--    ############  FIN FOLDER    ########### -->
    
<!--    ####################### -->
    
          <script type="text/ng-template" id="documentDialog.tmpl.html">
   <md-dialog aria-label="Documento" ng-cloak>
        <md-toolbar>
        <div class="md-toolbar-tools">
        <h2  ng-if="!update && document.isInsideFolder == false">Nuevo documento</h2>
         <h2  ng-if="!update && document.isInsideFolder == true">Nuevo documento para carpeta &quot;{{document.folder.name}}&quot;</h2>
         <h2 ng-if="update">Editar documento</h2>
        <span flex></span>
        <md-button class="md-icon-button" ng-click="cancel()">
        <md-icon md-svg-src="icons/ic_close_24px.svg" aria-label="Close dialog"></md-icon>
        </md-button>
        </div>
        </md-toolbar>

        <md-dialog-content>
        <div class="md-dialog-content">
        <form ng-submit="$event.preventDefault()" novalidate name="formDocument">
        <!-------------------------------------------->
<div ng-hide="update"> 
 <p>
		Seleccione un archivo : <input required type="file" file-Model="myFile" name="file" size="45" />
	   </p>
   </div>
   </br>
   
        <div class="input-field col s12" style="padding-top: 5px;padding-bottom: 5px;">
        <input md-autofocus autofocus  required  id="name" ng-model="document.name" type="text" class="validate">
        <label for="name">Nombre (campo obligatorio) </label>
        </div>
<!-------------------------------------------->
        <div class="input-field col ">
        <label  ng-if="!update" for="description">Descripción  (campo obligatorio)</label>
        <textarea  id="description" ng-model="document.description"   class="materialize-textarea" class="validate" required></textarea>

        </div>
<!--document.fileDateDate -->
  <div class="input-field col ">
        <p>Fecha del documento</p>
        <input required  id="fileDate" class="datepicker" ng-model="document.fileDate" type="date" class="validate">

        </div>
<!-------------------------------------------->
<br>
  <md-input-container  ng-show="document.vengoDeRootYPuedoCambiarDeArea == true">
          <label>Areas</label>
          <md-select ng-model="document.idArea">
<!--            <md-option><em>None</em></md-option> -->
<!--ng-show="shouldShow(area)"-->
            <md-option ng-repeat="area in areas" ng-value="area.id" >
              {{area.name}}
            </md-option>
          </md-select>
        </md-input-container>
 <br>
 <p><strong>Palabras clave</strong></p>
 <tags-input ng-model="tags"  display-property="name" placeholder="Palabras clave" 
                replace-spaces-with-dashes="false" template="tag-template">
      <auto-complete source="loadCountries($query)"
                     min-length="0" load-on-focus="true"load-on-empty="true"max-results-to-show="32" template="autocomplete-template"></auto-complete>
    </tags-input>
           <br>
   
        </form>
        </div>
        </md-dialog-content>
        <md-dialog-actions>
        <button ng-if="!update" ng-disabled="document.idArea==0 || trabajando == true" class="btn waves-effect waves-light" type="submit" name="action"   ng-click="nuevoDocument()">Registrar
        <i class="material-icons right">send</i>
        </button>
        <button ng-if="update" ng-disabled="document.idArea==0 || trabajando == true" class="btn waves-effect waves-light" type="submit" name="action"   ng-click="editDocument()">Editar
        <i class="material-icons right">send</i>
        </button>

        </md-dialog-actions>
        </md-dialog>
    </script>
    
<!-- ############   TEMPLATE DOCUMENTS ################-->
        <header>
      <div class="container">
    <a href="#" data-activates="nav-mobile" class="button-collapse top-nav waves-effect waves-paquetexpress circle hide-on-large-only"><i class="material-icons">menu</i></a>
      </div>
        <ul id="nav-mobile" class="side-nav fixed" ng-controller="controllerSideNavBar as ctrl">
            
        <li class="logo">
             <label style=" color:black; font-weight: bold;" >Bienvenido usuario {{loggedUser.name}} de {{loggedUser.area.name}}</label>
                <br>
            <a id="logo-container" href="#" class="brand-logo">
               
                <img id="front-page-logo"  src="img/uas.png" alt=""/>
<!--                <object id="front-page-logo" class="svg-class" type="image/svg+xml" data="img/uas.svg"></object>-->
           </a> 
        </li>
        <li class="search">
        
             
                  <!--------------------------------------------------------------------------------------->    
 <md-input-container class="md-icon-float md-block">
      <!-- Use floating label instead of placeholder -->
<!--      <input placeholder="Búsqueda" ng-model="dDto.query" type="text">-->
    </md-input-container>
              <!--------------------------------------------------------------------------------------->
           
        </li>
          <!--  ng-href="#/{{opcion.url}}"  -->
          <div style="margin-top: 20px">
               <div  ng-repeat="opcion in opciones"> 
              
        <li  class="bold" id="boton{{opcion.name}}"><a ng-href="#/{{opcion.url}}" class="waves-effect waves-paquetexpress">{{opcion.name}}</a></li>
        </div>
                   <li class="bold"><a ng-href="http://localhost:8080/FIMWebServices/UsuarioServlet?task=cerrarsesionhttp" class="waves-effect waves-paquetexpress">Cerrar sesión</a></li>
           
          </div>
         
</ul>
        
    </header>
            <main>     
        
        
        <div class="section no-pad-bot" id="index-banner" ng-controller="topBannerController">
        <div class="container">
                  <div class='row '>
        <!--   <h4 class ="header col s12 light center">A modern responsive front-end framework based on Material Design</h4>
         --> </div>
<!--            <p style="color:white;">Bienvenido - {{loggedUser.name}}</p>-->
         <h4 class="header">Repositorio de documentos - FIM</h4> 
          <h5 class="header">{{title}}</h5> 
          
             </div>
            <marquee scrollamount="10" ng-show="loggedUser.root" style="color:white;">{{mensaje}}</marquee>

          <br>

        </div>
     
      </div>
  <div id="main">
      <div class="container">
        <div class="section">
       
            <div ng-view autoscroll="true">
                
            </div>
      <!-- #01579b -->
          <div class="row">
            <div class="col s12 m8 offset-m2">
              <br>
              </div>
          </div>

          <div class="row">
            </div>

          <!--   Promo Section   -->
          <div class="row">
            <div class="col s12 m4">
              <div class="center promo">
              </div>
            </div>

            <div class="col s12 m4">
              <div class="center promo">
                  </div>
            </div>

            <div class="col s12 m4">
              <div class="center promo">
                </div>
            </div>
          </div>

        </div>
        <div class="divider"></div>
        <div class="section">
          <div class="row center">
          </div>
        </div>
      </div>
  </div>

    </main>    <footer class="page-footer">
      <div class="container">
        <div class="row">
          <div class="col l4 s12">
         
          </div>
          <div class="col l4 s12">
              </div>
          <div class="col l4 s12" style="overflow: hidden;">
               </div>
        </div>
      </div>
      <div class="footer-copyright">
        <div class="container">
       </div>
      </div>
    </footer>

    
        </div>
    </body>
</html>
