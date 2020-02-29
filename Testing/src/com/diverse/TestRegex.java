package com.diverse;
import java.util.Arrays;
import java.util.List;

public class TestRegex {


	protected static final String REGEX_WHITESPACES = "\\s+";
	protected static final String REGEX_COMMA = "[,]";
	protected static final String REGEX_COMMA_MATCHES = "^.*[,].*$";
	protected static final String REGEX_LETTERS = "[a-zA-Z]";
	protected static final String REGEX_PIPE = "[|]";
	protected static final String REGEX_NOT_A_CLEAN_POSITIVE_NUMBER = "^[\\D]*";
	protected static final String REGEX_COMMA_PIPE = "[|,]";

	private static final String CATEGORY_ROOT_ID_STRING = "0";
	private static final Long CATEGORY_ROOT_ID = 0L;
	private static final String ACCESS_TYPE_CHECK_REGEX = "\\b[aA].*";
	private static final String SERVICE_TYPE_CHECK_REGEX = "\\b[sS].*";
	private static final String ADDON_TYPE_CHECK_REGEX = "\\b[xX].*";
	private static final String ID_SPLIT_REGEX = "(?<=\\D)(?=\\d)";
	private static final String ADDON_ID_CHECK = "\\b[aA].*";
	private static final String CAMPAIGN_ID_CHECK = "\\b[cC].*";
	private static final String PRODUCT_OFFERING_ID_CHECK = "^[0-9, |]*$";
	private static final int ID_NUMERICAL_INDEX = 1;
	private static final String CATEGORY_ADDON_REFERRED_TYPE = "AddonOfferType";
	private static final int MAX_OFFER_LIMIT = 200;
	private static final int PAGE_NUMBER = 0;
	private static final int PAGE_SIZE = 1;
	private static final String REGEX_TRIM_SPLIT_COMMA = "\\s*,\\s*";
	private static final String ADDON_PREFIX = "X";


	protected static final String REGEX_PHONE1 = "[0][1-9]{6}";

	protected static final String REGEX_NOT_A_CLEAN_POSITIVE_NUMBER0 = "^[0-9]*$";
	protected static final String REGEX_NOT_A_CLEAN_POSITIVE_NUMBER1 = "^[\\D]*";
	protected static final String REGEX_NOT_A_CLEAN_POSITIVE_NUMBER2 = "^\\D*";
	protected static final String REGEX_NOT_A_CLEAN_POSITIVE_NUMBER3 = "\\D";
	protected static final String REGEX_NOT_A_CLEAN_POSITIVE_NUMBER4 = "\\D*";
	protected static final String REGEX_NOT_A_CLEAN_POSITIVE_NUMBER5 = "\\D+";

	protected static final String REGEX_NOT_A_CLEAN_POSITIVE_NUMBER6 = "\\[0-9]*";
	protected static final String REGEX_NOT_A_CLEAN_POSITIVE_NUMBER7 = "\\\\[0-9]";
	protected static final String REGEX_NOT_A_CLEAN_POSITIVE_NUMBER8 = "/[0-9]";
	protected static final String REGEX_NOT_A_CLEAN_POSITIVE_NUMBER9 = "/[0-9]+";
	protected static final String REGEX_NOT_A_CLEAN_POSITIVE_NUMBER10 = "\\D*";
	protected static final String REGEX_NOT_A_CLEAN_POSITIVE_NUMBER11 = "\\D*";

	public static void main(String argv[]) {
		List<String> phNumber = Arrays.asList("hej", "123", "he2j", "XXX", "b44", "55c");


	}

}
