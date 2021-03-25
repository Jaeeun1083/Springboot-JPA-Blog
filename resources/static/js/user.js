let index ={
	init: function(){
		$("#btn-save").on("click",()=>{ //function(){}을 사용하지 않고 ()=>{}를 사용한 이유 : this를 바인딩 하기 위해서.
		//리스너를 만드는 것. 누군가 btn-save를 찾아서 btn-save가 click이 되면
		/*첫번째 파라미터는 어떤 이벤트가 일어날지를 결정하고
		두번째 파라미터는 무엇을 할 건지를 적으면 된다.*/
			this.save();
		});

	},
	save: funcion(){
		//alert("user의 save함수 호출됨")
		let data= {
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val()
		};
		//console.log(data);
		//ajax호출 시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
		 
	  $.ajax({
		// 회원가입 수행 요청
		type: "POST", //insert할 것이니까
		url: "blog/api/user",
		data: JSON.stringify(data), //http body 데이터	 ->MIME타입 필요
		contentType: "application/jsonl charset-utf-8", //body데이터가 어떤 타입인지.
		dataType: "json" //요청을 서버로 해서 응답이 왔을 때 응답을 json타입으로 받겠다	기본적으로 모든 것이 String(문자열)인데 생긴게 json이라면 => javascript오브젝트로 변경해줌
	}).done(function(resp){
		//정상이면 실행
		alert("회원가입이 완료되었습니다.");
		//alert(resp);
		//console.log(resp);
		location.href="/blog";
	}).fail(function(error){
		//실패이면 실행
		alert(JSON.stringify(error));
	});
	
	}
}
	


index.init();

/*joinForm에서 다 읽어질 때 script를 읽고 index.init함수를 호출해주면 init이 호출이 되며 바인딩.
이때 btn-save를 클릭하면 함수가 호출이 됨.*/

