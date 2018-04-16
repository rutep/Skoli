NanoMorphoLexer.class NanoMorphoParser.class: NanoMorphoLexer.java NanoMorphoParser.java
	javac NanoMorphoLexer.java NanoMorphoParser.java
NanoMorphoLexer.java: nanomorpholexer.jflex
	java -jar jflex-1.6.1.jar nanomorpholexer.jflex
NanoMorphoParser.java NanoMorphoParserVal.java: nanomorphoparser.byaccj
	./byacc -Jclass=NanoMorphoParser nanomorphoparser.byaccj
clean:
	rm -Rf *~ *.class NanoMorphoLexer.java NanoMorphoParser.java NanoMorphoParserVal.java yacc* *.masm *.mexe
test: NanoMorphoLexer.class NanoMorphoParser.class test.nm
	java NanoMorphoParser test.nm > test.masm
	java -jar morpho.jar -c test.masm
	java -jar morpho.jar test