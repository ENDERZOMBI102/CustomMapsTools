package com.enderzombi102.cmt

import com.enderzombi102.cmt.exception.MissingKeyException
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import org.apache.commons.io.IOUtils
import org.quiltmc.loader.api.QuiltLoader
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.IOException
import java.io.InputStream
import java.nio.ByteBuffer
import javax.imageio.ImageIO

object Utils {
	val isKubeJsPresent = QuiltLoader.isModLoaded( "kubejs" )

	@JvmStatic
	@Throws(MissingKeyException::class)
	fun <E> checkMissingKey( obj: E?, message: String ): E {
		if ( obj == null )
			throw MissingKeyException( message )
		return obj
	}

	// useless methods?
	@Throws(IOException::class)
	private fun readImageToBuffer( stream: InputStream ): ByteBuffer {
		val bufferedImage = ImageIO.read( stream )
		IOUtils.closeQuietly( stream )
		return toByteBuffer( bufferedImage )
	}

	private fun resizeImage(src: BufferedImage, newPx: Int): BufferedImage {
		// if the given image is already the requested dimensions, return it
		if (src.getHeight(null) == newPx && src.getWidth(null) == newPx) return src
		// create new BufferedImage with the requested dimensions
		val img = BufferedImage(newPx, newPx, BufferedImage.TYPE_INT_ARGB)
		// get scaled image
		val tmp = src.getScaledInstance(newPx, newPx, Image.SCALE_DEFAULT)
		// get the BufferedImage graphic writer
		val bGr = img.createGraphics()
		// draw the scaled image
		bGr.drawImage(tmp, 0, 0, null)
		bGr.dispose()
		// return result
		return img
	}

	private fun resizeImage(src: Image, newPx: Int): Image {
		// if the given image is already the requested dimensions, return it
		return if (src.getHeight(null) == newPx && src.getWidth(null) == newPx) src else src.getScaledInstance(
			newPx,
			newPx,
			Image.SCALE_DEFAULT
		)
		// scale the image and return it
	}

	private fun toByteBuffer(src: BufferedImage): ByteBuffer {
		val aint = src.getRGB(0, 0, src.width, src.height, null, 0, src.width)
		// allocate the required space
		val buf = ByteBuffer.allocate(4 * aint.size)
		// add everything
		for (i in aint) {
			buf.putInt(i shl 8 or (i shr 24 and 255))
		}
		buf.flip()
		return buf
	}

	fun vecFrom(pos: BlockPos): Vec3d {
		return Vec3d(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
	}
}
