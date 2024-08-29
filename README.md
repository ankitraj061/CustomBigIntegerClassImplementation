# CustomBigInteger

CustomBigInteger is a Java implementation of arbitrary-precision integers. It allows for mathematical operations on integers of any size, limited only by the available memory of the Java Virtual Machine.

## Features

- Supports addition, subtraction, multiplication, and division of large integers
- Handles both positive and negative numbers
- Implements comparison between CustomBigInteger objects
- Provides absolute value functionality
- Throws appropriate exceptions for invalid operations (e.g., division by zero)

## Installation

To use CustomBigInteger in your project, simply copy the `CustomBigInteger.java` file into your project's source directory.

## Usage

Here's a quick example of how to use CustomBigInteger:

```java
CustomBigInteger a = new CustomBigInteger("123456789");
CustomBigInteger b = new CustomBigInteger("987654321");

CustomBigInteger sum = a.add(b);
CustomBigInteger difference = a.subtract(b);
CustomBigInteger product = a.multiply(b);
CustomBigInteger quotient = a.divide(b);

System.out.println("Sum: " + sum);
System.out.println("Difference: " + difference);
System.out.println("Product: " + product);
System.out.println("Quotient: " + quotient);
```

## API Reference

### Constructor

- `CustomBigInteger(String s)`: Creates a new CustomBigInteger object from a string representation of an integer.

### Methods

- `add(CustomBigInteger other)`: Adds this CustomBigInteger with another and returns the result.
- `subtract(CustomBigInteger other)`: Subtracts another CustomBigInteger from this one and returns the result.
- `multiply(CustomBigInteger other)`: Multiplies this CustomBigInteger with another and returns the result.
- `divide(CustomBigInteger divisor)`: Divides this CustomBigInteger by another and returns the quotient.
- `abs()`: Returns the absolute value of this CustomBigInteger.
- `compareTo(CustomBigInteger other)`: Compares this CustomBigInteger with another. Returns 1 if this is greater, -1 if less, and 0 if equal.
- `toString()`: Returns a string representation of this CustomBigInteger.

## Examples

Here are some examples of operations you can perform with CustomBigInteger:

```java
CustomBigInteger a = new CustomBigInteger("999999999999999999");
CustomBigInteger b = new CustomBigInteger("1");
System.out.println(a.add(b)); // Output: 1000000000000000000

CustomBigInteger c = new CustomBigInteger("-123456789");
CustomBigInteger d = new CustomBigInteger("987654321");
System.out.println(c.subtract(d)); // Output: -1111111110

CustomBigInteger e = new CustomBigInteger("123456789");
CustomBigInteger f = new CustomBigInteger("987654321");
System.out.println(e.multiply(f)); // Output: 121932631112635269

CustomBigInteger g = new CustomBigInteger("1234567890123456789");
CustomBigInteger h = new CustomBigInteger("987654321");
System.out.println(g.divide(h)); // Output: 1249999988
```

## Contributing

Contributions to CustomBigInteger are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- This project was inspired by Java's built-in BigInteger class.
- Special thanks to all contributors and users of this library.
