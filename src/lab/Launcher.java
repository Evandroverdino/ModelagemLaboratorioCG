package lab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Launcher {

	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);

		DrawingPanel drawingPanel = new DrawingPanel();

		final GLCanvas glcanvas = new GLCanvas(capabilities);
		glcanvas.addGLEventListener(drawingPanel);
		glcanvas.addKeyListener(drawingPanel);
		glcanvas.setSize(1024, 800);

		final FPSAnimator animator = new FPSAnimator(glcanvas, 60, true);

		final JFrame frame = new JFrame("Drawing Lab");
		frame.getContentPane().add(glcanvas);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (animator.isStarted())
					animator.stop();
				System.exit(0);
			}
		});
		frame.setSize(frame.getContentPane().getPreferredSize());

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int windowX = Math.max(0, (screenSize.width - frame.getWidth()) / 2);
		int windowY = Math.max(0, (screenSize.height - frame.getHeight()) / 2);

		frame.setLocation(windowX, windowY);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 0));
		frame.add(panel, BorderLayout.SOUTH);

		animator.start();
	}
}
