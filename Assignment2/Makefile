FLAGS = -g -cp ./src -d ./build/
JAVAC = javac

.PHONY: build clean run

build:
	$(JAVAC) $(FLAGS) ./src/*.java

clean:
	rm -rf ./build

# Run stage - build the project and run it
run: build
	java -cp ./build Solution
	