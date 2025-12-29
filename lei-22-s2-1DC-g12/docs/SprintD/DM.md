# OO Analysis #

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used. 

## Rationale to identify domain conceptual classes ##
To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development". 


### _Conceptual Class Category List_ ###

**Business Transactions**

* Vaccine Schedule

---

**Transaction Line Items**

*  Vaccine

---

**Product/Service related to a Transaction or Transaction Line Item**

* Vaccine Administration
* Vaccination Certificate 

---


**Transaction Records**

* Digital Certificate
* **Center Performance Analysis**
* User Health History
* Vaccine Administration 

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

*  Health Care Centers
*  Community Mass Vaccination Center

---


**Descriptions of Things**

* Vaccine Type
* **Center Performance Analysis**

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



| Concept (A) 			                   |  Association   			  |                   Concept (B) |
|-----------------------------------|:-------------------:|------------------------------:|		
| CommunityMassVaccinationCenter  	 | can administer    	 |                   VaccineType |
| Company 				                      |    has    		 		     |                 Administrator |
| 					                             |      has					       |                      Platform |
| 					                             |     manages				     |             VaccinationCenter |
| Day                               |         has         |                          Slot |
| Digital Certificate	              |       has				       |                   VaccineType |
| Employee			                       |   registers in		    |                      Platform |
| HealthCareCenter  		              | can administer  		  |                   VaccineType |
| Nurse	  			                       | checks list    		 	 |                   WaitingRoom |
| 					                             |    delivers				     |        VaccinationCertificate |
| 					                             |  sends user to 			  |                  RecoveryRoom |	
| Receptionist  			                 |    confirms				     |               VaccineSchedule |	
| RecoveryRoom  			                 | sends user a    		  |                  Notification |
| 					                             |      has					       |                       SNSUser |
| SNSUser	 		 	                     |  requests    		 	   |            DigitalCertificate |
|                                   |       **has**       |           **AdverseReaction** |
| ScheduleValidation	  	            |   emits    		 		    |                  Notification |
| Slot                              |         has         |               VaccineSchedule |
| 					                             |    confirms				     |               VaccineSchedule |
| VaccinationCenter  		             |   applies    			    |                       Vaccine |
| 					                             |      has					       |         VaccineAdministration |
|                                   |                     | **CenterPerformanceAnalysis** |
|                                   |  **is managed by**  |         **CenterCoordinator** |
| Vaccine	  			                     |   is of    			 	    |                   VaccineType |
| 					                             |      has					       |         AdministrationProcess |
| VaccineAdministration  	          | is done by    		 	  |                         Nurse |
| 					                             |    fulfilling			    |               VaccineSchedule |
| 					                             | administrated on		  |                       SNSUser |
| 					                             |       of					       |                       Vaccine |
| 					                             |       has				       |               AdverseReaction |
| 					                             |       has				       |            DigitalCertificate |
| 					                             | user gets sent to		 |                  Waiting Room |
| VaccinationCenter                 |         has         |                           Day |
|                                   |                     | **CenterPerformanceAnalysis** |
| VaccineSchedule  		               | created for    		 	 |                       SNSUser |
| 					                             |    for taking			    |                       Vaccine |
| 					                             |    registers				    |               UserArrivalDate |
| 					                             |      emits				      |                  Notification |
| WatingRoom			                     |      has					       |                       SNSUser |
| Platform                          |     register in     |                   VaccineType |
| Company                           |         has         |                      Platform |






## Domain Model

**Do NOT forget to identify concepts atributes too.**

**Insert below the Domain Model Diagram in a SVG format**

![Domain_Model.svg](Domain_Model.svg)



