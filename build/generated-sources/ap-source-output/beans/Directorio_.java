package beans;

import beans.Lugar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-04T13:50:48")
@StaticMetamodel(Directorio.class)
public class Directorio_ { 

    public static volatile SingularAttribute<Directorio, String> foto;
    public static volatile SingularAttribute<Directorio, String> correo;
    public static volatile SingularAttribute<Directorio, Lugar> lugar;
    public static volatile SingularAttribute<Directorio, String> direccion;
    public static volatile SingularAttribute<Directorio, String> usuario;
    public static volatile SingularAttribute<Directorio, Integer> id;
    public static volatile SingularAttribute<Directorio, String> nombre;

}