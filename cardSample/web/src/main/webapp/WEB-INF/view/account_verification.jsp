<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
  <title><spring:message code="account.verification"/></title>
  <link href="<spring:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
  <link href="<spring:url value="/css/styles.css"/>" rel="stylesheet" type="text/css"/>

  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
  <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>

  <script type="text/javascript" src="<spring:url value="/js/common.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/ajaxHandler.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/bootstrap.js"/>"></script>

  <script type="text/javascript">
    function secUrl() {
      return '${properties.securl}';
    }
    $(document).ready(function () {
      verifyEmail("${id}");
    });

    function verifyEmail(id) {
      var url = secUrl() + "/api/emailVerificationToken/" + id;
      handleSimpleAjaxRequest('POST', url, null, emailVerificationSuccessHandler, emailVerificationFailedHandler);
    }

    function emailVerificationSuccessHandler(msg, textStatus, xhr) {
      if (xhr.status == 200) {
        $('#init-message').hide();
        $('#success-message').show();
    	}
    }

    function emailVerificationFailedHandler(xhr) {
      $('#init-message').hide();
      $('#failure-message').show();
    }
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
  <div id="init-message">
    <div class="page-header">
      <h1><spring:message code="please.wait"/></h1>
    </div>
    <div class="single_message">
    	<spring:message code="please.wait.msg"/>
    </div>
  </div>
  <div id="success-message" style="display: none;">
    <div class="page-header">
      <h1><spring:message code="congratulations"/></h1>
    </div>
    <div class="single_message">
      <spring:message code="congratulations.msg"/> <a href="<spring:url value="/login.html"/>"><spring:message code="link.signin"/></a>.
    </div>
  </div>
  <div id="failure-message" style="display: none;">
    <div class="page-header">
      <h1><spring:message code="account.verified"/></h1>
    </div>
    <div class="single_message">
      <spring:message code="account.verified.msg"/> <a href="<spring:url value="/register.html"/>"><spring:message code="link.signup"/></a>. 
    </div>
  </div>
</div>
</body>
</html>