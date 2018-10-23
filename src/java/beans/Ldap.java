package beans;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 *
 * @author harold
 */
public class Ldap {

    private static final Logger LOG = getLogger(Ldap.class.getName());

    private final InitialDirContext ctx;
    private final String username;
   

    /**
     *
     * @param username
     * @param password
     * @throws NamingException
     */
    public Ldap(String username, String password) throws NamingException {
        Hashtable env = new Hashtable();

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "LDAP://10.25.27.13:389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "INTRANET\\" + username);
        env.put(Context.SECURITY_CREDENTIALS, password);

        ctx = new InitialDirContext(env);
        this.username = username;
        
    }

    /**
     *
     * @return @throws NamingException
     */
    public String getDisplayName() throws NamingException {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> ne = ctx.search("OU=Aica,DC=intranet,DC=aica,DC=cu",
                "(&(samaccountname=" + username + "))",
                searchControls);

        String result = null;
        if (ne.hasMore()) {
            result = ne.next().getAttributes().get("displayname").get().toString();
        }
        ctx.close();

        return result;
    }

    public String getUser() throws NamingException {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> ne = ctx.search("OU=Aica,DC=intranet,DC=aica,DC=cu",
                "(&(samaccountname=" + username + "))",
                searchControls);

        String result = null;
        if (ne.hasMore()) {
            result = ne.next().getAttributes().get("sAMAccountName").get().toString();
        }
        ctx.close();

        return result;
    }

    public String getMail() throws NamingException {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> ne = ctx.search("OU=Aica,DC=intranet,DC=aica,DC=cu",
                "(&(samaccountname=" + username + "))",
                searchControls);

        String result = null;
        if (ne.hasMore()) {
            result = ne.next().getAttributes().get("mail").get().toString();
        }
        ctx.close();

        return result;
    }
    
    public List<Directorio> UserDireccion() throws NamingException {
        System.out.println("paso ldap");
        List<Directorio> listado = new LinkedList<>();
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> ne1 = ctx.search("OU=AICA,DC=intranet,DC=aica,DC=cu", "(&(objectClass=user))", searchControls);
        //   Operaciones op = new Operaciones();
        int i = 0;
        while (ne1.hasMore()) {
             Directorio u = new Directorio();
            SearchResult res = (SearchResult) ne1.next();
            String attrs = res.getName();
          
           
            u.setId(i);
            try {
                u.setNombre(res.getAttributes().get("displayname").get().toString());
            
            } catch (NullPointerException e) {
                System.out.println(e);
            }
            u.setUsuario(res.getAttributes().get("sAMAccountName").get().toString());
            u.setCorreo(res.getAttributes().get("sAMAccountName").get().toString() + "@aica.cu");

            listado.add(u);
            // System.out.println(res.getAttributes().get("sAMAccountName").get().toString() + "  " + res.getAttributes().get("givenname").get().toString() + " " + res.getAttributes().get("sAMAccountName").get().toString() + "@aica.cu");
            i++;
             }
        
        ctx.close();
        return listado;

    }

    public String getApellidos() throws NamingException {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> ne = ctx.search("OU=Aica,DC=intranet,DC=aica,DC=cu",
                "(&(samaccountname=" + username + "))",
                searchControls);

        String result = null;
        if (ne.hasMore()) {
            result = ne.next().getAttributes().get("sn").get().toString();
        }
        ctx.close();

        return result;
    }


    public boolean isAuth() throws NamingException {

        return getDisplayName() == null;

    }
}
