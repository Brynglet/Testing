import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestingStreams {
	public static void main(String[] args) throws IOException {
		// 1. Integer Stream
		IntStream
			.range(1, 10)
			.forEach(System.out::print);
		System.out.println();

		// 2. Integer Stream with skip
		IntStream
			.range(1, 10)
			.skip(5)
			.forEach(x -> System.out.println(x));
		System.out.println();

		// 3. Integer Stream with sum
		System.out.println(
		IntStream
			.range(1, 5)
			.sum());
		System.out.println();

		// 4. Stream.of, sorted and findFirst
		Stream.of("Ava", "Aneri", "Alberto")
			.sorted()
			.findFirst()
			.ifPresent(System.out::println);

		// 5. Stream from Array, sort, filter and print
		String[] names = {"Al", "Ankit", "Kushal", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah"};
		Arrays.stream(names)	// same as Stream.of(names)
			.filter(x -> x.startsWith("S"))
			.sorted()
			.forEach(System.out::println);

		// 6. average of squares of an int array
		Arrays.stream(new int[] {2, 4, 6, 8, 10})
			.map(x -> x * x)
			.average()
			.ifPresent(System.out::println);

		// 7. Stream from List, filter and print
		List<String> people = Arrays.asList("Al", "Ankit", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah");
		people
			.stream()
			.map(String::toLowerCase)
			.filter(x -> x.startsWith("a"))
			.forEach(System.out::println);

		// 8. Stream rows from text file, sort, filter, and print
		Stream<String> bands = Files.lines(Paths.get("bands.txt"));
		bands
			.sorted()
			.filter(x -> x.length() > 13);
			//.forEach(System.out::println);
		bands.close();

		// 9. Stream rows from text file and save to List
		List<String> bands2 = Files.lines(Paths.get("bands.txt"))
			.filter(x -> x.contains("jit"))
			.collect(Collectors.toList());
		bands2.forEach(x -> System.out.println(x));

		// 10. Stream rows from CSV file and count
		Stream<String> rows1 = Files.lines(Paths.get("data.txt"));
		int rowCount = (int)rows1
			.map(x -> x.split(","))
            .filter(x -> x.length == 3)
			.count();
		System.out.println(rowCount + " rows.");
		rows1.close();

		Math.random();

		/* Predicate */
		List<String> names5 = Arrays.asList("Angela", "Aaron", "Bob", "Claire", "David");

		List<String> namesWithA = names5.stream()
		  .filter(name -> name.startsWith("A"))
		  .sorted()
		  .collect(Collectors.toList());

		/* Supplier */
//		 Supplier<Integer> supplier = new Supplier<Integer>() {
//	            @Override
//	            public Integer get() {
//	                //Returns a random value
//	                return new Random().nextInt();
//	            }
//	        };

	        Supplier<Integer> supplier = () -> new Random().nextInt();
	        System.out.println(supplier.get());


//		// 11. Stream rows from CSV file, parse data from rows
//		Stream<String> rows2 = Files.lines(Paths.get("data.txt"));
//		rows2
//			.map(x -> x.split(","))
//            .filter(x -> x.length == 3)
//			//.filter(x -> Integer.parseInt(x[1]) > 15)
//			.forEach(x -> System.out.println(x[0] + "  " + x[1] + "  " + x[2]));
//		rows2.close();
//
//		// 12. Stream rows from CSV file, store fields in HashMap
//		Stream<String> rows3 = Files.lines(Paths.get("data.txt"));
//		Map<String, Integer> map = new HashMap<>();
//		map = rows3
//			.map(x -> x.split(","))
//            .filter(x -> x.length == 3)
//			//.filter(x -> Integer.parseInt(x[1]) > 15)
//			.collect(Collectors.toMap(
//                x -> x[0],
//                x -> Integer.parseInt(x[1])));
//		rows3.close();
//		for (String key : map.keySet()) {
//			System.out.println(key + "  " + map.get(key));
//		}

		// 13. Reduction - sum
		double total = Stream.of(7.3, 1.5, 4.8)
			.reduce(0.0, (Double a, Double b) -> a + b);
		System.out.println("Total = " + total);

		// 14. Reduction - summary statistics
		IntSummaryStatistics summary = IntStream.of(7, 2, 19, 88, 73, 4, 10)
			.summaryStatistics();
		System.out.println(summary);


		//Svarta killen

		   List<Person> people2 = getPeople();

		    // Imperative approach ❌

		    /*
		    List<Person> females = new ArrayList<>();
		    for (Person person : people) {
		      if (person.getGender().equals(Gender.FEMALE)) {
		        females.add(person);
		      }
		    }
		    females.forEach(System.out::println);
		    */

		    // Declarative approach ✅

		    // Filter
		    List<Person> females = people2.stream()
		        .filter(person -> person.getGender().equals(Gender.FEMALE))
		        .collect(Collectors.toList());

//		    females.forEach(System.out::println);

		    // Sort
		    List<Person> sorted = people2.stream()
		        .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
		        .collect(Collectors.toList());

//		    sorted.forEach(System.out::println);

		    // All match
		    boolean allMatch = people2.stream()
		        .allMatch(person -> person.getAge() > 8);

//		    System.out.println(allMatch);
		    // Any match
		    boolean anyMatch = people2.stream()
		        .anyMatch(person -> person.getAge() > 121);

//		    System.out.println(anyMatch);
		    // None match
		    boolean noneMatch = people2.stream()
		        .noneMatch(person -> person.getName().equals("Antonio"));

//		    System.out.println(noneMatch);

		    // Max
		    people2.stream()
		        .max(Comparator.comparing(Person::getAge));
//		        .ifPresent(System.out::println);

		    // Min
		    people2.stream()
		        .min(Comparator.comparing(Person::getAge));
//		        .ifPresent(System.out::println);

		    // Group
		    Map<Gender, List<Person>> groupByGender = people2.stream()
		        .collect(Collectors.groupingBy(Person::getGender));

//		    groupByGender.forEach((gender, people1) -> {
//		      System.out.println(gender);
//		      people1.forEach(System.out::println);
//		      System.out.println();
//		    });

		    Optional<String> oldestFemaleAge = people2.stream()
		        .filter(person -> person.getGender().equals(Gender.FEMALE))
		        .max(Comparator.comparing(Person::getAge))
		        .map(Person::getName);

		    oldestFemaleAge.ifPresent(System.out::println);

/*
		    states.forEach(state -> {
			    // second loop for filtered elements
			    cities.stream().filter(city -> state.containsPoint(city.getLocation())).forEach(city -> {
			        System.out.printf("%30s is part of %-30s\n", city.getName(), state.getName());
			    });
			});


		List<Foo> foos = new ArrayList<>();
		// create foos
		IntStream
		    .range(1, 4)
		    .forEach(i -> foos.add(new Foo("Foo" + i)));
*/

		    testMore();
	}

	private static void testMore() {

		System.out.println("xx-xx-x-xx-xx-xx-x-xx-xx");
		System.out.println("xx-xx-x-xx-xx-xx-x-xx-xx");
		System.out.println("xx-xx-x-xx-xx-xx-x-xx-xx");

//	-
//	   List<ProductOfferingQualificationItem> familyItemList) {
//
//	    familyItemList
//	        .forEach(f -> itemList.stream()
//	            .filter(i -> i.getId().equals(f.getId()))
//	            .forEach(i -> {
//	              i.setQualificationItemResult(f.getQualificationItemResult());
//	              i.setEligibilityUnavailabilityReason(f.getEligibilityUnavailabilityReason());
//	            }));
//	  }
//	-
//	   List<String> categoryIds = postRequest.getProductOfferingQualificationItem() är en lista
//	        .stream()
//	        .filter(i -> i.getCategory() != null)
//	        .flatMap(i -> i.getCategory().stream()
//	            .map(CategoryRef::getId)
//	            .distinct())
//	        .distinct()
//	        .collect(Collectors.toList());
//	-
//	productOffering.getBundledProductOffering()
//	.stream()
//	.map(BundledProductOfferingRef::getId)
//	.collect(Collectors.toList())).build();
//	-
//	categoryIds list of string
//	   categoryIds.addAll(item.getCategory().stream()
//	          .map(CategoryRef::getId)
//	          .collect(Collectors.toList()));
//	-
//	ovanstående är i service,
//	nedan är FereatedIdentActionUtil
//	-
//	    HashSet<FederatedIdHeaderActionType> actionSet = entitlements
//	        .filter(entitlement -> entitlement.getAction() != null)
//	        .filter(entitlement ->
//	            Arrays.stream(FederatedIdHeaderActionType.values())
//	                .anyMatch(item -> item.getValue().equals(entitlement.getAction().toUpperCase())))
//	        .map(entitlement -> FederatedIdHeaderActionType
//	            .valueOf(entitlement.getAction().toUpperCase()))
//	        .filter(FederatedIdHeaderActionType::isValid)
//	        .collect(Collectors.toCollection(HashSet::new));


		Map<String, Person> personMapStr = getPeople()
		.stream()
		.collect(Collectors.toMap(Person::getName, p -> p));

		System.out.println("personMap1->" + personMapStr);

		Map<Integer, Person> personMapInt = getPeople()
        .stream()
        .collect(Collectors.toMap(Person::getAge, p -> p));

		System.out.println("personMap2->" + personMapInt);

		List<Person> personsSomeOld = getPeople();
		Person p0 = personsSomeOld.get(0);
		Person p1 = new Person("jb1", 31, Gender.MALE);
		Person p2 = new Person("jb2", 32, Gender.MALE);

		Integer count = 0;
		personsSomeOld.stream()
		.forEach(item -> {

			boolean isQualificationResultQualified = true;
			if (isQualificationResultQualified) {
				if (item.getAge() %2 == 0) {
					item.setAge(200);
				}
			}
		});

		System.out.println("p1:" + p1);
		System.out.println("personsSomeOld:" + personsSomeOld);
//		[Person! [name=Antonio, age=200, gender=MALE], Person! [name=Alina Smith, age=33, gender=FEMALE],
		//Person! [name=Helen White, age=57, gender=FEMALE], Person! [name=Alex Boz, age=200, gender=MALE],
		//Person! [name=Jamie Goa, age=99, gender=MALE], Person! [name=Anna Cook, age=7, gender=FEMALE],
		//Person! [name=Zelda Brown, age=200, gender=FEMALE]]

		List<Person> persons = getPeople();
	    persons
        .forEach(
        		p -> personsSomeOld.stream()
        		.filter(i -> i.getName().equals(p.getName()) && i.getName().equals("Anna Cook"))
        		.forEach(i -> {
        			System.out.println("hej:" + i.getName());
        			p.setAge(778);
        		}));
	    System.out.println("personsiii:" + persons); // Anna Cook nu 778

		List<Person> personsNew = getPeople();
		personsNew
        .forEach(
        		p -> personsSomeOld.stream()
        		.filter(i -> i.getName().equals(p.getName()) && (i.getName().equals("Anna Cook") || i.getName().equals("Alex Boz")))
        		.forEach(i -> {
        			System.out.println("hej:" + i.getName());
        			p.setAge(400);
        		}));
	    System.out.println("personsNew:" + personsNew); // Anna Cook och Alex Boz nu 400
	}



	private static List<Person> getPeople() {
		List<Person> list = new ArrayList<Person>();
		list.add(new Person("Antonio", 20, Gender.MALE));
		list.add(new Person("Alina Smith", 33, Gender.FEMALE));
		list.add(new Person("Helen White", 57, Gender.FEMALE));
		list.add(new Person("Alex Boz", 14, Gender.MALE));
		list.add(new Person("Jamie Goa", 99, Gender.MALE));
		list.add(new Person("Anna Cook", 7, Gender.FEMALE));
		list.add(new Person("Zelda Brown", 120, Gender.FEMALE));
		return list;
	}

}