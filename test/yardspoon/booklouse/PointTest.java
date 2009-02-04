package yardspoon.booklouse;

import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void canAccessInitialValues() {
        Point p = new Point(1, 2);

        assertEquals(1, p.x);
        assertEquals(2, p.y);
    }

    @Test
    public void equalPointsAreEqual() {
        Point p = new Point(3, 4);
        Point p2 = new Point(3, 4);

        assertEquals(p, p);
        assertEquals(p, p2);
        assertEquals(p2, p);
    }

    @Test
    public void nonEqualsPointsAreNotEqual() {
        Point p = new Point(1, 0);
        Point p2 = new Point(6, 100);

        assertFalse(p.equals(p2));
        assertFalse(p.equals(null));
    }

    @Test
    public void pointCanPrint() {
        Point p1 = new Point(3, 10);
        Point p2 = new Point(0, 2);

        assertEquals("(3,10)", p1.toString());
        assertEquals("(0,2)", p2.toString());
    }
}