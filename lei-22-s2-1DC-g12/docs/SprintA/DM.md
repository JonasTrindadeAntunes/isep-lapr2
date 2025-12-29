# OO Analysis #

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used. 

## Rationale to identify domain conceptual classes ##
To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development". 


### _Conceptual Class Category List_ ###

**Business Transactions**

* Vaccine Schedule

---

**Transaction Line Items**

* 

---

**Product/Service related to a Transaction or Transaction Line Item**

* Vaccine Administration
* Vaccination Certificate 

---


**Transaction Records**

* Digital Certificate
* Report
* User Health History

---  


**Roles of People or Organizations**

* Administrator 
* Center Coordinator
* Nurse
* Receptionist
* SNSUser
---


**Places**

* Healthcare Center 
* Community Mass Vaccination Center
* Recovery Room
* Vaccination Center
* Waiting Room

---

** Noteworthy Events**

* Vaccine Schedule
* Vaccine Administration

---


**Physical Objects**

* Vaccine 

---


**Descriptions of Things**

* Vaccine Type
* Report Type 
* Analysis

---


**Catalogs**

*  

---


**Containers**

*  

---


**Elements of Containers**

*  

---


**Organizations**

* DGS (Company)

---

**Other External/Collaborating Systems**

*  Platform


---


**Records of finance, work, contracts, legal matters**

* 

---


**Financial Instruments**

*  

---


**Documents mentioned/used to perform some work/**

*
---



###**Rationale to identify associations between conceptual classes**###

An association is a relationship between instances of objects that indicates a relevant connection and that is worth of remembering, or it is derivable from the List of Common Associations: 

+ **_A_** is physically or logically part of **_B_**
+ **_A_** is physically or logically contained in/on **_B_**
+ **_A_** is a description for **_B_**
+ **_A_** known/logged/recorded/reported/captured in **_B_**
+ **_A_** uses or manages or owns **_B_**
+ **_A_** is related with a transaction (item) of **_B_**
+ etc.



| Concept (A) 		|  Association   	|  Concept (B) |
|----------	   		|:-------------:		|------:       |	
| **AdverseReaction**		| has				| **Vaccine** |	
| **Analysis**			| gets analised by 			| **Report** |
| **CenterCoordinator**  	| manages					| **VaccinationCenter** |
|				| analyses 					| **Analysis** |
|				| registers in				| **Platform** |
| **CommunityMassVaccinationCenter**  	| can administer    	| **VaccineType**  |
| **Company** 	| has    		 		| **Administrator**  |
|			| has					| **Platform** |
|			| manages				| **VaccinationCenter** |
| **HealthCareCenter**  	| can administer  | **VaccineType**  |
| **Nurse**  	| checks list    		 	| **WaitingRoom**  |
|			| delivers				| **VaccinationCertificate** |
|			| sends user to 			| **RecoveryRoom** |
|			| registers in			| **Platform** |	
| **Receptionist**  	| confirms			| **VaccineSchedule** |
|				| registers in		| **Platform** |	
| **RecoveryRoom**  	| sends user a    	| **Notification**  |
|				| has				| **SNSUser** |
| **Report**	  	| is of    		 	| **ReportType** |
| **SNSUser**  	| requests    		 	| **DigitalCertificate** |
|			| registers in			| **Platform** |
|			| has					| **HealthHistory** |	
| **ScheduleValidation**  	| emits    		 	| **Notification**  |
|					| confirms			| **VaccineSchedule** |
| **UserHealthHistory**	| has				| **AdverseReaction** |
| **VaccinationCenter**  	| applies    		 	| **Vaccine**  |
|					| reports				| **Report** |
| **Vaccine**  	| is of    		 	| **VaccineType**  |
|			| has				| **AdministrationProcess** |
| **VaccineAdministration**  	| is done by    		 	| **Nurse**  |
|					| fulfilling			| **VaccineSchedule** |
|					| administrated on		| **SNSUser** |
|					| of					| **Vaccine** |
| **VaccineSchedule**  	| created for    		 	| **SNSUser** |
|				| for taking			| **VaccineType** |
|				| registers				| **UserArrivalDate** |
| **WatingRoom**		| has				| **SNSUser** |





## Domain Model

**Do NOT forget to identify concepts atributes too.**

**Insert below the Domain Model Diagram in a SVG format**

![DM.svg](DM.svg)



