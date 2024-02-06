package br.edu.ifpr.irati.ads.converter;

import br.edu.ifpr.irati.ads.modelo.Produto;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter(forClass = Produto.class, value = "produtoConverter")
public class ProdutoConverter implements Converter {

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
			Produto p = (Produto) value;
			adicionarAtributo(component, p);
			String chave = String.valueOf(p.getId());
			return chave;
		}
		return (String) value;
	}

	protected Map<String, Object> getMapaObjetos(UIComponent component) {
		return component.getAttributes();
	}

	protected void adicionarAtributo(UIComponent component, Produto produto) {
		String chave = String.valueOf(produto.getId());
		getMapaObjetos(component).put(chave, produto);
	}

}
