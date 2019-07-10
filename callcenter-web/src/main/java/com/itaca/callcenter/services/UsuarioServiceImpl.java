package com.itaca.callcenter.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.itaca.callcenter.dao.ParametroConfiguracionDAO;
import com.itaca.callcenter.dao.UsuarioDAO;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.pojo.UserValidation;
import com.itaca.callcenter.web.utils.DateUtils;
import com.itaca.callcenter.web.utils.ParametroConfiguracionConstants;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@Stateless(name = "ejb/usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
    private static final Logger logger = LogManager.getLogger(UsuarioServiceImpl.class);
    private static final int SET_PASSWORD_TOKEN_VALID_DAYS = 5;
    @EJB
    private UsuarioDAO usuarioDAO;
    @EJB
            LogService logService;
    
    @EJB
    private ParametroConfiguracionDAO parametroConfiguracionDAO;
    
    
    
    @Override
    public List<Usuario> getAll() {
        return usuarioDAO.findAllActivesExceptAdmin();
    }
    
    @Override
    public void save(Usuario usuario, Usuario user) {
        try {
            if (usuario.getId() == null) {
                logger.info("Guardando usuario.");
                usuario.setActivo(true);
                usuarioDAO.persist(usuario);
                logService.info(user,usuario.getId().intValue(),usuario.getClass().toString(),"Insert");
            } else {
                logger.info("Actualizando usuario.");
                usuarioDAO.update(usuario);
                logService.info(user,usuario.getId().intValue(),usuario.getClass().toString(),"Update");
            }
        } catch (Exception e) {
            logger.error("Error al guardar el usuario.", e);
            throw e;
        }
        
    }
    
    @Override
    public int countAll() {
        return usuarioDAO.countAllActivesExceptAdmin();
    }
    
    @Override
    public List<Usuario> getResultList(int first, int pageSize) {
        return usuarioDAO.findAllActivesExceptAdmin(first, pageSize);
    }
    
    
    
    
    
    
    
    private String generateSetPasswordToken(String username)
            throws IllegalArgumentException, UnsupportedEncodingException {
        String key = parametroConfiguracionDAO
                .findByField("key", ParametroConfiguracionConstants.LLAVE_TOKEN_ESTABLECER_PASSWORD).getValue();
        LocalDate fechaExpiracion = LocalDate.now().plusDays(SET_PASSWORD_TOKEN_VALID_DAYS);
        Algorithm algorithm = Algorithm.HMAC256(key);
        return JWT.create().withIssuer(username).withExpiresAt(DateUtils.getDateFrom(fechaExpiracion)).sign(algorithm);
    }
    
    @Override
    public UserValidation validateSetPasswordToken(String token)
            throws IllegalArgumentException, UnsupportedEncodingException {
        UserValidation userValidation = new UserValidation();
        String key = parametroConfiguracionDAO
                .findByField("key", ParametroConfiguracionConstants.LLAVE_TOKEN_ESTABLECER_PASSWORD).getValue();
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            DecodedJWT jwtd = JWT.decode(token);
            String username = jwtd.getIssuer();
            LocalDate fechaExpiracion = DateUtils.getLocalDateFrom(jwtd.getExpiresAt());
            LocalDate fechaActual = LocalDate.now();
            Usuario usuario = usuarioDAO.getByUserEmail(username);
            
            if (usuario == null) {
                userValidation.setErrorMessage("Parametros no válidos. No se puede continuar con el proceso.");
                logger.info("No existe usuario para el token proporcionado.");
            } else if (usuario.isVerificado()) {
                userValidation.setErrorMessage("La contraseña ya ha sido establecida. El enlace ya no es válido.");
            } else if (fechaActual.isAfter(fechaExpiracion)) {
                userValidation.setErrorMessage("El enlace ha expirado.");
            } else {
                JWTVerifier verifier = JWT.require(algorithm).withIssuer(username).build();
                verifier.verify(token);
                userValidation.setUser(usuario);
            }
        } catch (JWTVerificationException jwtEx) {
            logger.error("Token no válido.", jwtEx.getMessage());
            userValidation.setErrorMessage("Parametros no válidos. No se puede continuar con el proceso.");
        }
        
        return userValidation;
    }
    
    @Override
    public Usuario getByEmail(String email) {
        logger.info("Obteniendo usuario por nombre de usuario.");
        return usuarioDAO.getByUserEmail(email);
    }
    
    
}
