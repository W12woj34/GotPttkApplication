export class VerifyTrip {
  id: number;
  beginDate: string;
  endDate: string;
  mntGroups: string;
  status: string;
  suggScore: number;
  badge: number;
  firstName: string;
  lastName: string;
  username: string;
  badgeName: string;

  constructor(id: number, beginDate: string, endDate: string, mntGroups: string, status: string, suggScore: number,
              badge: number, firstName: string, lastName: string, username: string, badgeName: string) {
    this.id = id;
    this.beginDate = beginDate;
    this.endDate = endDate;
    this.mntGroups = mntGroups;
    this.status = status;
    this.suggScore = suggScore;
    this.badge = badge;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.badgeName = badgeName;
  }
}
