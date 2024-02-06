package br.edu.ifpr.irati.ads.converter;

import br.edu.ifpr.irati.ads.modelo.Funcao;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter(forClass = Funcao.class, value = "funcaoConverter")
public class FuncaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent component, String value) {
		if (value != null) {
			return getMapaObjetos(component).get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent component, Object value) {
		if (value != null) {
			Funcao f = (Funcao) value;
			adicionarAtributo(component, f);
			String chave = String.valueOf(f.getId());
			return chave;
		}
		return (String) value;
	}

	protected Map<String, Object> getMapaObjetos(UIComponent component) {
		return component.getAttributes();
	}

	protected void adicionarAtributo(UIComponent component, Funcao funcao) {
		String chave = String.valueOf(funcao.getId());
		getMapaObjetos(component).put(chave, funcao);
	}

}
