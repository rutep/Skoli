set employees_type; 			#Tegund vaktaplans frá 0 og upp í 9
set employee_per_day;		#Fjöldi starfsmanna á hverjum degi

param minimum_employees{employee_per_day};				#Minnsti leyfilegi fjöldi starfsmanna fyrir hvern dag.
param shift_on_day{employee_per_day, employees_type};	#Hvaða vakt er á hvaða degi.

var num_of_employee_of_type {p in employees_type} >= 0;	#Fjöldi starfsmanna á vakt af gerð 0 og upp í 9.

minimize total_employees: sum{i in employees_type} num_of_employee_of_type[i];	#minimize heildarfjölda starfsmanna af öllum vaktargerðum.

subject to employees_limit {i in employee_per_day}: # subject to minnsti leyfilegi fjoldi starfsmanna a hverjum degi
	minimum_employees[i] <= sum {j in employees_type} shift_on_day[i,j] * num_of_employee_of_type[j]  ;
