$(".answer-write input[type=submit]").click(addAnswer);
var count = $(".qna-comment-slipp").find("strong").text();

function addAnswer(e) {
	e.preventDefault();
	console.log("click event activated");	
	
	var queryString = $(".answer-write").serialize();
	console.log("query : " + queryString);
	
	var url = $(".answer-write").attr("action");
	console.log("fetched url : " + url)
	
	
	//console.log("fetched count : " + count);
	
	$.ajax({
			type : 'post',
			url : url, 
			data : queryString,
			dataType : 'json' ,
			error : onError,
			success : onSuccess});
}

function onError(){
	
}

function onSuccess(data, status){
	console.log(data);
	var count = $(".qna-comment-slipp").find("strong").text();
	var countNumber = parseInt(count) + 1;
	var countCaption = "<strong>" + countNumber + "개의 의견</strong>";
	$("strong").replaceWith(countCaption);
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(data.author, data.time, data.content, data.question.questionid, data.question.questionid);
	$(".qna-comment-slipp-articles").prepend(template);
}

String.prototype.format = function() {
	  var args = arguments;
	  return this.replace(/{(\d+)}/g, function(match, number) {
	    return typeof args[number] != 'undefined'
	        ? args[number]
	        : match
	        ;
	  });
	};