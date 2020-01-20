export class TripRoute {
  id: number;
  index: number;
  date: string;
  category: string;
  start_point: string;
  end_point: string;
  is_back: string;
  is_repeated: string;
  points: number;

  constructor(id: number, index: number, date: string, category: string, start_point: string, end_point: string, is_back: string, is_repeated: string, points: number) {
    this.id = id;
    this.index = index;
    this.date = date;
    this.category = category;
    this.start_point = start_point;
    this.end_point = end_point;
    this.is_back = is_back;
    this.is_repeated = is_repeated;
    this.points = points;
  }
}
