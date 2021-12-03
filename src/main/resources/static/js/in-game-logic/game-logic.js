var session = JSON.parse(httpGet('http://localhost:8083/api/v1/session/currentSession'));
var email = loadUser();
var gameSessionId = session.sessionId;


function httpGet(url)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", url, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}


function loadUser(){
    this.email =  document.cookie;
}

function rollTheDiceNew1(){

    var session = getActiveSession(this.gameSessionId);
    console.log("----------------->rollTheDiceNew1 gameSessionId" + session.sessionId);
    var dice = JSON.parse(httpGet('http://localhost:8083/api/v1/sessionValues/' + session.sessionId + '/' + email));

    var firstRandomNumber = dice.score.score_player1;
    var secondRandomNumber = dice.score.score_player2;

    setUserData(session);
    setImages(firstRandomNumber, secondRandomNumber);
    startTimer();
    disableButton();
}

function playerInfo1(){
    var session = getActiveSession(this.gameSessionId);
    var firstRandomNumber = session.score.score_player1;
    var secondRandomNumber = session.score.score_player2;

    setUserData(session);
    setImages(firstRandomNumber,secondRandomNumber);

    console.log("------------------> Sesssion userTurn " + session.userTurn);
    if(session.userTurn == email) {
        enableButton();
        stopTimer();

        if (firstRandomNumber == 0 && secondRandomNumber == 0) {
            firstRandomNumber = 1;
            secondRandomNumber = 1;
            setImages(firstRandomNumber, secondRandomNumber);

        } else if (secondRandomNumber == 0) {
            secondRandomNumber = 1;
            setImages(firstRandomNumber, secondRandomNumber);
        } else if (firstRandomNumber == 0) {
            firstRandomNumber = 1;
            setImages(firstRandomNumber, secondRandomNumber);
        } else {
            firstRandomNumber = session.score.score_player1;
            secondRandomNumber = session.score.score_player2;
            setImages(firstRandomNumber, secondRandomNumber);
        }
    }
    else{
        setImages(firstRandomNumber, secondRandomNumber);
        setUserData(session);
        disableButton();
    }
}

function prepareTemplate(){
    var sessionData = JSON.parse(httpGet('http://localhost:8083/api/v1/session/currentSession'));
    var session = getActiveSession(sessionData.sessionId);
    var email = document.cookie;

    var firstRandomNumber = 1;
    var secondRandomNumber = 1;

    setUserData(session);
    console.log(email);
    setImages(firstRandomNumber, secondRandomNumber);
    checkUserTurn(session);
 /*   if(session.userTurn != email){
        disableButton();
        startTimer();
    }*/
}

function getActiveSession(sessionId){
    var session = httpGet('http://localhost:8083/api/v1/activeSession/' + sessionId);
    var data = JSON.parse(session);
    return data;
}



//--------------------------------------------- Works ------------------------------------------
function getGameSession(){
    var sessionData = JSON.parse(httpGet('http://localhost:8083/api/v1/session/currentSession'));
    console.log(sessionData);
    this.gameSessionId = session.sessionId;
    return sessionData;
}


var timer;
function startTimer() {
    timer = setInterval(function() {
        //playerInfo();
        playerInfo1();
    }, 10000);
}


function stopTimer() {
    console.log("Timer stopped");
   clearInterval(timer);
}

function disableButton(){
    document.getElementById("rollTheDiceBtn").disabled = true;
    document.getElementById("rollTheDiceBtn").bgcolor = 'rgba(151,255,27,0.34)';
}

function enableButton(){
    document.getElementById("rollTheDiceBtn").disabled = false;
    document.getElementById("rollTheDiceBtn").bgcolor = 'rgba(24,218,154,0.66)';
}

//The parameter has to be an string that refers to an image in the asstes folder
function setImages(firstRandomNumber, secondRandomNumber) {

    if (firstRandomNumber == 0 && secondRandomNumber == 0) {
        firstRandomNumber = 1;
        secondRandomNumber = 1;
        var firstDiceImage = '/assets/dice' + firstRandomNumber + '.png';
        var secondDiceImage = '/assets/dice' + secondRandomNumber + '.png';

        document.querySelectorAll('img')[0].setAttribute('src', firstDiceImage);
        document.querySelectorAll('img')[1].setAttribute('src', secondDiceImage);
    }
    else if(firstRandomNumber == 0){
        firstRandomNumber = 1;
        var firstDiceImage = '/assets/dice' + firstRandomNumber + '.png';
        var secondDiceImage = '/assets/dice' + secondRandomNumber + '.png';

        document.querySelectorAll('img')[0].setAttribute('src', firstDiceImage);
        document.querySelectorAll('img')[1].setAttribute('src', secondDiceImage);
    }
    else if(secondRandomNumber == 0){
        secondRandomNumber = 1;
        var firstDiceImage = '/assets/dice' + firstRandomNumber + '.png';
        var secondDiceImage = '/assets/dice' + secondRandomNumber + '.png';

        document.querySelectorAll('img')[0].setAttribute('src', firstDiceImage);
        document.querySelectorAll('img')[1].setAttribute('src', secondDiceImage);
    }
    else {
        var firstDiceImage = '/assets/dice' + firstRandomNumber + '.png';
        var secondDiceImage = '/assets/dice' + secondRandomNumber + '.png';

        document.querySelectorAll('img')[0].setAttribute('src', firstDiceImage);
        document.querySelectorAll('img')[1].setAttribute('src', secondDiceImage);
    }

}


function setUserData(sessionObject){
    var sessionData = sessionObject;
    document.getElementById("user1").innerHTML = sessionData.user1.firstname;
    document.getElementById("user2").innerHTML = sessionData.user2.firstname;
}

function checkUserTurn(sessionData){
    var session = sessionData;
    if(session.userTurn == email){
        enableButton();
        stopTimer();
    }
    else{
        disableButton();
        startTimer();
    }
}