# US 9 - To register a vaccination center 

## 1. Requirements Engineering


### 1.1. User Story Description


As an Administrator , I want to register a vaccination center to respond to a certain pandemic.



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	The DGS vaccination process is mainly carried out through community mass vaccination centers
and health care centers distributed across the country. Different from the health care centers, which
provide a wide range of healthcare services to citizens in a certain area, the community mass
vaccination centers are facilities specifically created to administer vaccines of a single type as
response to an ongoing disease outbreak (e.g.: Covid-19).The main difference between the two kinds of centers is
that a healthcare center is associated with a given ARS (Administração Regional de Saúde) and
AGES (Agrupamentos de Centros de Saúde), and it can administer any type of vaccines (e.g.:
Covid-19, Dengue, Tetanus, smallpox).


>	An Administrator is responsible for properly configuring and managing the core information (e.g.:
type of vaccines, vaccines, vaccination centers, employees) required for this application to be
operated daily by SNS users, nurses, receptionists, etc.

>	The DGS has Administrators who administer the application. Any Administrator uses the
application to register centers, SNS users, center coordinators, receptionists, and nurses enrolled in
the vaccination process.

> Both kinds of vaccination centers are characterized by a name, an address, a phone number, an e-mail address, a
fax number, a website address, opening and closing hours, slot duration (e.g.: 5 minutes) and the
maximum number of vaccines that can be given per slot (e.g.: 10 vaccines per slot)



**From the client clarifications:**

> **Question:** 
>  
> **Answer:** 

-

> Client didn't responde to any question in forum "Client Forum - Requirements Clarification" about US09.

### 1.3. Acceptance Criteria


* **AC1:** All attributes are mandatory(none of them can be null).
* **AC2:** When registering a vaccination center with an already existing reference, the system must reject such operation and the Administrator must have to start another register process.

### 1.4. Found out Dependencies


* There is no dependency to "US 9 To register a vaccination center" .


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
    * a name 
    * an address 
    * a phoneNumber
    * an emailAddress
    * a faxNumber
    * a websiteAddress
    * openingHours
    * closingHours
    * slotDuration
    * maxNumberVaccines
  
* Selected data:
	* Specifying type of vaccination center 


**Output Data:**

* List of existing types of vaccination centers
* Shows the data inserted for the vaccination center and request a confirmation
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Alternative 1**

![US09_SSD](US09_SSD.svg)




**Other alternatives might exist.**

### 1.7 Other Relevant Remarks

* Vaccination center can be of two types CMVC and HCC.


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 

![US09_MD](US09_MD.svg)

### 2.2. Other Remarks

n/a


## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for...                 | Answer                              | Justification (with patterns)                                                                                                     |
|:---------------|:------------------------------------------------------------|:------------------------------------|:----------------------------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?                            | RegisterVaccinationCenterUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                     |
| 			  		        | 	... coordinating the US?                                   | RegisterVaccinationCenterController | Controller                                                                                                                        |                                          |
| 						         | ...knowing the app instance?                                | App                                 | Creator(rule 3): App closely uses Singleton                                                                                       |
|                | ...knowing the company?                                     | App	                                | Creator(Rule 4): App has the data used to initialize Company                                                                      |
| 	              | ...knowing the platform?                                    | Company                             | Creator(Rule 4): Company has the data used to initialize Platform                                                                 |
| 			  		        | 	... getting the register(list) of vaccination center?      | Platform                            | IE:  in the DM Platform is responsible for all the registers                                                                      |
| 			  		        | 	... knowing the types of vaccination center to show?       | RegisterVaccinationCenter           | HC + LC:   knows its types of centers                                                                                             |
| 			  		        | 							                                                     | RegisterVaccinationCenter           | IE: knows/has its own Vaccination Center                                                                                          |
| Step 2  		     | 							                                                     |                                     |                                                                                                                                   |
| Step 3         | ...holds temporarily the select type of vaccination center? | RegisterVaccinationCenterUI         | IE : Holding on temporarily the type of vaccination center select before passing it, after asking for the vaccination center data |
| Step 4         | |
| Step 5         | ...instantiating a new Vaccination Center?                  | RegisterVaccinationCenter           | Creator (Rule 1): in the DM Platform register Vaccination Center.                                                                 |
| 		             | 	...saving the inputted data?                               | VaccinationCenter                   | IE: object created in step 5 has its own data.                                                                                    |
| 		             | 	... validating all data (local validation)?                | VaccinationCenter                   | IE: owns its data.                                                                                                                | 
| 			  		        | 	... validating all data (global validation)?               | RegisterVaccinationCenter           | HC + LC: knows all its vaccination centers.                                                                                       | 
| Step 6         ||
| Step 7         |... validating all data (global validation)?               | RegisterVaccinationCenter           | HC + LC: knows all its vaccination centers.                                                                                       | 
| 			  		        | 	... saving the created vaccination center?                 | RegisterVaccinationCenter           | HC + LC: owns all its vaccination centers.                                                                                        | 
| Step 8  		     | 	... informing operation success?                           | RegisterVaccinationCenterUI         | IE: is responsible for user interactions.                                                                                         | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Company
 * Platform
 * VaccinationCenter
 * CommunityMassVaccinationCenter
 * HealthCareCenter

