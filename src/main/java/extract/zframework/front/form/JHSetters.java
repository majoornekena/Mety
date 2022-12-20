package extract.zframework.front.form;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;

import javax.swing.JComponent;

import extract.zframework.dao.ObjectBdd;
import extract.zframework.front.date.general.JHDate;
import extract.zframework.hform.HLine;

public class JHSetters {
    HLine hline;
    JComponent jComponent;

    public JHSetters(HLine hline) {
        this.setHline(hline);
        this.init();
    }

    public Object tObject(HLine hline) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        Object obj = hline.getMeth().getParameterTypes()[0].getDeclaredConstructor().newInstance();
        return obj;
    }

    public void isCombo(HLine hline) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException, extract.zframework.front.form.isCombo {
        Class<?> cls = hline.getMeth().getParameterTypes()[0];
        if (ObjectBdd.class.isAssignableFrom(cls)) {
            throw new isCombo();
        }
    }

    public void isDate(HLine hline) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException, extract.zframework.front.form.isDate {
        Class<?> cls = hline.getMeth().getParameterTypes()[0];
        if (cls.equals(Date.class)) {
            throw new isDate();
        }
    }

    void determine(HLine hline) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException, extract.zframework.front.form.isCombo,
            extract.zframework.front.form.isDate,
            isTextField {
        this.isCombo(hline);
        this.isDate(hline);
        throw new isTextField();
    }

    public JComponent determine() throws Exception {
        try {
            this.determine(this.getHline());
        } catch (extract.zframework.front.form.isCombo e1) {
            return new JHComboBox(this.getHline());
        } catch (extract.zframework.front.form.isDate e1) {
            return new JHDate(null, null, null, hline);
        } catch (isTextField e) {
            return new JHSetter(this.getHline());
        }catch (Exception e1) {
            return new JHSetter(this.getHline());
        };
        throw new Exception();
    }

    public void init() {
        JComponent jTextField = null;
        try {
            jTextField = this.determine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setjComponent(jTextField);
    }

    public JComponent getjComponent() {
        return jComponent;
    }

    public void setjComponent(JComponent jComponent) {
        this.jComponent = jComponent;
    }

    public HLine getHline() {
        return hline;
    }

    public void setHline(HLine hline) {
        this.hline = hline;
    }

}
