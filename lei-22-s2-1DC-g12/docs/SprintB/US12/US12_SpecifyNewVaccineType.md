# US 12 - To specify a new vaccine type

## 1. Requirements Engineering


### 1.1. User Story Description


AS an administrator, I intend to specify a new vaccine type



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

> The community mass vaccination centers are facilities specifically created to administer vaccines of a single type.
> Healthcare center […] can administer any type of vaccines (e.g.: Covid-19, Dengue, Tetanus, smallpox).
> For each type of vaccine, it might exist several vaccines […].




**From the client clarifications:**

> **Question:**  What are the data that characterize a parameter category?
> 
> **Answer:** Simply consider a code, a designation and an WHO identifier



> **Question:** "What is the size and requirements of the WHO identifier?"
> 
> **Answer:** We will not use the WHO identifier. The client did not introduce this attribute.

> **Question:** What kind of information would you like to include in a new type of vaccine?
> 
> **Answer:** The vaccine type should have the following attributes: Code (five alphanumeric characters), Short description and Vaccine technology. Please check the web page above to identify the six types of vaccine technologies that can be selected.
https://www.pfizer.com/news/articles/understanding_six_types_of_vaccine_technologies


### 1.3. Acceptance Criteria


* **AC1:** Code must be unique having 4 to 8 chars.
* **AC2:** Designation cannot be empty and has, at maximum, 40 chars.
* **AC3:** WHO identifier is not mandatory.


### 1.4. Found out Dependencies


* No dependencies were found.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
    * code
    * designation
    * WHO identified
	
* Selected data:
	* 


**Output Data:**

* (IN)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Alternative 1**

![US12_SSD](US12_SSD.svg)


**Other alternatives might exist.**

### 1.7 Other Relevant Remarks

* 


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 

![US12_MD](US12_MD.svg)

### 2.2. Other Remarks

n/a


## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for...        | Answer                          | Justification (with patterns)                                                                                 |
|:---------------|:---------------------------------------------------|:--------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?                   | SpecifyNewVaccineTypeUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                          | SpecifyNewVaccineTypeController | Controller                                                                                                    |
| Step 2         |
| Step 3			  		  | 	... instantiating a new vaccine type?             | RegisterVaccineType             | Creator (Rule 1): RegisterVaccineType contains class VaccineType objects                                      |
| 			  		        | ... knowing the user using the system?             | UserSession                     | IE: cf. A&A component documentation.                                                                          |
| 			  		        | 	... knowing to which company the user belongs to? | App                             | IE: has registed all companys                                                                                 |
| 			  		        | 							                                            | Platform                        | IE: knows/has its own Vaccine type                                                                            |
| 			  		        | 							                                            | Vaccine Type                    | IE: knows its own data (e.g. code)                                                                            |
| Step 4         |
| Step 5  		     | 	...saving the inputted data?                      | RegisterVaccineType             | HC + LC: object created in step 1 has its own data.                                                           |                                                                                                  |              
| 		             | 	... validating all data (local validation)?       | VaccineType                     | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)?      | RegisterVaccineType             | HC + LC: knows all its vaccine types.                                                                         | 
| 			  		        | 	... saving the created vaccine type?              | RegisterVaccineType                    | HC + LC: owns all its vaccine types.                                                                          |
| Step 6  		     | 	... informing operation success?                  | SpecifyNewVaccineTypeUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Company
 * Platform
 * Administrator
 * VaccineType
 * RegisterVaccineType

Other software classes (i.e. Pure Fabrication) identified: 

 * SpecifyNewVaccineTypeUI  
 * SpecifyNewVaccineTypeController

Other classes off the system:

* App



## 3.2. Sequence Diagram (SD)

**Alternative 1**

![US12_SD](US12_SD.svg)


## 3.3. Class Diagram (CD)

**From alternative 1**

![US12_CD](US12_CD.svg)

# 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the VaccineType class 
            without:  Code must be unique having 4 to 8 chars. 

	@Test
    public void ensureWrongFormatCodeNotAllowed() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new VaccineType("123","Moderna","2552");
        });

        String expectedMessage = "Code must be between 4 to 8 chars and designation max 40 chars";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }


**Test 2:** Designation cannot be empty .

    @Test
    public void ensureDesignationCannotBeEmpty() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new VaccineType("1243","","2552");
        });

        String expectedMessage = "Code must be between 4 to 8 chars and designation max 40 chars";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

**Test 3:** Designation has at maximum 40 chars.

    @Test
    public void ensureDesignationMAXChars() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new VaccineType("1243","asdsadsadsadsadasdsadsadsaddasdsadsadasdsadsadasdsadsadsadasdsadsad","2552");
        });

        String expectedMessage = "Code must be between 4 to 8 chars and designation max 40 chars";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }



*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)


