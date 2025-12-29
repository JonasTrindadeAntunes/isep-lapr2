# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

* _are common across several US/UC;_
* _are not related to US/UC, namely: Audit, Reporting and Security._


* This software's objective is to manage the COVID-19's vaccination process in Portugal.
* It can be also used for vaccination processes of other future pandemic-related diseases.
* The application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits.

 
## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._


* The application should both support at least Portuguese and English languages. 
* This software should be usable anytime.


## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._


* The data should persist through two runs of the application.
* The application should be functional wohtout failures.


## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._



* This application should be available on health centers.
* It should be able to start-up in less than 8 seconds and the response time should be fast.
* It must hold at least 5 different kinds of users (SNS user, nurse, receptionist, health center coordinator and administrator).


## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._ 


* The software will have unit tests, making it easier to detect errors.
* It must be easy to use.
* It must be compatible with phones and computers.
* It should be configured easily to any kind of pandemic. 


## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._
  
* During the project we will be writing the program in Java on the Intelij IDE
* The graphical interface to be developed in is JavaFX 11.


### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._


* There must be a set of modules for each language.
* Should be executable on Mac and Windows
* The application needs classes of objects to work effeciently.
* The JaCoCo plugin should generate the coverage report.
* Javadoc must be used to document the code. 


### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the system being developed with other external systems._


* The application must be able to differentiate what commands to allow for each role.


### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._


* The hardware used to run the application must be phones and desktop/laptop computers.  
