/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.bean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
@ApplicationScoped

public class BeanDefaultCommand {
    
    private String text;
    
    private String btn = "cadastrado";
    
    private List<String> buttons;
    
    public BeanDefaultCommand() {
        buttons = new ArrayList<String>();
        buttons.add("cadastrado");
        buttons.add("atualizado");
        buttons.add("removido");
    }

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

    public List<String> getButtons() {
        return buttons;
    }

    public void setButtons(List<String> buttons) {
        this.buttons = buttons;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public void addMessage(String btn) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro " + btn));
    }
    
    public void cadastradoSubmit() {
        addMessage("cadastrado");
    }
    
    public void atualizadoSubmit() {
        addMessage("atualizado");
    }
    
    public void removidoSubmit() {
        addMessage("removido");
    }
}
