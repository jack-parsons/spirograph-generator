package com.jack_parsons.spirograph;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class View extends JFrame {

	private static View frame;
	
	final int INITIALRADIUSRATIO = 800;
	final int INITIALPENOFFSET = 800;
	
	private JPanel contentPane;
	private JToolBar toolBar;
	private JLabel radiusRatio;
	private JSlider radiusRatioSlider;
	private Spirograph spirograph;
	private JLabel penOffsetLabel;
	private JSlider penOffsetSlider;
	private JLabel loopsLabel;
	private JSlider loopsSlider;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		// Set up the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// Create spirograph object
		spirograph = new Spirograph((float)INITIALRADIUSRATIO/1000, (float)INITIALPENOFFSET/1000);
		
		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		radiusRatio = new JLabel("Radius Ratio");
		toolBar.add(radiusRatio);
		
		// Create radius ration slider
		radiusRatioSlider = new JSlider();
		radiusRatioSlider.setPaintTicks(true);
		radiusRatioSlider.setMajorTickSpacing(50);
		radiusRatioSlider.setMaximum(1000);
		radiusRatioSlider.setValue(INITIALRADIUSRATIO);
		radiusRatioSlider.addChangeListener(e -> {
			spirograph.setRadiusRatio((float)radiusRatioSlider.getValue()/1000);
			frame.repaint();
		});
		toolBar.add(radiusRatioSlider);
		
		penOffsetLabel = new JLabel("Pen Offset");
		toolBar.add(penOffsetLabel);
		
		// Set up the pen offset slider
		penOffsetSlider = new JSlider();
		penOffsetSlider.setPaintTicks(true);
		penOffsetSlider.setMajorTickSpacing(50);
		penOffsetSlider.setMaximum(1000);
		penOffsetSlider.setValue(INITIALPENOFFSET);
		penOffsetSlider.addChangeListener(e -> {
			spirograph.setPenOffset((float)penOffsetSlider.getValue()/1000);
			frame.repaint();
		});
		toolBar.add(penOffsetSlider);
		
		loopsLabel = new JLabel("Loops");
		toolBar.add(loopsLabel);
		
		loopsSlider = new JSlider();
		loopsSlider.setMaximum(1000);
		loopsSlider.setMajorTickSpacing(10);
		loopsSlider.setMinimum(1);
		loopsSlider.setSnapToTicks(true);
		loopsSlider.setMinorTickSpacing(5);
		//loopsSlider.setPaintLabels(true);
		//loopsSlider.setPaintTicks(true);
		loopsSlider.addChangeListener(e -> {
			spirograph.setNumLoops(loopsSlider.getValue());
			frame.repaint();
		});
		toolBar.add(loopsSlider);
	}
	
	@Override
	/**
	 * 
	 */
	public void paint(Graphics canvas) {
        Graphics2D canvas2D = (Graphics2D) canvas;
        spirograph.update(frame.getWidth(), frame.getHeight()-80);
        super.paint(canvas);
        spirograph.paint(canvas2D, 0);
	}

}
