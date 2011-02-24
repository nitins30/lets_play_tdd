package com.jamesshore.finances.domain;

import static org.junit.Assert.*;
import java.net.*;
import javax.swing.*;
import org.junit.*;

public class _InvalidDollarsTest {

	private InvalidDollars invalidA;
	private InvalidDollars invalidB;
	private ValidDollars valid;

	@Before
	public void setup() {
		invalidA = new InvalidDollars();
		invalidB = new InvalidDollars();
		valid = new ValidDollars(13);
	}

	@Test
	public void isValid() {
		assertFalse(invalidA.isValid());
	}

	@Test
	public void plus() {
		assertEquals(new InvalidDollars(), invalidA.plus(invalidB));
		assertEquals(new InvalidDollars(), invalidA.plus(valid));
		assertEquals(new InvalidDollars(), valid.plus(invalidA));
	}

	@Test
	public void minus() {
		assertEquals(new InvalidDollars(), invalidA.minus(invalidB));
		assertEquals(new InvalidDollars(), invalidA.minus(valid));
		assertEquals(new InvalidDollars(), valid.minus(invalidB));
	}

	@Test
	public void subtractToZero() {
		assertEquals(new InvalidDollars(), invalidA.subtractToZero(invalidB));
		assertEquals(new InvalidDollars(), invalidA.subtractToZero(valid));
		assertEquals(new InvalidDollars(), valid.subtractToZero(invalidB));
	}

	@Test
	public void percentage() {
		assertEquals(new InvalidDollars(), invalidA.percentage(10));
	}

	@Test
	public void min() {
		assertEquals(new InvalidDollars(), invalidA.min(invalidB));
		assertEquals(new InvalidDollars(), invalidA.min(valid));
		assertEquals(new InvalidDollars(), valid.min(invalidB));
	}

	@Test
	public void renderToSwingLabel() {
		JLabel label = new JLabel();
		invalidA.render(label);

		URL iconUrl = getClass().getResource("invalid_dollars.gif");
		ImageIcon expectedIcon = new ImageIcon(iconUrl);
		ImageIcon actualIcon = (ImageIcon)label.getIcon();

		assertEquals("icon image", expectedIcon.getImage(), actualIcon.getImage());
		assertEquals("icon description", "Invalid dollar amount", actualIcon.getDescription());
	}

	@Test
	public void renderingShouldResetLabelToDefaultState() {
		JLabel label = new JLabel();
		label.setText("foodle");
		label.setToolTipText("bogus tooltip");

		invalidA.render(label);
		assertNull("should have no text", label.getText());
		assertNull("should have no tooltip", label.getToolTipText());
	}

	@Test
	public void valueObject() {
		assertEquals("$???", invalidA.toString());
		assertTrue("invalid dollars are always equal", invalidA.equals(invalidB));
		assertFalse("invalid dollars don't equal anything else", invalidA.equals(valid));
		assertFalse("shouldn't blow up when comparing to null", invalidA.equals(null));
		assertTrue("equal dollars should have same hash code", invalidA.hashCode() == invalidB.hashCode());
	}

}
