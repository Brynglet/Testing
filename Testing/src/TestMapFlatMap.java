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

		list.stream()
		.map(x -> ("hello:" + x))
		.forEach(x -> System.out.println(x));
//		hello:289
//		hello:468
//		hello:229
//		hello:406
//		hello:365

		System.out.println("");
		list.stream()
		.flatMap(num -> Stream.of(num)) // doesnt make any sense
		.forEach(x -> System.out.println(x));
//		289
//		468
//		229
//		406
//		365
		List<List<Integer>> listlist = getListListBonuses();
		System.out.println("ex1: Bad. This is why flatmap is needded");
		listlist.stream()
		.map(num -> Stream.of(num))
		.forEach(x -> System.out.println("ABC" + x)); // x is a list!
//		ex1: Bad. This is why flatmap is needded
//		ABCjava.util.stream.ReferencePipeline$Head@eed1f14
//		ABCjava.util.stream.ReferencePipeline$Head@7229724f
//		ABCjava.util.stream.ReferencePipeline$Head@4c873330

		System.out.println("ex2: somewhat good");
		listlist.stream()
		.flatMap(num -> Stream.of(num))
		.forEach(x -> System.out.println("CDE" + x)); // x is a list!

//		ex2: somewhat good
//		CDE[416, 414, 414, 455, 71]
//		CDE[438, 350, 297, 279, 346]
//		CDE[204, 496, 270, 28, 223]

		System.out.println("");
		System.out.println("ex3: good");
		listlist.stream()
		.flatMap(num -> num.stream())
		//.forEach(x -> System.out.println(x));
		.forEach(x -> System.out.println("FGH" + x)); // x is an integer, what we want!
//		ex3: good
//		FGH416
//		FGH414
//		FGH414
//		FGH455
//		FGH71
//		FGH438
//		FGH350
//		FGH297
//		FGH279
//		FGH346
//		FGH204
//		FGH496
//		FGH270
//		FGH28
//		FGH223

		System.out.println("");
		System.out.println("ex4: good");
		listlist.stream()
		.flatMap(num -> num.stream())
		.map(x -> ("IJK" + x))
		.forEach(x -> System.out.println(x)); // x is an integer, what we want!
//		ex4: good
//		IJK416
//		IJK414
//		IJK414
//		IJK455
//		IJK71
//		IJK438
//		IJK350
//		IJK297
//		IJK279
//		IJK346
//		IJK204
//		IJK496
//		IJK270
//		IJK28
//		IJK223


		// ListListList
		System.out.println("");

		List<List<List<Integer>>> listlistlist = getListListListBonuses();
		System.out.println("listlistlist:" + listlistlist);
		System.out.println("");
		System.out.println("ex5: soso good");
		listlistlist.stream()
		.flatMap(num -> num.stream())
		.map(x -> ("LMN" + x))
		.forEach(x -> System.out.println(x));

//
//ex5: soso good
//LMN[392, 497, 41, 300, 467]
//LMN[227, 82, 138, 211, 262]
//LMN[91, 122, 87, 240, 116]
//LMN[312, 487, 43, 36, 236]
//LMN[147, 218, 319, 293, 421]
//LMN[322, 435, 495, 301, 388]
//LMN[376, 72, 142, 205, 238]
//LMN[12, 154, 162, 426, 125]
//LMN[34, 306, 72, 146, 379]
//LMN[94, 410, 403, 385, 95]
//LMN[38, 227, 449, 176, 202]
//LMN[68, 238, 356, 70, 84]
//LMN[289, 99, 33, 185, 34]
//LMN[416, 462, 346, 442, 432]
//LMN[68, 272, 410, 407, 114]
//LMN[75, 313, 367, 196, 484]
//LMN[225, 321, 51, 204, 392]
//LMN[35, 320, 64, 108, 161]
//LMN[377, 113, 438, 76, 265]
//LMN[379, 452, 122, 276, 105]
//LMN[406, 129, 379, 233, 325]

		System.out.println("");
		System.out.println("ex6: good");
		listlistlist.stream()
		.flatMap(num -> num.stream())
		.flatMap(num -> num.stream())
		.map(x -> ("OPQ" + x))
		.forEach(x -> System.out.println(x));

