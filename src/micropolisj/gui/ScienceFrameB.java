package micropolisj.gui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.event.*;


import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.Window;
import java.awt.Dimension;
import java.awt.Color;
import java.util.ArrayList;

import micropolisj.engine.Micropolis;
import micropolisj.engine.techno.*;
import micropolisj.engine.*;

public class ScienceFrameB extends JDialog {

	Micropolis engine;
	
	JPanel panel;
    ArrayList<JButton> buttonList;
	JButton jbStreetUpgrade;
	JButton jbRailUpgrade;
	JButton jbPoliceUpgrade;
	JButton jbFireDepUpgrade;
	JButton jbTwoLaneRoad;
	JButton jbAirport;
    JProgressBar progressBar;
    JLabel noTechSelectedLabel;
    JButton jbReset;

    Color col2= new Color(100,50,101);
    Color col3= new Color(232,188,231);

//UNIVERSITAET FUER MANAGEMENT

    private void disableAllButtonsBut(JButton bNotToBeDisabled){
        for(JButton b : buttonList){
            b.setEnabled(false);
            //b.setBackground(col2);
        }
        bNotToBeDisabled.setEnabled(true);
        //bNotToBeDisabled.setBackground(col3);
    }

    private void enableAllButtons(JButton bNotToBeDisabled){
        for(JButton b : buttonList){
            b.setEnabled(false);
            //b.setBackground(col2);
        }
        bNotToBeDisabled.setEnabled(true);
        //bNotToBeDisabled.setBackground(col3);
    }


    public void updateProgressBar(){
        if(engine.getSelectedInfraTech() != null){
            if(progressBar == null){
                progressBar = new JProgressBar(0, (int) engine.getSelectedInfraTech().getPointsNeeded());
                panel.add(progressBar);
            }
            if(noTechSelectedLabel != null) panel.remove(this.noTechSelectedLabel);
            progressBar.setMaximum((int) engine.getSelectedInfraTech().getPointsNeeded());
            progressBar.setValue((int) engine.getSelectedInfraTech().getPointsUsed());
            progressBar.setStringPainted(true);
        } else  {
            if(this.noTechSelectedLabel != null){
                panel.add(this.noTechSelectedLabel);
            } else {
                this.noTechSelectedLabel = new JLabel("No Technology selected to be researched.");
                panel.add(this.noTechSelectedLabel);
            }
        }
        this.repaint();
    }

    public void updateButtons(){
        jbStreetUpgrade.setText("<html>Street Upgrade<br><br><br><br><br><font color=#666666>["+(int)engine.streetUpgradeTech.getPointsUsed()+"/"+(int)engine.streetUpgradeTech.getPointsNeeded()+" points]</font></html>");
        jbRailUpgrade.setText("<html>Rail Upgrade<br><br><br><br><br><font color=#666666>["+(int)engine.railUpgradeTech.getPointsUsed()+"/"+(int)engine.railUpgradeTech.getPointsNeeded()+" points]</font></html>");
        jbPoliceUpgrade.setText("<html>Police Upgrade<br><br><br><br><br><font color=#666666>["+(int)engine.policeUpgradeTech.getPointsUsed()+"/"+(int)engine.policeUpgradeTech.getPointsNeeded()+" points]</font></html>");
        jbFireDepUpgrade.setText("<html>Fire Department<br>Upgrade<br><br><br><br><font color=#666666>["+(int)engine.fireUpdateTech.getPointsUsed()+"/"+(int)engine.fireUpdateTech.getPointsNeeded()+" points]</font></html>");
        jbTwoLaneRoad.setText("<html>Research<br>Two-Lane Roads<br><br><br><br><font color=#666666>["+(int)engine.twoLaneRoadTech.getPointsUsed()+"/"+(int)engine.twoLaneRoadTech.getPointsNeeded()+" points]</font><html>");
        jbAirport.setText("<html>Research Airports<br><br><br><br><br><font color=#666666>["+(int)engine.airportTech.getPointsUsed()+"/"+(int)engine.airportTech.getPointsNeeded()+" points]</font></html>");
    }
	
