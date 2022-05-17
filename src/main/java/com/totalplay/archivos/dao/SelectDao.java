package com.totalplay.archivos.dao;

import com.totalplay.archivos.model.Archivo;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



@Mapper
public interface SelectDao {
    
    public void setArchivo(@Param("req") Archivo req);
    public ArrayList<Archivo> filesById(@Param("id") String id);

//	public StatsuVo getStatus();
//	
//	public List<RequerimientosModel> getRequerimeiinto(@Param("id") String id);
//
//	public void setRequerimiento(@Param("req") RequerimientoModel req);
//
//	public List<RequerimientosModel> getRequerimeiintoFilter(@Param("id") String id, @Param("numeric") boolean numeric);
//
//	public void setAddons( @Param("req") RequAddonModel req);
//        
//        public List<RequerimientosModel> getRequerimientoPorVencer(@Param("id") String id);
//        
//        public List<RequerimientosModel> getRequerimientosVencidos(@Param("id") String id);
//        
//	public List<RequerimientosModel> getRequerimientoEstado(@Param("id") String id);
//
//        public List<RequAddonModel> getRequerimientoCompleto(@Param("id") String id);
//
//        public void setRequerimientoRelacion(@Param("req") ReqVencidosYPorVencer req);
//
//        public void setRequerimientoReact(@Param("id") String id);
//
//        public void cambiaEstatusRequerimiento(@Param("idEstatus") String idEstatus,@Param("id") String id);
//        
//        public List<ComentariosModel> getComentarios(@Param("id") int id);
//        
//        public void addComentario(@Param("req") ComentariosModel comentario);
//        
//        public void addParametro(@Param("req") ParametroModel parametro);
//
//        public List<ParametroModel> getParametros(@Param("id") int id);
//        
//        
//	public List<RequerimientosModel> getRequermientoIds(@Param("id") String id);
//        
//        public void updateRequerimiento(@Param("req") RequerimientoModel req);
	
}
