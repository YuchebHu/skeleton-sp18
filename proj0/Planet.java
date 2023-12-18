/**
 * Planet
 */
public class Planet {

  private static final double kG = 6.67e-11;

  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  public Planet(double posX, double posY, double velX, double velY, double mass, String filename) {
    this.xxPos = posX;
    this.yyPos = posY;
    this.xxVel = velX;
    this.yyVel = velY;
    this.mass = mass;
    this.imgFileName = filename;
  }

  public Planet(Planet obj) {
    this.xxPos = obj.xxPos;
    this.yyPos = obj.yyPos;
    this.xxVel = obj.xxVel;
    this.yyVel = obj.yyVel;
    this.mass = obj.mass;
    this.imgFileName = obj.imgFileName;
  }

  private double calcSquaredDistance(Planet p) {
    return Math.pow(this.xxPos - p.xxPos, 2) + Math.pow(this.yyPos - p.yyPos, 2);
  }

  public double calcDistance(Planet p) {
    return Math.pow(calcSquaredDistance(p), 0.5);
  }

  public double calcForceExertedBy(Planet p) {
    return kG * this.mass * p.mass / calcSquaredDistance(p);
  }

  public double calcForceExertedByX(Planet p) {
    return calcForceExertedBy(p) * (p.xxPos - this.xxPos) / calcDistance(p);
  }

  public double calcForceExertedByY(Planet p) {
    return calcForceExertedBy(p) * (p.yyPos - this.yyPos) / calcDistance(p);
  }

  public double calcNetForceExertedByX(Planet[] planets) {
    double result = 0.0;
    for (Planet planet : planets) {
      if (this.equals(planet)) {
        continue;
      }
      result += this.calcForceExertedByX(planet);
    }
    return result;
  }

  public double calcNetForceExertedByY(Planet[] planets) {
    double result = 0.0;
    for (Planet planet : planets) {
      if (this.equals(planet)) {
        continue;
      }
      result += this.calcForceExertedByY(planet);
    }
    return result;
  }

  private boolean equals(Planet p) {
    return this.xxPos == p.xxPos && this.yyPos == p.yyPos && this.xxVel == p.xxVel && this.yyVel == p.yyVel
        && this.mass == p.mass && this.imgFileName.equals(p.imgFileName);
  }

  public void update(double delta, double fx, double fy) {
    double ax = fx / this.mass;
    double ay = fy / this.mass;

    this.xxVel += ax * delta;
    this.yyVel += ay * delta;

    this.xxPos += this.xxVel * delta;
    this.yyPos += this.yyVel * delta;
  }

  public void draw() {
    StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
  }
}