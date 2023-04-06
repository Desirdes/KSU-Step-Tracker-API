export class User {
  public id: number;
  public full_name: string;
  public email: string;
  public gender: string;
  public demographic: string;
  public age: number;
  public username: string;
  public targets: targetData[];
  public biometrics: biometricData[];
}

export class targetData {
  public id: number;
  public dailySteps: number;
  public personID: number;
  public weightLoss: number;
  public weightLossPercentage: string;
}

export class biometricData {
  public id: number;
  public bodyFatPercentage: number;
  public height: number;
  public neckCircumference: number;
  public waistCircumference: number;
  public weight: number;
  public personID: number;
}
