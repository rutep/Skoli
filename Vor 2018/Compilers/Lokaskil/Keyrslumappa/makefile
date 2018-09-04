# Makefil fyrir neðan sækin þýðanda
SHELL=/usr/bin/env /bin/bash


# Þíða parser skrár
parser: NanoMorphoParser.java NanoMorphoParserVal.java NanoMorphoLexer.java
	javac NanoMorphoLexer.java NanoMorphoParser.java NanoMorphoParserVal.java

# Þýðir test.s skrá yfir í morpho smalarmál
test: NanoMorphoParser.class NanoMorphoParserVal.class
	java NanoMorphoParser test.s > test.masm 

# Þýðir lokaÞuluna
compile: test.masm
	morpho -c test.masm

# keyrir NanoMorpho forrit
run: test.mexe
	morpho test 
