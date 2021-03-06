package devices;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

import particles.Particle;
import particles.ParticleController;

public class Wall extends Device{

	private final Rectangle body;
	private final Color color;
	public Wall(Point p, Point f) {
		body = new Rectangle(Math.min(p.x, f.x),
				Math.min(p.y, f.y),
				Math.abs(p.x - f.x),
				Math.abs(p.y - f.y));
		color = new Color((int)(180 + Math.random() * 40),
				(int)(180 + Math.random() * 40),
				(int)(180 + Math.random() * 40));
	}
	
	@Override
	public void update(ParticleController p) {
		
		if (body.width > body.height)
		{
			for (Particle a : p.getParticles(
					b -> b.distance(new Point(body.x,body.y + body.height/2)) < (float) body.height / 2
							&& new Rectangle(body.x, body.y,body.width/2, body.height).contains(b)
					, new Rectangle(body.x,body.y,body.width/2,body.height)))
			{
				a.reverseXVel();
				a.setX(body.x - (a.x - body.x));
			}
			for (Particle a : p.getParticles(
					b -> b.distance( new Point(body.x + body.width,body.y + body.height/2)) < (float) body.height / 2
							&& new Rectangle(body.x + body.width/2,body.y,body.width/2 + 1,body.height).contains(b)
					, new Rectangle(body.x + body.width/2,body.y,body.width/2 + 10,body.height)))
			{
				a.reverseXVel();
				a.setX((float) (body.getMaxX() + (body.getMaxX() - a.x)));
			}
			for (Particle a : p.getParticles(
					b -> new Rectangle(body.x + 1, body.y, body.width - 1, body.height/2).contains(b),
					new Rectangle(body.x, body.y, body.width, body.height/2)))
			{
				
				a.setY(body.y - (a.y - body.y));
				a.reverseYVel();
				
			}
			for (Particle a : p.getParticles(
					b -> new Rectangle(body.x + 1, body.y + body.height/2, body.width - 1, body.height/2).contains(b),
					new Rectangle(body.x, body.y + body.height/2, body.width, body.height/2)))
			{
				a.reverseYVel();
				a.setY((float) (body.getMaxY() + (body.getMaxY() - a.y)));
			}
		}
		else
		{
			for (Particle a : p.getParticles(
					b -> b.distance(new Point(body.x + body.width/2, body.y)) < (float) body.width / 2
							&& new Rectangle(body.x, body.y, body.width, body.height/2).contains(b)
					, new Rectangle(body.x, body.y, body.width, body.height/2)))
			{
				a.setY(body.y - (a.y - body.y));
				a.reverseYVel();
			}
			for (Particle a : p.getParticles(
					b -> b.distance(new Point(body.x + body.width/2, body.y + body.height)) < (float) body.width/2
							&& new Rectangle(body.x, body.y + body.height/2, body.width, body.height/2 + 1).contains(b)
					, new Rectangle(body.x, body.y + body.height/2 + 10, body.width, body.height/2)))
			{
				a.reverseYVel();
				a.setY((float) (body.getMaxY() + (body.getMaxY() - a.y)));
			}
			for (Particle a : p.getParticles(
					b -> new Rectangle(body.x, body.y + 1, body.width/2, body.height - 1).contains(b)
					, new Rectangle(body.x, body.y, body.width/2, body.height)))
			{
				a.reverseXVel();
				a.setX(body.x - (a.x - body.x));
			}
			for (Particle a : p.getParticles(
					b -> new Rectangle(body.x + body.width/2,body.y + 1,body.width/2,body.height - 1).contains(b),
					new Rectangle(body.x + body.width/2, body.y, body.width/2, body.height)))
			{
				a.reverseXVel();
				a.setX((float) (body.getMaxX() + (body.getMaxX() - a.x)));
			}
		}
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fill(body);
		
	}
	@Override
	public boolean onClick() {
		return true;
		
	}
	@Override
	public boolean contains(Point p) {
		return body.contains(p);
	}
	public static Wall create(Point[] p)
	{
		return new Wall(p[0], p[1]);
	}
	public static Shape[] createAffectedShape(Point[] a) {
		return new Shape[]{new Rectangle(Math.min(a[0].x, a[1].x),
				Math.min(a[0].y, a[1].y),
				Math.abs(a[0].x - a[1].x),
				Math.abs(a[0].y - a[1].y))};
	}
	public static Shape[] createMenuShape(Point p)
	{
		return new Shape[]{new Rectangle(p.x - 20,p.y - 20,40,40)};
	}

}
