'use strict';
alert("session cookie" + document.cookie);

var session;
var timer;

createNewSession1();

function createNewSession1(){

    var email = document.cookie;
    alert(email);

    var sessionData = JSON.parse(httpGet('http://localhost:8083/api/v1/newSession/' + email));
    session = sessionData;
    document.getElementById("user-info").innerHTML = 'Hello ' + session.user1.firstname;
    startTimer();
}

var session;

var timer;

function startTimer() {

    alert("Start Timer : " + session.sessionId);
    var countdown = 20;
    timer = setInterval(function() {

        if(countdown == 0){
            stopTimer();
            document.getElementById("waiting-time").innerHTML = "We couldnt find a second Player for you sorry";
        }
        else{
            countdown = countdown -1;
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
        alert("OK"+ session.user2.firstname);
        stopTimer();
        document.cookie = sessionData + "; email = " + email;
        joinCreatedSession();
    }
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
    document.cookie = email;
    window.location.href = "http://localhost:8083/api/v1/rollTheDice/" + sessionId;
}

function stopTimer() {
    alert("Timer stopped");
    clearInterval(timer);
}
