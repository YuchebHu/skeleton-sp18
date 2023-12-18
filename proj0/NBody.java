public class NBody {
  public static double readRadius(String filename) {
    In input = new In(filename);
    if (input.isEmpty()) {
      return 0;
    }
    input.readInt();

    return input.readDouble();
  }

  public static Planet[] readPlanets(String filename) {
    In input = new In(filename);
    if (input.isEmpty()) {
      return null;
    }
    int num = input.readInt();
    Planet[] planets = new Planet[num];
    input.readDouble();
    for (int i = 0; i < num && !input.isEmpty(); ++i) {
      planets[i] = new Planet(input.readDouble(), input.readDouble(), input.readDouble(), input.readDouble(),
          input.readDouble(), input.readString());
    }
    return planets;
  }

  public static void main(String[] args) {
    if (args.length != 3) {
      System.err.println("Need two input double value and a filename!");
    }
    double t = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    Planet[] planets = readPlanets(filename);
    double radius = readRadius(filename);

    StdDraw.setScale(-radius, radius);
    StdDraw.clear();
    StdDraw.enableDoubleBuffering();

    double time = 0;
    while (time < t) {
      double[] xForces = new double[planets.length];
      double[] yForces = new double[planets.length];
      for (int i = 0; i < planets.length; ++i) {
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }
      StdDraw.picture(0, 0, "./images/starfield.jpg");
      for (int i = 0; i < planets.length; ++i) {
        planets[i].update(dt, xForces[i], yForces[i]);
        planets[i].draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
      time += dt;
    }

    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }

}
