set activity;	#Tegund af hreyfingu

param cal {activity} >= 0; #kaloríur sem þú brennir við að hreyfa þig.
param max_hours {activity} >= 0; #Max klst sem þú eyðir i hreyfingu á viku.

var Time {i in activity} >= 0, <= max_hours[i]; #Fjöldi klst á viku eytt í ákveðna hreyfingu

minimize Total_time: sum {j in activity} Time[j]; #minnsti tíminn varið i alla hreyfingu
subject to calories: sum {i in activity} Time[i] * cal[i] >= 2000; 
		#Klst á viku af hreyfingu þar sem kaloríur sem þú brennir við hreyfingu verða að vera >= 2000 á viku

