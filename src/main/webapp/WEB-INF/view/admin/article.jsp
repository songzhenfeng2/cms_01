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
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resource/js/popper.min.js"></script>

</head>

<body>

	<div class="form-inline mb-2">
		<label for="title">文章标题:</label><input id="title"
			class="form-control form-control-sm" type="text" name="title"
			value="${article.title}">

		<div class="form-check ml-2">
			<input id="status0" type="radio" class="form-check-input"
				name="status" value=""> <label class="form-check-label"
				for="status0">所有</label>
		</div>
		<div class="form-check">
			<input id="status0" type="radio" class="form-check-input"
				name="status" value="0"> <label class="form-check-label"
				for="status0">待审</label>
		</div>
		<div class="form-check">
			<input id="status1" type="radio" class="form-check-input"
				name="status" value="1"> <label class="form-check-label"
				for="status1">已审</label>
		</div>
		<div class="form-check">
			<input id="status-1" type="radio" class="form-check-input"
				name="status" value="-1"> <label class="form-check-label"
				for="status-1"> 驳回</label>
		</div>
		<button type="button" onclick="query()"
			class="btn btn-warning btn-sm ml-2">查询</button>

	</div>

	<table class="table table-bordered text-center table-hover table-sm">

		<tr>
			<td>序号</td>
			<td>文章标题</td>
			<td>所属栏目</td>
			<td>所属分类</td>
			<td>作者</td>
			<td>发布时间</td>
			<td>状态</td>
			<td>热门</td>
			<td>详情</td>
		</tr>
		<c:forEach items="${info.list }" var="article" varStatus="i">
			<tr>
				<td>${i.count }</td>
				<td>${article.title }</td>
				<td>${article.channel.name }</td>
				<td>${article.category.name }</td>
				<td>${article.user.username }</td>
				<td><fmt:formatDate value="${article.created }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${article.status==0?"待审":article.status=="1"?"已审":"驳回" }</td>
				<td>
				 <c:if test="${article.hot==0}">
				 <button class="btn btn-info btn-sm"  onclick="update(${article.id},this)">否</button>
				 
				 </c:if>
				 <c:if test="${article.hot==1}"><button class="btn btn-danger btn-sm" onclick="update(${article.id},this)">是</button>
				 
				 </c:if>
				</td>
				<td><button type="button" class="btn btn-primary btn-sm"
						data-toggle="modal" data-target="#exampleModalLong" onclick="article(${article.id})">详情</button></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
	</div>
	
	
	<!-- Modal -->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle"><strong><span id="mtitle"></span></strong></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="mcontent">
        
      </div>
      <div class="modal-footer">
         <span id="msg" class="text-danger"></span>
        <button id="btnsuccess" type="button" class="btn btn-success" data-dismiss="modal" onclick="check(1)" >通过</button>
         <button id="btndanger" type="button" class="btn btn-danger" data-dismiss="modal" onclick="check(-1)">驳回</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
//设置热点文章
function update(id,obj){
	//否：表示当前的状态 0   是：1
	var hot = $(obj).text()=="否"?1:"0"; 
	
	
	$.post("/admin/updateArticle",{id:id,hot:hot},function(flag){
		if(flag){
			$(obj).text(hot==0?"否":"是");
			
			$(obj).attr("class",hot==0?"btn btn-info btn-sm":"btn btn-danger btn-sm");
		}
		
	})
	
	
	
}



var articleId;
//审核文章
function check(status){
	
	
	$.post("/admin/update",{id:articleId,status:status},function(flag){
		if(flag){
			alert("操作成功");
			location.reload();
		}
	})
	
}

//文章详情
function article(id){
	$("#msg").empty();
	articleId =id;//为文章id成员变量赋值
	
	$.get("/admin/article",{id:id},function(article){
		$("#mtitle").html(article.title);
		$("#mcontent").html(article.content);
		
		//根据文章的审核状态。为按钮追加样式 。可用或不可用的样式
		if(article.status!=0){
		$("#btnsuccess").prop("disabled",true);
		$("#btndanger").prop("disabled",true);
		}
	})
	
}


	$(function() {
		//让单选框回显
		var value = '${article.status}'
		$("input:radio[value='" + value + "']").attr('checked', 'true');

	})
	function query() {
		var title = $("[name='title']").val();
		var status = $("[name='status']:checked").val();
		var url = "/admin/articles?1=1";
		if (title != null)
			url += "&title=" + title;

		if (status != null && status != '')
			url += "&status=" + status

		$("#center").load(url);//在中间区域加载文章

	}

	function goPage(pageNum) {
		$("#center").load("/admin/articles?pageNum=" + pageNum);

	}
</script>
</html>