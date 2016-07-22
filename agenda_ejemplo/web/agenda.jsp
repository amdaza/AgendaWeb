<html>
<head>
<title>Listado agenda</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta name="copyright" content="Curso Java EE">
<link rel="stylesheet" type="text/css" href="estilos.css">
</head>

<%@ page errorPage="error.jsp" %> 

<%@ page import="agenda.modelo.*,java.util.*" %>

<script language="javascript">
    function send () {
        document.form1.action="logoff";
            document.form1.submit();
    }
</script>

<center>
  <h2>Agenda Usuario ${user}</h2>
  
  <%  List<AgendaBean> lista = (List) request.getAttribute("lista");  %>


 <table class="principaldatos" align="center" width="90%" border="2" bordercolor="#cccccc" cellspacing="2" cellpadding="4">

   <tr>
       <td colspan="3"></td>
   </tr>
   <tr align="center" class="principaldatostitulos">
       <th width="40%">Nombre</th>
       <th width="20%">Telefono</th>
       <th width="40%">E-mail</th>
   </tr>
   <tr>
       <td colspan="3"><div align="left"></div></td>
   </tr>

   <%
   for (AgendaBean a : lista) { 
  %>

    <tr>
    <td width="40%">
    <%= a.getNombre() %>
    </td>

    <td width="20%">
     <%= a.getTelefono() %>
    </td>

    <td width="40%">
     <%= a.getEmail() %>
    </td>

   </tr>
   
   <% } %>

    </table>
   
   <br>
    <br>
    
    <form name="form1" method="post">
     <input type="button" value="Desconectar"
      onClick="javascript:send()">
    </form>

</center>

   
