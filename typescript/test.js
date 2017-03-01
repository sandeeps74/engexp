var message = "Hello World";
console.log(message);
var Greeting = (function () {
    function Greeting() {
    }
    Greeting.prototype.greet = function () {
        console.log("Hello World!!!");
    };
    return Greeting;
}());
var obj = new Greeting();
obj.greet();
var str = '1';
var str2 = str; //str is now of type number 
console.log(str2);
