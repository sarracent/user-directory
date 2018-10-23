package beans;

import beans.Directorio;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-04T13:50:48")
@StaticMetamodel(Lugar.class)
public class Lugar_ { 

    public static volatile SingularAttribute<Lugar, Integer> id;
    public static volatile CollectionAttribute<Lugar, Directorio> directorioCollection;
    public static volatile SingularAttribute<Lugar, String> nombre;

}