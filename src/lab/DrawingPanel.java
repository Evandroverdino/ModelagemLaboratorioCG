package lab;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public class DrawingPanel implements GLEventListener, KeyListener {

	private static final float PI = 3.1415f;
	private static int shoulder = 0;
	private int DEF_D = 5;

	private GLUT glut = new GLUT();
	private GLU glu = new GLU();
	private LabTexture labTexture;

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

		aimCamera(gl, glu);
		moveCamera();

		createHall(drawable);
		createLab(drawable);

		// Iluminação
		init_lighting(gl);

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
		loadTextures(drawable);
		gl.glEnable(GL2.GL_DEPTH_TEST);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();
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
			cameraSpeed = 0.1F;
		}

		if (event.getKeyCode() == KeyEvent.VK_O) {
			cameraSpeed = -0.1F;
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
	
	public void loadTextures(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		labTexture = new LabTexture(gl);
	}
	
	public void init_lighting(GL2 gl) {
		float luzAmbiente[] = { 0.2f, 0.2f, 0.2f, 1.0f };
		float luzDifusa[] = { 0.7f, 0.7f, 0.7f, 1.0f }; // "cor"
		float luzEspecular[] = { 1.0f, 1.0f, 1.0f, 1.0f };// "brilho"
		float posicaoLuz[] = { -3.5f, 2f, 7.5f, 1.0f };
		// float posicaoLuz2[] = { -3.5f, 2f, 5.2f, 1.0f };
		float posicaoLuz3[] = { -3.5f, 2f, 3.2f, 1.0f };
		float especularidade[] = { 1, 1, 1, 1 };
		int especMaterial = 60;

		// Habilita o uso de iluminação
		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		// Define a refletância do material
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, especularidade, 1);
		// Define a concentração do brilho
		gl.glMateriali(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, especMaterial);
		// gl.glLightModeli(GL2.GL_LIGHT_MODEL_LOCAL_VIEWER, GL2.GL_TRUE);

		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, posicaoLuz, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, luzAmbiente, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, luzDifusa, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, luzEspecular, 0);

		// gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_POSITION, posicaoLuz2, 0);
		// gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_AMBIENT, luzAmbiente, 0);
		// gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_DIFFUSE, luzDifusa, 0);
		// gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_SPECULAR, luzEspecular, 0);

		gl.glLightfv(GL2.GL_LIGHT3, GL2.GL_POSITION, posicaoLuz3, 0);
		gl.glLightfv(GL2.GL_LIGHT3, GL2.GL_AMBIENT, luzAmbiente, 0);
		gl.glLightfv(GL2.GL_LIGHT3, GL2.GL_DIFFUSE, luzDifusa, 0);
		gl.glLightfv(GL2.GL_LIGHT3, GL2.GL_SPECULAR, luzEspecular, 0);

		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT1);
		// gl.glEnable(GL2.GL_LIGHT2);
		gl.glEnable(GL2.GL_LIGHT3);

		// Reflexo vidro
		gl.glEnable(GL2.GL_BLEND);
		gl.glEnable(GL2.GL_NORMALIZE);
		gl.glEnable(GL2.GL_DEPTH_TEST);
	}

	private void createHall(GLAutoDrawable drawable) {
		// chao do corredor
		createCube(drawable, labTexture.getTextureFloor(), 3, 0.2f, 18, -0.5f, 0, 0, 0, 0, 0);

		// Criar Teto
		createCube(drawable, labTexture.getTextureRoof2(), 3, 0.2f, 18, -0.5f, 2.1f, 0, 0, 0, 0);

		// Parede esquerda maior
		createCube(drawable, labTexture.getTextureWindow(), 0.2f, 2, 2.7f, -2, 1, 3.1f, 0, 0, 0);
		createCube(drawable, labTexture.getTextureHall(), 0.2f, 2, 4f, -2, 1, -0.2f, 0, 0, 0);
		createCube(drawable, labTexture.getTextureHall(), 0.2f, 2, 4f, -2, 1, -3.5f, 0, 0, 0);
		createCube(drawable, labTexture.getTextureHall(), 0.2f, 2, 4f, -2, 1, -6.8f, 0, 0, 0);

		// Parede esquerda menor
		createCube(drawable, labTexture.getTextureHall(), 0.2f, 2, 4.7f, -2, 1, 7.6f, 0, 0, 0);

		// Parede direita
		createCube(drawable, labTexture.getTextureHall(), 0.2f, 2, 4f, 1, 1, 3.1f, 0, 0, 0);
		createCube(drawable, labTexture.getTextureHall(), 0.2f, 2, 4f, 1, 1, -0.2f, 0, 0, 0);
		createCube(drawable, labTexture.getTextureHall(), 0.2f, 2, 4f, 1, 1, -3.5f, 0, 0, 0);
		createCube(drawable, labTexture.getTextureHall(), 0.2f, 2, 4f, 1, 1, -6.8f, 0, 0, 0);
		createCube(drawable, labTexture.getTextureHall(), 0.2f, 2, 4f, 1, 1, 6.4f, 0, 0, 0);

		// Parede direita menor
		createCube(drawable, labTexture.getTextureWall(), 0.2f, 2, 3f, 1, 1, 8.5f, 0, 0, 0);

		// fundo
		createCube(drawable, labTexture.getTextureBottom(), 3f, 2, 0.2f, -0.5f, 1, 9f, 0, 0, 0);

		// Frente
		createCube(drawable, labTexture.getTextureWall(), 3f, 2, 0.2f, -0.5f, 1, -8.8f, 0, 0, 0);
	}
	
	private void createLab(GLAutoDrawable drawable) {
		// chao do laboratorio
		createCube(drawable, labTexture.getTextureMain(), 8f, 0.2f, 3.5f, -5f, -0.0001f, 3.6f, 0, 0, 0);
		// teto do laboratorio
		createCube(drawable, labTexture.getTextureRoof(), 8f, 0.2f, 3.5f, -5f, 2.11f, 3.6f, 0, 0, 0);

		// Parede lab fundo
		createCube(drawable, labTexture.getTextureWall(), 0.2f, 1f, 3.5f, -9f, 0.5f, 3.6f, 0, 0, 0);// meia parede
		createCube(drawable, labTexture.getTextureWall(), 0.3f, 2f, 0.25f, -8.8f, 1f, 2f, 0, 0, 0);// Borda Parede
		createCube(drawable, labTexture.getTextureWindowLado(), 0.051f, 0.08f, 3.5f, -9f, 1.65f, 3.6f, 0, 0, 0);// Janela
																												// deitada
		createCube(drawable, labTexture.getTextureWindowEmPe(), 0.05f, 2f, 0.06f, -9f, 1.1f, 2.7f, 0, 0, 0);// Janela em
																											// pé
		createCube(drawable, labTexture.getTextureWindowEmPe(), 0.05f, 2f, 0.06f, -9f, 1.1f, 3.2f, 0, 0, 0);
		createCube(drawable, labTexture.getTextureWindowEmPe(), 0.05f, 2f, 0.06f, -9f, 1.1f, 3.7f, 0, 0, 0);
		createCube(drawable, labTexture.getTextureWindowEmPe(), 0.05f, 2f, 0.06f, -9f, 1.1f, 4.2f, 0, 0, 0);
		createCube(drawable, labTexture.getTextureWindowEmPe(), 0.05f, 2f, 0.06f, -9f, 1.1f, 4.7f, 0, 0, 0);

		// Parede lab esq
		createCube(drawable, labTexture.getTextureWall(), 7, 2, 0.2f, -5.5f, 1, 1.83f, 0, 0, 0);

		// parede lab dir
		createCube(drawable, labTexture.getTextureWall(), 7, 2, 0.2f, -5.5f, 1, 5.3f, 0, 0, 0);

		// Quadro Branco Lab
		createCube(drawable, labTexture.getTextureWhiteBoard(), 2.1f, 1.1f, 0.05f, -4.3f, 1.2f, 2f, 0, 0, 0);
		createCube(drawable, labTexture.getTextureEdge(), 2.15f, 1.15f, 0.05f, -4.3f, 1.2f, 1.98f, 0, 0, 0);
		createCube(drawable, labTexture.getTextureEdge(), 0.5f, 0.03f, 0.15f, -4.3f, 0.67f, 2.1f, 0, 0, 0);

		createTable(drawable);
		createDoor(drawable);
		createPcs(drawable);
		createChairs(drawable);
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

		// ---------Textura------

		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
		gl.glColor3f(1f, 1f, 1f);
		// gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER,
		// GL2.GL_LINEAR_MIPMAP_NEAREST);
		// gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER,
		// GL2.GL_LINEAR_MIPMAP_LINEAR);
		// gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
		// gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
		//
		gl.glBegin(GL2.GL_QUADS);

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
		gl.glDisable(GL2.GL_TEXTURE_2D);
		gl.glEnd();
		gl.glPopMatrix();
		gl.glFlush();
	}

	private void createCube2(GLAutoDrawable drawable, int texture, float width, float height, float lenght, float x,
			float y, float z, float rotateX, float rotateY, float rotateZ) {

		GL2 gl = drawable.getGL().getGL2();

		gl.glPushMatrix();

		gl.glTranslatef(-z, y, -x);
		gl.glRotated(rotateX, 1, 0, 0);
		gl.glRotated(rotateY, 0, 1, 0);
		gl.glRotated(rotateZ, 0, 0, 1);

		gl.glScalef(lenght, height, width);
		gl.glColor3f(0.6f, 0.6f, 0.6f);
		glut.glutSolidCube(1f);
		gl.glScalef(0, 0, 0);

		gl.glTranslatef(z, -y, x);

		gl.glPopMatrix();
		gl.glFlush();
	}

	private void createChairs(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		gl.glPushMatrix();
		// ---------------------Cadeira 1---------------------------------

		// Costas
		createCylinder(drawable, labTexture.getTextureChair(), 0.18f, 0.18f, 0.04f, -3.77f, 0.61f, 3.25f, 90, 0, 0);
		createCylinder(drawable, labTexture.getTextureChair(), 0.18f, 0.18f, 0.04f, -3.77f, 0.61f, 3.25f, 0, 0, 0);
		// sentar
		createCylinder(drawable, labTexture.getTextureChair(), 0.21f, 0.18f, 0.04f, -3.77f, 0.35f, 3.505f, 0, 0, 90);
		createCylinder(drawable, labTexture.getTextureChair(), 0.21f, 0.18f, 0.04f, -3.77f, 0.35f, 3.505f, 0, 90, 90);
		// Apoio
		createCylinder(drawable, labTexture.getTextureChair2(), 0.03f, 0.18f, 0.03f, -3.77f, 0.48f, 3.21f, 0, 0, 0);
		createCylinder(drawable, labTexture.getTextureChair2(), 0.03f, 0.18f, 0.03f, -3.77f, 0.326f, 3.365f, 0, 0, 90);
		// Pés Centro
		createCylinder(drawable, labTexture.getTextureChair2(), 0.03f, 0.08f, 0.03f, -3.77f, 0.23f, 3.52f, 0, 0, 0);
		// Pés
		createCylinder(drawable, labTexture.getTextureChair2(), 0.025f, 0.125f, 0.025f, -3.77f, 0.15f, 3.42f, 0, 0, 90);
		createCylinder(drawable, labTexture.getTextureChair2(), 0.025f, 0.125f, 0.025f, -3.65f, 0.15f, 3.49f, 0, 72,
				90);
		createCylinder(drawable, labTexture.getTextureChair2(), 0.025f, 0.125f, 0.025f, -3.7f, 0.15f, 3.61f, 0, 144,
				90);
		createCylinder(drawable, labTexture.getTextureChair2(), 0.025f, 0.125f, 0.025f, -3.845f, 0.15f, 3.61f, 0, 216,
				90);
		createCylinder(drawable, labTexture.getTextureChair2(), 0.025f, 0.125f, 0.025f, -3.885f, 0.15f, 3.485f, 0, 288,
				90);

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
		gl.glPopMatrix();

		gl.glPushMatrix();
		// ---------------------Cadeira 2---------------------------------

		// Costas
		createCylinder(drawable, labTexture.getTextureChair(), 0.18f, 0.18f, 0.04f, -5.27f, 0.61f, 4.25f, 90, 0, 0);
		createCylinder(drawable, labTexture.getTextureChair(), 0.18f, 0.18f, 0.04f, -5.27f, 0.61f, 4.25f, 0, 0, 0);
		// sentar
		createCylinder(drawable, labTexture.getTextureChair(), 0.21f, 0.18f, 0.04f, -5.27f, 0.35f, 4.505f, 0, 0, 90);
		createCylinder(drawable, labTexture.getTextureChair(), 0.21f, 0.18f, 0.04f, -5.27f, 0.35f, 4.505f, 0, 90, 90);
		// Apoio
		createCylinder(drawable, labTexture.getTextureChair2(), 0.03f, 0.18f, 0.03f, -5.27f, 0.48f, 4.21f, 0, 0, 0);
		createCylinder(drawable, labTexture.getTextureChair2(), 0.03f, 0.18f, 0.03f, -5.27f, 0.326f, 4.365f, 0, 0, 90);
		// Pés Centro
		createCylinder(drawable, labTexture.getTextureChair2(), 0.03f, 0.08f, 0.03f, -5.27f, 0.23f, 4.52f, 0, 0, 0);
		// Pés
		createCylinder(drawable, labTexture.getTextureChair2(), 0.025f, 0.125f, 0.025f, -5.27f, 0.15f, 4.42f, 0, 0, 90);
		createCylinder(drawable, labTexture.getTextureChair2(), 0.025f, 0.125f, 0.025f, -5.15f, 0.15f, 4.49f, 0, 72,
				90);
		createCylinder(drawable, labTexture.getTextureChair2(), 0.025f, 0.125f, 0.025f, -5.2f, 0.15f, 4.61f, 0, 144,
				90);
		createCylinder(drawable, labTexture.getTextureChair2(), 0.025f, 0.125f, 0.025f, -5.345f, 0.15f, 4.61f, 0, 216,
				90);
		createCylinder(drawable, labTexture.getTextureChair2(), 0.025f, 0.125f, 0.025f, -5.385f, 0.15f, 4.485f, 0, 288,
				90);

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
		gl.glPopMatrix();

		gl.glPushMatrix();
		// ---------------------Cadeira 3---------------------------------

		// Costas
		createCylinder(drawable, labTexture.getTextureChair(), 0.18f, 0.18f, 0.04f, -6.27f, 0.61f, 4.25f, 90, 0, 0);
		createCylinder(drawable, labTexture.getTextureChair(), 0.18f, 0.18f, 0.04f, -6.27f, 0.61f, 4.25f, 0, 0, 0);
		// sentar
		createCylinder(drawable, labTexture.getTextureChair(), 0.21f, 0.18f, 0.04f, -6.27f, 0.35f, 4.505f, 0, 0, 90);
		createCylinder(drawable, labTexture.getTextureChair(), 0.21f, 0.18f, 0.04f, -6.27f, 0.35f, 4.505f, 0, 90, 90);
		// Apoio
		createCylinder(drawable, labTexture.getTextureChair2(), 0.03f, 0.18f, 0.03f, -6.27f, 0.48f, 4.21f, 0, 0, 0);
		createCylinder(drawable, labTexture.getTextureChair2(), 0.03f, 0.18f, 0.03f, -6.27f, 0.326f, 4.365f, 0, 0, 90);
		// Pés Centro
		createCylinder(drawable, labTexture.getTextureChair2(), 0.03f, 0.08f, 0.03f, -6.27f, 0.23f, 4.52f, 0, 0, 0);
		// Pés
		createCylinder(drawable, labTexture.getTextureChair2(), 0.025f, 0.125f, 0.025f, -6.27f, 0.15f, 4.42f, 0, 0, 90);
		createCylinder(drawable, labTexture.getTextureChair2(), 0.025f, 0.125f, 0.025f, -6.15f, 0.15f, 4.49f, 0, 72,
				90);
		createCylinder(drawable, labTexture.getTextureChair2(), 0.025f, 0.125f, 0.025f, -6.2f, 0.15f, 4.61f, 0, 144,
				90);
		createCylinder(drawable, labTexture.getTextureChair2(), 0.025f, 0.125f, 0.025f, -6.345f, 0.15f, 4.61f, 0, 216,
				90);
		createCylinder(drawable, labTexture.getTextureChair2(), 0.025f, 0.125f, 0.025f, -6.385f, 0.15f, 4.485f, 0, 288,
				90);

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
		gl.glPopMatrix();
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
		createCube2(drawable, labTexture.getTextureTable(), 5.7f, 0.06f, 1, -6.1f, 0.52f, 4.8f, 0, 0, 0);
		// mesa apoio
		createCube2(drawable, labTexture.getTextureTable(), 0.1f, 0.45f, 1, -7f, 0.27f, 4.8f, 0, 0, 0);
		createCube2(drawable, labTexture.getTextureTable(), 0.1f, 0.45f, 1, -5.8f, 0.27f, 4.8f, 0, 0, 0);
		createCube2(drawable, labTexture.getTextureTable(), 0.1f, 0.45f, 1, -4.6f, 0.27f, 4.8f, 0, 0, 0);
		createCube2(drawable, labTexture.getTextureTable(), 0.1f, 0.45f, 1, -3.3f, 0.27f, 4.8f, 0, 0, 0);
		// mesa lab fundo
		createCube2(drawable, labTexture.getTextureTable(), 1f, 0.06f, 2.6f, -8.5f, 0.519f, 3.2f, 0, 0, 0);
		// mesa apoio
		createCube2(drawable, labTexture.getTextureTable(), 1f, 0.45f, 0.1f, -8.5f, 0.27f, 3.3f, 0, 0, 0);

	}

	private void createPcs(GLAutoDrawable drawable) {

		// PC3
		createCube(drawable, labTexture.getTexturePC(), 0.6f, 0.15f, 0.6f, -6.1f, 0.63f, 4.8f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePCCabinet(), 0.59f, 0.149f, 0.049f, -6.1f, 0.63f, 4.5f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePC(), 0.15f, 0.5f, 0.15f, -6.1f, 0.75f, 4.8f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePCMonitor(), 0.55f, 0.34f, 0.05f, -6.1f, 1f, 4.7f, 0, 0, 0);

		// PC 2
		createCube(drawable, labTexture.getTexturePC(), 0.55f, 0.15f, 0.51f, -4.8f, 0.63f, 4.8f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePCCabinet(), 0.549f, 0.149f, 0.049f, -4.8f, 0.63f, 4.55f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePC(), 0.15f, 0.5f, 0.15f, -4.8f, 0.75f, 4.9f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePCMonitor(), 0.51f, 0.38f, 0.05f, -4.8f, 1f, 4.8f, 0, 0, 0);

		// PC 1
		createCube(drawable, labTexture.getTexturePC(), 0.55f, 0.15f, 0.51f, -3.6f, 0.63f, 4.8f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePCCabinet(), 0.549f, 0.149f, 0.049f, -3.6f, 0.63f, 4.55f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePC(), 0.15f, 0.5f, 0.15f, -3.6f, 0.75f, 4.9f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePCMonitor(), 0.51f, 0.38f, 0.05f, -3.6f, 1f, 4.8f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePCMonitor2(), 0.509f, 0.379f, 0.049f, -3.6f, 1f, 4.79f, 0, 0, 0);

		// monitores
		createCube(drawable, labTexture.getTexturePCMonitor(), 0.51f, 0.38f, 0.05f, -8.5f, 0.8f, 4.8f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePC(), 0.12f, 0.3f, 0.12f, -8.5f, 0.65f, 4.87f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePC(), 0.5f, 0.02f, 0.35f, -8.5f, 0.55f, 4.8f, 0, 0, 0);

		// monitores
		createCube(drawable, labTexture.getTexturePC(), 0.05f, 0.38f, 0.51f, -8.5f, 0.8f, 3.6f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePC(), 0.12f, 0.3f, 0.12f, -8.5f, 0.65f, 3.6f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePC(), 0.35f, 0.02f, 0.5f, -8.5f, 0.55f, 3.6f, 0, 0, 0);

		// Pc diferente
		createCube(drawable, labTexture.getTexturePCMonitor(), 0.05f, 0.38f, 0.51f, -8.4f, 0.8f, 2.6f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePC(), 0.12f, 0.3f, 0.12f, -8.5f, 0.65f, 2.6f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePC(), 0.35f, 0.02f, 0.5f, -8.5f, 0.55f, 2.6f, 0, 0, 0);
		createCube(drawable, labTexture.getTextureKeyboard(), 0.19f, 0.02f, 0.5f, -8.15f, 0.55f, 2.6f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePC(), 0.37f, 0.6f, 0.2f, -8.4f, 0.8f, 2.2f, 0, 0, 0);
		createCube(drawable, labTexture.getTexturePCCabinet2(), 0.369f, 0.59f, 0.19f, -8.39f, 0.8f, 2.2f, 0, 0, 0);

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
		createCube(drawable, labTexture.getTextureDoor(), 0.10f, 1.43f, 0.75f, -2f, 0.72f, 4.82f, 0, 0, 0);
		gl.glPopMatrix();
		// porta vidro
		gl.glEnable(GL2.GL_BLEND);
		gl.glColor4f(1, 1, 1, 0.2f);
		createCube2(drawable, labTexture.getTextureTable(), 0.05f, 0.56f, 0.8f, -2f, 1.75f, 4.8f, 0, 0, 0);
		gl.glDisable(GL2.GL_BLEND);
		// porta vidro cima
		createCube(drawable, labTexture.getTextureTable(), 0.10f, 0.07f, 0.75f, -2f, 1.95f, 4.82f, 0, 0, 0);
		// porta vidro baixo
		createCube(drawable, labTexture.getTextureTable(), 0.10f, 0.07f, 0.75f, -2f, 1.47f, 4.82f, 0, 0, 0);
		// porta vidro lados
		createCube(drawable, labTexture.getTextureTable(), 0.10f, 0.5f, 0.07f, -2f, 1.68f, 4.486f, 0, 0, 0);
		createCube(drawable, labTexture.getTextureTable(), 0.10f, 0.5f, 0.07f, -2f, 1.68f, 5.17f, 0, 0, 0);

	}

	private void createAr(GLAutoDrawable drawable, float width, float height, float lenght, float x, float y, float z) {

		GL2 gl = drawable.getGL().getGL2();

		x = -x;
		z = -z;

		// Left Face
		gl.glBindTexture(GL2.GL_TEXTURE_2D, labTexture.getTextureAirLeft2());
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
		gl.glBindTexture(GL2.GL_TEXTURE_2D, labTexture.getTextureAirLeft());
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
		gl.glBindTexture(GL2.GL_TEXTURE_2D, labTexture.getTextureAirRight2());
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
		gl.glBindTexture(GL2.GL_TEXTURE_2D, labTexture.getTextureAirRight());
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
		gl.glBindTexture(GL2.GL_TEXTURE_2D, labTexture.getTextureAirRight2());
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
		gl.glBindTexture(GL2.GL_TEXTURE_2D, labTexture.getTextureAirRight2());
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
		gl.glBindTexture(GL2.GL_TEXTURE_2D, labTexture.getTextureAirFront());
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
		gl.glBindTexture(GL2.GL_TEXTURE_2D, labTexture.getTextureAirFront2());
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
}
