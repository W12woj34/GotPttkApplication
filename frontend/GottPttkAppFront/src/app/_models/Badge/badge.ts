export class Badge {
  earnDate: string;
  id: number;
  badge_name: string;
  points: number;
  referat: string;
  status: number;
  tourist: string;

  constructor(earnDate: string, id: number, badge_name: string, points: number, referat: string, status: number, tourist: string) {
    this.earnDate = earnDate;
    this.id = id;
    this.badge_name = badge_name;
    this.points = points;
    this.referat = referat;
    this.status = status;
    this.tourist = tourist;
  }
}
