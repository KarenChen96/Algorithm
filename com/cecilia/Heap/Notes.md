## Exception in Java
https://docs.oracle.com/javase/tutorial/essential/exceptions/catchOrDeclare.html

Checked Exception: These are exceptional conditions that a well-written app should anticipate the recover from. 

Unchecked Exception | Description | Example | Notes
--- | --- | --- | --- |
Runtime Exception | These are exceptional conditions that are internal to the application, and that the application usually cannot anticipate or recover from. These usually indicate programming bugs, such as logic errors or improper use of an API. | IllegalArgumentException, ArrayIndexOutOfBoundException, NullPointerException | 1. These exceptions happen during the runtime, they can't be anticipated. 2. The application can catch this exception, but it probably makes more sense to eliminate the bug that caused the exception to occur.
Error | These are exceptional conditions that are **external** to the application, and that the application usually cannot anticipate or recover from. | java.io.IOError | An application might choose to catch this exception, in order to notify the user of the problem — but it also might make sense for the program to print a stack trace and exit.|