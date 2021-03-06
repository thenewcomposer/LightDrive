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
 *  COPYRIGHT � 2015, Christian Bryce Alexander            *
 ***********************************************************/
package net.alexanderdev.lightdrive.input.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import net.alexanderdev.lightdrive.InternalMethod;

/**
 * A class which encapsulates the {@link KeyListener} interface, and enables
 * more game-friendly functionality.
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015, 2:47:47 AM
 */
public class Keyboard implements KeyListener {
	private final boolean[] KEYS = new boolean[Key.values()[Key.values().length - 1].code];

	private final int[] KEY_LOCATIONS = new int[KEYS.length];

	private boolean[] keysLast;

	private int lastKeyCode;

	/**
	 * Creates a standard {@link Keyboard}.
	 */
	public Keyboard() {
		for (int i = 0; i < KEYS.length; i++) {
			KEYS[i] = false;

			KEY_LOCATIONS[i] = KeyEvent.KEY_LOCATION_UNKNOWN;
		}

		keysLast = KEYS.clone();

		lastKeyCode = -1;
	}

	/**
	 * @param key
	 *            The keyboard key to check
	 * @return {@code true} if the key associated with the specified {@link Key}
	 *         's ordinal has been pressed, {@code false} otherwise
	 */
	public boolean pressed(Key key) {
		return KEYS[key.code] && !keysLast[key.code];
	}

	/**
	 * @return {@code true} if any key has been pressed, {@code false} otherwise
	 */
	public boolean anyPressed() {
		for (int i = 0; i < KEYS.length; i++)
			if (KEYS[i] && !keysLast[i])
				return true;
		return false;
	}

	/**
	 * @param key
	 *            The keyboard key to check
	 * @return {@code true} if the key associated with the specified {@link Key}
	 *         's ordinal is being held, {@code false} otherwise
	 */
	public boolean held(Key key) {
		return KEYS[key.code];
	}

	/**
	 * @return {@code true} if any key is being held, {@code false} otherwise
	 */
	public boolean anyHeld() {
		for (int i = 0; i < KEYS.length; i++)
			if (KEYS[i])
				return true;
		return false;
	}

	/**
	 * @param key
	 *            The keyboard key to check
	 * @return {@code true} if the key associated with the specified {@link Key}
	 *         's ordinal has been released, {@code false} otherwise
	 */
	public boolean released(Key key) {
		return !KEYS[key.code] && keysLast[key.code];
	}

	/**
	 * @return {@code true} if any key has been released, {@code false}
	 *         otherwise
	 */
	public boolean anyReleased() {
		for (int i = 0; i < KEYS.length; i++)
			if (!KEYS[i] && keysLast[i])
				return true;
		return false;
	}

	/**
	 * @param key
	 *            The keyboard key to check
	 * @return {@code true} if the specified {@link Key} appears only once, and
	 *         is not located on the number pad, {@code false} otherwise
	 */
	public boolean isStandard(Key key) {
		return KEY_LOCATIONS[key.ordinal()] == KeyEvent.KEY_LOCATION_STANDARD;
	}

	/**
	 * @param key
	 *            The keyboard key to check
	 * @return {@code true} if the specified {@link Key} is the leftmost
	 *         instance of the key, {@code false} otherwise
	 */
	public boolean isLeft(Key key) {
		return KEY_LOCATIONS[key.ordinal()] == KeyEvent.KEY_LOCATION_LEFT;
	}

	/**
	 * @param key
	 *            The keyboard key to check
	 * @return {@code true} if the specified {@link Key} is the rightmost
	 *         instance of the key, {@code false} otherwise
	 */
	public boolean isRight(Key key) {
		return KEY_LOCATIONS[key.ordinal()] == KeyEvent.KEY_LOCATION_RIGHT;
	}

	/**
	 * @param key
	 *            The keyboard key to check
	 * @return {@code true} if the specified {@link Key} is located in the
	 *         number pad, {@code false} otherwise
	 */
	public boolean isNumpad(Key key) {
		return KEY_LOCATIONS[key.ordinal()] == KeyEvent.KEY_LOCATION_NUMPAD;
	}

	public int getLastKeyCode() {
		return lastKeyCode;
	}

	@InternalMethod
	public void update() {
		keysLast = KEYS.clone();
	}

	@Override
	@InternalMethod
	public void keyTyped(KeyEvent e) {
		e.consume();
	}

	@Override
	@InternalMethod
	public void keyPressed(KeyEvent e) {
		lastKeyCode = e.getKeyCode();

		if (lastKeyCode >= 0 && lastKeyCode < KEYS.length) {
			KEYS[lastKeyCode] = true;

			KEY_LOCATIONS[lastKeyCode] = e.getKeyLocation();
		}

		e.consume();
	}

	@Override
	@InternalMethod
	public void keyReleased(KeyEvent e) {
		lastKeyCode = e.getKeyCode();

		if (lastKeyCode >= 0 && lastKeyCode < KEYS.length) {
			KEYS[lastKeyCode] = false;

			KEY_LOCATIONS[lastKeyCode] = e.getKeyLocation();
		}

		e.consume();
	}
}