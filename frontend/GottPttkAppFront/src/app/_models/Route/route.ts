export class Route {
  id: number;
  start_point: string;
  end_point: string;
  is_back: string;
  points: number;
  mnt_subgroup: string;
  length: number;

  constructor(id: number, start_point: string, end_point: string, is_back: string, points: number, mnt_subgroup: string, length: number) {
    this.id = id;
    this.start_point = start_point;
    this.end_point = end_point;
    this.is_back = is_back;
    this.points = points;
    this.mnt_subgroup = mnt_subgroup;
    this.length = length;
  }
}
