import {Component} from "angular2/core";
import {CountryService} from "./country.service";
import {Contact} from "./country";
import {OnInit} from "angular2/core";

@Component({
   selector: "country-list",
   template: ` List of Countries<br>
   <ul>
      <li *ngFor="#cntry of countries">{{ cntry.name }}</li>
   </ul>
   `,
   providers: [CountryService]
})

export class MyListComponent implements OnInit {
   public countries : Country[];
   constructor(private _countryService: CountryService) {
      console.log('Creating MyListComponent with service ' + _countryService);
   }

   getContacts(){
      console.log('MyListComponent Invkoing getContacts()');
      this._countryService.getContacts().then((countries: Country[]) => this.countries = countries);
	  console.log('MyListComponent getContacts() finished async manner');
   }

   ngOnInit():any{
      console.log('MyListComponent ngOnInit()');
      this.getContacts();
   }
}