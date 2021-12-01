'use strict';

const session = JSON.parse(document.cookie);
const session1 = JSON.stringify(session);

console.log(session.sessionId);
alert(session1);

document.getElementById("user-info").innerHTML = "Hello " + session.user1.firstname;

var timer;

function startTimer() {

    alert(session.sessionId);
    var countdown = 20;
    timer = setInterval(function() {

        countdown = countdown -1;
        document.getElementById("waiting-time").innerHTML = countdown;
        checkIfPlayer2JoindSession();
        if(countdown == 0){
            stopTimer();
            document.getElementById("waiting-time").innerHTML = "We couldnt find a second Player for you sorry";
        }


    }, 1000);
}

startTimer();

function stopTimer() {
    alert("Timer stopped");
    clearInterval(timer);
}

function getSession(){

}

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

function checkIfPlayer2JoindSession(){
    console.log("Checking Player 2");
    var sessionData = getSession(session.sessionId);
    alert("From Checker" + sessionData.user1.email);
    if(sessionData.user2 != null){
        alert("OK"+ session.user2.firstname);
        stopTimer();
        document.cookie = sessionData;
        joinCreatedSession();
    }
}

function joinSession(session_id, useremail){

    var email = useremail;
    var userInformation = httpGet('http://localhost:8083/account/'+ email);

    var sessionId = sessionId;

    var sessionData = httpGet('http://localhost:8083/api/v1/session/' + sessionId);
    var url = 'http://localhost:8083/api/v1/joinSession1/'+ email +'/' + sessionId
    httpGet(url);

    window.location.href = "http://localhost:8083/api/v1/rollTheDice/" + sessionId;
}

function joinCreatedSession(){

    var email = session.user1.email;
    var sessionId = session.sessionId;
    console.log("joinCreatedSession email" + email);
    console.log("joinCreatedSession sessionId" + sessionId);
    var userInformation = httpGet('http://localhost:8083/account/'+ email);

    var sessionData = httpGet('http://localhost:8083/api/v1/session/' + sessionId);
    var url = 'http://localhost:8083/api/v1/joinSession1/'+ email +'/' + sessionId;
    httpGet(url);

    window.location.href = "http://localhost:8083/api/v1/rollTheDice/" + sessionId;
}