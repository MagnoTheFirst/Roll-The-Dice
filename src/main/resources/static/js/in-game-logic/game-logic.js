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
    var dice = JSON.parse(httpGet('http://localhost:8083/api/v1/sessionValues/' + session.sessionId + '/' + email));
    disableButton();
    startTimer();
    
    var firstRandomNumber = dice.score.score_player1;
    var secondRandomNumber = dice.score.score_player2;

    setImages(firstRandomNumber, secondRandomNumber);
    setUserData(dice);

}

function playerInfo1(){

    var session = getActiveSession(this.gameSessionId);

    someUserCheckedOut(session);
    console.log(session.roundCounter);
    console.log(session.winnerRound);
    document.getElementById("winner title").innerHTML = session.winnerRound;
    var firstRandomNumber = session.score.score_player1;
    var secondRandomNumber = session.score.score_player2;


    setUserData(session);
    setImages(firstRandomNumber,secondRandomNumber);


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
        disableButton();
        setImages(firstRandomNumber, secondRandomNumber);
        setUserData(session);
    }
}

function prepareTemplate(){
    var sessionData = JSON.parse(httpGet('http://localhost:8083/api/v1/session/currentSession'));
    var session = getActiveSession(sessionData.sessionId);
    var email = document.cookie;

    var firstRandomNumber = 1;
    var secondRandomNumber = 1;

    setUserData(session);
    setImages(firstRandomNumber, secondRandomNumber);
    checkUserTurn(session);
}

function getActiveSession(sessionId){
    var session = httpGet('http://localhost:8083/api/v1/activeSession/' + sessionId);
    var data = JSON.parse(session);
    return data;
}



//--------------------------------------------- Works ------------------------------------------
function getGameSession(){
    var sessionData = JSON.parse(httpGet('http://localhost:8083/api/v1/session/currentSession'));
    this.gameSessionId = session.sessionId;
    return sessionData;
}

var timer;
function startTimer() {
    timer = setInterval(function() {
        //playerInfo();
        playerInfo1();
    }, 5000);
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

    document.getElementById("round").innerHTML = sessionObject.roundCounter;

    document.getElementById("user1").innerHTML = sessionData.user1.firstname;
    document.getElementById("user2").innerHTML = sessionData.user2.firstname;

    document.getElementById("score-player1").innerHTML = sessionData.player1_score;
    document.getElementById("score-player2").innerHTML = sessionData.player2_score;
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


function quitSession(){
    window.location.href = "http://localhost:8083/account/lobby/" + email;
}

function quitSession1(){
    var currentSession = getActiveSession(session.sessionId);
    var userEmail = document.cookie;
    if(currentSession.user1.email == email){
        httpGet('http://localhost:8083/api/v1/leaveSession/'+ email +"/" + currentSession.sessionId);
        window.location.href = "http://localhost:8083/account/lobby/" + email;
    }
    else if(currentSession.user2.email == email){
        httpGet('http://localhost:8083/api/v1/leaveSession/'+ email+ "/" + currentSession.sessionId);
        window.location.href = "http://localhost:8083/account/lobby/" + email;
    }
}

function someUserCheckedOut(currentSession){
    if(currentSession.user1 == null || currentSession.user2 == null){
        alert("Your opponent has left the session you win");
        httpGet('http://localhost:8083/api/v1/leaveSession/'+ email +"/" + currentSession.sessionId);
        httpGet('http://localhost:8083/api/v1/clearSession/' + currentSession.sessionId);
        window.location.href = "http://localhost:8083/account/lobby/" + email;
    }
}

function checkWhoWon(session){
    var sessionInfo = session;
    console.log(sessionInfo);
    var winnerRound = sessionInfo.winnerRound;
    var winnerMatch = sessionInfo.winnerMatch;

    if(winnerMatch != null){
        document.getElementById("winner title").innerHTML = "The winner of this match is " + winnerMatch;
        alert("Thanks for playing roll the dice");
        quitSession1();
        //Add Method to diable roll the dice button and enable quit button
    }
    if(winnerRound != null){
        document.getElementById("winner title").innerHTML = "The winner of this Round is " + winnerRound;
    }
}


