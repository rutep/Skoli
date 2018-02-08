set employees_type; 			#Tegund vaktaplans fr� 0 og upp � 9
set employee_per_day;		#Fj�ldi starfsmanna � hverjum degi

param minimum_employees{employee_per_day};				#Minnsti leyfilegi fj�ldi starfsmanna fyrir hvern dag.
param shift_on_day{employee_per_day, employees_type};	#Hva�a vakt er � hva�a degi.

var num_of_employee_of_type {p in employees_type} >= 0;	#Fj�ldi starfsmanna � vakt af ger� 0 og upp � 9.

minimize total_employees: sum{i in employees_type} num_of_employee_of_type[i];	#minimize heildarfj�lda starfsmanna af �llum vaktarger�um.

subject to employees_limit {i in employee_per_day}: # subject to minnsti leyfilegi fjoldi starfsmanna a hverjum degi
	minimum_employees[i] <= sum {j in employees_type} shift_on_day[i,j] * num_of_employee_of_type[j]  ;
