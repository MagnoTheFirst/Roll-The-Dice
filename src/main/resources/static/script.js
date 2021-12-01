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

function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}

// wird das noch benötigt?
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

// wird das noch benötigt?
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

// wird das noch benötigt?
function rollTheDice3(url){
    //Dice 1
    var resp1 = httpGet(url);
    var dice1 = JSON.parse(resp1);
    var firstRandomNumber = dice1.dice_value+1;
    const firstDiceImage = '/assets/dice' + firstRandomNumber + '.png';
    document.querySelectorAll('img')[0].setAttribute('src', firstDiceImage);
    //Dice 2
    var resp2 = httpGet(url);
    var dice2 = JSON.parse(resp2);
    const secondRandomNumber = dice2.dice_value+1;
    const secondDiceImage = '/assets/dice' + secondRandomNumber + '.png';
    document.querySelectorAll('img')[1].setAttribute('src', secondDiceImage);
    //Dice 3
    var resp3 = httpGet(url);
    var dice3 = JSON.parse(resp3);
    const thirdRandomNumber = dice3.dice_value+1;
    const thirdDiceImage = '/assets/dice' + thirdRandomNumber + '.png';
    document.querySelectorAll('img')[2].setAttribute('src', thirdDiceImage);
    //Dice 4
    var resp4 = httpGet(url);
    var dice4 = JSON.parse(resp4);
    const fourthRandomNumber = dice4.dice_value+1;
    const fourthDiceImage = '/assets/dice' + fourthRandomNumber + '.png';
    document.querySelectorAll('img')[3].setAttribute('src', fourthDiceImage);

    var valueP1 = firstRandomNumber + secondRandomNumber;
    var valueP2 = thirdRandomNumber+fourthRandomNumber;
    if (valueP1 > valueP2) {
        document.querySelector('h2').innerHTML = "The winner is User 1";

    } else if (valueP1 < valueP2) {

        document.querySelector('h2').innerHTML = "The winner is User 2";
    } else {

        document.querySelector('h2').innerHTML = "It's a draw";
    }
}

$(document).ready(function(){
    var getreq1 = httpGet('http://localhost:8083/api/v1/searchSession');
    var jsonresponse = JSON.parse(getreq1);
 //   alert(jsonresponse.sessionId);

    var queryString = window.location.search;
    var url = new URLSearchParams(queryString);
    console.log(url);
   // alert(url);
    //document.getElementById('user1').innerHTML = jsonresponse.user1.username;
    //document.getElementById('user2').innerHTML = jsonresponse.user2.username;
});

