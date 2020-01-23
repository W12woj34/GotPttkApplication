export class Trip {
  constructor(id: number, beginDate: string, endDate: string, mntGroups: string, status: string, suggScore: number,
              badge: number, badgeName: string) {
    this.id = id;
    this.beginDate = beginDate;
    this.endDate = endDate;
    this.mntGroups = mntGroups;
    this.status = status;
    this.suggScore = suggScore;
    this.badge = badge;
    this.badgeName = badgeName;
  }

  id: number;
  beginDate: string;
  endDate: string;
  mntGroups: string;
  status: string;
  suggScore: number;
  badge: number;
  badgeName: string;

}
