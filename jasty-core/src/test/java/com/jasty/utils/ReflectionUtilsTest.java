package com.jasty.utils;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Collection;

import com.jasty.core.InitProperty;
import org.junit.Test;

public class ReflectionUtilsTest {
	
	@Test
	public void testGetAllFields() {
		Collection<Field> fields = ReflectionUtils.getAllInitProperties(SubTest1.class);
		assertEquals(4, fields.size());
	}
	
	@Test
	public void testFindField() {
		assertNotNull(ReflectionUtils.findField(SubTest1.class, "testString"));
		assertNotNull(ReflectionUtils.findField(SubTest1.class, "testString1"));
		assertNotNull(ReflectionUtils.findField(Test1.class, "testString"));
		assertNotNull(ReflectionUtils.findField(Test1.class, "testNumber"));
		assertNull(ReflectionUtils.findField(SubTest1.class, "testNumber1"));
		assertNull(ReflectionUtils.findField(Test1.class, "testNumber1"));
	}
	
	@Test
	public void testCopyFields() {
		Test1 src = new Test1();
		src.setTestStringBase("bla-bla");
		src.testNumber = 123;
		SubTest1 dest = new SubTest1();
		ReflectionUtils.copyInitProperties(src, dest);
		assertEquals("bla-bla", dest.getTestString());
		assertEquals("bla-bla", dest.getTestStringBase());
		assertNull(dest.getTestString1());
		assertEquals(123, dest.testNumber);
	}

	@Test
	public void testCopyFieldsOtherClass() {
		SubTest1 src = new SubTest1();
		src.testString1 = "bla-bla";
		src.testNumber = 123;
		DestClass dest = new DestClass();
		ReflectionUtils.copyInitProperties(src, dest);
		assertEquals("bla-bla", dest.testString1);
		assertEquals(123, dest.testNumber);
	}
	
	public static class Test1 {
        @InitProperty
		private String testString;
        @InitProperty
		public int testNumber;
		
		public String getTestStringBase() {
			return testString;
		}
		public void setTestStringBase(String testString) {
			this.testString = testString;
		}
	}
	
	public static class SubTest1 extends Test1 {
        @InitProperty
		private String testString;
        @InitProperty
		private String testString1;
		
		public String getTestString() {
			return testString;
		}
		public void setTestString(String testString) {
			this.testString = testString;
		}
		public String getTestString1() {
			return testString1;
		}
		public void setTestString1(String testString1) {
			this.testString1 = testString1;
		}
	}
	
	public static class DestClass {
        @InitProperty
		private String testString1;
        @InitProperty
		private int testNumber;
		
		public String getTestString1() {
			return testString1;
		}
		public void setTestString1(String testString1) {
			this.testString1 = testString1;
		}
		public int getTestNumber() {
			return testNumber;
		}
		public void setTestNumber(int testNumber) {
			this.testNumber = testNumber;
		}
		
		
	}
}
