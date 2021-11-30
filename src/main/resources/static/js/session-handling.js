function hitTheSpot(){
    alert("show");
    console.log("Hello World");
}


function postRequest(email, id){
    var str = 'http://localhost:8083/api/v1/joinSession/'+ email+ '/' + id;
    alert(str);
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", str , true);
    xmlHttp.send();
}

function httpGet(url)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", url, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}

function getSessions(){
    var data = httpGet('http://localhost:8083/api/v1/sessions');
    var sessions = JSON.parse(data);
    var mainContainer = document.getElementById("table");
    var alert1 = JSON.stringify(sessions[0].sessionId);


    for(var i = 0; i < sessions.length; i++){


        var div = document.createElement("div");
        var div1 = document.createElement("div");
        div.setAttribute('class', 'sessions');
        div.setAttribute('onclick', 'joinSession(this.id)');
        div.setAttribute('id', 'sessions' + i);



        var sessionID = JSON.stringify(sessions[i].sessionId);
        sessionID = sessionID.replaceAll(/['"]+/g, '');

        var user = JSON.stringify(sessions[i].user1.firstname);
        user = user.replaceAll(/['"]+/g, '');

        div.innerHTML = 'Session: ' + sessionID;
        div1.innerHTML = 'Username: ' + user;
        mainContainer.appendChild(div);
        mainContainer.appendChild(div1);
    }
}

function joinSession(clicked_id){
    var email = document.getElementById('email').textContent;
    var userInformation = httpGet('http://localhost:8083/account/'+ email);

    var sessionId = document.getElementById(clicked_id).textContent;
    sessionId = sessionId.replace("Session: ", "");

    var sessionData = httpGet('http://localhost:8083/api/v1/session/' + email);

    postRequest(sessionId, email);

    alert(sessionId);
    var sessionData = httpGet('http://localhost:8083/api/v1/sessionId/'+ sessionId);
    window.location.href = "http://localhost:8083/api/v1/rollTheDice/" + sessionId;
}

