export class User {
  id: string;
  firstName: string;
  lastName: string;
  type: string;

  constructor(id: string, firstName: string, lastName: string, type: string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.type = type;
  }
}
