function send() {
    let object = {
        "code": document.getElementById("code_snippet").value,
        "time": document.getElementById("time_restriction").value,
        "views": document.getElementById("views_restriction").value
    };

    if (object['code'] !== '// write your code here' && object['code'] !== '') {
        let json = JSON.stringify(object);

        let xhr = new XMLHttpRequest();
        xhr.open("POST", '/api/code/new', false)
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json);
        let response = JSON.parse(xhr.responseText);

        let uuid = response['id'];
        let codeUuid = document.getElementById('code_uuid');
        codeUuid.innerHTML = 'Your unique id is: ' + uuid + ' (make sure to note it down!)';
        codeUuid.style.color = "white";

        let codeLink = document.getElementById('code_link');
        codeLink.href = uuid;
        codeLink.innerHTML = "View your code here";
    }
}
