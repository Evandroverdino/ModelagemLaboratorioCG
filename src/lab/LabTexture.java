package lab;

import java.io.File;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class LabTexture {

	private Texture texture;

	private int textureMain;
	private int textureFloor;
	private int textureWall;
	private int textureTable;
	private int textureDoor;
	private int textureWindow;

	private int textureWindow2;
	private int textureWhiteBoard;
	private int textureEdge;
	private int textureHall;
	private int textureRoof;
	private int textureRoof2;

	private int texturePC;
	private int texturePCMonitor;
	private int texturePCCabinet;
	private int texturePCCabinet2;
	private int texturePCMonitor2;
	private int textureKeyboard;
	private int textureBottom;
	private int textureFront;

	private int textureAirFront;
	private int textureAirRight;
	private int textureAirRight2;
	private int textureAirLeft;
	private int textureAirLeft2;
	private int textureAirFront2;
	private int textureChair;
	private int textureChair2;

	private int textureWindowLado;
	private int textureWindowEmPe;
	
	private int textureCup;

	public LabTexture(GL2 gl) {
		try {

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/piso2.jpg").getPath()), true);
			textureMain = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/parede3.jpg").getPath()), true);
			textureWall = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/mesa2.jpg").getPath()), true);
			textureTable = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/porta.jpg").getPath()), true);
			textureDoor = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/janelaFrente.jpg").getPath()), true);
			textureWindow = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/quadro.jpg").getPath()), true);
			textureWhiteBoard = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/janelaTras.jpg").getPath()), true);
			textureWindow2 = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/bordas.jpg").getPath()), true);
			textureEdge = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/corredor.jpg").getPath()), true);
			textureHall = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/teto.jpg").getPath()), true);
			textureRoof = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/tetoCorredor.jpg").getPath()), true);
			textureRoof2 = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/preto.jpg").getPath()), true);
			texturePC = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/monitor.jpg").getPath()), true);
			texturePCMonitor = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/gabinete.png").getPath()), true);
			texturePCCabinet = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/monitor2.jpg").getPath()), true);
			texturePCMonitor2 = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/gabinete2.png").getPath()), true);
			texturePCCabinet2 = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/keyboard.png").getPath()), true);
			textureKeyboard = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/fundo.jpg").getPath()), true);
			textureBottom = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/frente.jpg").getPath()), true);
			textureFront = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/arFrente.jpg").getPath()), true);
			textureAirFront = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/arFrente2.jpg").getPath()), true);
			textureAirFront2 = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/arEsq.jpg").getPath()), true);
			textureAirLeft = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/arEsq2.jpg").getPath()), true);
			textureAirLeft2 = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/arDir.jpg").getPath()), true);
			textureAirRight = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/arDir2.jpg").getPath()), true);
			textureAirRight2 = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/cadeira.jpg").getPath()), true);
			textureChair = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/cadeira2.jpg").getPath()), true);
			textureChair2 = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/piso3.jpg").getPath()), true);
			textureFloor = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/janelaLado.png").getPath()), true);
			textureWindowLado = texture.getTextureObject(gl);

			texture = TextureIO.newTexture(new File(getClass().getResource("/img/janelaEmPe.png").getPath()), true);
			textureWindowEmPe = texture.getTextureObject(gl);
			
			texture = TextureIO.newTexture(new File(getClass().getResource("/img/integralle.jpg").getPath()), true);
			textureCup = texture.getTextureObject(gl);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getTextureMain() {
		return textureMain;
	}

	public int getTextureFloor() {
		return textureFloor;
	}

	public int getTextureWall() {
		return textureWall;
	}

	public int getTextureTable() {
		return textureTable;
	}

	public int getTextureDoor() {
		return textureDoor;
	}

	public int getTextureWindow() {
		return textureWindow;
	}

	public int getTextureWindow2() {
		return textureWindow2;
	}

	public int getTextureWhiteBoard() {
		return textureWhiteBoard;
	}

	public int getTextureEdge() {
		return textureEdge;
	}

	public int getTextureHall() {
		return textureHall;
	}

	public int getTextureRoof() {
		return textureRoof;
	}

	public int getTextureRoof2() {
		return textureRoof2;
	}

	public int getTexturePC() {
		return texturePC;
	}

	public int getTexturePCMonitor() {
		return texturePCMonitor;
	}

	public int getTexturePCCabinet() {
		return texturePCCabinet;
	}

	public int getTexturePCCabinet2() {
		return texturePCCabinet2;
	}

	public int getTexturePCMonitor2() {
		return texturePCMonitor2;
	}

	public int getTextureKeyboard() {
		return textureKeyboard;
	}

	public int getTextureBottom() {
		return textureBottom;
	}

	public int getTextureFront() {
		return textureFront;
	}

	public int getTextureAirFront() {
		return textureAirFront;
	}

	public int getTextureAirRight() {
		return textureAirRight;
	}

	public int getTextureAirRight2() {
		return textureAirRight2;
	}

	public int getTextureAirLeft() {
		return textureAirLeft;
	}

	public int getTextureAirLeft2() {
		return textureAirLeft2;
	}

	public int getTextureAirFront2() {
		return textureAirFront2;
	}

	public int getTextureChair() {
		return textureChair;
	}

	public int getTextureChair2() {
		return textureChair2;
	}

	public int getTextureWindowLado() {
		return textureWindowLado;
	}

	public int getTextureWindowEmPe() {
		return textureWindowEmPe;
	}

	public int getTextureCup() {
		return textureCup;
	}

}
