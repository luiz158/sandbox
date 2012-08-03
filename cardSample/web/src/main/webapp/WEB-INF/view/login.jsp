<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
  <title>Login Page</title>
  <link href="<spring:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
  <link href="<spring:url value="/css/styles.css"/>" rel="stylesheet" type="text/css"/>

  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
  <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>

  <script type="text/javascript" src="<spring:url value="/js/common.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/bootstrap.js"/>"></script>

  <script type="text/javascript">
    $(document).ready(function () {
      $('#loginForm').validate();
    })
  </script>
  <%@include file="includes/common.jsp" %>
</head>
<body>

<div class="navbar">
  <div class="navbar-inner">
    <div class="container">
      <div class="nav-collapse">
        <ul class="nav">
          <li><a href="<spring:url value="/"/>"><strong><spring:message code="home"/></strong></a></li>
        </ul>
      </div>
    </div>
  </div>
</div>

<div class="container">
  <form id="loginForm" name="loginForm" action="j_spring_security_check" method="post" class="form-horizontal">
    <fieldset>
      <legend>Please Login</legend>

      <% if (request.getParameter("error") != null) { %>
      <br/>
      <div class="alert alert-error">
        <spring:message code="login.failed"/>
      </div>
      <% } else if (request.getParameter("registered") != null) { %>
        <br/>
        <div class="alert alert-success">
          <spring:message code="registered.successfully"/>
        </div>
      <% } %>

      <div class="control-group">
        <label class="control-label" for="login_email"><spring:message code="account.email"/></label>

        <div class="controls">
          <input id="login_email" type="text" name="j_username" autofocus="autofocus" class="required"/>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="login_password"><spring:message code="account.password"/></label>

        <div class="controls">
          <input id="login_password" type="password" name="j_password" class="required"/>
        </div>
      </div>

<%-- 
      <div class="control-group">
        <div class="controls">
          <span>Build: ${properties.buildNumber}</span>
        </div>
      </div>
--%>
      
      <div class="form-actions">
        <input id="login_loginBtn" type="submit" class="btn btn-primary" value="Login"/>
          <a id="login_register" href="<spring:url value="/register.html"/>" class="btn"><spring:message code="account.register"/></a>
        </div>
    </fieldset>
  </form>
</div>
</body>
</html>