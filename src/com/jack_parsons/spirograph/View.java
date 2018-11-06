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

	private JPanel contentPane;
	private JToolBar toolBar;
	private JLabel radiusRatio;
	private JSlider radiusRatioSlider;
	private Spirograph spirograph;
	private JLabel penOffsetLabel;
	private JSlider penOffsetSlider;
	
	private static View frame;
	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		radiusRatio = new JLabel("Radius Ratio");
		toolBar.add(radiusRatio);
		
		radiusRatioSlider = new JSlider();
		radiusRatioSlider.setMaximum(1000);
		radiusRatioSlider.setValue(800);
		radiusRatioSlider.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				spirograph.setRadiusRatio((float)radiusRatioSlider.getValue()/1000);
				frame.repaint();
			}
		});
		toolBar.add(radiusRatioSlider);
		
		penOffsetLabel = new JLabel("Pen Offset");
		toolBar.add(penOffsetLabel);
		
		penOffsetSlider = new JSlider();
		penOffsetSlider.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				spirograph.setPenOffset((float)penOffsetSlider.getValue()/1000);
				frame.repaint();
			}
		});
		penOffsetSlider.setMaximum(1000);
		penOffsetSlider.setValue(800);
		toolBar.add(penOffsetSlider);
		spirograph = new Spirograph((float)radiusRatioSlider.getValue()/1000, (float)penOffsetSlider.getValue()/1000);
	}
	
	@Override
	public void paint(Graphics canvas) {
        Graphics2D canvas2D = (Graphics2D) canvas;
        spirograph.update(frame.getWidth(), frame.getHeight()-80);
        super.paint(canvas);
        spirograph.paint(canvas2D);
	}

}
