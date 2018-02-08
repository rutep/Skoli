set activity;	#Tegund af hreyfingu

param cal {activity} >= 0; #kalor�ur sem �� brennir vi� a� hreyfa �ig.
param max_hours {activity} >= 0; #Max klst sem �� ey�ir i hreyfingu � viku.

var Time {i in activity} >= 0, <= max_hours[i]; #Fj�ldi klst � viku eytt � �kve�na hreyfingu

minimize Total_time: sum {j in activity} Time[j]; #minnsti t�minn vari� i alla hreyfingu
subject to calories: sum {i in activity} Time[i] * cal[i] >= 2000; 
		#Klst � viku af hreyfingu �ar sem kalor�ur sem �� brennir vi� hreyfingu ver�a a� vera >= 2000 � viku

