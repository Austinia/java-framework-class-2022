<%--
  Created by IntelliJ IDEA.
  User: austin
  Date: 2022/05/13
  Time: 4:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>File Upload</h2>
<form action="/upload" method="post" enctype="multipart/form-data" >
    <input type="file" name="file" />
    <input type="submit" />
</form>
<img src="${url}" />
</body>
</html>
