# StdIn/StdOut Unit Test Example

Small Tests reading from a file or String to StdIn and read the results from StdOut.
The goal of this project is to setup a test environment for a class within a few seconds.


## Usage
- Extend test class from SystemInOutTest.
- Implement abstract method classUnderTest() to return fully qualified class name.
- Use run...(input) methods to read input
- Test output against outContent/errContent Stream.

## History

2016.09.18:
- introduced abstract base class
- moved to package
- runInput/runFile to read input in one line
- documentation
    
    
## Todo
- Error handling
- Make StdOut redirection configurable
- Remove the need to supply '\n' in assertions.
- Make output testable against a file, perhaps with a pattern
