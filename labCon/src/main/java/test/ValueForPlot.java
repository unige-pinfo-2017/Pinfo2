package test;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "VALUEFORPLOT")
public class ValueForPlot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3688352173731049687L;
	
	
	@Id
	private int id;
	
	@Column(name = "X")
	private double x;
	
	@Column(name = "Y")
	private double y;
	
	
	public ValueForPlot() {
		// TODO Auto-generated constructor stub
	}
	
	public ValueForPlot(final double x, final double y, final int id){
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public final int getId(){
		return this.id; 
	}
	
	public final void setId(final int id){
		this.id = id;
	}
	
	
	public final double getX(){
		return this.id; 
	}
	
	public final void setX(final float x){
		this.x = x;
	}
	
	public final double getY(){
		return this.y; 
	}
	
	public final void setY(final float y){
		this.y = y;
	}

}
