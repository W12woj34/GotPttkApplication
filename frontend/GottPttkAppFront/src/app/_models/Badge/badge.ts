export class Badge {
  earnDate: string;
  id: number;
  badgeName: string;
  points: number;
  referat: string;
  status: number;
  tourist: string;

  constructor(earnDate: string, id: number, badgeName: string, points: number, referat: string, status: number, tourist: string) {
    this.earnDate = earnDate;
    this.id = id;
    this.badgeName = badgeName;
    this.points = points;
    this.referat = referat;
    this.status = status;
    this.tourist = tourist;
  }
}
