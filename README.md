# betting-host
Tote betting host

**How to run this application:**

* To build the application:
	`./gradlew clean build`

* To run the tests:
	`./gradlew test`

* To execute the application:
	`./gradlew run -Dexec.args="input.csv"`

I have created an input file (input.csv) in the root folder for convenience, which could be used for giving the input.

**Sample Input:**
```
Bet:W:1:3
Bet:W:2:4
Bet:W:3:5
Bet:W:4:5
Bet:W:1:16
Bet:W:2:8
Bet:W:3:22
Bet:W:4:57
Bet:W:1:42
Bet:W:2:98
Bet:W:3:63
Bet:W:4:15
Result:2:3:1
```
**Sample Output:**
```
W:2:$2.62
```