//		ex6: good
//		OPQ392
//		OPQ497
//		OPQ41
//		OPQ300
//		OPQ467
//		OPQ227
//		OPQ82
//		OPQ138
//		OPQ211
//		OPQ262
//		OPQ91
//		OPQ122
//		OPQ87
//		OPQ240
//		OPQ116
//		OPQ312
//		OPQ487
//		OPQ43
//		OPQ36
//		OPQ236
//		OPQ147
//		OPQ218
//		OPQ319
//		OPQ293
//		OPQ421
//		OPQ322
//		OPQ435
//		OPQ495
//		OPQ301
//		OPQ388
//		OPQ376
//		OPQ72
//		OPQ142
//		OPQ205
//		OPQ238
//		OPQ12
//		OPQ154
//		OPQ162
//		OPQ426
//		OPQ125
//		OPQ34
//		OPQ306
//		OPQ72
//		OPQ146
//		OPQ379
//		OPQ94
//		OPQ410
//		OPQ403
//		OPQ385
//		OPQ95
//		OPQ38
//		OPQ227
//		OPQ449
//		OPQ176
//		OPQ202
//		OPQ68
//		OPQ238
//		OPQ356
//		OPQ70
//		OPQ84
//		OPQ289
//		OPQ99
//		OPQ33
//		OPQ185
//		OPQ34
//		OPQ416
//		OPQ462
//		OPQ346
//		OPQ442
//		OPQ432
//		OPQ68
//		OPQ272
//		OPQ410
//		OPQ407
//		OPQ114
//		OPQ75
//		OPQ313
//		OPQ367
//		OPQ196
//		OPQ484
//		OPQ225
//		OPQ321
//		OPQ51
//		OPQ204
//		OPQ392
//		OPQ35
//		OPQ320
//		OPQ64
//		OPQ108
//		OPQ161
//		OPQ377
//		OPQ113
//		OPQ438
//		OPQ76
//		OPQ265
//		OPQ379
//		OPQ452
//		OPQ122
//		OPQ276
//		OPQ105
//		OPQ406
//		OPQ129
//		OPQ379
//		OPQ233
//		OPQ325

		System.out.println("");
		System.out.println("ex7: very good");
		listlistlist.stream()
		.flatMap(num -> num.stream().flatMap(x->x.stream()))
		.map(x -> ("RST" + x))
		.forEach(x -> System.out.println(x));

//
//ex7: good
//RST497
//RST128
//RST455
//RST242
//RST373
//RST225
//RST422
//RST239
//RST433
//RST396
//RST135
//RST368
//RST389
//RST446
//RST105
//RST223
//RST110
//RST461
//RST129
//RST141
//RST299
//RST109
//RST51
//RST151
//RST57
//RST111
//RST213
//RST319
//RST392
//RST192
//RST251
//RST191
//RST298
//RST407
//RST398
//RST284
//RST332
//RST378
//RST262
//RST339
//RST481
//RST138
//RST439
//RST478
//RST451
//RST456
//RST167
//RST407
//RST251
//RST194
//RST392
//RST132
//RST378
//RST274
//RST259
//RST198
//RST38
//RST469
//RST288
//RST172
//RST296
//RST451
//RST156
//RST180
//RST369
//RST104
//RST265
//RST452
//RST443
//RST222
//RST242
//RST43
//RST127
//RST302
//RST499
//RST98
//RST204
//RST159
//RST93
//RST377
//RST467
//RST441
//RST85
//RST443
//RST263
//RST472
//RST112
//RST396
//RST310
//RST429
//RST293
//RST65
//RST500
//RST134
//RST205
//RST272
//RST174
//RST96
//RST96
//RST484
//RST400
//RST235
//RST448
//RST254
//RST249


//		Example, read 1 million from list

		//Let's say I have a List<TimeSeries> where each TimeSeries is essentially a Map<LocalDate, Double>.
		//I want to get a list of all dates for which at least one of the time series has a value. flatMap to the rescue:

//		list.stream().parallel()
//	    .flatMap(ts -> ts.dates().stream()) // for each TS, stream dates and flatmap
//	    .distinct()                         // remove duplicates
//	    .sorted()                           // sort ascending
//	    .collect(toList());
//		Not only is it readable, but if you suddenly need to process 100k elements, simply adding parallel() will improve performance without you writing any concurrent code.
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

	private static List<List<List<Integer>>> getListListListBonuses() {
		List<List<List<Integer>>> list = new ArrayList<>();

		list.add(getListListBonuses());
		list.add(getListListBonuses());
		list.add(getListListBonuses());
		list.add(getListListBonuses());
		list.add(getListListBonuses());
		list.add(getListListBonuses());
		list.add(getListListBonuses());

		return list;
	}


}