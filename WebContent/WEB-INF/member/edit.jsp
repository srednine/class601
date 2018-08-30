<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../inc/header.jsp"></jsp:include>

<% 
	String mem_id = (String)request.getAttribute("mem_id");
	String mem_pw = (String)request.getAttribute("mem_pw");
	String mem_name = (String)request.getAttribute("mem_name");
	String mem_email = (String)request.getAttribute("mem_email");
	String mem_addr1 = (String)request.getAttribute("mem_addr1");
	String mem_addr2 = (String)request.getAttribute("mem_addr2");
	int mem_age = (int)request.getAttribute("mem_age");
%>


<div class="rn_frm_Box">
	<h1 style="font-size:50pt;">EDIT-PROFILE</h1>		
	<hr/>
	
	<form id="rn_edit_frm" action="edit.do" class="rn_frm" method="post">	
	
		<div class="rn_frm_row">
			<input type="text" id="mem_id" name="mem_id" class="rn_input" value="<%=mem_id%>" readonly="readonly"/>			
		</div>
		<div class="rn_frm_row">
			<input type="password" id="mem_pw" name="mem_pw" class="rn_input" value="<%=mem_pw%>" placeholder="비밀번호" onkeyup="pwchk();" />
		</div>
		<div class="rn_frm_row">
			<input type="password" id="mem_pwok" name="mem_pwok" class="rn_input" value="<%=mem_pw%>" placeholder="비밀번호확인" onkeyup="pwchk();" />
			<div id="rn_overlap_pwchk" class="rn_overlap" style="display:none;">
				일치확인 : 
				<span id="rn_frm_pwchk"></span>
			</div>
		</div>
		<div class="rn_frm_row">
			<input type="text" id="mem_name" name="mem_name" class="rn_input" value="<%=mem_name%>" placeholder="이름" />
		</div>
		<div class="rn_frm_row">
			<input type="text" id="mem_age" name="mem_age" class="rn_input" value="<%=mem_age%>"placeholder="나이" />
		</div>
		<div class="rn_frm_row">
			<input type="text" id="mem_email" name="mem_email" class="rn_input" value="<%=mem_email%>" placeholder="이메일" />
		</div>
		<div class="rn_frm_row">
			<input type="text" id="mem_addr1" name="mem_addr1" class="rn_input" value="<%=mem_addr1%>" placeholder="주소" />
		</div>
		<div class="rn_frm_row">
			<input type="text" id="mem_addr2" name="mem_addr2" class="rn_input" value="<%=mem_addr2%>" placeholder="상세주소" />
		</div>	
	
		<div class="rn_btn_list">
			<input type="button" id="rn_btn_edit" class="rn_btn rn_btn_B" value="회원정보수정"/>
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

$(function(){
	
	//화면 로딩 시 pw입력부터 받을 수 있도록 함.
	$("#mem_pw").focus();
	
	$("input").keydown(function(e){
		if (e.keyCode === 13) {
			$("#rn_btn_edit").click();
	    }
	})

	//회원수정 버튼 눌렀을 경우
	$("#rn_btn_edit").click(function(){
		
		//체크할 항목을 for문으로 처리한다.
		var idVal = ["pw","pwok","name","email"];
		var msgVal = ["비밀번호를 ", "비밀번호 확인을 ", "이름을 ", "이메일을 "];
		var len = idVal.length;
		
		for (var i = 0; i < len; i++) {
			if ( $("#mem_" + idVal[i]).val() == "" ) {
				alert(msgVal[i] + "입력해주세요.");
				$("#mem_" + idVal[i]).focus();
				return false;
			}
		}
		
		if ($("#rn_frm_pwchk").html() == "불일치") {
			alert("비밀번호가 일치하지 않습니다.");
			$("#mem_pw").focus();
			return false;	
		}
		
		//확인
		if(confirm("정보를 수정하시겠습니까?") == true){
			$("#rn_edit_frm").submit();
		//취소
		}else if(con_test == false){
		}
	});	
});

</script>

<jsp:include page="../inc/footer.jsp"></jsp:include>