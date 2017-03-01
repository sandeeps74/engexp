import {Injectable} from "angular2/core";
import {COUNTRIES} from "./country.contacts";

//@Injectable() specifies class is available to an injector for instantiation and an injector will display an error when trying to instantiate a class that is not marked as @Injectable()

@Injectable()

//CountryService exposes the getContacts() method that returns the data
export class CountryService {
	constructor() {
		console.log('CountryService is created');
	}
   getContacts() {
      console.log('CountryService getContacts() invoked');
      var countries = Promise.resolve(COUNTRIES); // takes values from country.contacts typescript file
	  console.log('CountryService getContacts() finished with countries ' + countries);
	  return countries;
   }
}
