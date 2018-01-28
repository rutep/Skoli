:Yfirlit skráa
    * NanoLisp.java er þýðandi
    * nanolisp er sama bara önnur aðferð. Notað jflex og biax

: Biax þáttara smiður

: test.s 
    * Dæmi um nonolisp forrit
    * ++ er samskeiting   
    * >javac NanoLisp.java // Til þess að þýða
    * java NanoLisp test.s // Forrit mun lesa úr skrá test.s
    * Svo þátta úr test.s og búa til tré svo fer það í
    * í loka þulusmið og það mun skrifa vélamáls kóða fyrir morpho.
    * >java NanoLisp test.s | java -jar morpho.jar -c //-c keirðu þýðandan
    * og nú er hægt að keira forritið.
    * java -jar morpho.jar test.

: nanolexer
    * jflex er lesgreinasmiður í java
    * makefile skrá sem hægt er að nota í unix
    * Áhuga á nanolexer.jflex
    * keira jflex þá býr hann til Nanolexer.java
    * >make // þá keirist lesgreinasmiðurinn les nanolexer.jflex
    * og býr til Nanolexer.java

: Þáttari
    * Kallar á lesgreinir og les inn streng
    * svo viljum við að lesgreinir flokkar þetta.
    