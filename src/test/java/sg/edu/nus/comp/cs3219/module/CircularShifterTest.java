package sg.edu.nus.comp.cs3219.module;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs3219.model.LineStorage;

public class CircularShifterTest {
	LineStorage inputLineStorage;
	LineStorage afterShiftLineStorage;
	CircularShifter shifter;

	@Before
	public void setUp() {
		inputLineStorage = new LineStorage();
		afterShiftLineStorage = new LineStorage();
		shifter = new CircularShifter(afterShiftLineStorage);
		Set<String> words = new HashSet<>();
		words.add("the");
		words.add("after");
		shifter.setIgnoreWords(words);
		inputLineStorage.addObserver(shifter);
	}

	@Test
	public void test() {
		inputLineStorage.addLine("The Day after Tomorrow");
		assertEquals(2, afterShiftLineStorage.size());

		assertEquals("Day after Tomorrow the", afterShiftLineStorage.get(0).toString());
		assertEquals("Tomorrow the Day after", afterShiftLineStorage.get(1).toString());
	}

	@Test
	public void test1() {
		inputLineStorage.addLine("The Year before 2020");
		assertEquals(3, afterShiftLineStorage.size());

		assertEquals("Year before 2020 the", afterShiftLineStorage.get(0).toString());
		assertEquals("before 2020 the Year", afterShiftLineStorage.get(1).toString());
		assertEquals("2020 the Year before", afterShiftLineStorage.get(2).toString());

	}

	@Test
	public void test2() {
		inputLineStorage.addLine("The Year after 2020");
		assertEquals(2, afterShiftLineStorage.size());

		assertEquals("Year after 2020 the", afterShiftLineStorage.get(0).toString());
		assertEquals("2020 the Year after", afterShiftLineStorage.get(1).toString());
	}


}
