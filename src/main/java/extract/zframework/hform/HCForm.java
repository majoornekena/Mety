package extract.zframework.hform;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import extract.zframework.annotation.EForm;
import extract.zframework.annotation.HForceForm;
import extract.zframework.annotation.HForm;
import extract.zframework.annotation.HListe;
import extract.zframework.annotation.NotForm;
import extract.zframework.dao.HFormParam;
import extract.zframework.dao.ObjectBdd;
import extract.zframework.tools.OwnTool;

public class HCForm {
    static private CompareHLine ascHLine = new CompareHLine();

    ObjectBdd<?> dao;
    HLine[] hLines;

    public HCForm() {
    }

    public HCForm(ObjectBdd<?> dao, EForm eForm) throws NoSuchMethodException, SecurityException {
        this.setDao(dao);
        this.init(dao, eForm);
    }

    public void init(ObjectBdd<?> objectBdd, EForm eForm) throws NoSuchMethodException, SecurityException {
        ArrayList<HLine> liste = new ArrayList<HLine>();
        Method[] meths = this.getDao().getClass().getMethods();
        for (int i = 0; i < meths.length; i++) {
            try {
                HLine hline = this.getHLine(meths[i], eForm);
                liste.add(hline);
            } catch (NotForm e) {
            }
        }
        this.sethLines(liste);
    }

    protected void gatherForm(Method method) throws NotForm, HFormParam {
        HForm hform = OwnTool.isHForm(method);
        HFormParam hformparam = new HFormParam(hform);
        throw hformparam;
    }

    protected void gatherListe(Method method) throws NotForm, HFormParam {
        try {
            HListe hform = OwnTool.isHListe(method);
            HFormParam hformparam = new HFormParam(hform);
            throw hformparam;
        } catch (NotForm e) {
            HForceForm hform = OwnTool.isHForceForm(method);
            HFormParam hformparam = new HFormParam(hform);
            throw hformparam;
        }
    }

    protected void gatherHlineData(Method meth, EForm eform) throws NotForm, HFormParam {
        switch (eform) {
            case Form:
                this.gatherForm(meth);
                break;
            case DESC:
                this.gatherListe(meth);
                break;
            case Liste:
                this.gatherListe(meth);
                break;
            default:
                this.gatherForm(meth);
                break;
        }
    }

    protected HLine getHLine(Method meth, EForm eform) throws NotForm, NoSuchMethodException, SecurityException {
        HLine hline = null;
        try {
            this.gatherHlineData(meth, eform);
        } catch (HFormParam e) {
            hline = new HLine(this.getDao(), e, meth);
            hline.setForm(e.isForm());
            hline.setQualifier(e.getQualifier());
        }
        return hline;
    }

    public HLine[] gethLines() {
        return hLines;
    }

    public void sortHLine() {
        this.sortHLine(this.gethLines());
    }

    public void sortHLine(HLine[] line) {
        Arrays.sort(line, ascHLine);
    }

    public void sethLines(ArrayList<HLine> liste) {
        HLine[] ans = new HLine[liste.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = liste.get(i);
        }
        this.sethLines(ans);
    }

    protected boolean canExtractObjt() {
        boolean verif = this.gethLines() != null;
        verif = verif ? this.gethLines().length > 0 : verif;
        return verif ? this.gethLines()[0].getObject() != null : verif;
    }

    public void sethLines(HLine[] hLines) {
        this.sortHLine(hLines);
        this.hLines = hLines;
        if (this.canExtractObjt()) {
            this.setDao(hLines[0].getObject());
        }
    }

    public ObjectBdd<?> getDao() {
        return dao;
    }

    public void setDao(ObjectBdd<?> dao) {
        this.dao = dao;
    }

    public static CompareHLine getAscHLine() {
        return ascHLine;
    }

    public static void setAscHLine(CompareHLine ascHLine) {
        HCForm.ascHLine = ascHLine;
    }

}
