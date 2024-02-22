package br.edu.ifpr.irati.ads.converter;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Usuario.class, value = "usuarioConverter")
public class UsuarioConverter implements Converter {

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
			Usuario p = (Usuario) value;
			adicionarAtributo(component, p);
			String chave = String.valueOf(p.getId());
			return chave;
		}
		return (String) value;
	}

	protected Map<String, Object> getMapaObjetos(UIComponent component) {
		return component.getAttributes();
	}

	protected void adicionarAtributo(UIComponent component, Usuario usuario) {
		String chave = String.valueOf(usuario.getId());
		getMapaObjetos(component).put(chave, usuario);
	}
}