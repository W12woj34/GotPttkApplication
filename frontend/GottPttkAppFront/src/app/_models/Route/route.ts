export class Route {
  start_point: string;
  end_point: string;
  is_back: string;
  points: number;

  constructor(start_point: string, end_point: string, is_back: string, points: number) {
    this.start_point = start_point;
    this.end_point = end_point;
    this.is_back = is_back;
    this.points = points;
  }
}
