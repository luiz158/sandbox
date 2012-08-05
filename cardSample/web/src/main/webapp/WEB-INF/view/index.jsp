<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
  <title><spring:message code="admin.console"/></title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
  <meta charset="UTF-8">
  <link href="<spring:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
  <link href="<spring:url value="/css/styles.css"/>" rel="stylesheet" type="text/css"/>
  <link href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/css/jquery.dataTables.css" rel="stylesheet" type="text/css"/>

  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
  <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/jquery.dataTables.min.js"></script>
  
  <script type="text/javascript" src="<spring:url value="/js/jquery.json-2.3.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/jquery.blockUI.2.4.2.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/ajaxHandler.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/mustache.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/common.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/user.api.js"/>"></script>
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
    function businessCardUrl() {
      return '/cardSample-rest';
    }
    $(document).ready(function () {
      Admin.loadPage();
    });
    
    loadErrMsg = '<spring:message code="js.admin.load.users.error"/>';
    noResultMsg = '<spring:message code="js.admin.load.users.no.result"/>';
    noRecordsMsg = '<spring:message code="js.admin.load.users.no.records"/>';
    notFoundMsg = '<spring:message code="js.admin.user.not.found"/>';
    enterEmailMsg = '<spring:message code="js.admin.enter.email"/>';
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
  <h1><spring:message code="admin.console"/></h1>

  <hr/>
  <h3><spring:message code="cards"/></h3>
  <div style="padding: 10px 0;">
    <div id="cards_table"> <table id="datatable"></table> </div>
    <div id="cards_table_footer"> <spring:message code="page"/> <span id="currentPage">1</span> </div>

    <ul class="pager">
      <li class="disabled previous">
        <a id="prevPage" href="javascript:void(0);">&larr; <spring:message code="prev"/></a>
      </li>
      <li class="next">
        <a id="nextPage" href="javascript:void(0);"><spring:message code="next"/> &rarr;</a>
      </li>
    </ul>
  </div>
</div>

</body>
</html>