Other software classes (i.e. Pure Fabrication) identified: 

 * RegisterVaccinationCenterUI  
 * RegisterVaccinationCenterController


## 3.2. Sequence Diagram (SD)

**Alternative 1**

![US09_SD](US09_SD.svg)



## 3.3. Class Diagram (CD)

**From alternative 1**

![US09_CD](US09_CD.svg)

# 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the VaccinationCenter class with null values. 

	@Test
    public void ensureNullIsNotAllowed() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CommunityMassVaccinationCenter(null, null, null, null, null,null,null,null,null,null);
        });

        String expectedMessage = "None of the arguments can be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }



*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)


## Class RegisterVaccinationCenterUI

    private RegisterVaccinationCenterController m_controller;


    public RegisterVaccinationCenterUI(){this.m_controller = new RegisterVaccinationCenterController();}

    public void run(){

        System.out.println("\n Register Vaccination Center");


        int typeOfVaccinationCenter = Utils.showAndSelectIndex(this.m_controller.getTypesOfVaccinationCenter(),"SELECT VACCINATION CENTER TYPE:");

        enterData(typeOfVaccinationCenter);
        showData();

        if(Utils.confirm("Confirms the data? (S/N)" )){
            if(m_controller.registerVaccinationCenter()){
                System.out.println("Registration successful");
            }else{
                System.out.println("It was not possible to conclude the registration successfully");
            }
        }

    }

    private boolean enterData(int typeOfVaccinationCenter){

        String name = Utils.readLineFromConsole("Name: ");
        String address = Utils.readLineFromConsole("Address: ");
        String phoneNumber = Utils.readLineFromConsole("Phone Number: ");
        String emailAddress = Utils.readLineFromConsole("Email Address: ");
        String faxNumber = Utils.readLineFromConsole("faxNumber: ");
        String websiteAddress = Utils.readLineFromConsole("websiteAddress: ");
        Double openingHours = Utils.readDoubleFromConsole("openingHours: ");
        Double closingHours = Utils.readDoubleFromConsole("closingHours: ");
        Double slotDuration = Utils.readDoubleFromConsole("slotDuration: ");
        int maxNumberVaccines = Utils.readIntegerFromConsole("maxNumberVaccines: ");


        return m_controller.newVaccinationCenter(name,address,phoneNumber,emailAddress,faxNumber,websiteAddress,openingHours,closingHours,slotDuration,maxNumberVaccines,typeOfVaccinationCenter);

    }

    private void showData(){System.out.println(m_controller.getVaccinationCenterString());}


## Class RegisterVaccinationCenterController


    private App m_oApp;
    private Platform m_oPlatform;
    private Company m_oCompany;
    private VaccinationCenter m_oVaccinationCenter;

    public RegisterVaccinationCenterController() {
        this.m_oApp = App.getInstance();
        this.m_oCompany = m_oApp.getCompany();
        this.m_oPlatform = m_oCompany.getPlatform();

    }

    public boolean newVaccinationCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress, Double openingHours, Double closingHours, Double slotDuration, Integer maxNumberVaccines,int typeOfVaccinationCenter)
    {
        try
        {
            this.m_oVaccinationCenter = this.m_oPlatform.getRegisterVaccinationCenter().newVaccinationCenter(name,address,phoneNumber,emailAddress,faxNumber,websiteAddress,openingHours,closingHours,slotDuration,maxNumberVaccines,typeOfVaccinationCenter);
            return this.m_oPlatform.getRegisterVaccinationCenter().validateVaccinationCenter(m_oVaccinationCenter);

        }
        catch(RuntimeException ex)
        {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            this.m_oVaccinationCenter = null;
            return false;
        }
    }

    public boolean registerVaccinationCenter(){return this.m_oPlatform.getRegisterVaccinationCenter().registerVaccinationCenter(m_oVaccinationCenter);}

    public List<String> getTypesOfVaccinationCenter(){return this.m_oPlatform.getRegisterVaccinationCenter().getTypesOfVaccinationCenter();}

    public String getVaccinationCenterString(){return this.m_oVaccinationCenter.toString();}

