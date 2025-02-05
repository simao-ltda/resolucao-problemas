// This file is part of DiverCity
// DiverCity is based on MicropolisJ
// Copyright (C) 2013 Jason Long
// Portions Copyright (C) 1989-2007 Electronic Arts Inc.
//
// MicropolisJ is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.engine;

import micropolisj.engine.techno.BuildingTechnology;

import static micropolisj.engine.TileConstants.*;

/**
 * Enumerates the various tools that can be applied to the map by the user.
 * Call the tool's apply() method to actually use the tool on the map.
 */
public enum MicropolisTool
{
    BULLDOZER(1, 1, 0),
    WIRE(1, 5, 0),   //cost=25 for underwater
    ROADS(1, 10, 0), //cost=50 for over water
    BIGROADS(1, 20, 0),
    RAIL(1, 25, 0),
    STATION(1, 200, 0),
    RESIDENTIAL(3, 100, 0),
    COMMERCIAL(3, 100, 0),
    INDUSTRIAL(3, 100, 0),
    FIRE(3, 500, 0),
    POLICE(3, 500, 0),
    STADIUM(4, 5000, 20),
    PARK(1, 10, 0),
    SEAPORT(4, 3000, 0),
    POWERPLANT(4, 3000, 0),
    NUCLEAR(4, 5000, 0),
    AIRPORT(6, 10000, 1000),
    SCHOOL(3, 500, 100),
    MUSEUM(3, 500, 50),
    UNIA(3, 1000, 0),
    UNIB(3, 1000, 0),
    OPENAIR(6, 500, 1500),
    CITYHALL(3, 1500, 0),
    QUERY(1, 0, 0),
    BIGPARK(3, 100, 500),
    SOLAR(4, 4000, 0),
    WIND(1, 325, 0);

    int size;
    int cost;
    int minPopulation;
    BuildingTechnology requiredTechnology;

    // Singleton instance
    private static MicropolisTool instance;

    private MicropolisTool(int size, int cost, int minPopulation) {
        this.size = size;
        this.cost = cost;
        this.minPopulation = minPopulation;
    }

    // Singleton getInstance method
    public static MicropolisTool getInstance() {
        if (instance == null) {
            instance = QUERY; // Default instance
        }
        return instance;
    }

    public int getWidth()
    {
        return size;
    }

    public int getHeight()
    {
        return getWidth();
    }

    // Factory Method
    public static class ToolFactory {
        public static ToolStroke createToolStroke(MicropolisTool tool, Micropolis engine, int xpos, int ypos) {
            switch (tool) {
                case BULLDOZER:
                    return new Bulldozer(engine, xpos, ypos);
                case WIRE:
                case ROADS:
                case BIGROADS:
                case RAIL:
                case STATION:
                    return new RoadLikeTool(engine, tool, xpos, ypos);
                default:
                    return new ToolStroke(engine, tool, xpos, ypos);
            }
        }
    }

    public ToolStroke beginStroke(Micropolis engine, int xpos, int ypos)
    {
        return ToolFactory.createToolStroke(this, engine, xpos, ypos);
    }

    public ToolResult apply(Micropolis engine, int xpos, int ypos)
    {
        return beginStroke(engine, xpos, ypos).apply();
    }

    /**
     * This is the cost displayed in the GUI when the tool is selected.
     * It does not necessarily reflect the cost charged when a tool is
     * applied, as extra may be charged for clearing land or building
     * over or through water.
     */
    public int getToolCost()
    {
        return cost;
    }

    public int getMinPopulation() {
        return minPopulation;
    }
}
