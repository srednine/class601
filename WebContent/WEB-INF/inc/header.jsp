<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css?ver=1">
<link rel="stylesheet" type="text/css" href="css/rednine.css?ver=2">
<script src="js/jquery-3.3.1.min.js"></script>
<title>RedNine's Board ;)</title>
</head>
<body>

<%
	HttpSession httpsession = request.getSession();
	String id = (String) httpsession.getAttribute("sid");		//id
	String name = (String) httpsession.getAttribute("sname");	//누구누구님 환영합니다 하려고 가져옴.
	String path = request.getServletPath();		//아무것도 없으면 / 만 날라옴.
	path = path.substring(9, path.length() - 4) + ".do";
	String query = request.getQueryString();	//파라메터가 없으면 null

	System.out.println("****************************************************************");
	System.out.println("MainInterceptor >> " + path + "?" + query);				
	
	String backUrl = null;
	if (query == null) {
		backUrl = path;
	} else {
		backUrl = path + "?" + query;
	}
	httpsession.setAttribute("back_url", backUrl);
	
	System.out.println("back_url : " + httpsession.getAttribute("back_url"));
	System.out.println("****************************************************************");
%>
<div class="wrap">
	<nav class="navbar navbar-toggleable-md navbar-light bg-faded">
		<a class="navbar-brand" href="index.do">HOME</a>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<% if(id == null) { %> 
						<a class="nav-link" href="#" onclick="Gointercepter('i');">로그인</a> 
					<% } else { %>
						<a class="nav-link" href="#" onclick="Gointercepter('o');">로그아웃</a>
					<% } %>
				</li>
				
				<% if(id == null) { %> 
				<li class="nav-item">
					<a class="nav-link" href="join.do">회원가입</a>
				</li> 
				<% } else { %> 
				<li class="nav-item">
					<a class="nav-link" href="edit.do">회원정보수정</a>
				</li> 
				<% } %>

				<li class="nav-item">
					<a class="nav-link" href="board.do">게시판</a>
				</li>
			</ul>
			
			<script>
			function Gointercepter(gubun){
				var loca = "";
				gubun == "i" ? loca = "login.do" : loca = "logout.do";
				window.location.href = loca;
			}
			</script>

			<!-- 
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="text" placeholder="...">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
			</form>
			 -->
		</div>
	</nav>
	
	<!-- 윗 공간 띄우기 용 -->
	<div style="height:20px"></div>
	
	<div class="container">