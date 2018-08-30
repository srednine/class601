/*
 Copyright (C) RedNine 2018
 Distributed under the RedNine License 
 */

/*
ajax의 개념.
복붙은 아래에.

$.ajax({
	type: "POST" //"POST", "GET"
,	async: true //true(default), false
,	url: "/common/response.jsp" //Request URL
,	dataType : "html"  //전송받을 데이터의 타입 "xml", "html", "script", "json" 등 지정 가능 미지정시 자동 판단
,	timeout: 30000 //제한시간 지정
,	cache: false //true(default), false
,	data: 'a=asdf' //$("#inputForm").serialize() //서버에 보낼 파라메터, form에 serialize() 실행시 a=b&c=d 형태로 생성되며 한글은 UTF-8 방식으로 인코딩 "a=b&c=d" 문자열로 직접 입력 가능
,	contentType: "application/x-www-form-urlencoded; charset=UTF-8" //"application/json"
,	success: function (response, status, request) {
		//통신 성공시 처리
		$('#target').html(response);
	}
,	error: function (request, status, error) {
		//통신 에러 발생시 처리
		alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
	}
,	beforeSend: function () {
		//통신을 시작할때 처리
		var padingTop = (Number(($('#target').css('height')).replace("px", "")) / 2) - 20;
		$('#loading').css('position', 'absolute');
		$('#loading').css('left', $('#target').offset().left);
		$('#loading').css('top', $('#target').offset().top);
		$('#loading').css('width', $('#target').css('width'));
		$('#loading').css('height', $('#target').css('height'));
		$('#loading').css('padding-top', padingTop);
		$('#loading').show().fadeIn('fast');
	}
,	complete: function () {
		//통신이 완료된 후 처리
		$('#loading').fadeOut();
	}
});


$.ajax({
	//$("#inputForm").serialize()
	type: "POST"
,	url: ""
,	async: true //true(default), false
,	dataType : "json"
,	cache: false
,	data: {
 	
 	}
,	success: function (data) {
		//통신 성공시 처리
	}
,	error: function (request, status, error) {
		//통신 에러 발생시 처리
		alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
	}
,	beforeSend: function () {
		//통신을 시작할때 처리
	}
,	complete: function () {
		//통신이 완료된 후 처리
	}
});

*/
