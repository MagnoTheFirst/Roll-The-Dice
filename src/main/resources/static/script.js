/*function rollTheDice() {
    const firstRandomNumber = Math.floor(Math.random() * 6) + 1;

    const firstDiceImage = 'assets/dice' + firstRandomNumber + '.png';

    document.querySelectorAll('img')[0].setAttribute('src', firstDiceImage);


    const secondRandomNumber = Math.floor(Math.random() * 6) + 1;

    const secondDiceImage = 'assets/dice' + secondRandomNumber + '.png';

    document.querySelectorAll('img')[1].setAttribute('src', secondDiceImage);


    if (firstRandomNumber > secondRandomNumber) {

        document.querySelector('h1').innerHTML = "The winner is User 1";

    } else if (firstRandomNumber < secondRandomNumber) {

        document.querySelector('h1').innerHTML = "The winner is User 2";
    } else {

        document.querySelector('h1').innerHTML = "It's a draw";
    }
}*/

function hitTheSpot(){
    alert("show");
}
function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}

function rollTheDice1(url){
    var resp1 = httpGet(url);
    var dice1 = JSON.parse(resp1);
    var firstRandomNumber = dice1.dice_value+1;

    const firstDiceImage = 'assets/dice' + firstRandomNumber + '.png';
    document.querySelectorAll('img')[0].setAttribute('src', firstDiceImage);

    var resp2 = httpGet(url);
    var dice2 = JSON.parse(resp2);
    const secondRandomNumber = dice2.dice_value+1;
    const secondDiceImage = 'assets/dice' + secondRandomNumber + '.png';
    document.querySelectorAll('img')[1].setAttribute('src', secondDiceImage);

    if (firstRandomNumber > secondRandomNumber) {
        document.querySelector('h1').innerHTML = "The winner is User 1";

    } else if (firstRandomNumber < secondRandomNumber) {

        document.querySelector('h1').innerHTML = "The winner is User 2";
    } else {

        document.querySelector('h1').innerHTML = "It's a draw";
    }

    function getSession(url){
        var resp1 = httpGet(url);
    }

}


function rollTheDice2(url){
    var resp1 = httpGet(url);
    var dice1 = JSON.parse(resp1);
    var firstRandomNumber = dice1.dice_value+1;

    const firstDiceImage = '/assets/dice' + firstRandomNumber + '.png';
    document.querySelectorAll('img')[0].setAttribute('src', firstDiceImage);

    var resp2 = httpGet(url);
    var dice2 = JSON.parse(resp2);
    const secondRandomNumber = dice2.dice_value+1;
    const secondDiceImage = '/assets/dice' + secondRandomNumber + '.png';
    document.querySelectorAll('img')[1].setAttribute('src', secondDiceImage);

    if (firstRandomNumber > secondRandomNumber) {
        document.querySelector('h1').innerHTML = "The winner is User 1";

    } else if (firstRandomNumber < secondRandomNumber) {

        document.querySelector('h1').innerHTML = "The winner is User 2";
    } else {

        document.querySelector('h1').innerHTML = "It's a draw";
    }

    function getSession(url){
        var resp1 = httpGet(url);
    }
}

$(document).ready(function(){
    var getreq1 = httpGet('http://localhost:8083/api/v1/searchSession');
    var jsonresponse = JSON.parse(getreq1);
    alert(jsonresponse.sessionId);

    var queryString = window.location.search;
    var url = new URLSearchParams(queryString);
    console.log(url);
    alert(url);
    document.getElementById('user1').innerHTML = jsonresponse.user1.username;
    document.getElementById('user2').innerHTML = jsonresponse.user2.username;
});

