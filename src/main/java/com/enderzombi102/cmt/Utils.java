package com.enderzombi102.cmt;

import com.enderzombi102.cmt.exception.MissingKeyException;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class Utils {

	public static final boolean isKubeJsPresent = FabricLoader.getInstance().isModLoaded("kubejs");

	public static <E> E checkMissingKey(E object, String message) throws MissingKeyException {
		if ( object == null ) throw new MissingKeyException(message);
		return object;
	}


	// useless methods?
	private static ByteBuffer readImageToBuffer(InputStream stream) throws IOException {

		BufferedImage bufferedImage = ImageIO.read(stream);
		IOUtils.closeQuietly(stream);
		return toByteBuffer(bufferedImage);
	}

	private static BufferedImage resizeImage(BufferedImage src, int newPx) {
		// if the given image is already the requested dimensions, return it
		if ( src.getHeight(null) == newPx && src.getWidth(null) == newPx ) return src;
		// create new BufferedImage with the requested dimensions
		BufferedImage img = new BufferedImage(newPx, newPx, BufferedImage.TYPE_INT_ARGB);
		// get scaled image
		Image tmp = src.getScaledInstance(newPx, newPx, Image.SCALE_DEFAULT);
		// get the BufferedImage graphic writer
		Graphics2D bGr = img.createGraphics();
		// draw the scaled image
		bGr.drawImage(tmp, 0, 0, null);
		bGr.dispose();
		// return result
		return img;
	}

	private static Image resizeImage(Image src, int newPx) {
		// if the given image is already the requested dimensions, return it
		if ( src.getHeight(null) == newPx && src.getWidth(null) == newPx ) return src;
		// scale the image and return it
		return src.getScaledInstance(newPx, newPx, Image.SCALE_DEFAULT);
	}


	private static ByteBuffer toByteBuffer(BufferedImage src) {
		int[] aint = src.getRGB( 0, 0, src.getWidth(), src.getHeight(), null, 0, src.getWidth() );
		// allocate the required space
		ByteBuffer buf = ByteBuffer.allocate(4 * aint.length);
		// add everything
		for (int i : aint) {
			buf.putInt(i << 8 | i >> 24 & 255);
		}

		buf.flip();
		return buf;
	}

	public static Vec3d vecFrom(BlockPos pos) {
		return new Vec3d( pos.getX(), pos.getY(), pos.getZ() );
	}

}
