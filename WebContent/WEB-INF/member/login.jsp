<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../inc/header.jsp"></jsp:include>

<div class="rn_loginBox">
	<h1 style="font-size:50pt;">LOGIN</h1>		
	<hr/>
	
	<form id="rn_login_frm" name="rn_login_frm" action="login.do" method="post">
	
		<div class="rn_frm_row">
			<input type="text" id="mem_id" name="mem_id" class="rn_input" placeholder="아이디" required autofocus
			oninvalid="this.setCustomValidity('아이디를 입력하세요.')" oninput="setCustomValidity('')"/>
		</div>		
		
		<div class="rn_frm_row">
			<input type="password" id="mem_pw" name="mem_pw" class="rn_input" placeholder="비밀번호" required
			oninvalid="this.setCustomValidity('암호를 입력하세요')" oninput="setCustomValidity('')"/>
		</div>

		<div class="rn_btn_list">
			<input type="submit" id="rn_btn_login" class="rn_btn rn_btn_A" value="로그인"/>
			<a href="join.do" class="rn_btn rn_btn_B">회원가입</a>
			<!-- <a href="index.do" class="rn_btn rn_btn_C">돌아가기</a> -->
		</div>
	</form>
</div>

<script>

$(function(){
	$("#rn_btn_login").click(function(e){
		
		var mem_id = $("#mem_id");
		var mem_pw = $("#mem_pw");
		
		if (mem_id.val()=="") {
			alert("아이디를 입력해주세요.");
			mem_id.focus();
			return false;
		}
		if (mem_pw.val()=="") {
			alert("비밀번호를 입력해주세요.");
			mem_pw.focus();
			return false;
		}
		
		var json = new Object();
		json.id = mem_id.val();
		json.pw = mem_pw.val();
		
		$.ajax({
			type: "POST"
		,	url: "/ajax_login.do"
		,	dataType : "json"
		,	cache: false
		,	async: false
		,	data : {
				temp : JSON.stringify(json)
			}
		,	success: function (data) {
				//if (data.rtn != "Y") {
				if (data.rtn != 1) {
					alert("아이디 또는 비밀번호를 확인하세요.");
					mem_id.focus();
					e.preventDefault();
					return false;
				}
			}
		,	error: function (request, status, error) {
				alert("code : "+request.status+"\n"+"message : "+request.responseText+"\n"+"error : "+error);
				alert("관리자에게 문의하세요.");
				e.preventDefault();
				return false;
			}
		});
	});
});

</script>


<jsp:include page="../inc/footer.jsp"></jsp:include>