export class Contact {
  constructor(
    public firstname: string,
    public lastname: string,
    public country: string,
    public phone: number
  ) {
		console.log('Initialized contact with first name ' + firstname);
  }
}