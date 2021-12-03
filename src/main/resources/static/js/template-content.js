var session = null;
var gameSessionId = null;
var email = null;




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
