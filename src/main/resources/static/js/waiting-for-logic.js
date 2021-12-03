'use strict';
const email = document.cookie;
var session;
var timer;

createNewSession1();

function createNewSession1(){

    var sessionData = JSON.parse(httpGet('http://localhost:8083/api/v1/newSession/' + email));


    session = sessionData;
    document.getElementById("sessionId").innerHTML = sessionData.sessionId + " " + session.sessionId;

    document.getElementById("user-info").innerHTML = 'Hello ' + session.user1.firstname;
    startTimer();
}


var timer;

function startTimer() {
    var countdown = 20;
    timer = setInterval(function() {
        countdown = countdown -1;
        if(countdown == 0){
            stopTimer();
            document.getElementById("waiting-time").innerHTML = "We couldn't find a second Player for you sorry";
        }
        else{

            document.getElementById("waiting-time").innerHTML = countdown;
            checkIfPlayer2JoindSession();
        }
    }, 1000);
}


function httpGet(url){
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
    console.log("Check if User2 joined" + sessionData.sessionId);
    if(sessionData.user2 != null){
        console.log("OK"+ sessionData.user2.firstname);
        stopTimer();
        joinCreatedSession();
    }
}


function getActiveSession(sessionId){
    var activeSession = httpGet('http://localhost:8083/api/v1/activeSession/' + sessionId);
    return activeSession;
}


function joinCreatedSession(){

    var email = session.user1.email;
    var sessionId = session.sessionId;
    console.log("joinCreatedSession email" + email);
    console.log("joinCreatedSession sessionId" + sessionId);
    var userInformation = httpGet('http://localhost:8083/account/'+ email);

    var sessionData = httpGet('http://localhost:8083/api/v1/session/' + sessionId);
    //var url = 'http://localhost:8083/api/v1/joinSession1/'+ email +'/' + sessionId ;
    //httpGet(url);
    window.location.href = "http://localhost:8083/api/v1/rollTheDice/" + sessionId;
}

function stopTimer() {
    console.log("Timer stopped");
    clearInterval(timer);
}
