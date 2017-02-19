<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="false" %>

<html>
<head>
    <title>Is it botat' time? </title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/static/css/bootstrap.min.css"/>">
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/static/css/main.css"/>">
</head>
<body>


<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
    <div class="container topnav">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand topnav" href="#">Is it botat time?</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="${pageContext.servletContext.contextPath}/about">About</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
<!-- /.navigation -->

<!-- Header -->
<a name="about"></a>
<div class="intro-header">
    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <div class="intro-message">
                    <%--<h1>Landing Page</h1>--%>
                    <%--<h3>A Template by Start Bootstrap</h3>--%>
                    <div id="main-info" style="padding: 30px">
                        <t:insertAttribute name="main-info"/>
                    </div>

                    <div id="weather" style="padding: 30px">
                        <c:forEach items="${data}" var="entry">
                            <h3>${entry.key} : ${entry.value.getTempDay()}&#176;C, ${entry.value.getWeatherMain()}</h3>
                        </c:forEach>

                    </div>
                    <hr class="intro-divider">
                    <ul class="list-inline intro-social-buttons">
                        <li>
                            <a href="https://twitter.com/SBootstrap" class="btn btn-default btn-lg"><i
                                    class="fa fa-twitter fa-fw"></i> <span class="network-name">Twitter</span></a>
                        </li>
                        <li>
                            <a href="https://github.com/IronSummitMedia/startbootstrap"
                               class="btn btn-default btn-lg"><i class="fa fa-github fa-fw"></i> <span
                                    class="network-name">VK</span></a>
                        </li>
                        <li>
                            <a href="#" class="btn btn-default btn-lg"><i class="fa fa-linkedin fa-fw"></i> <span
                                    class="network-name">Facebook</span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

    </div>
    <!-- /.container -->

</div>
<!-- /.intro-header -->

<div id="footer" style="text-align: center">
    <p>by @aynulin </p>
</div>
</body>
</html>