<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
		<link rel="stylesheet" href="/resource/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="/resource/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="/resource/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="/resource/kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			window.editor1 = K.create('textarea[name="content1"]', {
				cssPath : '/resource/kindeditor/plugins/code/prettify.css',
				uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body>
	<form id="form1">
		<div class="form-group">
			 <label for="title">文章标题:</label>
			 <input id="title" type="text" class="form-control" name="title" style="width:700px;">
		</div>
		
		<div class="form-group">
			<label>标题图片</label>
			<input type="file" class="form-control-file" name="file">
		</div>
		
		<div class="form-group form-inline">
			所属栏目:
			<select class="custom-select" id="channel" name="channelId">
				<option>请选择</option>
			</select>	
			所属分类:
			<select class="custom-select" id="category" name="categoryId"> 
				<option>请选择</option>
			</select>
		</div>
		
		<div>
			<textarea name="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
			<br />
		</div>
		
		<input type="button" name="button" value="发布" class="btn btn-primary" onclick="publish()"/>
	</form>
</body>
<script type="text/javascript">
	$(function(){
		/* 获取栏目下拉框的值 */
		$.get("/my/channel",function(list){
			for ( var i in list) {
				$("#channel").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>");
			}
		})
		
		/* 栏目下拉框发生改变 */
		$("#channel").change(function(){
			/* 获取栏目下拉框的id */
			var channelId = $(this).val();
			/* 档栏目下拉框发生改变时   清空当前分类下拉框的值 */
			$("#category").empty();
			/* 根据栏目id  查询该栏目下的分类 */
			$.get("/my/category",{channelId:channelId},function(list){
				$("#category").append("<option>请选择</option>")
				for ( var i in list) {
					$("#category").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>");
				}
			})
			
		})
	})
	
	function publish(){
		var formData = new FormData($("#form1")[0]);
		formData.set("content",editor1.html());
		$.ajax({
			type:"post",
			data:formData,
			url:"/my/publish",
			processData:false,//jquery不要处理发送东西
			contentType:false,//jquery 不要出处理发送的类型
			success:function(flag){
				if(flag){
					alert("发布成功");
					location.href="/my"   //跳转到我的文章页面
				}else{
					alert("发布失败");
				}
			}
		})
		
	}
</script>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>