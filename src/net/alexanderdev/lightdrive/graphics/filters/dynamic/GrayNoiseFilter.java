/***********************************************************
 *   _     _       _       _   ____        _               *
 *  | |   |_|     | |     | | |  _ \      |_|              *
 *  | |    _  ___ | |__  _| |_| | | | ____ _ _   _  ___    *
 *  | |   | |/ _ \|  _ \|_   _| | | |/ ___| | \ / |/ _ \   *
 *  | |___| | |_| | | | | | | | |_| | |   | |\ V /|  ___|  *
 *  |_____|_|\__  |_| |_| |_| |____/|_|   |_| \_/  \___|   *
 *   _____   ___| |  ___________________________________   *
 *  |_____| |____/  |_________JAVA_GAME_LIBRARY_________|  *
 *                                                         *
 *                                                         *
 *  COPYRIGHT © 2015, Christian Bryce Alexander            *
 ***********************************************************/
package net.alexanderdev.lightdrive.graphics.filters.dynamic;

import net.alexanderdev.lightdrive.graphics.filters.FilterS;
import net.alexanderdev.lightdrive.util.Pixel;
import net.alexanderdev.lightdrive.util.math.MathS;

/**
 * @author Christian Bryce Alexander
 * @since Jan 4, 2016, 10:15:33 PM
 */
public class GrayNoiseFilter implements FilterS {
	private float intensity;

	public GrayNoiseFilter(float intensity) {
		this.intensity = intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			float[] argb = Pixel.splitFloatARGB(pixels[i]);

			float v = MathS.clamp(MathS.randomFloat(), 1 - intensity, 1);

			argb[1] *= v;
			argb[2] *= v;
			argb[3] *= v;

			pixels[i] = Pixel.mergeARGB(argb);
		}
	}
}