package lab;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Activity implements GLEventListener, KeyListener {

	private GLUT glut = new GLUT();
	private GLU glu = new GLU();
	private GLCapabilities caps;
	private GLCanvas canvas;
	private FPSAnimator animator;
	private GLCanvas glcanvas;
	private int DEF_D = 5;

	public Activity() {

		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		caps = new GLCapabilities(profile);
		// caps.setDoubleBuffered(true);// request double buffer display mode
		canvas = new GLCanvas(caps);
		canvas.setSize(1024, 800);
		canvas.addGLEventListener(this);
		canvas.addKeyListener(this);
		// canvas.addMouseListener(this);
		animator = new FPSAnimator(canvas, 60);

		// creating frame
		final JFrame frame = new JFrame(" Laboratório");
		// frame.setUndecorated(true);
		// frame.setLayout(new FlowLayout(FlowLayout.LEFT));
		// adding canvas to it
		frame.getContentPane().add(canvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		// getContentPane().add(canvas);
	}

	public void run() {
		animator.start();
	}

	public static void main(String[] args) {
		new Activity().run();

	}

	// -----------------------------TELA------------------------------------
	private static final float PI = 3.1415f;

	private int WIDTH = 800;
	private int HEIGHT = 600;

	int texture;
	int textureWall;
	int textureTable;
	int textureDoor;
	int textureWindow;

	int textureWindow2;
	int textureWhiteBoard;
	int textureEdge;
	int textureCorredor;
	int textureRoof;
	int textureRoof2;

	int texturePC;
	int texturePCMonitor;
	int texturePCGabinete;
	int texturePCGabinete2;
	int texturePCMonitor2;
	int textureKeyboard;
	int textureFundo;
	int textureFrente;

	int textureArFrente;
	int textureArDir;
	int textureArDir2;
	int textureArEsq;
	int textureArEsq2;
	int textureArFrente2;
	int textureChair;
	int textureChair2;

	public void carregar_texturas(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		try {

			Texture text = TextureIO.newTexture(new File(getClass().getResource("/img/piso2.jpg").getPath()), true);
			texture = text.getTextureObject(gl);

			Texture text2 = TextureIO.newTexture(new File(getClass().getResource("/img/parede3.jpg").getPath()), true);
			textureWall = text2.getTextureObject(gl);

			Texture text3 = TextureIO.newTexture(new File(getClass().getResource("/img/mesa2.jpg").getPath()), true);
			textureTable = text3.getTextureObject(gl);

			Texture text4 = TextureIO.newTexture(new File(getClass().getResource("/img/porta.jpg").getPath()), true);
			textureDoor = text4.getTextureObject(gl);

			Texture text5 = TextureIO.newTexture(new File(getClass().getResource("/img/janelaFrente.jpg").getPath()),
					true);
			textureWindow = text5.getTextureObject(gl);

			Texture text6 = TextureIO.newTexture(new File(getClass().getResource("/img/quadro.jpg").getPath()), true);
			textureWhiteBoard = text6.getTextureObject(gl);

			Texture text7 = TextureIO.newTexture(new File(getClass().getResource("/img/janelaTras.jpg").getPath()),
					true);
			textureWindow2 = text7.getTextureObject(gl);

			Texture text8 = TextureIO.newTexture(new File(getClass().getResource("/img/bordas.jpg").getPath()), true);
			textureEdge = text8.getTextureObject(gl);

			Texture text9 = TextureIO.newTexture(new File(getClass().getResource("/img/corredor.jpg").getPath()), true);
			textureCorredor = text9.getTextureObject(gl);

			Texture text10 = TextureIO.newTexture(new File(getClass().getResource("/img/teto.jpg").getPath()), true);
			textureRoof = text10.getTextureObject(gl);

			Texture text11 = TextureIO.newTexture(new File(getClass().getResource("/img/tetoCorredor.jpg").getPath()),
					true);
			textureRoof2 = text11.getTextureObject(gl);

			Texture text12 = TextureIO.newTexture(new File(getClass().getResource("/img/preto.jpg").getPath()), true);
			texturePC = text12.getTextureObject(gl);

			Texture text13 = TextureIO.newTexture(new File(getClass().getResource("/img/monitor.jpg").getPath()), true);
			texturePCMonitor = text13.getTextureObject(gl);

			Texture text14 = TextureIO.newTexture(new File(getClass().getResource("/img/gabinete.png").getPath()),
					true);
			texturePCGabinete = text14.getTextureObject(gl);

			Texture text15 = TextureIO.newTexture(new File(getClass().getResource("/img/monitor2.jpg").getPath()),
					true);
			texturePCMonitor2 = text15.getTextureObject(gl);

			Texture text16 = TextureIO.newTexture(new File(getClass().getResource("/img/gabinete2.png").getPath()),
					true);
			texturePCGabinete2 = text16.getTextureObject(gl);

			Texture text17 = TextureIO.newTexture(new File(getClass().getResource("/img/keyboard.png").getPath()),
					true);
			textureKeyboard = text17.getTextureObject(gl);

			Texture text18 = TextureIO.newTexture(new File(getClass().getResource("/img/fundo.jpg").getPath()), true);
			textureFundo = text18.getTextureObject(gl);

			Texture text19 = TextureIO.newTexture(new File(getClass().getResource("/img/frente.jpg").getPath()), true);
			textureFrente = text19.getTextureObject(gl);

			Texture text20 = TextureIO.newTexture(new File(getClass().getResource("/img/arFrente.jpg").getPath()),
					true);
			textureArFrente = text20.getTextureObject(gl);

			Texture text21 = TextureIO.newTexture(new File(getClass().getResource("/img/arFrente2.jpg").getPath()),
					true);
			textureArFrente2 = text21.getTextureObject(gl);

			Texture text22 = TextureIO.newTexture(new File(getClass().getResource("/img/arEsq.jpg").getPath()), true);
			textureArEsq = text22.getTextureObject(gl);

			Texture text23 = TextureIO.newTexture(new File(getClass().getResource("/img/arEsq2.jpg").getPath()), true);
			textureArEsq2 = text23.getTextureObject(gl);

			Texture text24 = TextureIO.newTexture(new File(getClass().getResource("/img/arDir.jpg").getPath()), true);
			textureArDir = text24.getTextureObject(gl);

			Texture text25 = TextureIO.newTexture(new File(getClass().getResource("/img/arDir2.jpg").getPath()), true);
			textureArDir2 = text25.getTextureObject(gl);

			Texture text26 = TextureIO.newTexture(new File(getClass().getResource("/img/cadeira.jpg").getPath()), true);
			textureChair = text26.getTextureObject(gl);

			Texture text27 = TextureIO.newTexture(new File(getClass().getResource("/img/cadeira2.jpg").getPath()),
					true);
			textureChair2 = text27.getTextureObject(gl);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void createCube(GLAutoDrawable drawable, int texture, float width, float height, float lenght, float x,
			float y, float z, float rotateX, float rotateY, float rotateZ) {

		GL2 gl = drawable.getGL().getGL2();

		gl.glPushMatrix();

		gl.glTranslatef(-z, y, -x);
		gl.glRotated(rotateX, 1, 0, 0);
		gl.glRotated(rotateY, 0, 1, 0);
		gl.glRotated(rotateZ, 0, 0, 1);
		gl.glTranslatef(z, -y, x);
		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
		gl.glBegin(GL2.GL_QUADS);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		x = -x;
		z = -z;

		// Left Face (comprimento,altura,largura)
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z, -height / 2 + y, width / 2 + x);// frente
																		// baixo
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y, width / 2 + x);// atras
																		// baixo
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(lenght / 2 + z, height / 2 + y, width / 2 + x);// atras
																		// cima
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z, height / 2 + y, width / 2 + x);// frente
																		// cima

		// Right face
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z, -height / 2 + y, -width / 2 + x);// frente
																		// baixo
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z, height / 2 + y, -width / 2 + x);// frente
																		// cima
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(lenght / 2 + z, height / 2 + y, -width / 2 + x);// atras
																		// Cima
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y, -width / 2 + x);// atras
																		// baixo

		// Top Face
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z, height / 2 + y, -width / 2 + x);// direita
																		// cima
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z, height / 2 + y, width / 2 + x);// esquerda
																		// cima
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(lenght / 2 + z, height / 2 + y, width / 2 + x);// esquerda
																		// baixo
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(lenght / 2 + z, height / 2 + y, -width / 2 + x);// direita
																		// baixo

		// Bottom Face
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z, -height / 2 + y, -width / 2 + x);// direita
																		// cima
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y, -width / 2 + x);// direita
																		// baixo
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y, width / 2 + x);// esquerda
																		// baixo
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z, -height / 2 + y, width / 2 + x);// esquerda
																		// cima

		// Front face
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y, -width / 2 + x);// direita
																		// baixo
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(lenght / 2 + z, height / 2 + y, -width / 2 + x);// direita
																		// cima
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(lenght / 2 + z, height / 2 + y, width / 2 + x);// esquerda
																		// Cima
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y, width / 2 + x);// esquerda
																		// baixo

		// back Face
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z, -height / 2 + y, -width / 2 + x);// direita
																		// baixo
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z, -height / 2 + y, width / 2 + x);// esquerda
																		// baixo
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z, height / 2 + y, width / 2 + x);// esquerda
																		// Cima
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z, height / 2 + y, -width / 2 + x);// direita
																		// cima

		gl.glEnd();
		gl.glPopMatrix();
		gl.glFlush();
	}

	// -----------------------Public code--------------------------

	// Define camera variables
	float cameraAzimuth = 90.0f, cameraSpeed = 0.0f, cameraElevation = 0.0f;

	// Set camera at (0, 0, -20)
	float cameraCoordsPosx = 5.0f, cameraCoordsPosy = 1f, cameraCoordsPosz = 0.0f;

	// Set camera orientation
	float cameraUpx = 0.0f, cameraUpy = 1.0f, cameraUpz = 0.0f;

	public void moveCamera() {
		float[] tmp = polarToCartesian(cameraAzimuth, cameraSpeed, cameraElevation);

		// Replace old x, y, z coords for camera
		cameraCoordsPosx += tmp[0];
		cameraCoordsPosy += tmp[1];
		cameraCoordsPosz += tmp[2];
	}

	public void aimCamera(GL2 gl, GLU glu) {
		gl.glLoadIdentity();

		// Calculate new eye vector
		float[] tmp = polarToCartesian(cameraAzimuth, 100.0f, cameraElevation);

		// Calculate new up vector
		float[] camUp = polarToCartesian(cameraAzimuth, 100.0f, cameraElevation + 90);

		cameraUpx = camUp[0];
		cameraUpy = camUp[1];
		cameraUpz = camUp[2];

		glu.gluLookAt(cameraCoordsPosx, cameraCoordsPosy, cameraCoordsPosz, cameraCoordsPosx + tmp[0],
				cameraCoordsPosy + tmp[1], cameraCoordsPosz + tmp[2], cameraUpx, cameraUpy, cameraUpz);
	}

	private float[] polarToCartesian(float azimuth, float length, float altitude) {
		float[] result = new float[3];
		float x, y, z;

		// Do x-z calculation
		float theta = (float) Math.toRadians(90 - azimuth);
		float tantheta = (float) Math.tan(theta);
		float radian_alt = (float) Math.toRadians(altitude);
		float cospsi = (float) Math.cos(radian_alt);

		x = (float) Math.sqrt((length * length) / (tantheta * tantheta + 1));
		z = tantheta * x;

		x = -x;

		if ((azimuth >= 180.0 && azimuth <= 360.0) || azimuth == 0.0f) {
			x = -x;
			z = -z;
		}

		// Calculate y, and adjust x and z
		y = (float) (Math.sqrt(z * z + x * x) * Math.sin(radian_alt));

		if (length < 0) {
			x = -x;
			z = -z;
			y = -y;
		}

		x = x * cospsi;
		z = z * cospsi;

		// In contrast we could use the simplest form for computing Cartesian
		// from Spherical as follows:
		// x = (float)(length *
		// Math.sin(Math.toRadians(altitude))*Math.cos(Math.toRadians(azimuth)));
		// y = (float)(length *
		// Math.sin(Math.toRadians(altitude))*Math.sin(Math.toRadians(azimuth)));
		// z = (float)(length * Math.cos(Math.toRadians(altitude)));

		result[0] = x;
		result[1] = y;
		result[2] = z;

		return result;
	}

	// --------------------------------------------------------------------------------

	@Override
	public void display(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2();

		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		// gl.glClearColor(0f, 0f, 0f, 0f);

		aimCamera(gl, glu);
		moveCamera();

		createCorredor(drawable);
		createLab(drawable);

		gl.glFlush();

	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2();

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		carregar_texturas(drawable);
		gl.glEnable(GL2.GL_DEPTH_TEST);

		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();
		WIDTH = width;
		HEIGHT = height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(70.0, width / (float) height, 0.1, 30.0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);

	}

	// -----------------------------TECLADO------------------------------------
	@Override
	public void keyTyped(KeyEvent e) {

	}

	static int shoulder = 0;

	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_UP) {
			cameraElevation += 2;
		}

		if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			cameraElevation -= 2;
		}

		if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
			cameraAzimuth += 3;
		}

		if (event.getKeyCode() == KeyEvent.VK_LEFT) {
			cameraAzimuth -= 3;
		}

		if (event.getKeyCode() == KeyEvent.VK_I) {
			cameraSpeed = 0.05F;
		}

		if (event.getKeyCode() == KeyEvent.VK_O) {
			cameraSpeed = -0.05F;
		}

		if (event.getKeyCode() == KeyEvent.VK_D) {
			if (shoulder >= 85) {
				return;
			}
			shoulder = (shoulder + 5) % 360;
		}
		if (event.getKeyCode() == KeyEvent.VK_F) {
			if (shoulder == 0) {
				return;
			}
			shoulder = (shoulder - 5) % 360;
		}

		if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}

		if (cameraAzimuth > 359)
			cameraAzimuth = 1;

		if (cameraAzimuth < 1)
			cameraAzimuth = 359;
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_I || event.getKeyCode() == KeyEvent.VK_O) {
			cameraSpeed = 0;
		}

	}

	private void createLab(GLAutoDrawable drawable) {
		// chao do laboratorio
		createCube(drawable, texture, 8f, 0.2f, 3.5f, -5f, -0.0001f, 3.6f, 0, 0, 0);
		// teto do laboratorio
		createCube(drawable, textureRoof, 8f, 0.2f, 3.5f, -5f, 2.11f, 3.6f, 0, 0, 0);

		// Parede lab fundo
		createCube(drawable, textureWindow2, 0.2f, 2f, 3.5f, -9f, 1, 3.6f, 0, 0, 0);

		// Parede lab esq
		createCube(drawable, textureWall, 7, 2, 0.2f, -5.5f, 1, 1.83f, 0, 0, 0);

		// parede lab dir
		createCube(drawable, textureWall, 7, 2, 0.2f, -5.5f, 1, 5.3f, 0, 0, 0);

		// Quadro Branco Lab
		createCube(drawable, textureWhiteBoard, 2.1f, 1.1f, 0.05f, -4.3f, 1.2f, 2f, 0, 0, 0);
		createCube(drawable, textureEdge, 2.15f, 1.15f, 0.05f, -4.3f, 1.2f, 1.98f, 0, 0, 0);
		createCube(drawable, textureEdge, 0.5f, 0.03f, 0.15f, -4.3f, 0.67f, 2.1f, 0, 0, 0);

		createTable(drawable);
		createDoor(drawable);
		createPcs(drawable);
		createChairs(drawable);

	}

	private void createChairs(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2();

		// ---------------------Cadeira 1---------------------------------

		// Costas
		createCylinder(drawable, textureChair, 0.18f, 0.18f, 0.04f, -3.77f, 0.61f, 3.25f, 90, 0, 0);
		createCylinder(drawable, textureChair, 0.18f, 0.18f, 0.04f, -3.77f, 0.61f, 3.25f, 0, 0, 0);
		// sentar
		createCylinder(drawable, textureChair, 0.21f, 0.18f, 0.04f, -3.77f, 0.35f, 3.505f, 0, 0, 90);
		createCylinder(drawable, textureChair, 0.21f, 0.18f, 0.04f, -3.77f, 0.35f, 3.505f, 0, 90, 90);
		// Apoio
		createCylinder(drawable, textureChair2, 0.03f, 0.18f, 0.03f, -3.77f, 0.48f, 3.21f, 0, 0, 0);
		createCylinder(drawable, textureChair2, 0.03f, 0.18f, 0.03f, -3.77f, 0.326f, 3.365f, 0, 0, 90);
		// Pés Centro
		createCylinder(drawable, textureChair2, 0.03f, 0.08f, 0.03f, -3.77f, 0.23f, 3.52f, 0, 0, 0);
		// Pés
		createCylinder(drawable, textureChair2, 0.025f, 0.125f, 0.025f, -3.77f, 0.15f, 3.42f, 0, 0, 90);
		createCylinder(drawable, textureChair2, 0.025f, 0.125f, 0.025f, -3.65f, 0.15f, 3.49f, 0, 72, 90);
		createCylinder(drawable, textureChair2, 0.025f, 0.125f, 0.025f, -3.7f, 0.15f, 3.61f, 0, 144, 90);
		createCylinder(drawable, textureChair2, 0.025f, 0.125f, 0.025f, -3.845f, 0.15f, 3.61f, 0, 216, 90);
		createCylinder(drawable, textureChair2, 0.025f, 0.125f, 0.025f, -3.885f, 0.15f, 3.485f, 0, 288, 90);

		// Rodinhas

		gl.glColor3f(0, 0, 0);

		gl.glTranslated(-3.29f, 0.142f, 3.77f);
		glut.glutSolidSphere(0.032, 50, 50);

		gl.glTranslated(-0.42, 0f, 0.145f);
		glut.glutSolidSphere(0.032, 50, 50);

		gl.glTranslated(0.265f, 0f, 0.09f);
		glut.glutSolidSphere(0.032, 50, 50);

		gl.glTranslated(-0.012, 0f, -0.47f);
		glut.glutSolidSphere(0.032, 50, 50);

		gl.glTranslated(-0.25, 0f, 0.1f);
		glut.glutSolidSphere(0.032, 50, 50);

		gl.glTranslated(3.707f, -0.142f, -3.635f);

		gl.glColor3f(1, 1, 1);

		// ---------------------Cadeira 2---------------------------------

		// Costas
		createCylinder(drawable, textureChair, 0.18f, 0.18f, 0.04f, -5.27f, 0.61f, 4.25f, 90, 0, 0);
		createCylinder(drawable, textureChair, 0.18f, 0.18f, 0.04f, -5.27f, 0.61f, 4.25f, 0, 0, 0);
		// sentar
		createCylinder(drawable, textureChair, 0.21f, 0.18f, 0.04f, -5.27f, 0.35f, 4.505f, 0, 0, 90);
		createCylinder(drawable, textureChair, 0.21f, 0.18f, 0.04f, -5.27f, 0.35f, 4.505f, 0, 90, 90);
		// Apoio
		createCylinder(drawable, textureChair2, 0.03f, 0.18f, 0.03f, -5.27f, 0.48f, 4.21f, 0, 0, 0);
		createCylinder(drawable, textureChair2, 0.03f, 0.18f, 0.03f, -5.27f, 0.326f, 4.365f, 0, 0, 90);
		// Pés Centro
		createCylinder(drawable, textureChair2, 0.03f, 0.08f, 0.03f, -5.27f, 0.23f, 4.52f, 0, 0, 0);
		// Pés
		createCylinder(drawable, textureChair2, 0.025f, 0.125f, 0.025f, -5.27f, 0.15f, 4.42f, 0, 0, 90);
		createCylinder(drawable, textureChair2, 0.025f, 0.125f, 0.025f, -5.15f, 0.15f, 4.49f, 0, 72, 90);
		createCylinder(drawable, textureChair2, 0.025f, 0.125f, 0.025f, -5.2f, 0.15f, 4.61f, 0, 144, 90);
		createCylinder(drawable, textureChair2, 0.025f, 0.125f, 0.025f, -5.345f, 0.15f, 4.61f, 0, 216, 90);
		createCylinder(drawable, textureChair2, 0.025f, 0.125f, 0.025f, -5.385f, 0.15f, 4.485f, 0, 288, 90);

		// Rodinhas
		gl.glColor3f(0, 0, 0);

		gl.glTranslated(-4.29f, 0.142f, 5.27);
		glut.glutSolidSphere(0.032, 50, 50);

		gl.glTranslated(-0.42, 0f, 0.145f);
		glut.glutSolidSphere(0.032, 50, 50);

		gl.glTranslated(0.265f, 0f, 0.09f);
		glut.glutSolidSphere(0.032, 50, 50);

		gl.glTranslated(-0.012, 0f, -0.47f);
		glut.glutSolidSphere(0.032, 50, 50);

		gl.glTranslated(-0.25, 0f, 0.1f);
		glut.glutSolidSphere(0.032, 50, 50);

		gl.glTranslated(4.707f, -0.142f, -5.135f);

		gl.glColor3f(1, 1, 1);
		
		
		// ---------------------Cadeira 3---------------------------------

				// Costas
				createCylinder(drawable, textureChair, 0.18f, 0.18f, 0.04f, -6.27f, 0.61f, 4.25f, 90, 0, 0);
				createCylinder(drawable, textureChair, 0.18f, 0.18f, 0.04f, -6.27f, 0.61f, 4.25f, 0, 0, 0);
				// sentar
				createCylinder(drawable, textureChair, 0.21f, 0.18f, 0.04f, -6.27f, 0.35f, 4.505f, 0, 0, 90);
				createCylinder(drawable, textureChair, 0.21f, 0.18f, 0.04f, -6.27f, 0.35f, 4.505f, 0, 90, 90);
				// Apoio
				createCylinder(drawable, textureChair2, 0.03f, 0.18f, 0.03f, -6.27f, 0.48f, 4.21f, 0, 0, 0);
				createCylinder(drawable, textureChair2, 0.03f, 0.18f, 0.03f, -6.27f, 0.326f, 4.365f, 0, 0, 90);
				// Pés Centro
				createCylinder(drawable, textureChair2, 0.03f, 0.08f, 0.03f, -6.27f, 0.23f, 4.52f, 0, 0, 0);
				// Pés
				createCylinder(drawable, textureChair2, 0.025f, 0.125f, 0.025f, -6.27f, 0.15f, 4.42f, 0, 0, 90);
				createCylinder(drawable, textureChair2, 0.025f, 0.125f, 0.025f, -6.15f, 0.15f, 4.49f, 0, 72, 90);
				createCylinder(drawable, textureChair2, 0.025f, 0.125f, 0.025f, -6.2f, 0.15f, 4.61f, 0, 144, 90);
				createCylinder(drawable, textureChair2, 0.025f, 0.125f, 0.025f, -6.345f, 0.15f, 4.61f, 0, 216, 90);
				createCylinder(drawable, textureChair2, 0.025f, 0.125f, 0.025f, -6.385f, 0.15f, 4.485f, 0, 288, 90);

				// Rodinhas
				gl.glColor3f(0, 0, 0);

				gl.glTranslated(-4.29f, 0.142f, 6.27);
				glut.glutSolidSphere(0.032, 50, 50);

				gl.glTranslated(-0.42, 0f, 0.145f);
				glut.glutSolidSphere(0.032, 50, 50);

				gl.glTranslated(0.265f, 0f, 0.09f);
				glut.glutSolidSphere(0.032, 50, 50);

				gl.glTranslated(-0.012, 0f, -0.47f);
				glut.glutSolidSphere(0.032, 50, 50);

				gl.glTranslated(-0.25, 0f, 0.1f);
				glut.glutSolidSphere(0.032, 50, 50);

				gl.glTranslated(4.707f, -0.142f, -6.135f);

				gl.glColor3f(1, 1, 1);

	}

	private void createCylinder(GLAutoDrawable drawable, int texture, float width, float height, float lenght, float x,
			float y, float z, float rotateX, float rotateY, float rotateZ) {

		GL2 gl = drawable.getGL().getGL2();

		gl.glPushMatrix();
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);

		gl.glTranslatef(-z, y, -x);
		gl.glRotated(rotateX, 1, 0, 0);
		gl.glRotated(rotateY, 0, 1, 0);
		gl.glRotated(rotateZ, 0, 0, 1);
		gl.glScalef(lenght, height, width);
		gl.glBegin(GL2.GL_QUAD_STRIP);
		for (int j = 0; j <= 360; j += DEF_D) {
			gl.glTexCoord2f(1f, 0f);
			gl.glVertex3f((float) (Math.cos(j)), +1, (float) (Math.sin(j)));
			gl.glTexCoord2f(0.0f, 1.0f);
			gl.glVertex3f((float) (Math.cos(j)), -1, (float) (Math.sin(j)));
		}
		gl.glEnd();

		gl.glPopMatrix();
		gl.glFlush();

	}

	private void createTable(GLAutoDrawable drawable) {
		// mesa lab maior
		createCube(drawable, textureTable, 5.7f, 0.06f, 1, -6.1f, 0.52f, 4.8f, 0, 0, 0);
		// mesa apoio
		createCube(drawable, textureTable, 0.1f, 0.45f, 1, -7f, 0.27f, 4.8f, 0, 0, 0);
		createCube(drawable, textureTable, 0.1f, 0.45f, 1, -5.8f, 0.27f, 4.8f, 0, 0, 0);
		createCube(drawable, textureTable, 0.1f, 0.45f, 1, -4.6f, 0.27f, 4.8f, 0, 0, 0);
		createCube(drawable, textureTable, 0.1f, 0.45f, 1, -3.3f, 0.27f, 4.8f, 0, 0, 0);
		// mesa lab fundo
		createCube(drawable, textureTable, 1f, 0.06f, 2.6f, -8.5f, 0.52f, 3.2f, 0, 0, 0);
		// mesa apoio
		createCube(drawable, textureTable, 1f, 0.45f, 0.1f, -8.5f, 0.27f, 3.3f, 0, 0, 0);

	}

	private void createPcs(GLAutoDrawable drawable) {

		// PC3
		createCube(drawable, texturePC, 0.6f, 0.15f, 0.6f, -6.1f, 0.63f, 4.8f, 0, 0, 0);
		createCube(drawable, texturePCGabinete, 0.59f, 0.149f, 0.049f, -6.1f, 0.63f, 4.5f, 0, 0, 0);
		createCube(drawable, texturePC, 0.15f, 0.5f, 0.15f, -6.1f, 0.75f, 4.8f, 0, 0, 0);
		createCube(drawable, texturePCMonitor, 0.55f, 0.34f, 0.05f, -6.1f, 1f, 4.7f, 0, 0, 0);

		// PC 2
		createCube(drawable, texturePC, 0.55f, 0.15f, 0.51f, -4.8f, 0.63f, 4.8f, 0, 0, 0);
		createCube(drawable, texturePCGabinete, 0.549f, 0.149f, 0.049f, -4.8f, 0.63f, 4.55f, 0, 0, 0);
		createCube(drawable, texturePC, 0.15f, 0.5f, 0.15f, -4.8f, 0.75f, 4.9f, 0, 0, 0);
		createCube(drawable, texturePCMonitor, 0.51f, 0.38f, 0.05f, -4.8f, 1f, 4.8f, 0, 0, 0);

		// PC 1
		createCube(drawable, texturePC, 0.55f, 0.15f, 0.51f, -3.6f, 0.63f, 4.8f, 0, 0, 0);
		createCube(drawable, texturePCGabinete, 0.549f, 0.149f, 0.049f, -3.6f, 0.63f, 4.55f, 0, 0, 0);
		createCube(drawable, texturePC, 0.15f, 0.5f, 0.15f, -3.6f, 0.75f, 4.9f, 0, 0, 0);
		createCube(drawable, texturePCMonitor, 0.51f, 0.38f, 0.05f, -3.6f, 1f, 4.8f, 0, 0, 0);
		createCube(drawable, texturePCMonitor2, 0.509f, 0.379f, 0.049f, -3.6f, 1f, 4.79f, 0, 0, 0);

		// monitores
		createCube(drawable, texturePCMonitor, 0.51f, 0.38f, 0.05f, -8.5f, 0.8f, 4.8f, 0, 0, 0);
		createCube(drawable, texturePC, 0.12f, 0.3f, 0.12f, -8.5f, 0.65f, 4.87f, 0, 0, 0);
		createCube(drawable, texturePC, 0.5f, 0.02f, 0.35f, -8.5f, 0.55f, 4.8f, 0, 0, 0);

		// monitores
		createCube(drawable, texturePC, 0.05f, 0.38f, 0.51f, -8.5f, 0.8f, 3.6f, 0, 0, 0);
		createCube(drawable, texturePC, 0.12f, 0.3f, 0.12f, -8.5f, 0.65f, 3.6f, 0, 0, 0);
		createCube(drawable, texturePC, 0.35f, 0.02f, 0.5f, -8.5f, 0.55f, 3.6f, 0, 0, 0);

		// Pc diferente
		createCube(drawable, texturePCMonitor, 0.05f, 0.38f, 0.51f, -8.4f, 0.8f, 2.6f, 0, 0, 0);
		createCube(drawable, texturePC, 0.12f, 0.3f, 0.12f, -8.5f, 0.65f, 2.6f, 0, 0, 0);
		createCube(drawable, texturePC, 0.35f, 0.02f, 0.5f, -8.5f, 0.55f, 2.6f, 0, 0, 0);
		createCube(drawable, textureKeyboard, 0.19f, 0.02f, 0.5f, -8.15f, 0.55f, 2.6f, 0, 0, 0);
		createCube(drawable, texturePC, 0.37f, 0.6f, 0.2f, -8.5f, 0.8f, 2.2f, 0, 0, 0);
		createCube(drawable, texturePCGabinete2, 0.369f, 0.59f, 0.19f, -8.49f, 0.8f, 2.2f, 0, 0, 0);

		// Ar condicionado
		createAr(drawable, 1.05f, 0.25f, 0.15f, -6.4f, 1.7f, 2f);

	}

	private void createDoor(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		gl.glPushMatrix();
		gl.glTranslatef(-5.2f, 0.5f, 2f);
		gl.glRotatef(shoulder, 0.0f, -1.0f, 0.0f);
		gl.glTranslatef(5.2f, -0.5f, -2f);
		// porta
		createCube(drawable, textureDoor, 0.10f, 1.43f, 0.75f, -2f, 0.72f, 4.82f, 0, 0, 0);
		// porta vidro
		gl.glEnable(GL2.GL_BLEND);
		gl.glColor4f(1, 1, 1, 0.25f);
		createCube(drawable, textureTable, 0.05f, 0.56f, 0.8f, -2f, 1.7f, 4.8f, 0, 0, 0);
		gl.glDisable(GL2.GL_BLEND);
		// porta vidro cima
		createCube(drawable, textureTable, 0.10f, 0.07f, 0.75f, -2f, 1.95f, 4.82f, 0, 0, 0);
		// porta vidro lados
		createCube(drawable, textureTable, 0.10f, 0.5f, 0.07f, -2f, 1.68f, 4.486f, 0, 0, 0);
		createCube(drawable, textureTable, 0.10f, 0.5f, 0.07f, -2f, 1.68f, 5.17f, 0, 0, 0);
		gl.glPopMatrix();

	}

	private void createAr(GLAutoDrawable drawable, float width, float height, float lenght, float x, float y, float z) {

		GL2 gl = drawable.getGL().getGL2();

		x = -x;
		z = -z;

		// Left Face
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArEsq2);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z, -height / 2 + y + 0.07f, width / 2 + x);// frente
																				// baixo
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y + 0.07f, width / 2 + x);// atras
																				// baixo
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(lenght / 2 + z, height / 2 + y, width / 2 + x);// atras
																		// cima
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z, height / 2 + y, width / 2 + x);// frente
		gl.glEnd(); // cima

		// Left down Face
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArEsq);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z + 0.05f, -height / 2 + y, width / 2 + x);// frente
																				// baixo
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y, width / 2 + x);// atras
																		// baixo
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y + 0.07f, width / 2 + x);// atras
		// cima
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z, -height / 2 + y + 0.07f, width / 2 + x);// frente
		gl.glEnd(); // cima

		// Right face
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArDir2);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z, -height / 2 + y + 0.07f, -width / 2 + x);// frente
		// baixo
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z, height / 2 + y, -width / 2 + x);// frente
																		// cima
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(lenght / 2 + z, height / 2 + y, -width / 2 + x);// atras
																		// Cima
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y + 0.07f, -width / 2 + x);// atras
		gl.glEnd(); // baixo

		// Right face down
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArDir);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z + 0.05f, -height / 2 + y, -width / 2 + x);// frente
																				// baixo
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z, -height / 2 + y + 0.07f, -width / 2 + x);// frente
		// cima
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y + 0.07f, -width / 2 + x);// atras
		// Cima
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y, -width / 2 + x);// atras
		gl.glEnd(); // baixo

		// Top Face
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArDir2);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z, height / 2 + y, -width / 2 + x);// direita
																		// cima
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z, height / 2 + y, width / 2 + x);// esquerda
																		// cima
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(lenght / 2 + z, height / 2 + y, width / 2 + x);// esquerda
																		// baixo
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(lenght / 2 + z, height / 2 + y, -width / 2 + x);// direita
		gl.glEnd(); // baixo

		// Bottom Face
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArDir2);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z + 0.05f, -height / 2 + y, -width / 2 + x);// direita
		// cima
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y, -width / 2 + x);// direita
																		// baixo
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y, width / 2 + x);// esquerda
																		// baixo
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z + 0.05f, -height / 2 + y, width / 2 + x);// esquerda
		// cima
		gl.glEnd();

		// Front face
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y, -width / 2 + x);// direita
																		// baixo
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(lenght / 2 + z, height / 2 + y, -width / 2 + x);// direita
																		// cima
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(lenght / 2 + z, height / 2 + y, width / 2 + x);// esquerda
																		// Cima
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(lenght / 2 + z, -height / 2 + y, width / 2 + x);// esquerda
		gl.glEnd(); // baixo

		// back Face
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArFrente);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z, -height / 2 + y + 0.07f, -width / 2 + x);// direita
		// baixo
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z, -height / 2 + y + 0.07f, width / 2 + x);// esquerda
		// baixo
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z, height / 2 + y, width / 2 + x);// esquerda
																		// Cima
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z, height / 2 + y, -width / 2 + x);// direita
		gl.glEnd(); // cima

		// back low face
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArFrente2);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z + 0.05f, -height / 2 + y, -width / 2 + x);// direita
																				// baixo
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(-lenght / 2 + z + 0.05f, -height / 2 + y, width / 2 + x);// esquerda
																				// baixo
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z, -height / 2 + y + 0.07f, width / 2 + x);// esquerda
																				// Cima
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(-lenght / 2 + z, -height / 2 + y + 0.07f, -width / 2 + x);// direita
		gl.glEnd(); // cima

		gl.glFlush();

	}

	private void createCorredor(GLAutoDrawable drawable) {
		// chao do corredor
		createCube(drawable, texture, 3, 0.2f, 18, -0.5f, 0, 0, 0, 0, 0);

		// Criar Teto
		createCube(drawable, textureRoof2, 3, 0.2f, 18, -0.5f, 2.1f, 0, 0, 0, 0);

		// Parede esquerda maior
		// createCube(drawable, textureWall, 0.2f, 2, 14.5f, -2, 1, -2.8f);
		createCube(drawable, textureWindow, 0.2f, 2, 2.7f, -2, 1, 3.1f, 0, 0, 0);
		createCube(drawable, textureCorredor, 0.2f, 2, 4f, -2, 1, -0.2f, 0, 0, 0);
		createCube(drawable, textureCorredor, 0.2f, 2, 4f, -2, 1, -3.5f, 0, 0, 0);
		createCube(drawable, textureCorredor, 0.2f, 2, 4f, -2, 1, -6.8f, 0, 0, 0);

		// Parede esquerda menor
		createCube(drawable, textureWall, 0.2f, 2, 4.7f, -2, 1, 7.6f, 0, 0, 0);

		// Parede direita
		createCube(drawable, textureCorredor, 0.2f, 2, 4f, 1, 1, 3.1f, 0, 0, 0);
		createCube(drawable, textureCorredor, 0.2f, 2, 4f, 1, 1, -0.2f, 0, 0, 0);
		createCube(drawable, textureCorredor, 0.2f, 2, 4f, 1, 1, -3.5f, 0, 0, 0);
		createCube(drawable, textureCorredor, 0.2f, 2, 4f, 1, 1, -6.8f, 0, 0, 0);
		createCube(drawable, textureCorredor, 0.2f, 2, 4f, 1, 1, 6.4f, 0, 0, 0);

		// Parede direita menor
		createCube(drawable, textureWall, 0.2f, 2, 3f, 1, 1, 8.5f, 0, 0, 0);

		// fundo
		createCube(drawable, textureFundo, 3f, 2, 0.2f, -0.5f, 1, 9f, 0, 0, 0);

		// Frente
		createCube(drawable, textureWall, 3f, 2, 0.2f, -0.5f, 1, -8.8f, 0, 0, 0);

	}
}
