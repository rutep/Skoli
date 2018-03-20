# Makefil fyrir neðan sækin þýðanda
SHELL=/usr/bin/env /bin/bash


# Keyrir byacc srká með J
NanoMorpho.byaccj: NanoMorpho.byaccj
	byacc -J -Jclass=NanoMorphoParser NanoMorpho.byaccj

# ATH árekstra
NanoMorphoV: NanoMorpho.byaccj
	byacc -v NanoMorpho.byaccj

# Þíða parser skrár
parser: NanoMorphoParser.java NanoMorphoParserVal.java NanoMorphoLexer.java
	javac NanoMorphoLexer.java NanoMorphoParser.java NanoMorphoParserVal.java

# keyra jflex skrá
lesgreinir: nanoMorpho.jflex
	java -jar JFlex-1.6.1.jar nanomorpho.jflex

# keyrir test.s skrá
test: NanoMorphoParser.class NanoMorphoParserVal.class
	java NanoMorphoParser test.s
