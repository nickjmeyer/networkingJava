package NetworkingJava;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;
import java.util.*;

public class TestPerson {
	@Test
	public void testAttributes() {
		Person p = new Person();

		p.setName("john");
		p.setAge(10);
		p.ci.setPhone("8005551212");
		p.ci.setEmail("john@example.com");

		assertEquals(p.getName(),"john");
		assertEquals(p.getAge(),10);
		assertEquals(p.ci.getPhone(),"8005551212");
		assertEquals(p.ci.getEmail(),"john@example.com");
	}

	@Test
	public void testSerialization()
		throws IOException, ClassNotFoundException {

		Person p = new Person();

		p.setName("john");
		p.setAge(10);
		p.ci.setPhone("8005551212");
		p.ci.setEmail("john@example.com");

		assertEquals(p.getName(),"john");
		assertEquals(p.getAge(),10);
		assertEquals(p.ci.getPhone(),"8005551212");
		assertEquals(p.ci.getEmail(),"john@example.com");

		// serialize
		ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
		ObjectOutputStream objOutStream = new ObjectOutputStream(byteOutStream);
		objOutStream.writeObject(p);
		objOutStream.close();

		// object as byte array
		byte[] ba = byteOutStream.toByteArray();

		// de-serialize
		ByteArrayInputStream byteInStream = new ByteArrayInputStream(ba);
		ObjectInputStream objInStream = new ObjectInputStream(byteInStream);
		Person p2 = (Person) objInStream.readObject();

		// test objects have same attributes
		assertEquals(p.getName(),p2.getName());
		assertEquals(p.getAge(),p2.getAge());
		assertEquals(p.ci.getPhone(),p2.ci.getPhone());
		assertEquals(p.ci.getEmail(),p2.ci.getEmail());

		// modify original and make sure deserialized object hasn't changed
		p.setName("jane");
		p.setAge(11);
		p.ci.setPhone("8885551212");
		p.ci.setEmail("jane@example.com");

		assertNotEquals(p.getName(),p2.getName());
		assertNotEquals(p.getAge(),p2.getAge());
		assertNotEquals(p.ci.getPhone(),p2.ci.getPhone());
		assertNotEquals(p.ci.getEmail(),p2.ci.getEmail());
	}
}
