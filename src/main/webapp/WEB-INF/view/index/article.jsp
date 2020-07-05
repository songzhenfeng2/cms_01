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
<title>${article.title }</title>
<link href="/resource/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="row bg-dark" style="height: 34px">
			<div class="col-md-12 pt-1">
				<div class="float-right">
					<a href="#" class="text-white ml-2">登录</a> <a href="#"
						class="text-white">侵权投诉</a><a href="#" class="text-white ml-2">
						头条产品</a>
				</div>
			</div>

		</div>
	</div>
	<div class="container">
		<div class="row">

			<div class="col-md-2"></div>
			<div class="col-md-7">
				<h2>
					<strong> ${article.title }</strong>
				</h2>
				<p>${article.user.username }
					·
					<fmt:formatDate value="${article.created}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</p>
				<hr>
				${article.content}
				<c:if test="${sessionScope.user==null }">
					<p>请先进行登录在进行评论</p><br><p><a href="/passport/login">登录</a></p>
				</c:if>
				<c:if test="${sessionScope.user!=null }">
					<div>
						文章评论
						<textarea rows="5" cols="100" name="content"></textarea>
						<button type="button" onclick="addComment()" class="btn btn-info">评论</button>
					</div>
				</c:if>
			</div>
			
			<c:forEach var="comment" items="${info.list }"> 
				<tr>
					${comment.user.username }·${comment.disPlayDate }
					${comment.content }
				</tr>
			</c:forEach>
			<div class="col-md-3"></div>

		</div>


	</div>
</body>
<script type="text/javascript">
	function addComment(){
		var content = $("[name='content']").val();
		$.post("/addComments",{articleId:'${article.id}',content:content},function(flag){
			if(flag){
				alert("评论成功");
				location.reload();
			}else{
				alert("评论失败");
			}
		})
	}
</script>
</html>