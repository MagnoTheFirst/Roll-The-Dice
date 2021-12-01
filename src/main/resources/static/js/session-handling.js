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
    document.cookie = email;
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
    document.cookie = email;
    var sessionData = httpGet('http://localhost:8083/api/v1/sessionId/'+ sessionId);
    window.location.href = "http://localhost:8083/api/v1/rollTheDice/" + sessionId;
}

function createNewSession(){

    var email = document.getElementById('email').textContent;
    alert(email);

    var session = JSON.parse(httpGet('http://localhost:8083/api/v1/newSession/' + email));
    var sessionData = httpGet('http://localhost:8083/api/v1/newSession/' + email);
    document.cookie = sessionData;
    alert(session.sessionId);
    //alert(session.sessionId);

    var sessionId = session.sessionId;

   // var sessionData = httpGet('http://localhost:8083/api/v1/session/' + sessionId);

    window.location.href = "http://localhost:8083/api/v1/waitingForUser";

}

function getGameSession(){
    var session = JSON.parse(httpGet('http://localhost:8083/api/v1/session/currentSession'));
    console.log(session);
    this.gameSessionId = session.sessionId;
    return session;
}

function rollTheSessionDice(){
    var session = getGameSession();
    var dice = httpGet('http://localhost:8083/api/v1/sessionDiceValue/' + session.sessionId);
    var activeSession = JSON.parse(httpGet('http://localhost:8083/api/v1/activeSession/' + session.sessionId));
    document.getElementById("score-player1").innerHTML = activeSession.score.score_player1;
    document.getElementById("score-player2").innerHTML = activeSession.score.score_player2;

}


function getActiveSession(sessionId){
    var session = httpGet('http://localhost:8083/api/v1/activeSession/' + sessionId);
    var data = JSON.parse(session);
    return data;
}


function rollTheDiceNew(){
    var session = getActiveSession(this.gameSessionId);
    alert(session.sessionId);
    var dice1 = JSON.parse(httpGet('http://localhost:8083/api/v1/sessionValues/' + session.sessionId));

    var firstRandomNumber = dice1.score.score_player1;
    var secondRandomNumber = dice1.score.score_player2;

    alert(firstRandomNumber);
    alert(secondRandomNumber);

    const firstDiceImage = '/assets/dice' + firstRandomNumber + '.png';
    const secondDiceImage = '/assets/dice' + secondRandomNumber + '.png';

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
    document.getElementById("score-player1").innerHTML = dice1.score.score_player1;
    document.getElementById("score-player2").innerHTML = dice1.score.score_player2;
    startTimer();
}

function rollTheDiceNew1(){
    var session = getActiveSession(this.gameSessionId);
    alert(session.sessionId);
    var dice1 = JSON.parse(httpGet('http://localhost:8083/api/v1/sessionValues/' + session.sessionId + '/' + email));

    var firstRandomNumber = dice1.score.score_player1;
    var secondRandomNumber = dice1.score.score_player2;

    alert(firstRandomNumber);
    alert(secondRandomNumber);

    const firstDiceImage = '/assets/dice' + firstRandomNumber + '.png';
    const secondDiceImage = '/assets/dice' + secondRandomNumber + '.png';

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
    document.getElementById("score-player1").innerHTML = dice1.score.score_player1;
    document.getElementById("score-player2").innerHTML = dice1.score.score_player2;
    startTimer();
    document.getElementById("rollTheDiceBtn").disabled = true;
    document.getElementById("rollTheDiceBtn").bgcolor = '#FFFFFF1B';


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

//TODO[] check functionality
function playerInfo1(){
    var session = getActiveSession(this.gameSessionId);
//TODO[] check if userturn true if it is stop timer
    var email = document.cookie;

    if(session.userTurn == email){
        alert('Stop the loop. Its ' + email + 'turn');
        document.getElementById("rollTheDiceBtn").disabled = false;
        stopTimer();
        document.getElementById("rollTheDiceBtn").bgcolor = 'rgba(151,255,27,0.34)';
    }

    var firstRandomNumber = 0;
    var secondRandomNumber = 0;


    if(firstRandomNumber == 0 && secondRandomNumber == 0){
        firstRandomNumber = 1;
        secondRandomNumber = 1;
    }
    else if(secondRandomNumber == 0){
        secondRandomNumber = 1;
    }
    else if(firstRandomNumber == 0){
        firstRandomNumber = 1;
    }
    else{
        firstRandomNumber = session.score.score_player1;
        secondRandomNumber = session.score.score_player2;
    }

    const firstDiceImage = '/assets/dice' + firstRandomNumber + '.png';
    const secondDiceImage = '/assets/dice' + secondRandomNumber + '.png';

    document.getElementById("score-player1").innerHTML = session.score.score_player1;
    document.getElementById("score-player2").innerHTML = session.score.score_player2;

    document.getElementById("user1").innerHTML = session.user1.firstname;
    document.getElementById("user2").innerHTML = session.user2.firstname;

    document.querySelectorAll('img')[0].setAttribute('src', firstDiceImage);
    document.querySelectorAll('img')[1].setAttribute('src', secondDiceImage);


}

var timer;

function startTimer() {
    timer = setInterval(function() {
        //playerInfo();
        playerInfo1();
    }, 10000);
}

function stopTimer() {
    alert("Timer stopped");
    clearInterval(timer);
}

function getCookies(){
    return document.cookie;
}

function loadUser(){
    this.email =  document.cookie;
}

function getEmail(){return document.cookie}

function prepareTemplate(){
    var session = getActiveSession(this.gameSessionId);
    var email = document.cookie;


    var firstRandomNumber = session.score.score_player1 + 1;
    var secondRandomNumber = session.score.score_player2 + 1;

    const firstDiceImage = '/assets/dice' + firstRandomNumber + '.png';
    const secondDiceImage = '/assets/dice' + secondRandomNumber + '.png';

    document.getElementById("user1").innerHTML = session.user1.firstname;
    document.getElementById("user2").innerHTML = session.user2.firstname;

    document.querySelectorAll('img')[0].setAttribute('src', firstDiceImage);
    document.querySelectorAll('img')[1].setAttribute('src', secondDiceImage);

    if(session.userTurn != email){
        startTimer();
        document.getElementById("rollTheDiceBtn").disabled = true;
        document.getElementById("rollTheDiceBtn").bgcolor = 'rgba(151,255,27,0.34)';
    }

    //load dice images by default
    //check who has the turn
    //block player whoms turn is the second
}

function redirectToWaitingFor(){
    window.location.href = "http://localhost:8083/api/v1/waitingForUser";

}
