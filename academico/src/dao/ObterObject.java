package dao;

import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class ObterObject <T>{
	
	 
    T objeto;
    private Session session; 
    private Transaction transacao;
    
    public ObterObject(T objeto)
    {
        this.objeto = objeto;
    }
     /* Como usar
      * ObterRegistros<Usuario> ob = new  ObterRegistros(new Usuario());
        try {
            List lista = ob.listar();
            Iterator it = lista.iterator();
            
            while(it.hasNext())
            {
                Usuario us = (Usuario) it.next();
                System.out.println(us.getNome());
            }*/
     public List<T> listar() throws Exception
    {
        List<T> objGen                                          = null;
        try
        {
            session                                             = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao                                           = session.beginTransaction();
            Criteria filtro                                     = session.createCriteria(objeto.getClass());
            objGen                                              = filtro.list();
            transacao.commit();
        }
        catch(Exception erro)
        {
           if(transacao.isActive())
               transacao.rollback();
           throw new Exception(erro.getMessage());
        }
        finally
        {
            try
            {
            }
            catch(Exception erro)
            {
                if(session.isOpen())
                    session.close();
                throw new Exception(erro.getMessage());
            }
        }
        return objGen;
    }
     
        public List<T> listarFiltro(String email) throws Exception
    {
        List<T> objGen                                          = null;
        try
        {
            session                                             = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao                                           = session.beginTransaction();
            Criteria filtro                                     = session.createCriteria(objeto.getClass()).add(Restrictions.eq("email", email));
            objGen                                              = filtro.list();
            transacao.commit();
        }
        catch(Exception erro)
        {
           if(transacao.isActive())
               transacao.rollback();
           throw new Exception(erro.getMessage());
        }
        finally
        {
            try
            {
            }
            catch(Exception erro)
            {
                if(session.isOpen())
                    session.close();
                throw new Exception(erro.getMessage());
            }
        }
        return objGen;
    }
        
        public Object buscarObjectID(long id,String campo) throws Exception
        {
            Object obj = null;
            try
            {
                this.session = HibernateUtil.getSessionFactory().getCurrentSession();
                this.transacao = this.session.beginTransaction();
                Criteria filtro =this.session.createCriteria(objeto.getClass());
                filtro.add(Restrictions.eq(campo,id));
                obj = filtro.uniqueResult();
                this.transacao.commit();
                
            }
            catch(Exception erro)
            {
                if(this.transacao.isActive())
                    this.transacao.rollback();
                throw new Exception(erro.getMessage());
            }
            finally
            {
                try
                {
                    if(this.session.isOpen())
                    this.session.close();
                }
                catch(Exception erro)
                {
                   throw new Exception(erro.getMessage()); 
                }
            }
            
            return obj;
        }
        
        public Object buscarObjectID(String id,String campo) throws Exception
        {
            Object obj = null;
            try
            {
                this.session = HibernateUtil.getSessionFactory().getCurrentSession();
                this.transacao = this.session.beginTransaction();
                Criteria filtro =this.session.createCriteria(objeto.getClass());
                filtro.add(Restrictions.eq(campo,id));
                obj = filtro.uniqueResult();
                this.transacao.commit();
                
            }
            catch(Exception erro)
            {
                if(this.transacao.isActive())
                    this.transacao.rollback();
                throw new Exception(erro.getMessage());
            }
            finally
            {
                try
                {
                    if(this.session.isOpen())
                    this.session.close();
                }
                catch(Exception erro)
                {
                   throw new Exception(erro.getMessage()); 
                }
            }
            
            return obj;
        }
        
        
        public Object buscarObjectUnique(String valor,String campo) throws Exception
        {
            Object obj = null;
            try
            {
                this.session = HibernateUtil.getSessionFactory().getCurrentSession();
                this.transacao = this.session.beginTransaction();
                Criteria filtro =this.session.createCriteria(objeto.getClass());
                filtro.add(Restrictions.eq(campo,valor));
                obj = filtro.uniqueResult();
                this.transacao.commit();
                
            }
            catch(Exception erro)
            {
                if(this.transacao.isActive())
                    this.transacao.rollback();
                throw new Exception(erro.getMessage());
            }
            finally
            {
                try
                {
                    if(this.session.isOpen())
                    this.session.close();
                }
                catch(Exception erro)
                {
                   throw new Exception(erro.getMessage()); 
                }
            }
            
            return obj;
        }

}
