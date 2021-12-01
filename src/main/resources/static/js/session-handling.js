var session = null;

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

function postRequest(email, id){
    alert(email + " " + id);
    var str = 'http://localhost:8083/api/v1/joinSession/'+ email + '/' + id;
    alert(str);
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

function getGameSession(){
    var session = JSON.parse(httpGet('http://localhost:8083/api/v1/session/currentSession'));
    console.log(session);
    return session;
}

function rollTheSessionDice(){
    var session = getGameSession();
    var dice = httpGet('http://localhost:8083/api/v1/sessionDiceValue/' + session.sessionId);
    var activeSession = JSON.parse(httpGet('http://localhost:8083/api/v1/activeSession/' + session.sessionId));
    document.getElementById("score-player1").innerHTML = activeSession.score.score_player1;
    document.getElementById("score-player2").innerHTML = activeSession.score.score_player2;

}


function rollTheDiceNew(){
    var session = getGameSession();

    var dice1 = JSON.parse(httpGet('http://localhost:8083/api/v1/sessionDiceValue/' + session.sessionId));
    var firstRandomNumber = dice1.dice_value+1;

    const firstDiceImage = '/assets/dice' + firstRandomNumber + '.png';
    document.querySelectorAll('img')[0].setAttribute('src', firstDiceImage);

    document.querySelectorAll('img')[1].setAttribute('src', secondDiceImage);

    if (firstRandomNumber > secondRandomNumber) {
        document.querySelector('h1').innerHTML = "The winner is User 1";

    } else if (firstRandomNumber < secondRandomNumber) {

        document.querySelector('h1').innerHTML = "The winner is User 2";
    } else {

        document.querySelector('h1').innerHTML = "It's a draw";
    }

    var activeSession = JSON.parse(httpGet('http://localhost:8083/api/v1/activeSession/' + session.sessionId));
    document.getElementById("score-player1").innerHTML = activeSession.score.score_player1;
    document.getElementById("score-player2").innerHTML = activeSession.score.score_player2;
}

function playerInfo(){
    var session = getGameSession();
    document.getElementById("user1").innerHTML = session.user1.firstname;
    document.getElementById("user2").innerHTML = session.user2.firstname;
}

var timer;

function startTimer() {
    timer = setInterval(function() {
        rollTheSessionDice();
    }, 10000);
}

function stopTimer() {
    alert("Timer stopped");
    clearInterval(timer);
}