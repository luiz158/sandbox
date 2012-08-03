<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title><spring:message code="edit.user"/></title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
  <meta charset="UTF-8">
  <link href="<spring:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
  <link href="<spring:url value="/css/styles.css"/>" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
  <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>

  <script type="text/javascript" src="<spring:url value="/js/jquery.json-2.3.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/ajaxHandler.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/common.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/user.api.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/bootstrap.js"/>"></script>

  <script type="text/javascript">
    $(document).ready(function () {
      authorizationKey = '${requestScope.authorizationKey}';
      Admin.editUserPage("${id}");
    });

    function secUrl() {
      return '${properties.securl}';
    }
    
    loadErrMsg = '<spring:message code="js.admin.load.users.error"/>';
    noResultMsg = '<spring:message code="js.admin.load.users.no.result"/>';
    noRecordsMsg = '<spring:message code="js.admin.load.users.no.records"/>';
    notFoundMsg = '<spring:message code="js.admin.user.not.found"/>';
    enterEmailMsg = '<spring:message code="js.admin.enter.email"/>';
  </script>
  <%@include file="../includes/common.jsp" %>
</head>
<body>
<div class="navbar">
  <div class="navbar-inner">
    <div class="container">
      <div class="nav-collapse">
        <ul class="nav pull-left">
          <li><a id="userLoggedIn" href="<spring:url value="/"/>"><spring:message code="welcome"/>&nbsp;<b>${requestScope.username}</b></a></li>
        </ul>
        <ul class="nav pull-right">
          <li><a href="<spring:url value="/"/>"><spring:message code="back.mainpage"/></a></li>
          <%@include file="../_logout.jsp" %>
        </ul>
      </div>
    </div>
  </div>
</div>

<div class="container">
  <form id="updatePwdForm" name="updatePwdForm" action="" method="post" class="form-horizontal">
    <fieldset>
      <legend><spring:message code="user.details"/></legend>

      <div class="control-group">
        <label class="control-label" for="email"><b><spring:message code="email"/> : </b></label>

        <div class="controls">
          <div id="email" style="padding: 4px 2px;"></div>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="userType"><b><spring:message code="user.type"/> : </b></label>

        <div class="controls">
          <div id="userType" style="padding: 4px 2px;"></div>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="enabled"><b><spring:message code="enabled"/> : </b></label>

        <div class="controls">
          <div id="enabled" style="padding: 4px 2px;"></div>
        </div>
      </div>

      <div class="form-actions">
        <a href="javascript:void(0)" id="switchBtn" class="btn" disabled="disabled"><spring:message code="switch.to"/></a>
        <a href="<spring:url value="/"/>" class="btn"><spring:message code="cancel"/></a>
      </div>
    </fieldset>
  </form>

  <form action="<spring:url value="/j_spring_security_switch_user"/>" method="POST" id="switchForm">
      <input type="hidden" id="switchEmail" name="j_username" value=""/>
  </form>
</div>
</body>
</html>