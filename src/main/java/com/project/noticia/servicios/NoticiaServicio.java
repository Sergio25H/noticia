
package com.project.noticia.servicios;

import com.project.noticia.entidades.Noticia;
import com.project.noticia.excepciones.MiException;
import com.project.noticia.repositorios.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicio {
    
    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    
    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws MiException{
        
        validar(titulo, cuerpo);
        
        Noticia noticia = new Noticia();
        
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setAlta(new Date());
        
        noticiaRepositorio.save(noticia);
    }
    
    public List<Noticia> listarNoticias(){
        
        List<Noticia> noticias = new ArrayList();
        
        noticias = noticiaRepositorio.findAll();
        
        return noticias;
    }
    
    @Transactional
    public void modificarNoticia(String id, String titulo, String cuerpo) throws MiException{
        
        validar(titulo, cuerpo);
        
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Noticia noticia = respuesta.get();
            
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            
            noticiaRepositorio.save(noticia);
        }
    }
    
    private void validar(String titulo, String cuerpo) throws MiException{
        
        if (titulo == null || titulo.isEmpty()) {
            throw new MiException("El titulo no puede ser nulo o estar vacio");
        }
        if (cuerpo == null || cuerpo.isEmpty()) {
            throw new MiException("El cuerpo no puede ser nulo o estar vicio");
        }
    }
    
}
