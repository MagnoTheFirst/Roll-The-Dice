var session = null;
var gameSessionId = null;
var email = null;

function httpGet(url)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", url, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}

function getSession(sessionId){
    var session = httpGet('http://localhost:8083/api/v1/session/' + sessionId);
    var data = JSON.parse(session);
    return data;
}

function getThisSession(){
    return this.session;
}

function getSessions(){
    var data = httpGet('http://localhost:8083/api/v1/sessions');
    var sessions = JSON.parse(data);
    var mainContainer = document.getElementById("table");



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
        mainContainer.appendChild(div1);
        mainContainer.appendChild(div);

    }
}

//TODO[] do I need this function?
function postRequest(email, id){
    var str = 'http://localhost:8083/api/v1/joinSession/'+ email + '/' + id;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", str , true);
    xmlHttp.send();
}

function joinSession(clicked_id){

    var email = document.getElementById('email').textContent;
    var userInformation = httpGet('http://localhost:8083/account/'+ email);

    var sessionId = document.getElementById(clicked_id).textContent;
    sessionId = sessionId.replace("Session: ", "");

    var sessionData = httpGet('http://localhost:8083/api/v1/session/' + sessionId);
    var url = 'http://localhost:8083/api/v1/joinSession1/'+ email +'/' + sessionId
    httpGet(url);
    session = JSON.parse(sessionData);

    var sessionData = httpGet('http://localhost:8083/api/v1/sessionId/'+ sessionId);
    window.location.href = "http://localhost:8083/api/v1/rollTheDice/" + sessionId;
}

function joinSessionNew(clicked_id){

    var email = document.getElementById('email').textContent;
    var userInformation = httpGet('http://localhost:8083/account/'+ email);

    var sessionId = document.getElementById(clicked_id).textContent;
    sessionId = sessionId.replace("Session: ", "");

    var sessionData = httpGet('http://localhost:8083/api/v1/session/' + sessionId);
    var url = 'http://localhost:8083/api/v1/joinSession1/'+ email +'/' + sessionId
    httpGet(url);
    session = JSON.parse(sessionData);
    var sessionData = httpGet('http://localhost:8083/api/v1/sessionId/'+ sessionId);
    window.location.href = "http://localhost:8083/api/v1/rollTheDice/" + sessionId;
}

function rollTheSessionDice(){
    var session = getGameSession();
    var dice = httpGet('http://localhost:8083/api/v1/sessionDiceValue/' + session.sessionId);
    var activeSession = JSON.parse(httpGet('http://localhost:8083/api/v1/activeSession/' + session.sessionId));
    document.getElementById("score-player1").innerHTML = activeSession.score.score_player1;
    document.getElementById("score-player2").innerHTML = activeSession.score.score_player2;

}



function playerInfo(){
    var session = getActiveSession(this.gameSessionId);
//TODO[] check if userturn true if it is stop timer
    var email = document.cookie;

    var firstRandomNumber = session.score.score_player1;
    var secondRandomNumber = session.score.score_player2;

    const firstDiceImage = '/assets/dice' + firstRandomNumber + '.png';
    const secondDiceImage = '/assets/dice' + secondRandomNumber + '.png';

    document.getElementById("user1").innerHTML = session.user1.firstname;
    document.getElementById("user2").innerHTML = session.user2.firstname;

    document.querySelectorAll('img')[0].setAttribute('src', firstDiceImage);
    document.querySelectorAll('img')[1].setAttribute('src', secondDiceImage);
}

function getCookies(){
    return document.cookie;
}

function getEmail(){return document.cookie}

function redirectToWaitingFor(){
    var email = document.getElementById('email').textContent;
    window.location.href = "http://localhost:8083/api/v1/waitingForUser";
}
