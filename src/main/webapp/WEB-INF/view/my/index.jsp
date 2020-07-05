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
<link href="/resource/css/bootstrap.css" rel="stylesheet">
<link href="/resource/css/index.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
<script type="text/javascript" src="/resource/js/popper.min.js"></script>
</head>
<body>


	<div class="container-fluid">
		<!-- 页面header -->
		<div class="row" style="background-color: #563D7C;height: 50px" >
			<div class="col-md-12">
				
				
				<font style="color: white">个人中心</font>
				
				<div style="float: right">
					<div class="btn-group dropleft">
						<button type="button" class="btn btn-warning   dropdown-toggle"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							${sessionScope.user.username }</button>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="/">回到首页</a>
							<a class="dropdown-item" href="/passport/logout">注销</a>
						</div>
						<img alt="" src="/resource/pic/my.png" width="50px" height="50px">
					</div>
				</div>
			</div>
				
		</div>
         <!-- body -->
		<div class="row" style="padding-top: 5px">
		   <div class="col-md-2 bg-light" style="height: 550px"  >
				<div class="list-group">
				  <a href="#" data="/my/articles" class="list-group-item list-group-item-action active">我的文章</a>
				  <a href="#" data="/my/publish" class="list-group-item list-group-item-action">发布文章</a>
				  <a href="#" class="list-group-item list-group-item-action">我的收藏</a>
				  <a href="#" class="list-group-item list-group-item-action">我的设置</a>
				</div>
		   </div>
		   
		   
		   <div class="col-md-10" id="center">
		   		<!-- 引入样式 -->
		   		<div style="display: none">
		   			<jsp:include page="/resource/kindeditor/jsp/demo.jsp"></jsp:include>
		   		</div>
		   </div>
		
		</div>

	</div>
</body>

<script type="text/javascript">


	$(function(){
		$("#center").load("/my/articles")
		$("a").click(function(){
			$("a").removeClass("active");
			$(this).addClass("active");
			var url = $(this).attr("data");
			$("#center").load(url);
		})
	
	})

</script>

</html>