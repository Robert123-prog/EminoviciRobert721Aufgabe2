package model;

import java.io.Serializable;

public interface HasID extends Serializable {
    /**
     * Retrieves the unique identifier of the object.
     *
     * @return the unique ID of the object
     */
    Integer getId();

}
