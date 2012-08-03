<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
  <title>End User page</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
  <meta charset="UTF-8">
  <link href="<spring:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css"/>
  <link href="<spring:url value="/css/styles.css"/>" rel="stylesheet" type="text/css"/>
  <link href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/css/jquery.dataTables.css" rel="stylesheet" type="text/css"/>

  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
  <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/jquery.dataTables.min.js"></script>

  <script type="text/javascript" src="<spring:url value="/js/jquery.json-2.3.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/jquery.url.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/ajaxHandler.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/mustache.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/common.js"/>"></script>
  <script type="text/javascript" src="<spring:url value="/js/bootstrap.js"/>"></script>

  <script type="text/javascript">
    window.onload = initializeAll;

    function hostUrl() {
      return '${properties.hosturl}';
    }
    function spatialUrl() {
      return '${properties.spatialurl}';
    }
    $(document).ready(function () {
      authorize('${requestScope.username}', '${requestScope.authorizationKey}');

      loadEnduser();
      loadPolygons();
      displayPolygonsTable();
    });
    
    searchMsg     = '<spring:message code="js.enduser.search.warn"/>';
    respNullMsg   = '<spring:message code="js.enduser.response.null"/>';
    resultMsg     = '<spring:message code="js.enduser.result.found"/>';
    confirmMsg    = '<spring:message code="js.enduser.delete.confirm"/>';
    moreLocationsMsg = '<spring:message code="js.enduser.search.more.search.results"/>';

    searchPageSize = '${properties.SearchPageSize}';

  </script>
  <%@include file="includes/common.jsp" %>
</head>
<body>

<div class="navbar">
  <div class="navbar-inner">
    <div class="container">
      <div class="nav-collapse">
        <ul class="nav pull-left">
          <li><a id="userLoggedIn" href="<spring:url value="/profile.html"/>"><spring:message code="logged.in.as"/><b>${requestScope.username}</b></a></li>
          <li class="active"><a href="<spring:url value="/index.html"/>"><spring:message code="home"/></a></li>
        </ul>
        <ul class="nav pull-right">
          <li><a id="profile" href="<spring:url value="/profile.html"/>"><spring:message code="profile"/></a></li>
          <%@include file="_logout.jsp" %>
        </ul>
      </div>
    </div>
  </div>
</div>
<div class="container">
  <h1><spring:message code="End User page"/></h1>

  <div id="enduser_div" style="position: relative; width: 940px; height: 600px; clear: both;"></div>

  <div class="form-actions">
    <div class="pull-right">
      <input type="button" id="search" value="Search" class="btn btn-primary">
    </div>
  </div>

</div>

</body>
</html>