all:
	javac -d classes -sourcepath src src/org/ioopm/calculator/Calculator.java
	javac -d classes -sourcepath src src/org/ioopm/calculator/Test.java

run:
	java -cp classes org.ioopm.calculator.Calculator

test:
	java -cp classes org.ioopm.calculator.Test

clean:
	rm -rf classes

.PHONY: all run clean