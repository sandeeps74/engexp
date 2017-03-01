import {bootstrap} from 'angular2/platform/browser';
import {AppComponent} from "./forms_app.component";

console.log('before AppComponent invocation');
bootstrap(AppComponent);
console.log('after AppComponent invocation');