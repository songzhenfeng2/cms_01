<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container-fluid">
		<ul class="list-unstyled">
			<c:forEach var="article" items="${info.list }">
				
					 <li class="media">
					   <img src="/pic/${article.picture }" class="mr-3 rounded" alt="...">
					   <div class="media-body">
					     <h5 class="mt-2 mb-1">${article.title }</h5>
					     <h5 class="mt-5 ">${article.hot}评论·${article.displayDate }</h5>
					  		<div>
					  			 <button onclick="article(${article.id})" type="button" class="float-right btn btn-link" data-toggle="modal" data-target="#exampleModal">
						  			详情
								</button>
					  		</div>
					   </div>
					 </li>
				
				
				<hr>
			</c:forEach>
			
			<div>
				<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
			</div>
		</ul>
		

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="title" aria-hidden="true">
  <div class="modal-dialog modal-lg"  role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="title"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="content">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
		
	</div>
</body>
<script type="text/javascript">
	function article(id){
		$.get("/admin/article",{id:id},function(article){
			$("#title").html(article.title);
			$("#content").html(article.content);
		})
	}
	
	function goPage(pageNum){
		$("#center").load("/my/articles?pageNum="+pageNum);
	}
		
</script>
</html>