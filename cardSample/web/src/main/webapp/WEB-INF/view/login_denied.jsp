<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title><spring:message code="login.denied" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta charset="UTF-8">

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="<spring:url value="/js/jquery.json-2.3.js"/>"></script>
<script type="text/javascript">
    function hostUrl() {
        return '${properties.hosturl}';
    }
</script>
<%@include file="includes/common.jsp" %>
</head>
<body>
    <h1>
        <spring:message code="login.denied" />
    </h1>
</body>
</html>