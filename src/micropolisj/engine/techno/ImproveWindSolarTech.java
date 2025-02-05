// This file is part of DiverCity
// DiverCity is based on MicropolisJ
// Copyright (C) 2014 Arne Roland, Benjamin Kretz, Estela Gretenkord i Berenguer, Fabian Mett, Marvin Becker, Tom Brewe, Tony Schwedek, Ullika Scholz, Vanessa Schreck for DiverCity
//
// DiverCity is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.engine.techno;

import micropolisj.engine.Micropolis;
import micropolisj.engine.MicropolisMessage;

/**
 * Created by tom on 26.03.14.
 */
public class ImproveWindSolarTech extends GeneralTechnology {
    public ImproveWindSolarTech(Micropolis engine_,double pointsNeeded_, String description_, String name_,MicropolisMessage m){
        super(engine_, pointsNeeded_, description_, name_,m);
    }

    public boolean tryToApply(){
        if(super.tryToApply() == true){
            engine.windsolarsccount +=1;// do some fancy street Upgrade stuff
            System.out.println("improve wind and solar");
            return true;
        }
        return false;
    }
}
