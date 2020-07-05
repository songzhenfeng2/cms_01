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
<title>注册</title>
<body background="/resource/pic/3..jpg" ></body>
	<link href="/resource/css/bootstrap.css" rel="stylesheet">
	<link href="/resource/css/jquery/screen.css" rel="stylesheet">
	<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
	<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
</head>


<body>
	<div class="container-fluid">
	
		<div class="row" style="height: 300px;"></div>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-7"></div>
			<div class="col-md-3">
				<span id="s1" class="text-danger"></span>
				<form id="form1">
					<div class="card" style="width: 18rem;">
						<div class="card-header title-box text-center">
							头条号注册
						</div>
						<div class="card-body">
							<div class="input-group mb-3">
								<label for="username">用户名：&nbsp;&nbsp;&nbsp;</label><input type="text"
									name="username" id="username" placeholder="请输入您的用户名">
							</div>
							<div class="input-group mb-3">
								<label for="password">密&nbsp;&nbsp;&nbsp;码：&nbsp;&nbsp;&nbsp;</label><input
									type="password" name="password" id="password" placeholder="请输入您的密码">
							</div>
							<div class="input-group mb-3">
								<label for="repassword">确认密码：</label><input
									type="password" name="repassword" id="repassword" placeholder="请输入您的密码">
							</div>

							<div class="form-group form-inline">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="gender"
										id="boy" value="1" checked> <label
										class="form-check-label" for="boy">男</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="gender"
										id="girl" value="0"> <label class="form-check-label"
										for="girl">女</label>
								</div>
							</div>

							<div>
								<button class="btn btn-info" type="submit">注册</button>
								<button class="btn btn-warning" type="reset">重置</button>
							</div>
						</div>
					</div>	
					</form>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	$(function() {
		$("#form1").validate({
			//1.定义规则
			rules:{
				username: {
					required : true,
					rangelength:[3,10],
					remote:{
						type:"post",
						data:{
							username:function(){
								return $("[name='username']").val();
							}
						},
						url:"/passport/checkusername"
					}
				},
				password:{
					required : true,
					rangelength:[6,10]
				},
				repassword:{
					equalTo:"#password"
				}
			},
			//2.小时提示
			messages:{
				username:{
					required : "用户名必须输入",
					rangelength:"用户名长度必须为3-10位",
					remote:"用户名已被注册"
				},
				password:{ 
					required : "密码不能为空",
					rangelength:"密码长度必须为6-10位"
				},
				repassword:{
					equalTo:"两次密码输入不一致"
				}
			},
			submitHandler:function(){
				$.post("/passport/reg",$("#form1").serialize(),function(result){
					if(result.code==200){
						alert(result.msg)
						location = "/passport/login"
					}else{
						$("#s1").text(result.msg)
					}
				})
			}
		})
	})
	
</script>
</html>