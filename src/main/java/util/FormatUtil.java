package util;

import java.util.Date;

public class FormatUtil {

	// 🔹 Main method (long version)
	public static String formatIndianNumber(long number) {

		String num = String.valueOf(number);

		if (num.length() <= 3)
			return num;

		String last3 = num.substring(num.length() - 3);
		String remaining = num.substring(0, num.length() - 3);

		StringBuilder sb = new StringBuilder();

		while (remaining.length() > 2) {
			sb.insert(0, "," + remaining.substring(remaining.length() - 2));
			remaining = remaining.substring(0, remaining.length() - 2);
		}

		sb.insert(0, remaining);

		return sb.toString() + "," + last3;
	}

	// 🔹 Overloaded method (double version)
	public static String formatIndianNumber(double number) {
		return formatIndianNumber(Math.round(number));
	}

	public static String currency(double number) {
		return "₹" + formatIndianNumber(number);
	}

	public static String date(Date date) {
		if (date == null)
			return "-";
		return new java.text.SimpleDateFormat("dd MMM yyyy").format(date);
	}
}