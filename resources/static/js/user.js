let index ={
	init: function(){
		$("#btn-save").on("click",()=>{
					//리스너를 만드는 것. 누군가 btn-save를 찾아서 btn-save가 click이 되면
		//첫번째 파라미터는 어떤 이벤트가 일어날지를 결정하고
		//두번째 파라미터는 무엇을 할 건지를 적으면 된다.
			this.save();
		});

	},
	save: funcion(){
		//alert("user의 save함수 호출됨")
		let data= {
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val()
		}
		//console.log(data);
	  $.ajax().done().fail(); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청할 것.
	} 
}

index.init();

//joinForm에서 다 읽어질 때 script를 읽고 index.init함수를 호출해주면 init이 호출이 되며 바인딩.
//이때 btn-save를 클릭하면 함수가 호출이 됨.