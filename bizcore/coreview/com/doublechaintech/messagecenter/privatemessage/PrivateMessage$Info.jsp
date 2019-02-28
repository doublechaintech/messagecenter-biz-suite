
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty privateMessage}">
<div class="col-xs-12 col-ms-6 col-md-4 section">
	
	<div class="inner-section">
	
	<b title="A PrivateMessage">${userContext.localeMap['private_message']} </b><a href="#"><i class="fa fa-refresh" aria-hidden="true"></i></a>
	<hr/>
	<ul>
	
	
	<li><span>ID</span><a class="link-action-removed" href="./privateMessageManager/view/${privateMessage.id}/"> ${privateMessage.id}</a></li>
<li><span>${userContext.localeMap['private_message.title']}</span> ${privateMessage.title}</li>
<li><span>${userContext.localeMap['private_message.content']}</span> ${privateMessage.content}</li>
<li><span>${userContext.localeMap['private_message.send_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${privateMessage.sendTime}" /></li>
<li><span>${userContext.localeMap['private_message.read_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${privateMessage.readTime}" /></li>
<li><span>${userContext.localeMap['private_message.status']}</span> ${privateMessage.status}</li>

	
	</ul>
	
	</div><!-- end of inner-section -->
	
</div>

</c:if>




