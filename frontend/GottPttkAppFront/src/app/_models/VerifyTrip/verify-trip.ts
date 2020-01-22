export class VerifyTrip {
  id: number;
  begin_date: string;
  end_date: string;
  mnt_groups: string;
  status: string;
  sugg_score: number;
  badge: number;
  first_name: string;
  last_name: string;
  username: string;
  badgeName: string;

  constructor(id: number, begin_date: string, end_date: string, mnt_groups: string, status: string, sugg_score: number, badge: number, first_name: string, last_name: string, username: string, badgeName: string) {
    this.id = id;
    this.begin_date = begin_date;
    this.end_date = end_date;
    this.mnt_groups = mnt_groups;
    this.status = status;
    this.sugg_score = sugg_score;
    this.badge = badge;
    this.first_name = first_name;
    this.last_name = last_name;
    this.username = username;
    this.badgeName = badgeName;
  }
}