	public ScienceFrameB(Window owner, Micropolis m){
		super(owner);
		this.engine=m;
        buttonList = new ArrayList<JButton>();


        jbReset = new JButton("Reset current Research.");

		jbStreetUpgrade = new JButton();
		jbRailUpgrade = new JButton ();
		jbPoliceUpgrade = new JButton ();
		jbFireDepUpgrade = new JButton ();
		jbTwoLaneRoad = new JButton ();
		jbAirport = new JButton ();
        buttonList.add(jbStreetUpgrade);
        buttonList.add(jbRailUpgrade);
        buttonList.add(jbPoliceUpgrade);
        buttonList.add(jbFireDepUpgrade);
        buttonList.add(jbTwoLaneRoad);
        buttonList.add(jbAirport);
        updateButtons();





        //disable already researched buildings
        if(engine.twoLaneRoadTech.getIsResearched()) jbTwoLaneRoad.setEnabled(false);
        if(engine.airportTech.getIsResearched()) jbAirport.setEnabled(false);


        GeneralTechnology selectedTech = engine.getSelectedInfraTech();
        if(selectedTech != null){
            if(selectedTech.isSame(engine.streetUpgradeTech)){
                disableAllButtonsBut(jbStreetUpgrade);
            } else if(selectedTech.isSame(engine.railUpgradeTech)){
                disableAllButtonsBut(jbRailUpgrade);
            } else if(selectedTech.isSame(engine.policeUpgradeTech)){
                disableAllButtonsBut(jbPoliceUpgrade);
            } else if(selectedTech.isSame(engine.fireUpdateTech)){
                disableAllButtonsBut(jbFireDepUpgrade);
            } else if(selectedTech.isSame(engine.twoLaneRoadTech)){
                disableAllButtonsBut(jbTwoLaneRoad);
            } else if(selectedTech.isSame(engine.airportTech)){
                disableAllButtonsBut(jbAirport);
            }
        }
		
		jbStreetUpgrade.setToolTipText("Let more money flow towards road construction. The quality of your roads will improve.");
		jbRailUpgrade.setToolTipText("Let more money flow towards rail construction. The quality of your roads will improve.");
		jbPoliceUpgrade.setToolTipText("Let more money flow towards police stations. Police officers will be much more efficient.");
		jbFireDepUpgrade.setToolTipText("Let more money flow towards fire departments. Fire fighters will be much more efficient.");
		jbTwoLaneRoad.setToolTipText("Let more money flow towards rail construction. Eventually someone will find a way to reduce heavy traffic on your roads.");
		jbAirport.setToolTipText("Let more money flow towards research. You will be able to build an airport... sooner or later...");
        jbReset.setToolTipText("You will lose all current research progress, but can research something differnt.");

		jbStreetUpgrade.setPreferredSize(new Dimension(180,125));
		jbRailUpgrade.setPreferredSize(new Dimension(180,125));
		jbPoliceUpgrade.setPreferredSize(new Dimension(180,125));
		jbFireDepUpgrade.setPreferredSize(new Dimension(180,125));
		jbTwoLaneRoad.setPreferredSize(new Dimension(180,125));
		jbAirport.setPreferredSize(new Dimension(180,125));



		
		
		
		jbStreetUpgrade.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){

			engine.setSelectedInfraTech(engine.streetUpgradeTech);
            disableAllButtonsBut(jbStreetUpgrade);
            updateProgressBar();
			
			}
		});
		
		jbRailUpgrade.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
                engine.setSelectedInfraTech(engine.railUpgradeTech);
                disableAllButtonsBut(jbRailUpgrade);
                updateProgressBar();
			}
			
		});
			
		jbPoliceUpgrade.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
                engine.setSelectedInfraTech(engine.policeUpgradeTech);
                disableAllButtonsBut(jbPoliceUpgrade);
                updateProgressBar();
			}
			
		});
		
		jbFireDepUpgrade.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
                engine.setSelectedInfraTech(engine.fireUpdateTech);
                disableAllButtonsBut(jbFireDepUpgrade);
                updateProgressBar();


			}
		});
		
		jbTwoLaneRoad.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
            disableAllButtonsBut(jbTwoLaneRoad);
            engine.setSelectedInfraTech(engine.twoLaneRoadTech);
            updateProgressBar();
			}
			
		});
		
		


		jbAirport.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
                disableAllButtonsBut(jbAirport);
                engine.setSelectedInfraTech(engine.airportTech);
                updateProgressBar();
			}
			
		});

        jbReset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                engine.getSelectedInfraTech().resetResearchPoints();
                engine.selectedInfraTech = null;
                for(JButton b : buttonList){
                    b.setEnabled(true);
                }
                updateProgressBar();
                repaint();

            }
        });
		
		
		panel  = new JPanel(null,true);
		getContentPane().add(panel);
		
		panel.add(jbStreetUpgrade);
		panel.add(jbRailUpgrade);
		panel.add(jbPoliceUpgrade);
		panel.add(jbFireDepUpgrade);
		panel.add(jbTwoLaneRoad);
		panel.add(jbAirport);
        panel.add(jbReset);
        updateProgressBar();




		
		
		panel.setLayout(new FlowLayout());
		
		pack();
		setSize(410,500);
		setLocationRelativeTo(getParent());
		Color color=new Color(121,101,151);  
		panel.setBackground(color);
	}
	
	
}
