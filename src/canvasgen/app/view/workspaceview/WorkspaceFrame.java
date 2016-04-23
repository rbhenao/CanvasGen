package canvasgen.app.view.workspaceview;

import canvasgen.app.factory.mvpFactory;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class WorkspaceFrame extends JFrame {

	private JMenuBar menuBar;
	private JToolBar toolBar;
	public JDesktopPane canvasDesktop;
	private static int openFrameCount = 0;
	JLabel mousePosition;

	public WorkspaceFrame() {
		super("Canvas Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		
		menuBar = new WorkspaceMenuBar();
		toolBar = new WorkspaceToolBar(JToolBar.VERTICAL);

		mousePosition = new JLabel();
		mousePosition.setPreferredSize(new Dimension(-1, 22));
		//mousePosition.setBorder(LineBorder.createGrayLineBorder());
		mousePosition.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, (new Color(128,128,128))));
		mousePosition.setOpaque(true);
		mousePosition.setBackground(new Color(90,90,90));
        add(mousePosition, BorderLayout.SOUTH);
		mousePosition.setFont(new Font("Arial", Font.PLAIN, 10));
		mousePosition.setForeground(Color.white);

		canvasDesktop = new JDesktopPane();
		setJMenuBar(menuBar);
		add(toolBar, BorderLayout.WEST);	
		canvasDesktop.setBackground(Color.darkGray);
		add(canvasDesktop, BorderLayout.CENTER);
	}
	
	public JToolBar getToolBar() {
		return toolBar;
	}

	public JLabel getPointPosition() {
		return this.mousePosition;
	}
	
	public void setToolBar(JToolBar toolBar) {
		this.toolBar = toolBar;
	}
	
	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public JDesktopPane getCanvasDesktop() {
		return this.canvasDesktop;
	}
	
	public void setCanvasDesktop(JDesktopPane desktopPane){
		this.canvasDesktop = desktopPane;
	}
}