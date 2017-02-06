// class=answer-write, input type이 submit인 앨리먼트에 클릭 이벤트가 발생하면 addAnswer() 호출
$(".answer-write input[type=submit]").click(addAnswer);

// e - click이 발생한 Event 정보
function addAnswer(e) {
    console.log("click me");
    e.preventDefault();  // 서버로 바로 전송하는 default 동작을 막는다

    // 서버로 전송할 데이터를 읽어온다
    var queryString = $(".answer-write").serialize();  // key:value형태로 온다
    console.log("query: " + queryString);

    // action에 있는 값 읽어오기
    var url = $(".answer-write").attr("action");
    console.log("url: " + url);

    // 서버에 데이터를 전송
    // type - http method
    $.ajax({
        type: 'post',
        url: url,
        data: queryString,
        dataType: "json",
        error: onError,  // 에러발생시 호출할 function
        success: onSuccess
    });  // 정상 동작시 호출할 function
}

function onError() {
}
function onSuccess(data, status) {
    console.log(data);
    var answerTemplate = $("#answerTemplate").html();  // id=answerTemplate을 읽어온다
    //밑에 있는 format() 호출 -> template에 data를 넣어 html을 만든다
    var template = answerTemplate.format(
        data.writer.userId,
        data.formattedCreatedAt,
        data.contents,
        data.question.id,
        data.id);
    $(".qna-comment-slipp-articles").prepend(template);  // 만든 html을 붙여준다

    // textarea 초기화
    // $("textarea[name=contents]").val("");
    $(".answer-write div[class=form-group] textarea[name=contents]").val("");
}


// 동적으로 생성된 html에는  e.preventDefault() 동작X -> Event Delegation 이용
// $("a.link-delete-answer").click(deleteAnswer);
$('.qna-comment-slipp-articles').on('click', '.link-delete-answer', deleteAnswer);

function deleteAnswer(e) {
    e.preventDefault();
    var deleteBtn = $(this);  // 현재 이벤트가 발생한 자기자신
    var url = deleteBtn.attr("href");
    console.log("url: " + url);

    $.ajax({
        type: 'delete',
        url: url,
        dataType: 'json',
        error: function (xhr, status) {
            console.log("error");
        },
        success: function (data, status) {
            console.log("data: " + data);
            if (data.valid) {
                deleteBtn.closest("article").remove();
            } else {
                alert(data.errorMessage);
            }
        }
    });
}

// template를 쓰기 위한 코드
String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined' ? args[number] : match;
    });
};


