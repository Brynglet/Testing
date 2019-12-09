import java.util.List;

public class Person {

  private final String name;
  private final int age;
  private final Gender gender;
  private final List<Integer> bonuses;

  public Person(String name, int age, Gender gender, List<Integer> bonuses) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.bonuses = bonuses;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public Gender getGender() {
    return gender;
  }

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", gender=" + gender + ", bonuses=" + bonuses + "]";
	}

}
