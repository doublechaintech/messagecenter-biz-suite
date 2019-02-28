<%@ page import='java.util.*,com.doublechaintech.messagecenter.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty privateMessageList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['private_message']}! 
		 <a href="./${ownerBeanName}Manager/addPrivateMessage/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty privateMessageList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("privateMessageList"); 
 	int totalCount = list.getTotalCount();
 	List pages = list.getPages();
 	pageContext.setAttribute("rowsPerPage",list.getRowsPerPage()); 
 	
 	pageContext.setAttribute("pages",pages); 
 	pageContext.setAttribute("totalCount",totalCount); 
 	//pageContext.setAttribute("accessible",list.isAccessible()); 
 	//the reason using scriptlet here is the el express is currently not able resolv common property from a subclass of list
%>
    
 	   
    <div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		<i class='fa fa-table'></i> ${userContext.localeMap['private_message']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addPrivateMessage/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'privateMessageList' eq action.actionGroup}">
		<a class="btn btn-${action.actionLevel} btn-sm" href="${action.managerBeanName}/${action.actionPath}">${userContext.localeMap[action.localeKey]}</a>
		</c:if>
	</c:forEach>
	</div><!--end of div class="btn-group" -->
	
		 
		 
		 
		 </div>
 </div>
    
    
<div class="table-responsive">


<c:set var="currentPageNumber" value="1"/>	



<nav aria-label="Page navigation example">
  <ul class="pagination">
<c:forEach var="page" items="${pages}"> 
<c:set var="classType" value=""/>
<c:if test='${page.selected}' >
<c:set var="classType" value="active"/>
<c:set var="currentPageNumber" value="${page.pageNumber}"/>
</c:if>


	<li class="page-item ${classType}">
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${privateMessageListName};${privateMessageListName}CurrentPage=${page.pageNumber};${privateMessageListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='privateMessageListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['private_message.id']}</th>
</c:if>
<c:if test="${param.referName ne 'title'}">
	<th>${userContext.localeMap['private_message.title']}</th>
</c:if>
<c:if test="${param.referName ne 'content'}">
	<th>${userContext.localeMap['private_message.content']}</th>
</c:if>
<c:if test="${param.referName ne 'sendTime'}">
	<th>${userContext.localeMap['private_message.send_time']}</th>
</c:if>
<c:if test="${param.referName ne 'readTime'}">
	<th>${userContext.localeMap['private_message.read_time']}</th>
</c:if>
<c:if test="${param.referName ne 'sender'}">
	<th>${userContext.localeMap['private_message.sender']}</th>
</c:if>
<c:if test="${param.referName ne 'receiver'}">
	<th>${userContext.localeMap['private_message.receiver']}</th>
</c:if>
<c:if test="${param.referName ne 'status'}">
	<th>${userContext.localeMap['private_message.status']}</th>
</c:if>
<c:if test="${param.referName ne 'platform'}">
	<th>${userContext.localeMap['private_message.platform']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${privateMessageList}">
				<tr currentVersion='${item.version}' id="privateMessage-${item.id}" ><td><a class="link-action-removed" href="./privateMessageManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'title'}">	<td contenteditable='true' class='edit-value'  propertyToChange='title' storedCellValue='${item.title}' prefix='${ownerBeanName}Manager/updatePrivateMessage/${result.id}/${item.id}/'>${item.title}</td>
</c:if><c:if test="${param.referName ne 'content'}">	<td contenteditable='true' class='edit-value'  propertyToChange='content' storedCellValue='${item.content}' prefix='${ownerBeanName}Manager/updatePrivateMessage/${result.id}/${item.id}/'><a title='${item.content}'>${fn:substring(item.content,0,10)}...</a></td>
</c:if><c:if test="${param.referName ne 'sendTime'}">	<td contenteditable='true' class='edit-value'  propertyToChange='sendTime' storedCellValue='${item.sendTime}' prefix='${ownerBeanName}Manager/updatePrivateMessage/${result.id}/${item.id}/'><fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.sendTime}" /></td>
</c:if><c:if test="${param.referName ne 'readTime'}">	<td contenteditable='true' class='edit-value'  propertyToChange='readTime' storedCellValue='${item.readTime}' prefix='${ownerBeanName}Manager/updatePrivateMessage/${result.id}/${item.id}/'><fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.readTime}" /></td>
</c:if><c:if test="${param.referName ne 'sender'}">
	<td class="select_candidate_td"
			data-candidate-method="./privateMessageManager/requestCandidateSender/${ownerBeanName}/${item.id}/"
			data-switch-method="./privateMessageManager/transferToAnotherSender/${item.id}/"
			data-link-template="./profileManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.sender}">
			<a href='./profileManager/view/${item.sender.id}/'>${item.sender.displayName}</a>
			</c:if>
			<c:if test="${empty  item.sender}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'receiver'}">
	<td class="select_candidate_td"
			data-candidate-method="./privateMessageManager/requestCandidateReceiver/${ownerBeanName}/${item.id}/"
			data-switch-method="./privateMessageManager/transferToAnotherReceiver/${item.id}/"
			data-link-template="./profileManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.receiver}">
			<a href='./profileManager/view/${item.receiver.id}/'>${item.receiver.displayName}</a>
			</c:if>
			<c:if test="${empty  item.receiver}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'status'}">	<td contenteditable='true' class='edit-value'  propertyToChange='status' storedCellValue='${item.status}' prefix='${ownerBeanName}Manager/updatePrivateMessage/${result.id}/${item.id}/'>${item.status}</td>
</c:if><c:if test="${param.referName ne 'platform'}">
	<td class="select_candidate_td"
			data-candidate-method="./privateMessageManager/requestCandidatePlatform/${ownerBeanName}/${item.id}/"
			data-switch-method="./privateMessageManager/transferToAnotherPlatform/${item.id}/"
			data-link-template="./platformManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.platform}">
			<a href='./platformManager/view/${item.platform.id}/'>${item.platform.displayName}</a>
			</c:if>
			<c:if test="${empty  item.platform}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>

				<td>

				<a href='#${ownerBeanName}Manager/removePrivateMessage/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyPrivateMessageFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


