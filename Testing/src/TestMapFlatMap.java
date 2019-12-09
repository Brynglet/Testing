import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class TestMapFlatMap {
	public static void main(String[] args) throws IOException {

// just an example of flatmap
//		Example 1 :
//			The list before flattening :
//			[ [2, 3, 5], [7, 11, 13], [17, 19, 23] ]
//			The list has 2 levels and consists of 3 small lists. After Flattening, it gets transformed into “one level” structure as shown :
//			[ 2, 3, 5, 7, 11, 13, 17, 19, 23 ]

//		Example2
//        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
//        Stream<String[]> temp = Arrays.stream(data);
//        //Stream<String>, GOOD!
//        Stream<String> stringStream = temp.flatMap(x -> Arrays.stream(x));
//        Stream<String> stream = stringStream.filter(x -> "a".equals(x.toString()));
//        stream.forEach(System.out::println);
//		  output a

//	    entities.stream();
//	    If you need to have a stream containing the elements of the collection, then you must use entities.stream().
//
//	    Stream.of(entities);
//	    Does not do what you think it does! It does not give you a stream
//	    with the elements of the collection; instead, it will give you a stream with a single element, which is the collection itself (not its elements).

		List<Integer> list = getBonuses();
		List<List<Integer>> listlist = getListListBonuses();

		list.stream()
		.map(x -> ("hello:" + x))
		.forEach(x -> System.out.println(x));
//		hello:380
//		hello:448
//		hello:328
//		hello:377
//		hello:365

		System.out.println("");
		list.stream()
		.flatMap(num -> Stream.of(num)) // doesnt make any sense
		.forEach(x -> System.out.println(x));
//		380
//		448
//		328
//		377
//		365


		System.out.println("ex1: Bad. This is why flatmap is needded");
		listlist.stream()
		.map(num -> Stream.of(num))
		.forEach(x -> System.out.println("ABC" + x)); // x is a list!


		System.out.println("ex2: somewhat good");
		listlist.stream()
		.flatMap(num -> Stream.of(num))
		.forEach(x -> System.out.println("CDE" + x)); // x is a list!

//ex1:
//ABC[456, 113, 254, 117, 38]
//ABC[353, 406, 294, 252, 25]
//ABC[312, 300, 274, 285, 489]

		System.out.println("");
		System.out.println("ex3: good");
		listlist.stream()
		.flatMap(num -> num.stream())
		//.forEach(x -> System.out.println(x));
		.forEach(x -> System.out.println("FGH" + x)); // x is an integer, what we want!
//		ex2:
//			DEF456
//			DEF113
//			DEF254
//			DEF117
//			DEF38
//			DEF353
//			DEF406
//			DEF294
//			DEF252
//			DEF25
//			DEF312
//			DEF300
//			DEF274
//			DEF285
//			DEF489

	}

	private static List<Integer> getBonuses() {
		List<Integer> list = new ArrayList<>();

		Random rand = new Random();
		list.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		list.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		list.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		list.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		list.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		return list;
	}

	private static List<List<Integer>> getListListBonuses() {
		List<List<Integer>> list = new ArrayList<>();

		Random rand = new Random();
		List<Integer> tmpList = new ArrayList<>();
		tmpList.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		tmpList.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		tmpList.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		tmpList.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		tmpList.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		list.add(tmpList);

		tmpList = new ArrayList<>();
		tmpList.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		tmpList.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		tmpList.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		tmpList.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		tmpList.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		list.add(tmpList);

		tmpList = new ArrayList<>();
		tmpList.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		tmpList.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		tmpList.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		tmpList.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		tmpList.add(new Integer(rand.nextInt((500 - 10) + 1) + 10));
		list.add(tmpList);

		return list;
	}

}