export class User {
  id: number;
  firstName: string;
  lastName: string;
  type: string;

  constructor(id: number, firstName: string, lastName: string, type: string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.type = type;
  }
}
