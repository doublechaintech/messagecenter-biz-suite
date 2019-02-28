
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty message}">
<div class="col-xs-12 col-ms-6 col-md-4 section">
	
	<div class="inner-section">
	
	<b title="A Message">${userContext.localeMap['message']} </b><a href="#"><i class="fa fa-refresh" aria-hidden="true"></i></a>
	<hr/>
	<ul>
	
	
	<li><span>ID</span><a class="link-action-removed" href="./messageManager/view/${message.id}/"> ${message.id}</a></li>
<li><span>${userContext.localeMap['message.title']}</span> ${message.title}</li>
<li><span>${userContext.localeMap['message.content']}</span> ${message.content}</li>
<li><span>${userContext.localeMap['message.send_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${message.sendTime}" /></li>
<li><span>${userContext.localeMap['message.read_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${message.readTime}" /></li>
<li><span>${userContext.localeMap['message.status']}</span> ${message.status}</li>

	
	</ul>
	
	</div><!-- end of inner-section -->
	
</div>

</c:if>




