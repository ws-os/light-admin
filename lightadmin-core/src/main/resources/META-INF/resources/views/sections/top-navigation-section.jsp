<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="light" uri="http://www.lightadmin.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:useAttribute name="lightAdminConfiguration" ignore="true"/>

<div id="topNav">
    <div class="fixed">
        <div class="wrapper">
            <div class="userNav" style="float:left;">
                <ul>
                    <li>
                        <a href="<c:out value='${lightAdminConfiguration.backToSiteUrl}'/>" title="Back to Site">
                            <img src="<light:url value='/images/icons/topnav/mainWebsite.png'/>" alt=""><span>Back to Site</span>
                        </a>
                    </li>
                </ul>
            </div>

            <sec:authorize ifAllGranted="ROLE_ADMIN">
                <div class="welcome">
                    <a href="#" title="">
                        <img src="<light:url value='/images/userPic.png'/>" alt=""/></a>
                    <span>Hello, <sec:authentication property="principal.username"/>!</span>
                </div>
                <div class="userNav">
                    <ul>
                        <li><a href="<c:out value='${lightAdminConfiguration.securityLogoutUrl}'/>" title=""><img
                                src="<light:url value='/images/icons/topnav/logout.png'/>"
                                alt=""/><span>Logout</span></a></li>
                    </ul>
                </div>
            </sec:authorize>

            <div class="userNav">
                <ul>
                    <li>
                        <a href="<c:out value='${lightAdminConfiguration.helpUrl}'/>"><img src="<light:url value='/images/icons/topnav/help.png'/>" alt=""><span>Help</span></a>
                    </li>
                </ul>
            </div>

            <div class="userNav">
                <iframe allowtransparency="true" frameborder="0" scrolling="no"
                        src="https://platform.twitter.com/widgets/tweet_button.html?lang=en&url=http%3A%2F%2Flightadmin.org&related=lightadm_team&text=LightAdmin%20-%20Pluggable%20data%20administration%20UI%20library%20for%20Spring%20Boot&hashtags=LightAdmin,springboot"
                        style="width:130px; height:20px; position: relative; right: -35px; top: 8px;"></iframe>
            </div>

            <div class="fix"></div>
        </div>
    </div>
</div>