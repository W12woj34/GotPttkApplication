export class Route {
  id: number;
  startPoint: string;
  endPoint: string;
  isBack: string;
  points: number;
  mntSubgroup: string;
  length: number;

  constructor(id: number, startPoint: string, endPoint: string, isBack: string, points: number, mntSubgroup: string, length: number) {
    this.id = id;
    this.startPoint = startPoint;
    this.endPoint = endPoint;
    this.isBack = isBack;
    this.points = points;
    this.mntSubgroup = mntSubgroup;
    this.length = length;
  }
}
