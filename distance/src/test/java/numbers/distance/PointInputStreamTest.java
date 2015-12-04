package numbers.distance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import numbers.distance.PointInputStream;

public class PointInputStreamTest {
	
	private PointInputStream subject;

	@Before
	public void setUp() throws Exception {
		this.subject = new PointInputStream(new InputStream() {
			public int read() throws IOException {
				return 1;
			}
		});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadPoint() throws Exception {
		assertNotNull(subject.readPoint());
		assertEquals(257, subject.readPoint().getX());
		assertEquals(257, subject.readPoint().getY());
	}

}