## Class SpecifyNewVaccineTypeUI 


    private SpecifyNewVaccineTypeController m_controller;

    public SpecifyNewVaccineTypeUI(){ this.m_controller = new SpecifyNewVaccineTypeController();}

    public void run(){

        System.out.println("\n Specify new Vaccine Type");

        enterData();
        showData();

        if(Utils.confirm("Confirms the data? (S/N)" )){
            if(m_controller.registerVaccineType()){
                System.out.println("Registration successful");
            }else{
                System.out.println("It was not possible to conclude the registration successfully");
            }
        }
        getListVaccineType();
    }

    private boolean enterData(){

        String code = Utils.readLineFromConsole("Code:");
        String designation = Utils.readLineFromConsole("Designation:");
        String whoID = Utils.readLineFromConsole("Who identifier:");

        return m_controller.newVaccineType(code,designation,whoID);
    }

    private void showData() {System.out.println(m_controller.getVaccineTypeString());}


    private void getListVaccineType(){
        Utils.showList(m_controller.getListVaccineType(),"Listvaccines type");
    }


## Class SpecifyNewVaccineTypeController

    private App m_oApp;
    private Company m_oCompany;
    private Platform m_oPlatform;
    private VaccineType m_oVaccineType;

    public SpecifyNewVaccineTypeController()
    {
        this.m_oApp = App.getInstance();
        this.m_oCompany = m_oApp.getCompany();
        this.m_oPlatform = m_oCompany.getPlatform();

    }

    public boolean newVaccineType(String code,String designation,String whoID)
    {
        try
        {
            this.m_oVaccineType = this.m_oPlatform.getRegisterVaccineType().newVaccineType(code,designation,whoID);
            return this.m_oPlatform.getRegisterVaccineType().validateVaccineType(m_oVaccineType);

        }
        catch(RuntimeException ex)
        {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            this.m_oVaccineType = null;
            return false;
        }

    }

    public boolean registerVaccineType(){return this.m_oPlatform.getRegisterVaccineType().registerVaccineType(m_oVaccineType);}

    public String getVaccineTypeString(){return m_oVaccineType.toString(); }

    public List<VaccineType> getListVaccineType(){return this.m_oPlatform.getRegisterVaccineType().getListVaccineType();}

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

    ///omitted...

    private final RegisterVaccineType m_oRegisterVaccineType;

    public Platform(String description) {
        if((description == null) || (description.isEmpty()))
            throw new IllegalArgumentException("argument can be null or empty");

        this.description = description;
    
    ///omitted...
    }
    
    ///omitted...
    public RegisterVaccineType getRegisterVaccineType() {return this.m_oRegisterVaccineType;}

    ///omitted...

    }

## Class RegisterVaccineType

    private List<VaccineType> listVaccineTypes = new ArrayList<>();

    public VaccineType newVaccineType(String code, String designation, String whoID)
    {
        return new VaccineType(code,designation,whoID);
    }

    public boolean registerVaccineType(VaccineType vac)
    {
        if(this.validateVaccineType(vac))
            return addVaccineType(vac);
        else
            return false;

    }

    private boolean addVaccineType(VaccineType vac){return this.listVaccineTypes.add(vac);}

    public boolean validateVaccineType(VaccineType vac)
    {
        for(VaccineType vacs : listVaccineTypes)
        {
            if(vacs.equals(vac)){
                return false;
            }
        }
        return true;
    }

    public List<VaccineType> getListVaccineType()
    {
        return listVaccineTypes;
    }


## Class VaccineType

    private String code;
    private String designation;
    private String whoID;
    private List<Vaccine> vacineList;

    public VaccineType(String code, String designation, String whoID) {
        if( (code.length() < 4) || (code.length() > 8) || (designation.isEmpty()) || (designation.length() > 40))
            throw new IllegalArgumentException("Code must be between 4 to 8 chars and designation max 40 chars");
        this.code = code;
        this.designation = designation;
        this.whoID = whoID;
        this.vacineList = new ArrayList<>();
    }

    public String getCode() {return code;}
    public void setCode(String code) {this.code = code;}
    public String getDesignation() {return designation;}
    public void setDesignation(String designation) {this.designation = designation;}
    public String getWhoID() {return whoID;}
    public void setWhoID(String whoID) {this.whoID = whoID;}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.code);
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

        VaccineType vt = (VaccineType) o;
        return this.code.equalsIgnoreCase(vt.code);
    }
    
    public boolean addVaccine(Vaccine vaccine){
        return this.vacineList.add(vaccine);
    }

    public boolean validateVaccine(Vaccine vaccine){
        return this.getVaccine(vaccine.getId()) == null;
    }

    public static Vaccine newVaccine(String id, String name) {
        return new Vaccine(id,name);
    }

    public Vaccine getVaccine(String id){
        for (Vaccine v : this.vacineList){
            if(v.getId() == id){
                return v;
            }
        }
        return null;
    }

    

    @Override
    public String toString() {
        String str = String.format("Vaccine Type\nCode: %s\nDesignation: %s\n Who identifier: %s\n",
                this.code,this.designation,this.whoID);
        return str;
    }

# 6. Integration and Demo 

* A new option on the administrator menu was added .




# 7. Observations







