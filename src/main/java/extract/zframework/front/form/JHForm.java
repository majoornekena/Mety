package extract.zframework.front.form;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import extract.zframework.annotation.EForm;
import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.JHShow;
import extract.zframework.front.JHTextField;
import extract.zframework.front.button.create.JHCreate;
import extract.zframework.front.button.create.JHPCreate;
import extract.zframework.front.except.IsArray;
import extract.zframework.hform.HLine;

import java.util.ArrayList;

public class JHForm extends JHShow {
    JHPCreate validation;

    public JHForm(ObjectBdd<?> object) throws Exception {
        super(object, EForm.Form);
    }

    public void addJHLine(HLine hLine, ArrayList<Object> param) throws Exception {
        JHLineForm pan = new JHLineForm(hLine);
        param.add(pan.getObject());
        this.add(pan);
    }

    public void loadLineForm(HLine hLine, int num)
            throws Exception {
        ArrayList<Object> param = new ArrayList<Object>();
        for (int j = 0; j < num; j++) {
            this.addJHLine(hLine, param);
        }
        hLine.getMeth().invoke(hLine.getObject(), param);
    }

    public void generateBtn() {
        try {
            JHCreate jhCreate = new JHCreate(this.getHcForm().getDao());
            JHPCreate validation = new JHPCreate();
            validation.setJhCreate(jhCreate);
            this.add(validation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JHPCreate getValidation() {
        return validation;
    }

    public void setValidation(JHPCreate validation) {
        this.validation = validation;
    }

    @Override
    public void doAfter() {
        this.generateBtn();
    }

    @Override
    public void addNormalComponent(HLine hLine) throws IsArray {
        JPanel pan = new JHTextField(hLine);
        this.add(pan);
    }

    @Override
    public void addArrayComponent(HLine hLine) throws Exception {
        int len = 0;
        while (true) {
            try {
                String ans = JOptionPane.showInputDialog(hLine.getQuestion());
                len = Integer.parseInt(ans);
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        this.loadLineForm(hLine, len);
    }

}
