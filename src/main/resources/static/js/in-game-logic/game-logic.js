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

function rollTheDiceNew1(){
    var session = getActiveSession(this.gameSessionId);
    var dice1 = JSON.parse(httpGet('http://localhost:8083/api/v1/sessionValues/' + session.sessionId + '/' + email));

    var firstRandomNumber = dice1.score.score_player1;
    var secondRandomNumber = dice1.score.score_player2;

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


function loadUser(){
    this.email =  document.cookie;
}

function prepareTemplate(){
    var session = getActiveSession(session.sessionId);
    var email = document.cookie;

    console.log(email);
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


//TODO[] check functionality
function playerInfo1(){
    var session = JSON.parse(httpGet('http://localhost:8083/api/v1/session/currentSession'));
//TODO[] check if userturn true if it is stop timer

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
    console.log("Timer stopped");
    clearInterval(timer);
}