package com.tsi2;

import com.tsi2.entidades.Rol;
import com.tsi2.entidades.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.primefaces.context.RequestContext;

@SessionScoped
@Named("usuarioController")
@Transactional
public class UsuarioController implements Serializable {

    @PersistenceContext(unitName = "com.tsi2_web-jsf_war_1.0PU")
    private EntityManager em;
    
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private int rol;
    private String filtro;
    private int pagina;
    private String usernameLogin;
    private String passwordLogin;
    private String rolLogin;
    private boolean registrado;

    public UsuarioController() {
    }
    
    public void create(ActionEvent event) throws Exception {
        RequestContext context = RequestContext.getCurrentInstance();
        Usuario u = find();
        if(u == null){
            Rol r = em.find(Rol.class, rol);
            if(r == null) r = em.createQuery("SELECT r FROM Rol r", Rol.class).getSingleResult();
            u = new Usuario(username, password, name, lastname, email, r);
            em.persist(u);
            context.addCallbackParam("exito", exists(u));
        }
    }
    
    public void update() throws Exception {
        Usuario u = find();
        if(u != null){
            u.setName(name);
            u.setLastname(lastname);
            u.setEmail(email);
            u.setPassword(password);
            Rol r = em.find(Rol.class, rol);
            if(r != null) u.setRoleid(r);
            em.persist(u);
        }
    }
    
    public void delete() throws Exception {
        Usuario u = find();
        if(u != null) em.remove(u);
    }
    
    public String login(){
        Usuario u = em.find(Usuario.class, usernameLogin);
        if(u == null) return "index";
        if(!u.getPassword().equals(passwordLogin)) return "index";
        if(u.getRoleid().getId() == 1){
            setRolLogin("Admin");
            return "admin";
        }else{
            setRolLogin("Usuario");
            return "usuario";
        }
    }
    
    public String logout(){
        setUsernameLogin(null);
        setRolLogin(null);
        return "index";
    }
    
    public boolean exists(Usuario u){
        return em.contains(u);
    }
    
    public Usuario find(){
        return em.find(Usuario.class, username);
    }
    
    public List<Usuario> findAll(){
        int skip = ((pagina * 10) - 10);
        return em.createQuery("SELECT u FROM Usuario u WHERE u.username LIKE :f ORDER BY u.creationDate DESC", Usuario.class).setParameter("f", "%" + filtro + "%").setMaxResults(10).setFirstResult(skip).getResultList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRolLogin() {
        return rolLogin;
    }

    public void setRolLogin(String rolLogin) {
        this.rolLogin = rolLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isRegistrado() {
        return registrado;
    }

    public void setRegistrado(boolean registrado) {
        this.registrado = registrado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public String getUsernameLogin() {
        return usernameLogin;
    }

    public void setUsernameLogin(String usernameLogin) {
        this.usernameLogin = usernameLogin;
    }

    public String getPasswordLogin() {
        return passwordLogin;
    }

    public void setPasswordLogin(String passwordLogin) {
        this.passwordLogin = passwordLogin;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
