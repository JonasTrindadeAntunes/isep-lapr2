package app.controller.Admin;

import app.domain.model.Serialization.ObjectDeserializer;
import app.domain.model.Serialization.ObjectSerializer;

/**
 * The type Serialization controller.
 */
public class SerializationController {

    private final int SERIALIZE = 1;
    private final int DESERIALIZE = 2;

    /**
     * Instantiates a new Serialization controller.
     */
    public SerializationController() {
    }


    /**
     * Serialize boolean.
     *
     * @param option the option
     * @return the boolean
     */
    public boolean serialize(int option){
        if(option == SERIALIZE){
            ObjectSerializer objSerializer = new ObjectSerializer();
            objSerializer.serialize();
            return true;
        }else if(option == DESERIALIZE) {
            ObjectDeserializer objDeserializer = new ObjectDeserializer();
            objDeserializer.deserialize();
            return true;
        }
        return false;
    }
}
