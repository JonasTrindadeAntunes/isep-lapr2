package app.domain.model;

import app.domain.shared.MessageBundle;
import org.apache.commons.lang3.RandomStringUtils;
import pt.isep.lei.esoft.auth.domain.model.Password;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The interface Password generator.
 */
public interface PasswordGenerator {

    /**
     * Password generator string.
     *
     * @param email the email
     * @return the string
     */
    public String passwordGenerator(String email);

    /**
     * The type Password generator randomly.
     */
    class PasswordGeneratorRandomly implements PasswordGenerator{

        @Override
        public String passwordGenerator(String email)
        {

            String upperCaseLetters = RandomStringUtils.randomAlphabetic(3).toUpperCase();
            String lowerCaseLetters = RandomStringUtils.randomAlphabetic(2).toLowerCase();
            String numbers = RandomStringUtils.randomNumeric(2);

            String combinedChars = upperCaseLetters.concat(lowerCaseLetters).concat(numbers);

            List<Character> pwdChars = combinedChars.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

            Collections.shuffle(pwdChars);

            String password = pwdChars.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();



            System.out.println(MessageBundle.getString("email") + ":" + email + " " + MessageBundle.getString("password") + ":"+ password);
            String emailMessage = String.format(MessageBundle.getString("login") + ":" + email + " " + MessageBundle.getString("password") + ":"+ password);
            //SendEmail.SendEmail(email,emailMessage,MessageBundle.getString("accountdetailslapr2"));


            Password m_oPassword = new Password(password);
            if(m_oPassword.checkPassword(password))
                return password;
            else
                throw new RuntimeException(MessageBundle.getString("wrongpworempty"));
        }



    }
}
