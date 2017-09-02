##Makefile to compile
##Zach Nudelman
##24 August 2017

LIB = ../lib
SRCDIR = src
BINDIR = bin
TESTDIR = test
DOCDIR = doc
CLI = $(LIB)/cli/commons-cli-1.3.1.jar
ASM = $(LIB)/asm/asm-5.0.4.jar:$(LIB)/asm/asm-commons-5.0.4.jar:$(LIB)/asm/asm-tree-5.0.4.jar
JUNIT = $(LIB)/junit/junit-4.12.jar:$(LIB)/junit/hamcrest-core-1.3.jar
JACOCO = $(LIB)/jacoco/org.jacoco.core-0.7.5.201505241946.jar:$(LIB)/jacoco/org.jacoco.report-0.7.5.201505241946.jar:
TOOLS = $(LIB)/tools
JAVAC = /usr/bin/javac
JFLAGS = -g -d $(BINDIR) $(SRCDIR)/*.java -cp $(BINDIR):$(JUNIT)
FSIZE = 3
ARRSIZE = 100000
SC = 200

vpath %.java $(SRCDIR):$(TESTDIR)
vpath %.class $(BINDIR)
vpath %.exec coverage

# define general build rule for java sources
.SUFFIXES:  .java  .class

.java.class:
	$(JAVAC)  $(JFLAGS)  $<

#default rule - will be invoked by make
all: MedianFilter.class \
				ParallelFilter.class \
				Main.class \
				Querygen.class \



# Rule for generating documentation
doc:
	$(NO)

#Rules for executing applications
MF: all
		java -cp ./bin MedianFilter

Experiment: all
	java -cp ./bin MainExperiment $(FSIZE) $(ARRSIZE) $(SC)

Main: clean all
	java -cp ./bin Main "sampleinputfile.txt" $(FSIZE)


#Self-defined

Everything: clean all Experiment


#Cleans folders
clean:
	@rm -f  $(BINDIR)/*.class
	@rm -Rf doc
	@rm -Rf Resources/*.csv
