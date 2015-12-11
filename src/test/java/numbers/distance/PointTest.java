package numbers.distance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import numbers.distance.Point;

public class PointTest {

	private Point subject;
	
	@Before
	public void setUp() throws Exception {
		this.subject = new Point(-715, 22165);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHashCode() {
		assertTrue(subject.hashCode() == subject.hashCode());
		assertFalse(subject.hashCode() == "".hashCode());
		assertTrue(subject.hashCode() == (new Point(-715, 22165)).hashCode());
		assertFalse(subject.hashCode() == (new Point(22165, -715)).hashCode());
	}

	@Test
	public void testDistance() {
		assertEquals(0.0, subject.distance(new Point(-715, 22165)), 0.00001);
		assertEquals(32357.20631, subject.distance(new Point(22165, -715)), 0.00001);
	}

	@Test
	public void testEqualsObject() {
		assertTrue(subject.equals(subject));
		assertFalse(subject.equals(null));
		assertFalse(subject.equals("Point"));
		assertTrue(subject.equals(new Point(-715, 22165)));
		assertFalse(subject.equals(new Point(22165, -715)));
	}

}
