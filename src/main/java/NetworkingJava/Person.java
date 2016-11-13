package NetworkingJava;

public class Person implements java.io.Serializable {
	private String name;
	private int age;
	public ContactInfo ci;

	public Person() {
		this.ci = new ContactInfo();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	class ContactInfo implements java.io.Serializable {
		private String phone;
		private String email;

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getPhone() {
			return this.phone;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getEmail() {
			return this.email;
		}

	}
}
