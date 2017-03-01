import {bootstrap} from 'angular2/platform/browser';     //importing bootstrap function
import {AppComponent} from "./app_service.component";    //importing component function

console.log('Invoking AppComponent bootstap');
bootstrap(AppComponent);
console.log('Finished AppComponent bootstap');
