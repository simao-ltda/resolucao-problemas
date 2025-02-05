package micropolisj.gui;

import javax.swing.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.*;
import java.awt.Dimension;
import java.util.ArrayList;

import micropolisj.engine.Micropolis;
import micropolisj.engine.techno.GeneralTechnology;

public class ScienceFrameA extends JDialog {

	Micropolis engine;
	
	JPanel panel;
	JButton jbPollution;
	JButton jbNuclear;
	JButton jbPowerEfficiency;
	JButton jbSolar;
	JButton jbWind;
    JButton jbReset;
    JProgressBar progressBar;
    JLabel noTechSelectedLabel;
    ArrayList<JButton> buttonList;

    Color col2= new Color(255,229,168);
    Color col3= new Color(169,125,19);



    //UNIVERSITAET FUER NATURWISSENSCHAFT


    public void updateProgressBar(){
        if(engine.getSelectedEETech() != null){
            if(progressBar == null){
                progressBar = new JProgressBar(0, (int) engine.getSelectedEETech().getPointsNeeded());
                panel.add(progressBar);
            }
            if(noTechSelectedLabel != null) panel.remove(this.noTechSelectedLabel);
            progressBar.setMaximum((int) engine.getSelectedEETech().getPointsNeeded());
            progressBar.setValue((int) engine.getSelectedEETech().getPointsUsed());
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

    private void disableAllButtonsBut(JButton bNotToBeDisabled){
        for(JButton b : buttonList){
            b.setEnabled(false);
            //b.setBackground(col2);
        }
        bNotToBeDisabled.setEnabled(true);
        //bNotToBeDisabled.setBackground(col3);
        jbPowerEfficiency.setEnabled(engine.windTech.getIsResearched() && engine.solarTech.getIsResearched());
    }

    public void updateButtons(){

        jbPollution.setText("<html>Reduce Pollution<br><br><br><br><br><font color=#666666>[" +(int)engine.reducePollutionTech.getPointsUsed()+ "/" +(int)engine.reducePollutionTech.getPointsNeeded()+ " points]</font></html>");
        jbNuclear.setText("<html>Reduce Risk Of<br>Nuclear Meltdown<br><br><br><br><font color=#666666>["+(int)engine.meltdownTech.getPointsUsed()+"/"+(int)engine.meltdownTech.getPointsNeeded()+" points]</font></html>");
        jbPowerEfficiency.setText("<html>Improve<br> Efficiency Of<br>Wind And Solar<br>Power Stations<br><br><font color=#666666>["+(int)engine.improveWindSolarTech.getPointsUsed()+"/"+(int)engine.improveWindSolarTech.getPointsNeeded()+" points]</font></html>");
        jbSolar.setText("<html>Research Solar<br>Power Stations<br><br><br><br><font color=#666666>["+(int)engine.solarTech.getPointsUsed()+"/"+(int)engine.solarTech.getPointsNeeded()+" points]</font></html>");
        jbWind.setText("<html>Research Wind<br>Power Stations<br><br><br><br><font color=#666666>["+(int)engine.windTech.getPointsUsed()+"/"+(int)engine.windTech.getPointsNeeded()+" points]</font></html>");

    }
	
	public ScienceFrameA(Window owner, Micropolis m){
		super(owner);
		this.engine=m;

        buttonList = new ArrayList<JButton>();
        jbReset = new JButton("Reset current Research.");
		jbPollution = new JButton();
		jbNuclear = new JButton();
		jbPowerEfficiency = new JButton();
		jbSolar = new JButton();
		jbWind = new JButton();
        buttonList.add(jbPollution);
        buttonList.add(jbNuclear);
        buttonList.add(jbPowerEfficiency);
        buttonList.add(jbSolar);
        buttonList.add(jbWind);
        updateButtons();


        jbNuclear.setEnabled(true);
        jbPowerEfficiency.setEnabled(engine.windTech.getIsResearched() && engine.solarTech.getIsResearched());
        jbSolar.setEnabled(true);
        jbWind.setEnabled(true);
        jbReset.setEnabled(true);
        jbNuclear.setEnabled(true);


        GeneralTechnology selectedTech = engine.getSelectedEETech();
        if(selectedTech != null){
            if(selectedTech.isSame(engine.reducePollutionTech)){
                disableAllButtonsBut(jbPollution);
            } else if(selectedTech.isSame(engine.solarTech)){
                disableAllButtonsBut(jbSolar);
            } else if(selectedTech.isSame(engine.windTech)){
                disableAllButtonsBut(jbWind);
            } else if(selectedTech.isSame(engine.improveWindSolarTech)){
                disableAllButtonsBut(jbPowerEfficiency);
            } else if(selectedTech.isSame(engine.meltdownTech)){
                disableAllButtonsBut(jbNuclear);
            }

            // disable already researched buildings
            if(engine.solarTech.getIsResearched()) jbSolar.setEnabled(false);
            if(engine.windTech.getIsResearched()) jbWind.setEnabled(false);


        }
		
		jbPollution.setToolTipText("Let more money flow towards research. They will discover new methods of decreasing air pollution.");
		jbNuclear.setToolTipText("Let more money flow towards nuclear research. It will decrease air pollution immediately.");
		jbPowerEfficiency.setToolTipText("Let more money flow towards research. You will have immediate results.");
		jbSolar.setToolTipText("Let more money flow towards research. Researchers will work on finding a way to use solar power in your town.");
		jbWind.setToolTipText("Let more money flow towards research. Researchers will work on finding a way to use wind power in your town.");
        jbReset.setToolTipText("You will lose all current research progress, but can research something differnt.");
		
		jbPollution.setPreferredSize(new Dimension(180,125));
		jbNuclear.setPreferredSize(new Dimension(180,125));
		jbPowerEfficiency.setPreferredSize(new Dimension(180,125));
		jbSolar.setPreferredSize(new Dimension(180,125));
		jbWind.setPreferredSize(new Dimension(180,125));


		jbPollution.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
                engine.setSelectedEETech(engine.reducePollutionTech);
                disableAllButtonsBut(jbPollution);
                updateProgressBar();

			}
		});
		
		jbNuclear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				engine.setSelectedEETech(engine.meltdownTech);
                disableAllButtonsBut(jbNuclear);
                updateProgressBar();

			}
			
		});
		
		jbPowerEfficiency.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
                engine.setSelectedEETech(engine.improveWindSolarTech);
                disableAllButtonsBut(jbPowerEfficiency);
                updateProgressBar();

			}
		});
		
		jbSolar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
			
			
			engine.setSelectedEETech(engine.solarTech);
            disableAllButtonsBut(jbSolar);
            updateProgressBar();
			}
			
		});
		
		jbWind.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){

            engine.setSelectedEETech(engine.windTech);
            disableAllButtonsBut(jbWind);
            updateProgressBar();
			}
		});

        jbReset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
               engine.getSelectedEETech().resetResearchPoints();
                engine.selectedEETech = null;
               for(JButton b : buttonList){
                   b.setEnabled(true);
               }
                repaint();
               updateProgressBar();

            }
        });
		
		
		panel  = new JPanel(null,true);	
		getContentPane().add(panel);
		
		panel.add(jbPollution);
		panel.add(jbNuclear);
		panel.add(jbPowerEfficiency);
		panel.add(jbSolar);
		panel.add(jbWind);
        panel.add(jbReset);
        updateProgressBar();


		
		panel.setLayout(new FlowLayout());
		
		pack();
		setSize(410,500);
		setLocationRelativeTo(getParent());
		Color color=new Color(245,181,28);  
		panel.setBackground(color);
	}	
	

}