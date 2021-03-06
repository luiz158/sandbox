<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
  <title><spring:message code="admin.title"/></title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
  <meta charset="UTF-8">
  <link href="<spring:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
  <link href="<spring:url value="/css/styles.css"/>" rel="stylesheet" type="text/css"/>
  <link href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.2/css/jquery.dataTables.css" rel="stylesheet" type="text/css"/>

  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
  <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.2/jquery.dataTables.min.js"></script>
  
  <script type="text/javascript" src="<spring:url value="/js/jquery.json-2.3.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/jquery.blockUI.2.4.2.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/ajaxHandler.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/common.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/businesscard.api.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/admin.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/bootstrap.js"/>"></script>

  <script type="text/javascript">
    function hostUrl() {
      return '${properties.hosturl}';
    }
    function secUrl() {
      return '${properties.securl}';
    }
    function cardUrl() {
      return '/cardSample-rest';
    }
    $(document).ready(function () {
      Admin.loadPage();
    });
    
    loadErrMsg = '<spring:message code="js.admin.load.cards.error"/>';
  </script>
  <%@include file="includes/common.jsp" %>
</head>
<body>

<div class="navbar">
  <div class="navbar-inner">
    <div class="container">
      <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>
      <div class="nav-collapse collapse">
        <ul class="nav">
          <li><a id="userLoggedIn" href="<spring:url value="/"/>"><spring:message code="welcome"/>&nbsp;<b>${requestScope.username}</b></a></li>
        </ul>
      </div>
    </div>
  </div>
</div>
<div class="container">
  <hr/>
  
  <div style="padding: 10px; float: left; width: 45%;">
  <h3><spring:message code="businesscards"/></h3>
    <div id="businesscards_table"> <table id="businesscardtable"></table> </div>
  </div>
  
  <div style="padding: 10px; float: right; width: 45%;">
	<h3><spring:message code="clientcards"/></h3>
    <div id="clientcards_table"> <table id="clientcardtable"></table> </div>
  </div>
  
</div>
  
<div class="container">
  <h5>Simulate Notification - adding sample Client Card to the first Business Card in table</h5>
  <button id="addClientToBusiness">Add</button>
</div>

</body>
</html>