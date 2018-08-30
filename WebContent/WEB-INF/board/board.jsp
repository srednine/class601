<%@page import="com.ds.vo.RN_Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../inc/header.jsp"></jsp:include>
<link rel="stylesheet" 
href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" 
integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" 
crossorigin="anonymous">

<style>

.rn_hover{
	background-color:#fdf7df;
}

.rn_font_weight{
	font-weight:bold;
}
.
table > a{
	text-decoration:none;
}

th:nth-child(1), td:nth-child(1){
	width:10px;
}
th:nth-child(2), td:nth-child(2){
	width:60px;
}
th:nth-child(3), td:nth-child(3){
	width:300px;
}
th:nth-child(4), td:nth-child(4){
	width:60px;
}
th:nth-child(5), td:nth-child(5){
	width:60px;
}
th:nth-child(6), td:nth-child(6){
	width:100px;
}
th:nth-child(7), td:nth-child(7){
	width:0px;
}

.rn_board_page{
	margin:0 auto;
	text-align:center;
}

.rn_board_page a{
	padding:0 5px;
}

.nowpage{
	font-weight:bold;
	text-decoration: underline;
}

.rn_frist_tappage, .rn_end_tappage, .rn_prev_tappage, .rn_next_tappage{
	/* font-weight:bold;
    border: none; */
    color: black;
    background-color:white;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
}
.rn_frist_tappage, .rn_end_tappage{
    color: black;
    border: 2px solid #555555;
}
.rn_frist_tappage:hover, .rn_end_tappage:hover{
    background-color: #555555;
    color: white;
    cursor: pointer;
}
.rn_prev_tappage, .rn_next_tappage{
    color: black; 
    border: 2px solid #008CBA;
}
.rn_prev_tappage:hover, .rn_next_tappage:hover{
    background-color: #008CBA;
    color: white;
    cursor: pointer;
}
.rn_frist_tappage[disabled=disabled], .rn_frist_tappage:disabled,
.rn_end_tappage[disabled=disabled], .rn_end_tappage:disabled,
.rn_prev_tappage[disabled=disabled], .rn_prev_tappage:disabled, 
.rn_next_tappage[disabled=disabled], .rn_next_tappage:disabled{
  border: 2px solid #999999;
  background-color: #cccccc;
  color: white;
  cursor:default;
}

</style>

<table border="1" class="rn_table">
	<thead>
		<tr>
			<th><input type="checkbox" id="rn_header_chkbox"/></th>
			<th>순번</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>작성일자</th>
			<th>비공개</th>
			<th>글번호</th>
		</tr>
	</thead>
	
	<tbody>
		<%
			List<RN_Board> list = (List<RN_Board>) request.getAttribute("list");
			for(RN_Board vo : list){
		%> 
		<tr>
			<td><input type="checkbox" class="rn_body_chkbox" value="<%=vo.getBrd_no()%>"/></td>
			<td><%=vo.getRnum()%></td>
			<td>
				<a href="#">
					<% if( vo.getBrd_secret().equals("Y") ) { %>
						<i class="fas fa-lock"></i>
					<% } %>
					<%=vo.getBrd_title()%>
				</a>
			</td>
			<td><%=vo.getBrd_writer()%></td>
			<td><%=vo.getBrd_hit()%></td>
			<td><%=vo.getBrd_date()%></td>
			<td><%=vo.getBrd_secret()%></td>
			<td><%=vo.getBrd_no()%></td>
		</tr>
		<%
			}
		%>	
	</tbody>
</table>


<div class="rn_btn_list">
	<input type="button" id="rn_board_write" value="새글"/>
	<input type="button" id="rn_board_del_all" value="삭제"/>
</div>

<div class="rn_board_page">
	
	<%
		String nowPage = request.getParameter("page");
		if(nowPage == null) nowPage = "1";
	%>
	<button class="rn_frist_tappage" onclick="rnPaging('1');" <%=(String)request.getAttribute("firstBtnYN")%>>
		&lt;&lt;처음
	</button>
	<button class="rn_prev_tappage" onclick="rnPaging('<%=Integer.parseInt(nowPage)-1%>');" <%=(String)request.getAttribute("prevBtnYN")%>>
		&lt;이전
	</button>
	
	<%
		for(int i = (int)request.getAttribute("startTapPage");i<(int)request.getAttribute("endTapPage")+1;i++){ 
	%>
		<a href="board.do?page=<%=i%>" class="<%= Integer.parseInt(nowPage) == i ? "nowpage" : "" %>"><%=i%></a>
	<% } %>
	
	<button class="rn_next_tappage" onclick="rnPaging('<%=Integer.parseInt(nowPage)+1%>');" <%=(String)request.getAttribute("nextBtnYn")%>>
		다음&gt;
	</button>
	<button class="rn_end_tappage" onclick="rnPaging('<%=request.getAttribute("lastPage")%>');" <%=(String)request.getAttribute("lastBtnYN")%>>
		맨끝&gt;&gt;
	</button>
</div>

<script>

//페이징 관련 함수
function rnPaging(page) {
	window.location.href="board.do?page=" + page;
}

$(function(){

	//전체체크
	$('#rn_header_chkbox').on("click",function(){		
		if( $("#rn_header_chkbox").is(":checked") ) {
			$(".rn_body_chkbox").prop("checked", true);
			$(".rn_table tr").addClass("rn_font_weight");
		} else {
			$(".rn_body_chkbox").prop("checked", false);
			$(".rn_table tr").removeClass("rn_font_weight");
		}
	});
	
	//0번째 tr보다 큰 애들만
    $(".rn_table tr:gt(0)").hover(
		function () {
			$(this).addClass("rn_hover");
		}
	,	function () {
			$(this).removeClass("rn_hover");
		}
	);
	
	//rn_body_chkbox
    $(".rn_body_chkbox").on("click",function(){    	
    	var idx = $(this).index(".rn_body_chkbox");    	
    	if ( $(".rn_body_chkbox").eq(idx).is(":checked") ) {
    		$(".rn_table tr:gt(0)").eq(idx).addClass("rn_font_weight");			
		} else {
			$(".rn_table tr:gt(0)").eq(idx).removeClass("rn_font_weight");
		}
    });	
	
	//선택한 행 삭제
	$("#rn_board_del_all").on("click",function(){
		if ( $(".rn_body_chkbox:checked").length < 1 ) {
			alert("삭제할 행을 먼저 선택해주세요.");
			return false;
		}
		if (confirm("선택한 행들을 삭제하시겠습니까?")) {
			var brdNoArr = new Array();
			$(".rn_body_chkbox:checked").each(function() {
				brdNoArr.push($(this).val());				
			});
			//alert("brdNoArr : " + brdNoArr);
			
			var json = new Object();
			json.brdNoArr = brdNoArr;
			
			console.log("brdNoArr : " + brdNoArr);
			
			$.ajax({
				type: "POST"
			,	url: "/ajax_board_delete_batch.do"
			,	dataType : "json"
			,	cache: false
			,	async: false
			,	data : {
					//temp : JSON.stringify(json)
					temp : brdNoArr
				}
			,	success: function (data) {
					if (data.rtn == 1) {
						alert("삭제완료");						
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
	});
    
});


</script>

<jsp:include page="../inc/footer.jsp"></jsp:include>

