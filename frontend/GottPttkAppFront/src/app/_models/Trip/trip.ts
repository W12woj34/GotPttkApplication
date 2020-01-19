export class Trip {
  constructor(id: number, begin_date: string, end_date: string, mnt_groups: string, status: string, sugg_score: number) {
    this.id = id;
    this.begin_date = begin_date;
    this.end_date = end_date;
    this.mnt_groups = mnt_groups;
    this.status = status;
    this.sugg_score = sugg_score;
  }

  id: number;
  begin_date: string;
  end_date: string;
  mnt_groups: string;
  status: string;
  sugg_score: number;

}
