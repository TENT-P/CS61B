public class NBody
{
	public static double readRadius(String filepath)
	{
		In in = new In(filepath);
		int num = in.readInt();
		double radi = in.readDouble();
		return radi;
	}
	public static Planet[] readPlanets(String filepath)
	{
		In in = new In(filepath);
		int num = in.readInt();
		double radi = in.readDouble();
		Planet[] p = new Planet[num];
		for (int i = 0; i < num; i++)
		{
			p[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}
		return p;
	}
	public static void main (String[] args)
	{
		double T;
		double dt;
		String filename;
		T = Double.parseDouble(args[0]);
		dt = Double.parseDouble(args[1]);
		filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		String imgname = "./images/starfield.jpg";
		StdDraw.enableDoubleBuffering();
		for (double t = 0; t < T; t+=dt)
		{
			double[] xForce = new double[planets.length];
			double[] yForce = new double[planets.length];
			for (int i = 0; i < planets.length; i++)
			{
				xForce[i] = planets[i].calcNetForceExertedByX(planets);
				yForce[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < planets.length; i++)
			{
				planets[i].update(dt, xForce[i], yForce[i]);
			}
			StdDraw.setScale(-radius, radius);
			StdDraw.clear();
			StdDraw.picture(0, 0, imgname);
			for (int i = 0; i < planets.length; i++)
			{
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++)
		{
   			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
   		}
	}
}