<%--
  Created by IntelliJ IDEA.
  User: wung
  Date: 2016/12/15
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
    <title>main</title>
</head>
<body>
    Welcome! ${user.loginName}, You are ${user.age} years old.
    </br>
    <input type="button" onclick="logout()" value="Log out"/>
    </br></br>
    <span id="errorInfo" style="color: red;"></span></br>
    Username:<input type="text" name="username"/>
    Age:<input type="text" name="age"/></br>
    <input type="button" onclick="saveUser()" value="Save"/>
</body>
</html>
