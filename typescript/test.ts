var message:string = "Hello World" 
console.log(message)

class Greeting { 
   greet():void { 
      console.log("Hello World!!!") 
   } 
} 
var obj = new Greeting(); 
obj.greet();

var str = '1' 
var str2:number = <number> <any> str   //str is now of type number 
console.log(str2)
