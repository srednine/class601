<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../inc/header.jsp"></jsp:include>

<div class="rn_frm_Box">
	<h1 style="font-size:50pt;">JOIN-US</h1>		
	<hr/>
	
	<form id="rn_join_frm" action="join.do" class="rn_frm" method="post">	
	
		<div class="rn_frm_row">
			<div class="rn_overlap">
				중복확인 : 
				<span id="rn_frm_idchk"></span>
			</div>
			<input type="text" id="mem_id" name="mem_id" class="rn_input" placeholder="아이디" onkeyup="idchk();"/>			
		</div>
		<div class="rn_frm_row">
			<input type="password" id="mem_pw" name="mem_pw" class="rn_input" placeholder="비밀번호" onkeyup="pwchk();" />
		</div>
		<div class="rn_frm_row">
			<input type="password" id="mem_pwok" name="mem_pwok" class="rn_input" placeholder="비밀번호확인" onkeyup="pwchk();" />
			<div id="rn_overlap_pwchk" class="rn_overlap" style="display:none;">
				일치확인 : 
				<span id="rn_frm_pwchk"></span>
			</div>
		</div>
		<div class="rn_frm_row">
			<input type="text" id="mem_name" name="mem_name" class="rn_input" placeholder="이름" />
		</div>
		<div class="rn_frm_row">
			<input type="text" id="mem_age" name="mem_age" class="rn_input" placeholder="나이" />
		</div>
		<div class="rn_frm_row">
			<input type="text" id="mem_email" name="mem_email" class="rn_input" placeholder="이메일" />
		</div>
		<div class="rn_frm_row">
			<input type="text" id="mem_addr1" name="mem_addr1" class="rn_input" placeholder="주소" />
		</div>
		<div class="rn_frm_row">
			<input type="text" id="mem_addr2" name="mem_addr2" class="rn_input" placeholder="상세주소" />
		</div>	
	
		<div class="rn_btn_list">
			<input type="submit" id="rn_btn_join" name="rn_btn_join" class="rn_btn rn_btn_B" value="회원가입"/>
			<a href="#" class="rn_btn rn_btn_C" onclick="history.back();">돌아가기</a>
		</div>
	</form>
</div>

<script>

//비밀번호 일치확인
function pwchk() {
	if ( $("#mem_pw").val() != $("#mem_pwok").val() ) {
		$("#rn_frm_pwchk").html("불일치");
		$("#rn_frm_pwchk").css({'color':'red'});		
		$("#rn_overlap_pwchk").css({'display':'block'});
	} else {
		$("#rn_frm_pwchk").html("일치");
		$("#rn_frm_pwchk").css({'color':'blue'});
		$("#rn_overlap_pwchk").css({'display':'none'});
	}
}

//아이디 사용 가능여부 확인
function idchk() {
	var json = new Object();
	json.mem_id = $("#mem_id").val();
	
	$.ajax({
		type: "POST"
	,	url: "/ajax_id_check.do"
	,	dataType : "json"
	,	cache: false
	,	async: false
	,	data : {
			temp : JSON.stringify(json)
		}
	,	success: function (data) {
			if(data.rtn == 1 || json.mem_id.length == 0){
				$("#rn_frm_idchk").html("사용불가");
				$("#rn_frm_idchk").css({'color':'red'});
			}else {
				$("#rn_frm_idchk").html("사용가능");
				$("#rn_frm_idchk").css({'color':'blue'});
			}
		}
	,	error: function (request, status, error) {
			alert("code : "+request.status+"\n"+"message : "+request.responseText+"\n"+"error : "+error);
			alert("관리자에게 문의하세요.");
			e.preventDefault();
			return false;
		}
	});		
}
$(function(){
	
	//화면 로딩 시 id입력부터 받을 수 있도록 함.
	$("#mem_id").focus();

	//회원가입 버튼 눌렀을 경우
	$("#rn_btn_join").click(function(){
		
		//체크할 항목을 for문으로 처리한다.
		var idVal = ["id","pw","pwok","name","email"];
		var msgVal = ["아이디를 ", "비밀번호를 ", "비밀번호 확인을 ", "이름을 ", "이메일을 "];
		var len = idVal.length;
		
		for (var i = 0; i < len; i++) {
			if ( $("#mem_" + idVal[i]).val() == "" ) {
				alert(msgVal[i] + "입력해주세요.");
				$("#mem_" + idVal[i]).focus();
				return false;
			}
		}
		
		if ($("#rn_frm_idchk").html() == "사용불가") {
			alert("아이디를 사용할 수 없습니다.");
			$("#mem_id").focus();
			return false;			
		}
		
		if ($("#rn_frm_pwchk").html() == "불일치") {
			alert("비밀번호가 일치하지 않습니다.");
			$("#mem_pw").focus();
			return false;	
		}
	});	
});

</script>

<jsp:include page="../inc/footer.jsp"></jsp:include>