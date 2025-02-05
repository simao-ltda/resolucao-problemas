// This file is part of DiverCity
// DiverCity is based on MicropolisJ
// Copyright (C) 2014 Arne Roland, Benjamin Kretz, Estela Gretenkord i Berenguer, Fabian Mett, Marvin Becker, Tom Brewe, Tony Schwedek, Ullika Scholz, Vanessa Schreck for DiverCity
//
// DiverCity is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.engine;
import java.lang.Comparable;

public class VerySpecifiedTile implements Comparable<VerySpecifiedTile>{
	RoadSpecifiedTile roadTile;
	int value;
	
	public VerySpecifiedTile(CityLocation loc, int roadType, int value){
		this.roadTile=new RoadSpecifiedTile(loc, roadType);
		this.value=value;
	}
	public VerySpecifiedTile(RoadSpecifiedTile r, int value){
		this.value=value;
		this.roadTile=r;
	}
	public boolean equals(Object o){
		if(o instanceof VerySpecifiedTile){
			VerySpecifiedTile v = (VerySpecifiedTile)o;
			return getLocation().equals(v.getLocation())&&getRoadType()==v.getRoadType()&&this.value==v.value;
		}
		return false;
	}
	
	public int getValue() {
		return value;
	}
	
	public CityLocation getLocation() {
		return roadTile.getLocation();
	}
	
	public int getRoadType() {
		return roadTile.getRoadType();
	}
	
	public RoadSpecifiedTile getRoad() {
		return roadTile;
	}
	
	public int compareTo(VerySpecifiedTile b){
		if(this.equals(b)){
			return 0;
		}
		if(this.getLocation().x < b.getLocation().x){
			return -1;
		}
		if(this.getLocation().x > b.getLocation().x){
			return 1;
		}
		if(this.getLocation().y<b.getLocation().y){
			return -1;
		}
		if(this.getLocation().y>b.getLocation().y){
			return 1;
		}
		if(this.getRoadType()<b.getRoadType()){
			return -1;
		}
		if(this.getRoadType()>b.getRoadType()){
			return 1;
		}
		if(this.value<b.value){
			return -1;
		}
		return 1;
	}

}
