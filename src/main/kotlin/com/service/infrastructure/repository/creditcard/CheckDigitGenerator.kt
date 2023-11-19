package com.service.infrastructure.repository.creditcard

/**
 * A credit card number generator.
 *
 * @author Josef Galea
 * Link: https://gist.github.com/josefeg/5781824
 */
object CheckDigitGenerator {
    /**
     * Generates the check digit required to make the given credit card number
     * valid (i.e. pass the Luhn check)
     *
     * @param number
     *            The credit card number for which to generate the check digit.
     * @return The check digit required to make the given credit card number
     *         valid.
     */
    fun getCheckDigit(number: String): Int {
        // Get the sum of all the digits, however we need to replace the value
        // of the first digit, and every other digit, with the same digit
        // multiplied by 2. If this multiplication yields a number greater
        // than 9, then add the two digits together to get a single digit
        // number.
        //
        // The digits we need to replace will be those in an even position for
        // card numbers whose length is an even number, or those is an odd
        // position for card numbers whose length is an odd number. This is
        // because the Luhn algorithm reverses the card number, and doubles
        // every other number starting from the second number from the last
        // position.
        var sum = 0
        for (i in number.indices) {

            // Get the digit at the current position.
            var digit = number.substring(i, i + 1).toInt()
            if (i % 2 == 0) {
                digit *= 2
                if (digit > 9) {
                    digit = digit / 10 + digit % 10
                }
            }
            sum += digit
        }

        // The check digit is the number required to make the sum a multiple of
        // 10.
        val mod = sum % 10
        return if (mod == 0) 0 else 10 - mod
    }
}