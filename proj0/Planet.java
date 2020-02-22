public class Planet
{
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;
	public double r, force, forcex, forcey;
	public Planet(double xP, double yP, double xV, double yV, double m, String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p)
	{
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	public double calcDistance(Planet p)
	{
		r = Math.sqrt((p.xxPos - xxPos) * (p.xxPos - xxPos) + (p.yyPos - yyPos) * (p.yyPos - yyPos));
		return r;
	}
	public double calcForceExertedBy(Planet p)
	{
		r = calcDistance(p);
		force = 6.67E-11 * mass * p.mass / (r * r);
		return force;
	}
	public double calcForceExertedByX(Planet p)
	{
		r = calcDistance(p);
		force = calcForceExertedBy(p);
		forcex = force * (p.xxPos - xxPos) / r;
		return forcex;
	}
	public double calcForceExertedByY(Planet p)
	{
		r = calcDistance(p);
		force = calcForceExertedBy(p);
		forcey = force * (p.yyPos - yyPos) / r;
		return forcey;
	}
	public double calcNetForceExertedByX(Planet[] p)
	{
		double netforcex = 0;
		for (int i = 0; i < p.length; i++)
		{
			if (!this.equals(p[i]))
			{
				r = calcDistance(p[i]);
				force = calcForceExertedBy(p[i]);
				forcex = force * (p[i].xxPos - xxPos) / r;
				netforcex+=forcex;
			}
		}
		return netforcex;
	}
	public double calcNetForceExertedByY(Planet[] p)
	{
		double netforcey = 0;
		for (int i = 0; i < p.length; i++)
		{
			if (!this.equals(p[i]))
			{
				r = calcDistance(p[i]);
				force = calcForceExertedBy(p[i]);
				forcey = force * (p[i].yyPos - yyPos) / r;
				netforcey+=forcey;
			}
		}
		return netforcey;
	}
	public void update(double dt, double fx, double fy)
	{
		double ax, ay;
		ax = fx / mass;
		ay = fy / mass;
		xxVel+=dt * ax;
		yyVel+=dt * ay;
		xxPos+=dt * xxVel;
		yyPos+=dt * yyVel;

	}
	public void draw()
	{
		StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
	}
}