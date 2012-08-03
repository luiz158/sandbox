<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
  <title>Register Page</title>
  <link rel="stylesheet" type="text/css" href="<spring:url value="/css/bootstrap.css"/>" />
  <link rel="stylesheet" type="text/css" href="<spring:url value="/css/styles.css"/>" />

  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
  <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
  <script type="text/javascript" src="<spring:url value="/js/jquery.json-2.3.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/ajaxHandler.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/common.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/bootstrap.js"/>"></script>
  <script type="text/javascript">
    function hostUrl() {
      return '${properties.hosturl}';
    }
    $(document).ready(function () {
      $('#registerForm').validate({
        rules : { registration_password2 : { equalTo : '#registration_password' } },
        messages : { registration_password2 : { equalTo : '<spring:message code="account.validate.password"/>'} },
        submitHandler: function() { doRegistration(); }
      });
    })
    
    regWaitMsg   = '<spring:message code="js.register.wait"/>';
    regSuccesMsg = '<spring:message code="js.register.success"/>';
    regFailedMsg = '<spring:message code="js.register.failed"/>';
    regUserExistMsg  = '<spring:message code="js.register.user.exist"/>';
    userNotFoundMsg  = '<spring:message code="js.register.user.not.found"/>';
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
  <form id="registerForm" name="registerForm" action="" method="post" class="form-horizontal">
    <fieldset>
      <legend>Register</legend>

      <div class="control-group">
        <label class="control-label" for="registration_email"><spring:message code="account.email"/></label>

        <div class="controls">
          <input id="registration_email" type="email" name="email" class="required email"/>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="registration_password"><spring:message code="account.password"/></label>

        <div class="controls">
          <input id="registration_password" type="password" name="password" class="required"/>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="registration_password2"><spring:message code="account.password.confirm"/></label>

        <div class="controls">
          <input id="registration_password2" type="password" name="registration_password2" class="required"/>
        </div>
      </div>

      <div class="form-actions">
        <input id="registration_register" type="submit" class="btn btn-primary" value="<spring:message code="account.register"/>"/>
      </div>
    </fieldset>
  </form>
</div>
</body>
</html>