## Class Company


    private String designation;
    private AuthFacade authFacade;
    private Platform m_oPlatform;

    public Company(String designation)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.m_oPlatform = new Platform("Registers Platform");

    }
    ///...ommited
    
    public AuthFacade getAuthFacade() {return authFacade;}
    public Platform getPlatform(){return m_oPlatform;}

    }


## Class Platform


      private String description;

    // ... (omitted)
    private final RegisterVaccinationCenter m_oRegisterVaccinationCenter;
    // ... (omitted)

    public Platform(String description) {
        if((description == null) || (description.isEmpty()))
            throw new IllegalArgumentException("argument can be null or empty");

        this.description = description;

        this.m_oRegisterVaccinationCenter = new RegisterVaccinationCenter();
        this.m_oRegisterVaccineType = new RegisterVaccineType();
        
    }

    public RegisterVaccinationCenter getRegisterVaccinationCenter(){return this.m_oRegisterVaccinationCenter;}
    // ... (omitted)


## Class VaccinationCenter

    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String faxNumber;
    private String websiteAddress;
    private Double openingHours;
    private Double closingHours;
    private Double slotDuration;
    private Integer maxNumberVaccines;

  
    public VaccinationCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress, Double openingHours, Double closingHours, Double slotDuration, Integer maxNumberVaccines) {

        if((name == null) || (name.isEmpty()) || (address == null) || (address.isEmpty()) || (phoneNumber == null) || (phoneNumber.isEmpty()) ||
            (emailAddress == null) || (emailAddress.isEmpty()) || (faxNumber == null) || (faxNumber.isEmpty()) || (websiteAddress == null) || (websiteAddress.isEmpty()) ||
                (openingHours == null) || (openingHours.isNaN()) || (closingHours == null) || (closingHours.isNaN()) ||
                    (slotDuration == null) || (slotDuration.isNaN()) || (maxNumberVaccines == null))
                    throw new IllegalArgumentException("None of the arguments can be null or empty");

        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.faxNumber = faxNumber;
        this.websiteAddress = websiteAddress;
        this.openingHours = openingHours;
        this.closingHours = closingHours;
        this.slotDuration = slotDuration;
        this.maxNumberVaccines = maxNumberVaccines;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public String getEmailAddress() {return emailAddress;}
    public void setEmailAddress(String emailAddress) {this.emailAddress = emailAddress;}
    public String getFaxNumber() {return faxNumber;}
    public void setFaxNumber(String faxNumber) {this.faxNumber = faxNumber;}
    public String getWebsiteAddress() {return websiteAddress;}
    public void setWebsiteAddress(String websiteAddress) {this.websiteAddress = websiteAddress;}
    public Double getOpeningHours() {return openingHours;}
    public void setOpeningHours(Double openingHours) {this.openingHours = openingHours;}
    public Double getClosingHours() {return closingHours;}
    public void setClosingHours(Double closingHours) {this.closingHours = closingHours;}
    public Double getSlotDuration() {return slotDuration;}
    public void setSlotDuration(Double slotDuration) {this.slotDuration = slotDuration;}
    public Integer getMaxNumberVaccines() {return maxNumberVaccines;}
    public void setMaxNumberVaccines(Integer maxNumberVaccines) {this.maxNumberVaccines = maxNumberVaccines;}


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.address);
        return hash;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        VaccinationCenter center = (VaccinationCenter) o;
        return this.address.equalsIgnoreCase(center.address);
    }

    @Override
    public String toString() {
        String str = String.format("Vaccination Center name: %s\nAddress: %s\nPhone Number: %s\nEmailAddress: %s\nFaxNumber: %s\nWebsiteAddress: %s\nOpeningHours: %s\nClosingHours: %s\nSlotDuration: %s\nMaxNumberVaccines: %s\n",
                                    this.name,this.address,this.phoneNumber,this.emailAddress,this.faxNumber,this.websiteAddress,this.openingHours,this.closingHours,this.slotDuration,this.maxNumberVaccines);
        return str;
    }

## Class CommunityMassVaccinationCenter

    public CommunityMassVaccinationCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress, Double openingHours, Double closingHours, Double slotDuration, Integer maxNumberVaccines) {
        super(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours, closingHours, slotDuration, maxNumberVaccines);
    }

## Class HealthCareCenter

    public HealthCareCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress, Double openingHours, Double closingHours, Double slotDuration, Integer maxNumberVaccines) {
        super(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours, closingHours, slotDuration, maxNumberVaccines);
    }


# 6. Integration and Demo 

* A new option on the Administrator menu options was added.



# 7. Observations







