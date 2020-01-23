export class TripRoute {
  id: number;
  index: number;
  date: string;
  category: string;
  startPoint: string;
  endPoint: string;
  isBack: string;
  isRepeated: string;
  points: number;
  routeId: number;

  constructor(id: number, index: number, date: string, category: string, startPoint: string, endPoint: string,
              isBack: string, isRepeated: string, points: number, routeId: number) {
    this.id = id;
    this.index = index;
    this.date = date;
    this.category = category;
    this.startPoint = startPoint;
    this.endPoint = endPoint;
    this.isBack = isBack;
    this.isRepeated = isRepeated;
    this.points = points;
    this.routeId = routeId;
  }
}
