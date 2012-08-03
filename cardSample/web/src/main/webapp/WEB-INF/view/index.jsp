<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title>Index Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta charset="UTF-8">
<link href="<spring:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css" />
<link href="<spring:url value="/css/styles.css"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

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
          <ul class="nav pull-right">
            <li><a href="<spring:url value="/login.html"/>"><strong><spring:message code="login" /></strong></a></li>
            <li><a href="<spring:url value="/register.html"/>"><strong><spring:message code="register" /></strong></a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
  
  </div>

</body>
</html>