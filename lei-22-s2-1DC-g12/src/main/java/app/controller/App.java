package app.controller;

import app.domain.model.*;
import app.domain.model.Company;
import app.domain.model.Employee.CenterCoordinator;
import app.domain.model.Employee.Employee;
import app.domain.model.Serialization.ObjectDeserializer;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.AdministrationProcess;
import app.domain.model.Vaccine.Vaccine;
import app.domain.model.Vaccine.VaccineType;
import app.domain.shared.Constants;
import app.domain.shared.MessageBundle;
import app.tasks.RecordDailyTask;
import app.ui.view.Base;
import pt.isep.lei.esoft.auth.UserSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.*;


/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {

    private Company company;
    private Locale currentLocale;
    private Base base;
    private RecordDailyTask task;

    private App()
    {
        Properties props = getProperties();
        currentLocale = new Locale("en","US");
        setBundle();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION),props.getProperty(Constants.PARAMS_RECOVERY_TIME_DESIGNATION));
        bootstrap();
        dailyRecord();
    }

    public void deserialize(){
        ObjectDeserializer objDeserializer = new ObjectDeserializer(this);
        objDeserializer.deserialize();
    }

    public int getConfig(String configName) {
        Properties props = getProperties();
        return Integer.parseInt(props.getProperty(configName));
    }


    public void setBundle(){
        final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        if(jarFile.isFile()){
            InputStream inputStream;
            if(currentLocale.equals(new Locale("en","US")))
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("MessagesBundle_en_US.properties");
            else if(currentLocale.equals(new Locale("pt","PT")))
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("MessagesBundle_pt_PT.properties");
            else
                throw new RuntimeException("Invalid locale!");
            try {
                MessageBundle.setMessageBundle(new PropertyResourceBundle(inputStream));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            MessageBundle.setMessageBundle(ResourceBundle.getBundle("messagesBundle",currentLocale));
        }
    }

    public List<Locale> languageOptions(){
        List<Locale> list = new ArrayList<>();
        list.add(new Locale("en","US"));
        list.add(new Locale("pt","PT"));
        return list;
    }

    public Company getCompany()
    {
        return this.company;
    }


    public UserSession getCurrentUserSession()
    {
        return this.company.getUserRoleStore().getAuthFacade().getCurrentUserSession();
    }

    public boolean doLogin(String email, String pwd)
    {
        return this.company.getUserRoleStore().getAuthFacade().doLogin(email,pwd).isLoggedIn();
    }

    public void doLogout()
    {
        this.company.getUserRoleStore().getAuthFacade().doLogout();
    }

    private Properties getProperties()
    {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "DGS/SNS");

        // Read configured values
        try
        {
            final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
            if(jarFile.isFile()){
                InputStream inputStream;
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(Constants.PARAMS_FILENAME);
                props.load(inputStream);
                inputStream.close();
            }else{
                InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
                props.load(in);
                in.close();
            }
        }
        catch(IOException ex)
        {

        }
        return props;
    }


    private void bootstrap()
    {
        this.company.getUserRoleStore().addUserRole(Constants.ROLE_ADMIN,Constants.ROLE_ADMIN);
        this.company.getUserRoleStore().addUserRole(Constants.ROLE_SNS_USER,Constants.ROLE_SNS_USER);
        this.company.getUserRoleStore().addUserRole(Constants.ROLE_RECEPTIONIST,Constants.ROLE_RECEPTIONIST);
        this.company.getUserRoleStore().addUserRole(Constants.ROLE_NURSE,Constants.ROLE_NURSE);
        this.company.getUserRoleStore().addUserRole(Constants.ROLE_CENTER_COORDINATOR,Constants.ROLE_CENTER_COORDINATOR);


        this.company.getUserRoleStore().addUser("Main Administrator", "admin@lei.sem2.pt", "123456",Constants.ROLE_ADMIN);
        this.company.getUserRoleStore().addUser("joaquim", "centercoordinator@lei.sem2.pt", "123456",Constants.ROLE_CENTER_COORDINATOR);
        this.company.getUserRoleStore().addUser("joaquim", "receptionist@lei.sem2.pt", "123456",Constants.ROLE_RECEPTIONIST);
        this.company.getUserRoleStore().addUser("joaquim", "nurse@lei.sem2.pt", "123456",Constants.ROLE_NURSE);
        this.getCompany().getPlatform().initializeRegisters(this.company.getUserRoleStore());
        bootstrapVaccineType();
        bootstrapVaccinationCenter();
        bootstrapEmployees();
        bootstrapSNSUser();
        bootstrapVaccinationSchedules();
        bootstrapExportStatistics();
        bootstrapWaitingRoom();


    }


    public void bootstrapVaccinationCenter(){
        VaccinationCenter vacCentre = this.company.getPlatform().getRegisterVaccinationCenter().newVaccinationCenter("s joao", "porto","916597487","vaccinationcenterLAPR2@gmail.com","2525","www.sns.pt",8,0,20,0,10,10,0);
        this.company.getPlatform().getRegisterVaccinationCenter().registerVaccinationCenter(vacCentre);
        VaccineType vt = this.company.getPlatform().getRegisterVaccineType().getListVaccineType().get(0);
        Vaccine v = vt.getVaccineList().get(0);
        vacCentre.addVaccineType(vt);
        vacCentre.addVaccine(v);
    }

    public void bootstrapVaccineType(){
        VaccineType vt = this.company.getPlatform().getRegisterVaccineType().newVaccineType("6655","flu-A","mRna");
        VaccineType vt1 = this.company.getPlatform().getRegisterVaccineType().newVaccineType("1234","Covid","mRna");
        Vaccine v = new Vaccine("4455","Spikevax");
        Integer[] arr = new Integer[2];
        arr[0] = 8;
        arr[1] = 80;
        List<Integer[]> ageGroups = new ArrayList<>();
        ageGroups.add(arr);
        v.addAdministrationProcess(v.newAdministrationProcess(ageGroups,30.0,1,new ArrayList<>()));
        vt.addVaccine(v);
        this.company.getPlatform().getRegisterVaccineType().registerVaccineType(vt);
        this.company.getPlatform().getRegisterVaccineType().registerVaccineType(vt1);
    }

    public void bootstrapEmployees()
    {
        ////////RECEPTIONIST
        Employee emp = this.company.getPlatform().getRegisterEmployee().newEmployee("jonas","rua penalves","912222222","1181478@isep.ipp.pt","10101010",0);
        this.company.getPlatform().getRegisterEmployee().registerEmployee(emp);

        ////////NURSE
        Employee emp1 = this.company.getPlatform().getRegisterEmployee().newEmployee("gui","rua penalves","913333333","1191038@isep.ipp.pt","13111110",2);
        this.company.getPlatform().getRegisterEmployee().registerEmployee(emp1);

        ////////CENTER COORDINATOR
        Employee emp2 = this.company.getPlatform().getRegisterEmployee().newEmployee("pedro","rua das amelias","914444444","1211691@isep.ipp.pt","15934660",1);
        this.company.getPlatform().getRegisterEmployee().registerEmployee(emp2);
        VaccinationCenter vac = this.company.getPlatform().getRegisterVaccinationCenter().getVaccinationCenter("vaccinationcenterLAPR2@gmail.com");
        vac.setManageVaccinationCenter((CenterCoordinator) emp2);
        emp2.setWorkingVaccinationCenter(vac);
    }

    public  void bootstrapSNSUser()
    {
        Calendar date1 = Calendar.getInstance();
        date1.set(Calendar.DAY_OF_MONTH,1);
        date1.set(Calendar.MONTH,1);
        date1.set(Calendar.YEAR,1930);

        SNSUser user1 = this.company.getPlatform().getRegisterSNSUser().newSNSUser("jonas","Rua do Joao","Male","916597489","snsuser1@lei.sem2.pt",date1.getTime(),"12345679","12345679");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user1);

        Calendar date2 = Calendar.getInstance();
        date2.set(Calendar.DAY_OF_MONTH,1);
        date2.set(Calendar.MONTH,1);
        date2.set(Calendar.YEAR,1940);

        SNSUser user2 = this.company.getPlatform().getRegisterSNSUser().newSNSUser("rui","rua penalves","Male","916597488","snsuser2@lei.sem2.pt",date2.getTime(),"12345678","12345678");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user2);

        Calendar date3 = Calendar.getInstance();
        date3.set(Calendar.DAY_OF_MONTH,1);
        date3.set(Calendar.MONTH,1);
        date3.set(Calendar.YEAR,1950);

        SNSUser user3 = this.company.getPlatform().getRegisterSNSUser().newSNSUser("Melo","Rua do Joao","Male","916597487","1211008@isep.ipp.pt",date3.getTime(),"12345677","12345677");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user3);

        Calendar date4 = Calendar.getInstance();
        date4.set(Calendar.DAY_OF_MONTH,1);
        date4.set(Calendar.MONTH,1);
        date4.set(Calendar.YEAR,1960);

        SNSUser user4 = this.company.getPlatform().getRegisterSNSUser().newSNSUser("Coelho","Rua do Joao","Male","916597486","1211154@isep.ipp.pt",date4.getTime(),"12345676","12345676");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user4);

        Calendar date5 = Calendar.getInstance();
        date5.set(Calendar.DAY_OF_MONTH,1);
        date5.set(Calendar.MONTH,1);
        date5.set(Calendar.YEAR,1960);

        SNSUser user5 = this.company.getPlatform().getRegisterSNSUser().newSNSUser("pedro","Rua do Joao","Male","916597485","1211690@isep.ipp.pt",date5.getTime(),"12345675","12345675");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user5);

        Calendar date6 = Calendar.getInstance();
        date6.set(Calendar.DAY_OF_MONTH,1);
        date6.set(Calendar.MONTH,1);
        date6.set(Calendar.YEAR,1970);

        SNSUser user6 = this.company.getPlatform().getRegisterSNSUser().newSNSUser("rui","Rua do Joao","Male","916597484","snsuser3@lei.sem2.pt",date6.getTime(),"12345674","12345674");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user6);

        Calendar date7 = Calendar.getInstance();
        date7.set(Calendar.DAY_OF_MONTH,1);
        date7.set(Calendar.MONTH,1);
        date7.set(Calendar.YEAR,1980);

        SNSUser user7 = this.company.getPlatform().getRegisterSNSUser().newSNSUser("pedro","Rua penalves","Male","916597483","snsuser4@lei.sem2.pt",date7.getTime(),"12345673","12345673");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user7);

        Calendar date8 = Calendar.getInstance();
        date8.set(Calendar.DAY_OF_MONTH,1);
        date8.set(Calendar.MONTH,1);
        date8.set(Calendar.YEAR,1990);

        SNSUser user8 = this.company.getPlatform().getRegisterSNSUser().newSNSUser("gui","Rua do Joao","Male","916597482","snsuser5@lei.sem2.pt",date8.getTime(),"12345672","12345672");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user8);

        Calendar date9 = Calendar.getInstance();
        date9.set(Calendar.DAY_OF_MONTH,1);
        date9.set(Calendar.MONTH,1);
        date9.set(Calendar.YEAR,2000);

        SNSUser user9 = this.company.getPlatform().getRegisterSNSUser().newSNSUser("rui","Rua do Joao","Male","916597481","snsuser6@lei.sem2.pt",date9.getTime(),"12345671","12345671");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user9);

        Calendar date10 = Calendar.getInstance();
        date10.set(Calendar.DAY_OF_MONTH,1);
        date10.set(Calendar.MONTH,1);
        date10.set(Calendar.YEAR,2010);

        SNSUser user10 = this.company.getPlatform().getRegisterSNSUser().newSNSUser("gui","Rua do Joao","Male","916597480","snsuser7@lei.sem2.pt",date10.getTime(),"12345670","12345670");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user10);

        Calendar date11 = Calendar.getInstance();
        date11.set(Calendar.DAY_OF_MONTH,1);
        date11.set(Calendar.MONTH,1);
        date11.set(Calendar.YEAR,2020);

        SNSUser user11 = this.company.getPlatform().getRegisterSNSUser().newSNSUser("rui","Rua do Joao","Male","916597479","snsuser8@lei.sem2.pt",date11.getTime(),"12345669","12345669");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user11);

        //THE ONLY ONE THAT DOESNT HAVE A VACCINE SCHEDULE WHEN PROGRAM STARS, CHANGE EMAIL FOR A PERSONAL ONE TO SEE THE EMAIL THAT YOU WILL RECEIVE WHEN CREATING USER AND REGISTER A NEW VACCINE SCHEDULE
        Calendar date12 = Calendar.getInstance();
        date11.set(Calendar.DAY_OF_MONTH,1);
        date11.set(Calendar.MONTH,1);
        date11.set(Calendar.YEAR,2000);
        SNSUser user12 = this.company.getPlatform().getRegisterSNSUser().newSNSUser("Gilberto", "Rua do Gilberto", "Male","919876542", "snsuser9@lei.sem2.pt",date12.getTime(), "36582157","30152458");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user12);

    }

    private void bootstrapVaccinationSchedules() {
        VaccinationCenter vc = this.getCompany().getPlatform().getRegisterVaccinationCenter().getListVaccinationCenters().get(0);
        VaccineType vt = vc.getListVaccineTypes().get(0);
        SNSUser user1 = this.getCompany().getPlatform().getRegisterSNSUser().getSNSUserBySNSUserNumber("12345679");
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH,1);
        vc.scheduleVaccine(user1,vt,date.getTime(), 10, 0);

        SNSUser user2 = this.getCompany().getPlatform().getRegisterSNSUser().getSNSUserBySNSUserNumber("12345678");
        vc.scheduleVaccine(user2,vt,date.getTime(), 10, 0);

        SNSUser user3 = this.getCompany().getPlatform().getRegisterSNSUser().getSNSUserBySNSUserNumber("12345677");
        vc.scheduleVaccine(user3,vt,date.getTime(), 10, 0);

        SNSUser user4 = this.getCompany().getPlatform().getRegisterSNSUser().getSNSUserBySNSUserNumber("12345676");
        vc.scheduleVaccine(user4,vt,date.getTime(), 10, 0);

        SNSUser user5 = this.getCompany().getPlatform().getRegisterSNSUser().getSNSUserBySNSUserNumber("12345675");
        vc.scheduleVaccine(user5,vt,date.getTime(), 10, 0);

        SNSUser user6 = this.getCompany().getPlatform().getRegisterSNSUser().getSNSUserBySNSUserNumber("12345674");
        vc.scheduleVaccine(user6,vt,date.getTime(), 10, 0);

        SNSUser user7 = this.getCompany().getPlatform().getRegisterSNSUser().getSNSUserBySNSUserNumber("12345673");
        vc.scheduleVaccine(user7,vt,date.getTime(), 10, 0);

        SNSUser user8 = this.getCompany().getPlatform().getRegisterSNSUser().getSNSUserBySNSUserNumber("12345672");
        vc.scheduleVaccine(user8,vt,date.getTime(), 10, 0);

        SNSUser user9 = this.getCompany().getPlatform().getRegisterSNSUser().getSNSUserBySNSUserNumber("12345671");
        vc.scheduleVaccine(user9,vt,date.getTime(), 10, 0);

        SNSUser user10 = this.getCompany().getPlatform().getRegisterSNSUser().getSNSUserBySNSUserNumber("12345670");
        vc.scheduleVaccine(user10,vt,date.getTime(), 10, 0);

        SNSUser user11 = this.getCompany().getPlatform().getRegisterSNSUser().getSNSUserBySNSUserNumber("12345669");
        vc.scheduleVaccine(user11,vt,date.getTime(), 10, 0);



    }

    public void bootstrapWaitingRoom()
    {
        VaccinationCenter vc = this.getCompany().getPlatform().getRegisterVaccinationCenter().getListVaccinationCenters().get(0);
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH,1);
        date.set(Calendar.MONTH,1);
        date.set(Calendar.YEAR,1994);

        Calendar date1 = Calendar.getInstance();
        date1.add(Calendar.MINUTE,1);

        SNSUser user = this.company.getPlatform().getRegisterSNSUser().newSNSUser("jonas","Rua do Joao","Male","916597411","snsuser10@lei.sem2.pt",date.getTime(),"12345611","12345611");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user);
        VaccineSchedule vs = new VaccineSchedule(date1.getTime(), vc.getListVaccineTypes().get(0), user);
        vc.registerUserArrival(vs,date1.getTime());
        VaccineAdministration vaccineAdministration = vc.createVaccineAdministration(user,vc.getListVaccineTypes().get(0),vc.getListVaccineTypes().get(0).getVaccine("4455"),"555");
        vc.registerVaccineAdministration(vaccineAdministration);
        AdverseReaction reaction = new AdverseReaction("Inflated arm",vaccineAdministration);
        user.registerVaccineAdministration(vaccineAdministration);
        user.registerAdverseReaction(reaction);
        VaccineSchedule vs1 = new VaccineSchedule(date1.getTime(), vc.getListVaccineTypes().get(0), user);
        vc.registerUserArrival(vs1,date1.getTime());
    }

    public void bootstrapExportStatistics()
    {
        //VaccinationCenter
        VaccinationCenter vaccinationCenter = this.company.getPlatform().getRegisterVaccinationCenter().getVaccinationCenter("vaccinationcenterLAPR2@gmail.com");

        //SNSUsers
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH,1);
        date.set(Calendar.MONTH,1);
        date.set(Calendar.YEAR,1994);
        SNSUser user = this.company.getPlatform().getRegisterSNSUser().newSNSUser("jonas","Rua do Joao","Male","916565656","exportstatistics@lei.sem2.pt",date.getTime(),"65656565","65656565");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user);
        SNSUser user1 = this.company.getPlatform().getRegisterSNSUser().newSNSUser("jonas","Rua do Joao","Male","916565655","exportstatistics1@lei.sem2.pt",date.getTime(),"65656564","65656564");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user1);
        SNSUser user2 = this.company.getPlatform().getRegisterSNSUser().newSNSUser("jonas","Rua do Joao","Male","916565654","exportstatistics2@lei.sem2.pt",date.getTime(),"65656563","65656563");
        this.company.getPlatform().getRegisterSNSUser().addSNSUser(user2);

        //VaccineType
        VaccineType vaccineType = this.company.getPlatform().getRegisterVaccineType().newVaccineType("1233","Covids","mRna");
        this.company.getPlatform().getRegisterVaccineType().registerVaccineType(vaccineType);
        vaccinationCenter.addVaccineType(vaccineType);

        //Vaccine
        Vaccine vaccine = new Vaccine("6633","Spikevax");

        //AdministrationProcess
        List<Integer[]> ageGroups = new ArrayList<>();
        Integer[] ageGroup = {8,80};
        ageGroups.add(ageGroup);
        AdministrationProcess ap = new AdministrationProcess(ageGroups,10.0,1);
        vaccine.addAdministrationProcess(ap);

        vaccineType.addVaccine(vaccine);
        vaccinationCenter.addVaccine(vaccine);

        //VaccineSchedule
        Calendar date1 = Calendar.getInstance();
        date1.add(Calendar.SECOND,120);

        vaccinationCenter.scheduleVaccine(user,vaccineType,date1.getTime(), date1.get(Calendar.HOUR_OF_DAY),date1.get(Calendar.MINUTE));
        vaccinationCenter.scheduleVaccine(user1,vaccineType,date1.getTime(), date1.get(Calendar.HOUR_OF_DAY),date1.get(Calendar.MINUTE));
        vaccinationCenter.scheduleVaccine(user2,vaccineType,date1.getTime(), date1.get(Calendar.HOUR_OF_DAY),date1.get(Calendar.MINUTE));

        //VaccineAdministration
        VaccineAdministration vaccineAdministration = vaccinationCenter.createVaccineAdministration(user,vaccineType,vaccine,"555");
        user.registerVaccineAdministration(vaccineAdministration);
        vaccinationCenter.registerVaccineAdministration(vaccineAdministration);

        VaccineAdministration vaccineAdministration1 = vaccinationCenter.createVaccineAdministration(user1,vaccineType,vaccine,"554");
        user1.registerVaccineAdministration(vaccineAdministration1);
        vaccinationCenter.registerVaccineAdministration(vaccineAdministration1);


        //AdministrationProcess ap = vaccine.newAdministrationProcess(ageGroups,10.0,2,timeIntervals)
        //como o valor e 2 em doses administrar o programa so vai considerar este user2 como totalmente vaccinado, ou seja
        //Total vaccinated people=1 este é o resultado esperado
        VaccineAdministration vaccineAdministration2 = vaccinationCenter.createVaccineAdministration(user2,vaccineType,vaccine,"555");
        user2.registerVaccineAdministration(vaccineAdministration2);
        vaccinationCenter.registerVaccineAdministration(vaccineAdministration2);

        VaccineAdministration vaccineAdministration3 = vaccinationCenter.createVaccineAdministration(user2,vaccineType,vaccine,"554");
        user2.registerVaccineAdministration(vaccineAdministration3);
        vaccinationCenter.registerVaccineAdministration(vaccineAdministration3);

    }




    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;
    public static App getInstance()
    {
        if(singleton == null)
        {
            synchronized(App.class)
            {
                singleton = new App();
            }
        }
        return singleton;
    }

    public boolean setLanguage(Locale locale) {
        this.currentLocale = locale;
        setBundle();
        return true;
    }

    /**
     * Used after deserializing the company class
     * @param company company info read after deserialization.
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    public void dailyRecord(){

        Timer timer = new Timer();
        this.task = new RecordDailyTask(this);

        timer.schedule(task,getDateFromConfig(),86400000);
    }

    private Date getDateFromConfig() {
        Properties props = getProperties();
        String s = props.getProperty("Time");
        String[] arr = s.split(":");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(arr[0]));
        cal.set(Calendar.MINUTE,Integer.parseInt(arr[1]));
        cal.set(Calendar.SECOND,Integer.parseInt(arr[2]));
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTime();
    }

    public void stopTask(){
        task.cancel();
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public Base getBase() {
        return this.base;
    }
}
